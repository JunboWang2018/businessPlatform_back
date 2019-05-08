package com.agriculture.platform.pojo.base.Qo;

import com.agriculture.platform.pojo.base.Do.CartDo;
import com.agriculture.platform.pojo.base.Do.ProductDo;

/**
 * @author Junbo Wang
 * @description
 * @date 2019/5/7
 */
public class CartInfoQo {
    private ProductDo productDo;
    private CartDo cartDo;

    public ProductDo getProductDo() {
        return productDo;
    }

    public void setProductDo(ProductDo productDo) {
        this.productDo = productDo;
    }

    public CartDo getCartDo() {
        return cartDo;
    }

    public void setCartDo(CartDo cartDo) {
        this.cartDo = cartDo;
    }

    @Override
    public String toString() {
        return "CartInfoQo{" +
                "productDo=" + productDo +
                ", cartDo=" + cartDo +
                '}';
    }
}
