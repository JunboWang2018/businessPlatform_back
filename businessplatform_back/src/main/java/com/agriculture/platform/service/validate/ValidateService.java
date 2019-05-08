package com.agriculture.platform.service.validate;


import com.agriculture.platform.pojo.base.Do.*;

/**
 * 用户输入数据校验业务
 */
public interface ValidateService {

    /**
     * 用户登录/注册数据校验
     * @param userDo
     * @return
     */
    void validateUser(UserDo userDo);

    /**
     * 用户查询数据校验
     * @param userDo
     * @return
     */
    boolean validateUserSearch(UserDo userDo);

    /**
     * 发布商品数据校验
     * @param productDo
     * @return
     */
    boolean validateReleaseProd(ProductDo productDo);

}
