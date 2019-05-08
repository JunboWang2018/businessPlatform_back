package com.agriculture.platform.pojo.base.Qo;

import com.agriculture.platform.pojo.base.Do.AuctionInfoDo;
import com.agriculture.platform.pojo.base.Do.ProductDo;
import com.agriculture.platform.pojo.base.Do.UserDo;

/**
 * @author Junbo Wang
 * @description
 * @date 2019/5/6
 */
public class ProductInfoQo {
    private ProductDo productDo;
    private String prodTypeName;
    private String saleWayName;
    private UserDo userDo;
    private String sellStatusName;
    private AuctionInfoDo auctionInfoDo;
    private Double maxAuctionPrice;

    public ProductDo getProductDo() {
        return productDo;
    }

    public void setProductDo(ProductDo productDo) {
        this.productDo = productDo;
    }

    public String getProdTypeName() {
        return prodTypeName;
    }

    public void setProdTypeName(String prodTypeName) {
        this.prodTypeName = prodTypeName;
    }

    public String getSaleWayName() {
        return saleWayName;
    }

    public void setSaleWayName(String saleWayName) {
        this.saleWayName = saleWayName;
    }

    public UserDo getUserDo() {
        return userDo;
    }

    public void setUserDo(UserDo userDo) {
        this.userDo = userDo;
    }

    public String getSellStatusName() {
        return sellStatusName;
    }

    public void setSellStatusName(String sellStatusName) {
        this.sellStatusName = sellStatusName;
    }

    public AuctionInfoDo getAuctionInfoDo() {
        return auctionInfoDo;
    }

    public void setAuctionInfoDo(AuctionInfoDo auctionInfoDo) {
        this.auctionInfoDo = auctionInfoDo;
    }

    public Double getMaxAuctionPrice() {
        return maxAuctionPrice;
    }

    public void setMaxAuctionPrice(Double maxAuctionPrice) {
        this.maxAuctionPrice = maxAuctionPrice;
    }

    @Override
    public String toString() {
        return "ProductInfoQo{" +
                "productDo=" + productDo +
                ", prodTypeName='" + prodTypeName + '\'' +
                ", saleWayName='" + saleWayName + '\'' +
                ", userDo=" + userDo +
                ", sellStatusName='" + sellStatusName + '\'' +
                ", auctionInfoDo=" + auctionInfoDo +
                ", maxAuctionPrice=" + maxAuctionPrice +
                '}';
    }
}
