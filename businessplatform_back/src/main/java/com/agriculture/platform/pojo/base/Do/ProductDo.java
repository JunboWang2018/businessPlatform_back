package com.agriculture.platform.pojo.base.Do;

import java.sql.Timestamp;

/**
 * @author Junbo Wang
 * @description
 * @date 2019/5/3
 */
public class ProductDo {
    private Integer productId;
    private String number;
    private String name;
    private String imageMain;
    private String description;
    private Integer quantity;
    private Double price;
    private String typeCode;
    private String saleWayCode;
    private Integer userId;
    private Timestamp createTime;
    private Timestamp modifyTime;
    private Integer sellStatus;
    private Integer isActive;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageMain() {
        return imageMain;
    }

    public void setImageMain(String imageMain) {
        this.imageMain = imageMain;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getSaleWayCode() {
        return saleWayCode;
    }

    public void setSaleWayCode(String saleWayCode) {
        this.saleWayCode = saleWayCode;
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

    public Integer getSellStatus() {
        return sellStatus;
    }

    public void setSellStatus(Integer sellStatus) {
        this.sellStatus = sellStatus;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "ProductDo{" +
                "productId=" + productId +
                ", number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", imageMain='" + imageMain + '\'' +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", typeCode='" + typeCode + '\'' +
                ", saleWayCode='" + saleWayCode + '\'' +
                ", userId=" + userId +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                ", sellStatus=" + sellStatus +
                ", isActive=" + isActive +
                '}';
    }
}
