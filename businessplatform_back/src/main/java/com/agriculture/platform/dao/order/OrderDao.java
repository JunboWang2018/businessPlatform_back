package com.agriculture.platform.dao.order;

import com.agriculture.platform.pojo.base.Do.OrderDo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderDao {
    /**
     * 根据指定条件查询订单列表
     * @param orderDo
     * @return
     */
    List<OrderDo> selectOrderList(OrderDo orderDo);

    /**
     * 添加订单
     * @param orderDo
     * @return
     */
    Integer addOrder(OrderDo orderDo);

    /**
     * 根据指定条件修改订单
     * @param orderDo
     * @return
     */
    Integer updateOrder(OrderDo orderDo);

    /**
     * 删除指定的订单
     * @param orderDo
     * @return
     */
    Integer deleteOrder(OrderDo orderDo);

    /**
     * 删除指定用户的所有订单
     * @param userId
     * @return
     */
    Integer deleteUserAllOrder(Integer userId);

    /**
     * 修改订单价格
     * @param orderDo
     * @return
     */
    Integer updateOrderPrice(OrderDo orderDo);
}
