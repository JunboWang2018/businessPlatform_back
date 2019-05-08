package com.agriculture.platform.service.product.impl;

import com.agriculture.platform.common.constant.Result;
import com.agriculture.platform.common.exception.BusinessException;
import com.agriculture.platform.dao.product.ProductDao;
import com.agriculture.platform.pojo.base.Do.*;
import com.agriculture.platform.pojo.base.Qo.EasyUIProdDataQo;
import com.agriculture.platform.pojo.base.Qo.ProductInfoQo;
import com.agriculture.platform.service.order.AuctionRecordService;
import com.agriculture.platform.service.product.AuctionInfoService;
import com.agriculture.platform.service.product.ProdTypeService;
import com.agriculture.platform.service.product.ProductService;
import com.agriculture.platform.service.product.SaleWayService;
import com.agriculture.platform.service.user.UserService;
import com.agriculture.platform.service.validate.ValidateService;
import com.agriculture.platform.utils.AutoGenerateNumberUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.sql.Timestamp;
import java.util.*;

/**
 * @author Junbo Wang
 * @description
 * @date 2019/5/5
 */
@Service
public class ProductServiceImpl implements ProductService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ValidateService validateService;

    @Resource
    private ProductDao productDao;

    @Autowired
    private AuctionInfoService auctionInfoService;

    @Autowired
    private ProdTypeService prodTypeService;

    @Autowired
    private SaleWayService saleWayService;

    @Autowired
    private UserService userService;

    @Autowired
    private AuctionRecordService auctionRecordService;

    @Override
    public Result releaseProduct(ProductDo productDo, UserDo userDo, MultipartFile imageFile, String path, Integer auctionTimeLimit, Double addPrice) {
        validateService.validateReleaseProd(productDo);
        /*if (userDo == null) {
            return Result.USER_NOT_LOGIN;
        }*/
        //生成商品编号
        String prodNumber = AutoGenerateNumberUtil.getAutoGenerateId("PROD");
        if (StringUtils.isEmpty(prodNumber)) {
            return Result.GENER_NUM_FAILED;
        }
        productDo.setNumber(prodNumber);
        //上传图片并设置名称
        if (imageFile == null) {
            return Result.NEED_PROD_IMAGE;
        }
        String suffix = imageFile.getOriginalFilename().substring(imageFile.getOriginalFilename().indexOf("."));
        if(StringUtils.isEmpty(suffix)) {
            suffix = ".jpg";
        }
        String imageName = prodNumber + "_1" + suffix;
        productDo.setImageMain(imageName);
        this.uploadImageFile(imageFile, imageName, path);
        //设置发布人id
        UserDo queryUser = new UserDo();
        queryUser.setUsername(userDo.getUsername());
        UserDo resultUser = userService.selectUser(queryUser);
        productDo.setUserId(resultUser.getUserId());
        //设置is_active = 1， sell_status = -1(下架)
        productDo.setSellStatus(-1);
        productDo.setIsActive(1);
        //如果是竞拍商品，保存竞拍信息
        if (productDo.getSaleWayCode().equals("AUCTI")) {
            if (addPrice < 1) {
                addPrice = Double.valueOf(1);
            }
            AuctionInfoDo auctionInfoDo = new AuctionInfoDo();
            auctionInfoDo.setAddPrice(addPrice);
            auctionInfoDo.setDeadline(auctionTimeLimit);
            auctionInfoDo.setProdNumber(prodNumber);
            try {
                auctionInfoService.addAuctionInfo(auctionInfoDo);
            } catch (IllegalArgumentException e) {
                LOGGER.error("参数错误，竞拍信息添加失败", e);
            }
        }
        Integer releaseResult = productDao.addProduct(productDo);
        if (releaseResult.intValue() == 1) {
            return Result.RELEASE_PROD_SUCCESS;
        }
        return Result.RELEASE_PROD_FAILED;
    }

    @Override
    public Result updateProduct(ProductDo productDo, UserDo userDo, MultipartFile imageFile, String path, Integer auctionTimeLimit, Double addPrice) {
        if (productDo == null) {
            throw new IllegalArgumentException("参数有误");
        }
        ProductDo queryProd = new ProductDo();
        queryProd.setNumber(productDo.getNumber());
        ProductDo oldProd = this.selectProduct(queryProd);
        if (oldProd == null) {
            return Result.FAILED;
        }
        //如果是竞拍商品，保存竞拍信息
        if (productDo.getSaleWayCode().equals("AUCTI")) {
            if (addPrice < 1) {
                addPrice = Double.valueOf(1);
            }
            AuctionInfoDo queryAuctionInfo = new AuctionInfoDo();
            queryAuctionInfo.setProdNumber(productDo.getNumber());
            AuctionInfoDo resultAuctionInfo = auctionInfoService.selectAuctionInfo(queryAuctionInfo);
            if (resultAuctionInfo != null) {
                if (resultAuctionInfo.getAddPrice().doubleValue() != addPrice.doubleValue()
                        || resultAuctionInfo.getDeadline().intValue() != auctionTimeLimit.intValue()) {
                    Result result = auctionInfoService.deleteAuctionInfo(productDo.getNumber());
                    if (result == Result.AUCTION_INFO_DELETE_SUCCESS) {
                        //设置新的竞拍信息
                        resultAuctionInfo.setDeadline(auctionTimeLimit);
                        resultAuctionInfo.setAddPrice(addPrice);
                    }
                }
            } else {
                //从其他类型转为竞拍
                resultAuctionInfo = new AuctionInfoDo();
                resultAuctionInfo.setProdNumber(productDo.getNumber());
                resultAuctionInfo.setDeadline(auctionTimeLimit);
                resultAuctionInfo.setAddPrice(addPrice);
            }

            try {
                auctionInfoService.addAuctionInfo(resultAuctionInfo);
            } catch (IllegalArgumentException e) {
                LOGGER.error("参数错误，竞拍信息添加失败", e);
            }
        } else if (oldProd.getSaleWayCode().equals("AUCTI")) {
            //原先是竞拍商品的，后改为其他方式，则删除竞拍信息
            auctionInfoService.deleteAuctionInfo(productDo.getNumber());
        }
        this.checkUpdate(oldProd, productDo);
        // 新上传的图片
        if (imageFile != null) {
            if (!productDo.getImageMain().equals(imageFile.getOriginalFilename())) {
                String suffix = imageFile.getOriginalFilename().substring(imageFile.getOriginalFilename().indexOf("."));
                if(StringUtils.isEmpty(suffix)) {
                    suffix = ".jpg";
                }
                String imageName = productDo.getNumber() + "_1" + suffix;
                productDo.setImageMain(imageName);
                this.uploadImageFile(imageFile, imageName, path);
            }
        }
        oldProd.setModifyTime(new Timestamp(new Date().getTime()));
        //若用户不同，则修改用户为当前修改商品的用户
        UserDo queryUser = new UserDo();
        queryUser.setUsername(userDo.getUsername());
        UserDo resultUser = userService.selectUser(queryUser);
        if (resultUser.getUserId().intValue() != oldProd.getUserId().intValue()) {
            oldProd.setUserId(resultUser.getUserId());
        }
        Integer updateResult = productDao.updateProduct(oldProd);
        if (updateResult.intValue() == 1) {
            return Result.UPDATE_PROD_SUCCESS;
        }
        return Result.UPDATE_PROD_FAILED;
    }

    /**
     * 检查更新
     * @param oldProd
     * @param newProd
     */
    private void checkUpdate(ProductDo oldProd, ProductDo newProd) {
        if (!oldProd.getName().equals(newProd.getName())) {
            oldProd.setName(newProd.getName());
        }
        if (oldProd.getDescription() != null) {
            if (!oldProd.getDescription().equals(newProd.getDescription())) {
                oldProd.setDescription(newProd.getDescription());
            }
        } else if (newProd != null) {
            oldProd.setDescription(newProd.getDescription());
        }
        if (!oldProd.getPrice().equals(newProd.getPrice())) {
            oldProd.setPrice(newProd.getPrice());
        }
        if (!oldProd.getQuantity().equals(newProd.getQuantity())) {
            oldProd.setQuantity(newProd.getQuantity());
        }
        if (!oldProd.getTypeCode().equals(newProd.getTypeCode())) {
            oldProd.setTypeCode(newProd.getTypeCode());
        }
        if (!oldProd.getSaleWayCode().equals(newProd.getSaleWayCode())) {
            oldProd.setSaleWayCode(newProd.getSaleWayCode());
        }
    }

    @Override
    public ProductDo selectProduct(ProductDo productDo) {
        List<ProductDo> resultList = this.selectProductList(productDo);
        if (resultList == null || resultList.size() == 0) {
            return null;
        }
        return resultList.get(0);
    }

    @Override
    public List<ProductDo> selectProductList(ProductDo productDo) {
        if (productDo == null) {
            productDo = new ProductDo();
        }
        return productDao.selectProdList(productDo);
    }

    @Override
    public ProductInfoQo selectProdInfoQo(ProductDo productDo) throws BusinessException {
        List<ProductInfoQo> resultList = this.selectProdInfoQoList(productDo);
        if (resultList == null || resultList.size() == 0) {
            return null;
        }
        return resultList.get(0);
    }

    @Override
    public List<ProductInfoQo> selectProdInfoQoList(ProductDo productDo) throws BusinessException {
        if (productDo == null) {
            productDo = new ProductDo();
        }
        List<ProductInfoQo> prodInfoQoList = new ArrayList<ProductInfoQo>();
        List<ProductDo> resultProdList = this.selectProductList(productDo);
        if (resultProdList == null || resultProdList.size() == 0) {
            return null;
        }
        //遍历查询商品及其相关信息，并封装
        for (int i = 0; i < resultProdList.size(); i++) {
            prodInfoQoList.add(this.setProductInfoQo(resultProdList.get(i)));
        }
        return prodInfoQoList;
    }

    /**
     * 查询所有没有售出（sell_status = 1/-1）和没有删除（isAcitve = 1）的商品
     * @return
     */
    @Override
    public List<ProductInfoQo> selectUnsoldProdInfoQoList() {
        List<ProductInfoQo> resultList = null;
        try {
            resultList = this.selectProdInfoQoList(new ProductDo());
        } catch (BusinessException e) {
            LOGGER.error(e.getMessage());
        }
        if (resultList == null || resultList.size() == 0) {
            return null;
        }
        List<ProductInfoQo> unsoldProdList = new ArrayList<ProductInfoQo>();
        for (int i = 0; i < resultList.size(); i++) {
            if (resultList.get(i).getProductDo().getSellStatus().intValue() == -1 || resultList.get(i).getProductDo().getSellStatus().intValue() == 1) {
                if (resultList.get(i).getProductDo().getIsActive() == 1) {
                    unsoldProdList.add(resultList.get(i));
                }
            }
        }
        return unsoldProdList;
    }

    /**
     *
     * @param page 当前页码
     * @param rows 当前页记录数
     * @return
     */
    @Override
    public Map<String, Object> selectUnsoldProdInfoQoListForEasyUI(int page, int rows) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<EasyUIProdDataQo> easyUIProdDataQoList = new ArrayList<EasyUIProdDataQo>();
        PageHelper.startPage(page, rows);
        List<ProductInfoQo> resultList = this.selectUnsoldProdInfoQoList();
        if (resultList != null && resultList.size() != 0) {
            for (int i = 0; i < resultList.size(); i++) {
                EasyUIProdDataQo easyUIProdDataQo = new EasyUIProdDataQo();
                easyUIProdDataQo.setProdNumber(resultList.get(i).getProductDo().getNumber());
                easyUIProdDataQo.setName(resultList.get(i).getProductDo().getName());
                easyUIProdDataQo.setQuantity(resultList.get(i).getProductDo().getQuantity());
                easyUIProdDataQo.setPrice(resultList.get(i).getProductDo().getPrice());
                easyUIProdDataQo.setProdTypeName(resultList.get(i).getProdTypeName());
                easyUIProdDataQo.setSaleWayName(resultList.get(i).getSaleWayName());
                if (resultList.get(i).getProductDo().getSaleWayCode().equals("AUCTI")) {
                    easyUIProdDataQo.setDeadline(resultList.get(i).getAuctionInfoDo().getDeadline());
                    easyUIProdDataQo.setMaxAuctionPrice(resultList.get(i).getMaxAuctionPrice());
                    easyUIProdDataQo.setAddPrice(resultList.get(i).getAuctionInfoDo().getAddPrice());
                }
                easyUIProdDataQo.setUserId(resultList.get(i).getUserDo().getUserId());
                easyUIProdDataQo.setUsername(resultList.get(i).getUserDo().getUsername());
                easyUIProdDataQo.setSellStatusName(resultList.get(i).getSellStatusName());
                easyUIProdDataQo.setCreateTime(resultList.get(i).getProductDo().getCreateTime());
                easyUIProdDataQo.setModifyTime(resultList.get(i).getProductDo().getModifyTime());
                easyUIProdDataQoList.add(easyUIProdDataQo);
            }
        }
        //设置返回列表rows
        resultMap.put("rows", easyUIProdDataQoList);
        //取分页信息
        PageInfo<ProductInfoQo> pageList = new PageInfo<ProductInfoQo>(resultList);
        //设置返回值total
        resultMap.put("total", pageList.getTotal());
        return resultMap;
    }

    @Override
    public Result deleteProduct(String[] prodNumbers) throws IllegalArgumentException {
        if (prodNumbers == null || prodNumbers.length == 0) {
            throw new IllegalArgumentException("未选中商品");
        }
        boolean flag = true;
        for (int i = 0; i < prodNumbers.length; i++) {
            Integer result = productDao.deleteProduct(prodNumbers[i]);
            if (result.intValue() == 0) {
                flag = false;
            }
        }
        if (flag) {
            return Result.DELETE_PROD_SUCCESS;
        }
        return Result.DELETE_PROD_FAILED;
    }

    @Override
    public Result takeUpOrDownProd(String[] prodNumbers, Integer sellStatus) throws IllegalArgumentException {
        if (prodNumbers == null || prodNumbers.length == 0 || sellStatus == null) {
            throw new IllegalArgumentException("上/下架商品参数错误");
        }
        boolean flag = true;
        for (int i = 0; i < prodNumbers.length; i++) {
            //取出商品信息并设置selStatus
            ProductDo queryProd = new ProductDo();
            queryProd.setNumber(prodNumbers[i]);
            ProductDo resultProd = this.selectProduct(queryProd);
            if (resultProd.getSellStatus().intValue() == sellStatus.intValue()) {
                continue;
            }
            resultProd.setModifyTime(new Timestamp(new Date().getTime()));
            resultProd.setSellStatus(sellStatus);
            Integer result = productDao.updateProduct(resultProd);
            if (result.intValue() == 0) {
                flag = false;
            }
        }
        if (flag) {
            return Result.TAKE_PROD_SUCCESS;
        }
        return Result.TAKE_PROD_FAILED;
    }

    /**
     * 封装商品列表查询信息
     * @param productDo
     * @return
     * @throws BusinessException
     */
    private ProductInfoQo setProductInfoQo(ProductDo productDo) throws BusinessException {
        ProductInfoQo productInfoQo = new ProductInfoQo();
        productInfoQo.setProductDo(productDo);
        //查询商品类型名
        ProductTypeDo resultProdType = prodTypeService.selectProdType(productDo.getTypeCode());
        if (resultProdType == null) {
            throw new BusinessException(productDo.getTypeCode() + "，此商品类型不存在");
        }
        productInfoQo.setProdTypeName(resultProdType.getName());
        //查询出售形式名称
        SaleWayDo resultSaleWay = saleWayService.selectSaleWay(productDo.getSaleWayCode());
        if (resultSaleWay == null) {
            throw new BusinessException(productDo.getSaleWayCode() + "，此出售形式不存在");
        }
        productInfoQo.setSaleWayName(resultSaleWay.getName());
        //查询发布用户信息
        UserDo queryUser = new UserDo();
        queryUser.setUserId(productDo.getUserId());
        UserDo resultUser = userService.selectUser(queryUser);
        if (resultUser == null) {
            throw new BusinessException(productDo.getUserId() + "，该用户不存在");
        }
        productInfoQo.setUserDo(resultUser);
        //设置出售状态
        productInfoQo.setSellStatusName(this.getStatusName(productDo.getSellStatus()));
        if (!productDo.getSaleWayCode().equals("AUCTI")) {
            return productInfoQo;
        }
        //如果是竞拍商品 设置竞拍信息,并查询最高价
        AuctionInfoDo queryAuctionInfo = new AuctionInfoDo();
        queryAuctionInfo.setProdNumber(productDo.getNumber());
        AuctionInfoDo resultAuctionInfo = auctionInfoService.selectAuctionInfo(queryAuctionInfo);
        if (resultAuctionInfo == null) {
            throw new BusinessException(productDo.getNumber() + "，该竞拍商品缺少竞拍信息");
        }
        productInfoQo.setAuctionInfoDo(resultAuctionInfo);
        Double maxPrice = auctionRecordService.selectMaxPrice(productDo.getNumber());
        if (maxPrice == null) {
            productInfoQo.setMaxAuctionPrice(productDo.getPrice());
        } else {
            productInfoQo.setMaxAuctionPrice(maxPrice);
        }
        return productInfoQo;
    }

    /**
     * 根据出售状态返回状态名
     * @param sellStatus
     * @return
     */
    private String getStatusName(Integer sellStatus) {
        switch (sellStatus.intValue()) {
            case -1 : return "下架";
            case 0 : return "已出售";
            case 1 : return "上架出售中";
            default : return null;
        }

    }
    /**
     * 文件设置文件名，并上传
     * @param imageFile
     * @return
     */
    private void uploadImageFile(MultipartFile imageFile, String imageFileName, String path){
        if (imageFile != null) {
            try {
                path = path + "prodImage\\";
                File pathFile = new File(path);
                if (!pathFile.exists()) {
                    pathFile.mkdirs();
                }
                String imagePath = path + imageFileName;
                //转存文件
                imageFile.transferTo(new File(imagePath));
            }catch (Exception e){
                LOGGER.error("文件上传错误", e);
            }
        }
    }
}
