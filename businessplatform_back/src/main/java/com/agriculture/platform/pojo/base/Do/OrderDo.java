package com.agriculture.platform.pojo.base.Do;

import java.sql.Timestamp;

/**
 * @author Junbo Wang
 * @description
 * @date 2019/5/3
 */
public class OrderDo {
    private Integer orderId;
    private String orderNumber;
    private Timestamp dealTime;
    private String prodNumber;
    private Integer quantity;
    private Double price;
    private String logisticInfoCode;
    private Integer isPaid;
    private Integer userId;
    private Timestamp createTime;
    private Timestamp modifyTime;
    private Integer isActive;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Timestamp getDealTime() {
        return dealTime;
    }

    public void setDealTime(Timestamp dealTime) {
        this.dealTime = dealTime;
    }

    public String getProdNumber() {
        return prodNumber;
    }

    public void setProdNumber(String prodNumber) {
        this.prodNumber = prodNumber;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getLogisticInfoCode() {
        return logisticInfoCode;
    }

    public void setLogisticInfoCode(String logisticInfoCode) {
        this.logisticInfoCode = logisticInfoCode;
    }

    public Integer getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(Integer isPaid) {
        this.isPaid = isPaid;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "OrderDo{" +
                "orderId=" + orderId +
                ", orderNumber='" + orderNumber + '\'' +
                ", dealTime=" + dealTime +
                ", prodNumber='" + prodNumber + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", logisticInfoCode='" + logisticInfoCode + '\'' +
                ", isPaid=" + isPaid +
                ", userId=" + userId +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                ", isActive=" + isActive +
                '}';
    }
}
