package com.agriculture.platform.pojo.base.Do;

import java.sql.Timestamp;

/**
 * @author Junbo Wang
 * @description
 * @date 2019/5/3
 */
public class UserDo {
    private Integer userId;
    private String username;
    private String password;
    private String power;
    private Integer score;
    private Double balance;
    private String address;
    private Timestamp createTime;
    private Timestamp modifyTime;
    private Integer isActive;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
        return "UserDo{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", power='" + power + '\'' +
                ", score=" + score +
                ", balance=" + balance +
                ", address='" + address + '\'' +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                ", isActive=" + isActive +
                '}';
    }
}
