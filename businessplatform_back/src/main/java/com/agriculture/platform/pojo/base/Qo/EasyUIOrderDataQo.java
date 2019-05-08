package com.agriculture.platform.pojo.base.Qo;

import java.sql.Timestamp;

/**
 * @author Junbo Wang
 * @description
 * @date 2019/5/8
 */
public class EasyUIOrderDataQo {
    private String orderNumber;
    private String prodNumber;
    private String prodName;
    private Integer orderQuantity;
    private Double prodPrice;
    private String prodTypeName;
    private String saleWayName;
    private Double orderPrice;
    private String orderUsername;
    private String orderStatus;
    private Timestamp createTime;
    private Timestamp modifyTime;

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getProdNumber() {
        return prodNumber;
    }

    public void setProdNumber(String prodNumber) {
        this.prodNumber = prodNumber;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public Integer getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(Integer orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public Double getProdPrice() {
        return prodPrice;
    }

    public void setProdPrice(Double prodPrice) {
        this.prodPrice = prodPrice;
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

    public Double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getOrderUsername() {
        return orderUsername;
    }

    public void setOrderUsername(String orderUsername) {
        this.orderUsername = orderUsername;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Timestamp modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Override
    public String toString() {
        return "EasyUIOrderDataQo{" +
                "orderNumber='" + orderNumber + '\'' +
                ", prodNumber='" + prodNumber + '\'' +
                ", prodName='" + prodName + '\'' +
                ", orderQuantity=" + orderQuantity +
                ", prodPrice=" + prodPrice +
                ", prodTypeName='" + prodTypeName + '\'' +
                ", saleWayName='" + saleWayName + '\'' +
                ", orderPrice=" + orderPrice +
                ", orderUsername='" + orderUsername + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                '}';
    }
}
