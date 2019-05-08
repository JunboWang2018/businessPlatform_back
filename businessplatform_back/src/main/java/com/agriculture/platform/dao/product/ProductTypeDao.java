package com.agriculture.platform.dao.product;

import com.agriculture.platform.pojo.base.Do.ProductTypeDo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductTypeDao {
    /**
     * 根据编码查询商品类型
     * @param productTypeDo
     * @return
     */
    List<ProductTypeDo> selectProdTypeList(ProductTypeDo productTypeDo);
}
