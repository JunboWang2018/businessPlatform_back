package com.agriculture.platform.common.constant;

public enum Result {
    SUCCESS(1000, "success"),
    LOGIN_SUCCESS(1001, "登录成功"),
    REGISTER_SUCCESS(1002, "注册成功"),
    RELEASE_PROD_SUCCESS(1003, "商品发布成功"),
    AUCTION_INFO_ADD_SUCCESS(1004, "竞拍信息添加成功"),
    AUCTION_INFO_DELETE_SUCCESS(1005, "竞拍信息删除成功"),
    AUCTION_RECORD_ADD_SUCCESS(1006, "竞拍记录添加成功"),
    AUCTION_RECORD_DELETE_SUCCESS(1007, "竞拍记录删除成功"),
    DELETE_PROD_SUCCESS(1008, "删除商品成功"),
    TAKE_PROD_SUCCESS(1009, "上/下架商品成功"),
    UPDATE_PROD_SUCCESS(1010, "商品修改成功"),
    UPDATE_USER_SUCCESS(1011, "用户修改成功"),
    RESET_PWD_SUCCESS(1012, "密码重置成功"),
    DELETE_USER_SUCCESS(1013, "删除用户成功"),

    FAILED(0, "failed"),
    EXEC_TIMEOUT(1, "执行超时"),
    PARAM_ERR(2, "参数错误"),
    VALIDATE_FAILED(3, "校验失败"),
    USERNAME_NULL(4, "用户名为空"),
    PASSWORD_NULL(5, "密码为空"),
    USER_NOT_EXIST(6,"用户不存在"),
    PASSWORD_ERROR(7, "密码错误"),
    USER_IS_EXIST(8, "用户名已存在"),
    REGISTER_FAILED(9, "注册失败"),
    USER_NOT_LOGIN(10, "请登录"),
    GENER_NUM_FAILED(11, "生成编号失败"),
    RELEASE_PROD_FAILED(12, "商品发布失败"),
    NEED_PROD_IMAGE(13, "请上传展示图片"),
    AUCTION_INFO_ADD_FAILED(14, "竞拍信息添加失败"),
    AUCTION_INFO_DELETE_FAILED(15, "竞拍信息删除失败"),
    AUCTION_RECORD_ADD_FAILED(16, "竞拍记录添加失败"),
    AUCTION_RECORD_DELETE_FAILED(17, "竞拍记录删除失败"),
    DELETE_PROD_FAILED(18, "删除商品失败"),
    TAKE_PROD_FAILED(19, "上/下架商品失败"),
    UPDATE_PROD_FAILED(20, "商品修改失败"),
    UPDATE_USER_FAILED(21, "修改账号信息失败"),
    RESET_PWD_FAILED(22, "密码重置失败"),
    DELETE_USER_FAILED(23, "删除用户成功"),
    PROD_NUMBERS_IS_NULL(24, "未选中商品"),
    GENER_ORDER_FAILED(25, "提交订单失败"),
    ORDER_NUM_BIG(26, "库存数量不足，提交订单失败"),
    NOT_ALLOW_USER_LOGIN(27, "该账号没有登录权限");

    private int code;
    private String message;

    Result(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
