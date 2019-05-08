package com.agriculture.platform.pojo.base.Do;

import java.sql.Timestamp;

/**
 * @author Junbo Wang
 * @description
 * @date 2019/5/3
 */
public class AuctionRecordDo {
    private Integer auctionRecordId;
    private String prodNumber;
    private Double price;
    private Integer userId;
    private Timestamp createTime;
    private Integer isActive;

    public Integer getAuctionRecordId() {
        return auctionRecordId;
    }

    public void setAuctionRecordId(Integer auctionRecordId) {
        this.auctionRecordId = auctionRecordId;
    }

    public String getProdNumber() {
        return prodNumber;
    }

    public void setProdNumber(String prodNumber) {
        this.prodNumber = prodNumber;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "AuctionRecordDo{" +
                "auctionRecordId=" + auctionRecordId +
                ", prodNumber='" + prodNumber + '\'' +
                ", price=" + price +
                ", userId=" + userId +
                ", createTime=" + createTime +
                ", isActive=" + isActive +
                '}';
    }
}
