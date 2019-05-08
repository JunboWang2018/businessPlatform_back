package com.agriculture.platform.pojo.base.Qo;

import java.sql.Timestamp;

/**
 * @author Junbo Wang
 * @description
 * @date 2019/5/6
 */
public class EasyUIAuctionRecordQo {
    private Integer auctionRecordId;
    private Double price;
    private String username;
    private Timestamp createTime;

    public Integer getAuctionRecordId() {
        return auctionRecordId;
    }

    public void setAuctionRecordId(Integer auctionRecordId) {
        this.auctionRecordId = auctionRecordId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "EasyUIAuctionRecordQo{" +
                "auctionRecordId=" + auctionRecordId +
                ", price=" + price +
                ", username='" + username + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
