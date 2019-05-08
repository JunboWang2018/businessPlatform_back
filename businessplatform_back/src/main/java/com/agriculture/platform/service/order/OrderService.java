package com.agriculture.platform.service.order;

import com.agriculture.platform.common.constant.Result;
import com.agriculture.platform.pojo.base.Do.OrderDo;
import com.agriculture.platform.pojo.base.Do.UserDo;
import com.agriculture.platform.pojo.base.Qo.OrderInfoQo;

import java.util.List;
import java.util.Map;

public interface OrderService {

    /**
     * 查找订单列表，并封装
     * @param orderDo
     * @return
     */
    List<OrderInfoQo> selectOrderInfoQoList(OrderDo orderDo);

    /**
     * 根据指定条件查找订单列表
     * @param orderDo
     * @return
     */
    List<OrderDo> selectOrderList(OrderDo orderDo);

    /**
     * 根据指定条件查找订单
     * @param orderDo
     * @return
     */
    OrderDo selectOrder(OrderDo orderDo);

    /**
     * 订单改价
     * @param orderNumber
     * @param price
     * @return
     */
    Result updateOrderPrice(String orderNumber, Double price);

    /**
     * 订单管理页面，查询订单列表
     * @param page
     * @param rows
     * @return
     */
    Map<String, Object> selectEasyUIOrderOoList(int page, int rows);
}
