package com.agriculture.platform.service.product;

import com.agriculture.platform.pojo.base.Do.ProductTypeDo;

import java.util.List;

public interface ProdTypeService {
    /**
     * 根据指定条件查询商品类型列表
     * @param productTypeDo
     * @return
     */
    List<ProductTypeDo> selectProdTypeList(ProductTypeDo productTypeDo);

    /**
     * 通过商品类型编码查询商品类型信息
     * @param code
     * @return
     */
    ProductTypeDo selectProdType(String code);
}
