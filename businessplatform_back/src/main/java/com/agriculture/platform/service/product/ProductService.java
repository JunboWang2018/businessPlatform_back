package com.agriculture.platform.service.product;


import com.agriculture.platform.common.constant.Result;
import com.agriculture.platform.common.exception.BusinessException;
import com.agriculture.platform.pojo.base.Do.ProductDo;
import com.agriculture.platform.pojo.base.Do.UserDo;
import com.agriculture.platform.pojo.base.Qo.ProductInfoQo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface ProductService {

    /**
     * 发布商品
     * @param productDo
     * @param userDo
     * @param imageFile
     * @param path
     * @param auctionTimeLimit
     * @param addPrice
     * @return
     */
    Result releaseProduct(ProductDo productDo, UserDo userDo, MultipartFile imageFile, String path, Integer auctionTimeLimit, Double addPrice);

    /**
     * 修改商品
     * @param productDo
     * @param userDo
     * @param imageFile
     * @param path
     * @param auctionTimeLimit
     * @param addPrice
     * @return
     */
    Result updateProduct(ProductDo productDo, UserDo userDo, MultipartFile imageFile, String path, Integer auctionTimeLimit, Double addPrice);
    /**
     * 查找单个商品，无需封装
     * @param productDo
     * @return
     */
    ProductDo selectProduct(ProductDo productDo);

    /**
     * 查找多个商品，返回商品集合，无需封装
     * @param productDo
     * @return
     */
    List<ProductDo> selectProductList(ProductDo productDo);

   /**
     * 查找单个商品，并查找其它需要的参数
     * @param productDo
     * @return
     */
   ProductInfoQo selectProdInfoQo(ProductDo productDo)  throws BusinessException;

    /**
     * 查找多个商品，并查找其它需要的参数，返回封装后对象的集合
     * @param productDo
     * @return
     */
    List<ProductInfoQo> selectProdInfoQoList(ProductDo productDo) throws BusinessException;

    /**
     * 查找未出售的商品信息列表
     * @return
     */
    List<ProductInfoQo> selectUnsoldProdInfoQoList();

    /**
     * 商品管理页面表格数据
     * @return
     */
    Map<String, Object> selectUnsoldProdInfoQoListForEasyUI(int page, int rows);

    /**
     * 删除指定商品
     * @param prodNumbers
     * @return
     */
    Result deleteProduct(String[] prodNumbers) throws IllegalArgumentException;

    /**
     * 物品上/下架（sell_status设为1/-1）
     * @param prodNumbers
     * @param sellStatus
     * @return
     * @throws IllegalArgumentException
     */

    Result takeUpOrDownProd(String[] prodNumbers, Integer sellStatus) throws IllegalArgumentException;
}
