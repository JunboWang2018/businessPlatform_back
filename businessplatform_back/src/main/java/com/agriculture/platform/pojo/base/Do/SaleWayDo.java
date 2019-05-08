package com.agriculture.platform.pojo.base.Do;

import java.sql.Timestamp;

/**
 * @author Junbo Wang
 * @description
 * @date 2019/5/3
 */
public class SaleWayDo {
    private Integer saleWayId;
    private String code;
    private String name;
    private Timestamp createTime;

    public Integer getSaleWayId() {
        return saleWayId;
    }

    public void setSaleWayId(Integer saleWayId) {
        this.saleWayId = saleWayId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "SaleWayDo{" +
                "saleWayId=" + saleWayId +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
