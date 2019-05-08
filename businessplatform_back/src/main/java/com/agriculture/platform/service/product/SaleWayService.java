package com.agriculture.platform.service.product;

import com.agriculture.platform.pojo.base.Do.SaleWayDo;

import java.util.List;

public interface SaleWayService {
    /**
     * 根据指定条件查询出售形式列表
     * @param saleWayDo
     * @return
     */
    List<SaleWayDo> selectSaleWayList(SaleWayDo saleWayDo);

    /**
     * 通过商品类型编码查询出售形式信息
     * @param code
     * @return
     */
    SaleWayDo selectSaleWay(String code);
}
