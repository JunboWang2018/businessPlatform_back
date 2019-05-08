package com.agriculture.platform.pojo.base.Qo;

import java.sql.Timestamp;

/**
 * @author Junbo Wang
 * @description 用于渲染easyui datagrid 表格数据
 * @date 2019/5/6
 */
public class EasyUIProdDataQo {
    private String prodNumber;
    private String name;
    private Integer quantity;
    private Double price;
    private String prodTypeName;
    private String saleWayName;
    private Integer deadline;
    private Double maxAuctionPrice;
    private Double addPrice;
    private Integer userId;
    private String username;
    private String sellStatusName;
    private Timestamp createTime;
    private Timestamp modifyTime;

    public String getProdNumber() {
        return prodNumber;
    }

    public void setProdNumber(String prodNumber) {
        this.prodNumber = prodNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getDeadline() {
        return deadline;
    }

    public void setDeadline(Integer deadline) {
        this.deadline = deadline;
    }

    public Double getMaxAuctionPrice() {
        return maxAuctionPrice;
    }

    public void setMaxAuctionPrice(Double maxAuctionPrice) {
        this.maxAuctionPrice = maxAuctionPrice;
    }

    public Double getAddPrice() {
        return addPrice;
    }

    public void setAddPrice(Double addPrice) {
        this.addPrice = addPrice;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSellStatusName() {
        return sellStatusName;
    }

    public void setSellStatusName(String sellStatusName) {
        this.sellStatusName = sellStatusName;
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
        return "EasyUIProdDataQo{" +
                "prodNumber='" + prodNumber + '\'' +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", prodTypeName='" + prodTypeName + '\'' +
                ", saleWayName='" + saleWayName + '\'' +
                ", deadline=" + deadline +
                ", maxAuctionPrice=" + maxAuctionPrice +
                ", addPrice=" + addPrice +
                ", userId=" + userId +
                ", username='" + username + '\'' +
                ", sellStatusName='" + sellStatusName + '\'' +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                '}';
    }
}
