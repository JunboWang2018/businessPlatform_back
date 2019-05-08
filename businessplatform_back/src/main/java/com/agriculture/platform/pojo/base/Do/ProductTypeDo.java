package com.agriculture.platform.pojo.base.Do;

import java.sql.Timestamp;

/**
 * @author Junbo Wang
 * @description
 * @date 2019/5/3
 */
public class ProductTypeDo {
    private Integer prodTypeId;
    private String code;
    private String name;
    private Timestamp createTime;

    public Integer getProdTypeId() {
        return prodTypeId;
    }

    public void setProdTypeId(Integer prodTypeId) {
        this.prodTypeId = prodTypeId;
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
        return "ProductTypeDo{" +
                "prodTypeId=" + prodTypeId +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
