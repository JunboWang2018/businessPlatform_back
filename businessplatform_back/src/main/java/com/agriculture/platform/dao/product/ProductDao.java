package com.agriculture.platform.dao.product;

import com.agriculture.platform.pojo.base.Do.ProductDo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductDao {
    /**
     * 根据指定条件搜索商品列表
     * @param productDo
     * @return
     */
    List<ProductDo> selectProdList(ProductDo productDo);

    /**
     * 添加商品
     * @param productDo
     * @return
     */
    Integer addProduct(ProductDo productDo);

    /**
     * 修改商品信息
     * @param productDo
     * @return
     */
    Integer updateProduct(ProductDo productDo);

    /**
     * 删除指定商品
     * @param number
     * @return
     */
    Integer deleteProduct(String number);
}
