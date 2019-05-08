package com.agriculture.platform.dao.product;

import com.agriculture.platform.pojo.base.Do.SaleWayDo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SaleWayDao {
    /**
     * 根据编码查找出售形式
     * @param saleWayDo
     * @return
     */
    List<SaleWayDo> selectSaleWayList(SaleWayDo saleWayDo);
}
