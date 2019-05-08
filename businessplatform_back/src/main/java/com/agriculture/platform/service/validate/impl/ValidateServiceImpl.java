package com.agriculture.platform.service.validate.impl;

import com.agriculture.platform.common.constant.Result;
import com.agriculture.platform.pojo.base.Do.ProductDo;
import com.agriculture.platform.pojo.base.Do.UserDo;
import com.agriculture.platform.service.validate.ValidateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author Wang Junbo
 * @description
 * @date 2019/4/11
 */
@Service
public class ValidateServiceImpl implements ValidateService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ValidateServiceImpl.class);

    @Override
    public void validateUser(UserDo userDo) {
        if (StringUtils.isEmpty(userDo.getUsername())) {
            throw new IllegalArgumentException(Result.USERNAME_NULL.getMessage());
        }
        if (StringUtils.isEmpty(userDo.getPassword())) {
            throw new IllegalArgumentException(Result.PASSWORD_NULL.getMessage());
        }
    }

    @Override
    public boolean validateUserSearch(UserDo userDo) throws IllegalArgumentException {
        return true;
    }

    @Override
    public boolean validateReleaseProd(ProductDo productDo) {
        return true;
    }

}
