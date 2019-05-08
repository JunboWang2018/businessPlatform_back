package com.agriculture.platform.pojo.base.Do;

import java.sql.Timestamp;

/**
 * @author Junbo Wang
 * @description
 * @date 2019/5/4
 */
public class LogisticInfoDo {
    private Integer logisticInfoId;
    private String code;
    private String name;
    private Timestamp createTime;

    public Integer getLogisticInfoId() {
        return logisticInfoId;
    }

    public void setLogisticInfoId(Integer logisticInfoId) {
        this.logisticInfoId = logisticInfoId;
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
        return "LogisticInfoDo{" +
                "logisticInfoId=" + logisticInfoId +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
