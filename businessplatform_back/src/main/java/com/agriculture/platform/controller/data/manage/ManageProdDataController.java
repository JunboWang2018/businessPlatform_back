package com.agriculture.platform.controller.data.manage;

import com.agriculture.platform.common.constant.RespJson;
import com.agriculture.platform.common.constant.Result;
import com.agriculture.platform.controller.data.BaseDataController;
import com.agriculture.platform.pojo.base.Do.AuctionInfoDo;
import com.agriculture.platform.pojo.base.Do.ProductDo;
import com.agriculture.platform.pojo.base.Do.UserDo;
import com.agriculture.platform.service.product.AuctionInfoService;
import com.agriculture.platform.service.product.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Junbo Wang
 * @description
 * @date 2019/5/5
 */
@Controller
@RequestMapping("/data/manage/product")
public class ManageProdDataController extends BaseDataController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ManageProdDataController.class);

    @Autowired
    private ProductService productService;

    @Autowired
    private AuctionInfoService auctionInfoService;

    /**
     * 新增商品
     * @param productDo
     * @param imageFile
     * @param auctionTimeLimit
     * @param addPrice
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/releaseProduct")
    public RespJson releaseProduct(ProductDo productDo, @RequestParam(value = "imageFile") MultipartFile imageFile,
                                   @RequestParam(value = "auctionTimeLimit") Integer auctionTimeLimit, @RequestParam(value = "addPrice") Double addPrice,
                                   HttpSession session) {
        UserDo sessionUser = (UserDo) session.getAttribute("user");
        String path = session.getServletContext().getRealPath("/");
        Result releaseResult = productService.releaseProduct(productDo, sessionUser, imageFile, path, auctionTimeLimit, addPrice);
        return this.responseMsg(releaseResult.getCode(), releaseResult.getMessage());
    }

    /**
     * 查询商品列表
     * @param page
     * @param rows
     * @return
     */
    @ResponseBody
    @RequestMapping("/selectProdList")
    public Map<String, Object> selectProdList(@RequestParam("page") int page, @RequestParam("rows") int rows) {
        Map<String, Object> resultMap =  productService.selectUnsoldProdInfoQoListForEasyUI(page, rows);
        return resultMap;
    }

    /**
     * 编辑商品
     * @param productDo
     * @param imageFile
     * @param auctionTimeLimit
     * @param addPrice
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateProduct")
    public RespJson editProduct(ProductDo productDo, @RequestParam(value = "imageFile", required = false) MultipartFile imageFile,
                                @RequestParam(value = "auctionTimeLimit", required = false) Integer auctionTimeLimit, @RequestParam(value = "addPrice", required = false) Double addPrice,
                                HttpSession session) {
        UserDo sessionUser = (UserDo) session.getAttribute("user");
        String path = session.getServletContext().getRealPath("/");
        Result releaseResult = productService.updateProduct(productDo, sessionUser, imageFile, path, auctionTimeLimit, addPrice);
        return this.responseMsg(releaseResult.getCode(), releaseResult.getMessage());
    }

    @ResponseBody
    @RequestMapping("/selectProduct")
    public RespJson selectProduct(String prodNumber) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        ProductDo queryProd = new ProductDo();
        queryProd.setNumber(prodNumber);
        ProductDo resultProd = productService.selectProduct(queryProd);
        resultMap.put("product", resultProd);
        if (resultProd.getSaleWayCode().equals("AUCTI")) {
            AuctionInfoDo queryAuctionInfo = new AuctionInfoDo();
            queryAuctionInfo.setProdNumber(prodNumber);
            AuctionInfoDo auctionInfoDo = auctionInfoService.selectAuctionInfo(queryAuctionInfo);
            resultMap.put("auctionInfo", auctionInfoDo);
        }
        return this.responseData(resultMap);
    }

    /**
     * 删除指定编号的商品
     * @param prodNumbers
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteProduct")
    public RespJson deleteProduct(String[] prodNumbers) {
        Result result = null;
        try {
            result = productService.deleteProduct(prodNumbers);
        } catch (IllegalArgumentException e) {
            LOGGER.error(e.getMessage());
        }
        return this.responseMsg(result.getCode(), result.getMessage());
    }

    /**
     * 上/下架商品，传入prodNumbers和sell_status
     * @param prodNumbers
     * @param sellStatus
     * @return
     */
    @ResponseBody
    @RequestMapping("/takeDownOrUpProd")
    public RespJson takeDownOrUpProd(String[] prodNumbers, Integer sellStatus) {
        Result result = null;
        try {
            result = productService.takeUpOrDownProd(prodNumbers, sellStatus);
        } catch (IllegalArgumentException e) {
            LOGGER.error(e.getMessage());
        }
        return this.responseMsg(result.getCode(), result.getMessage());
    }


}
