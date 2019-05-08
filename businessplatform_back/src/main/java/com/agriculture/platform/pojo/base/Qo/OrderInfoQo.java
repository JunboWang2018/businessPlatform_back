package com.agriculture.platform.pojo.base.Qo;

import com.agriculture.platform.pojo.base.Do.OrderDo;
import com.agriculture.platform.pojo.base.Do.ProductDo;

/**
 * @author Junbo Wang
 * @description
 * @date 2019/5/7
 */
public class OrderInfoQo {
    private ProductDo productDo;
    private OrderDo orderDo;

    public ProductDo getProductDo() {
        return productDo;
    }

    public void setProductDo(ProductDo productDo) {
        this.productDo = productDo;
    }

    public OrderDo getOrderDo() {
        return orderDo;
    }

    public void setOrderDo(OrderDo orderDo) {
        this.orderDo = orderDo;
    }

    @Override
    public String toString() {
        return "OrderInfoQo{" +
                "productDo=" + productDo +
                ", orderDo=" + orderDo +
                '}';
    }
}
