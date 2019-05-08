package com.agriculture.platform.pojo.base.Do;

import java.sql.Timestamp;

/**
 * @author Junbo Wang
 * @description
 * @date 2019/5/3
 */
public class AuctionInfoDo {
    private Integer auctionInfoId;
    private String prodNumber;
    private Integer deadline;
    private Double addPrice;
    private Timestamp createTime;
    private Timestamp modifyTime;

    public Integer getAuctionInfoId() {
        return auctionInfoId;
    }

    public void setAuctionInfoId(Integer auctionInfoId) {
        this.auctionInfoId = auctionInfoId;
    }

    public String getProdNumber() {
        return prodNumber;
    }

    public void setProdNumber(String prodNumber) {
        this.prodNumber = prodNumber;
    }

    public Integer getDeadline() {
        return deadline;
    }

    public void setDeadline(Integer deadline) {
        this.deadline = deadline;
    }

    public Double getAddPrice() {
        return addPrice;
    }

    public void setAddPrice(Double addPrice) {
        this.addPrice = addPrice;
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
        return "AuctionInfoDo{" +
                "auctionInfoId=" + auctionInfoId +
                ", prodNumber='" + prodNumber + '\'' +
                ", deadline=" + deadline +
                ", addPrice=" + addPrice +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                '}';
    }
}
