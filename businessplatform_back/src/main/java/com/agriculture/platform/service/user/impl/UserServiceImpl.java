package com.agriculture.platform.service.user.impl;


import com.agriculture.platform.common.constant.Result;
import com.agriculture.platform.dao.user.UserDao;
import com.agriculture.platform.pojo.base.Do.UserDo;
import com.agriculture.platform.pojo.base.Qo.EasyUIProdDataQo;
import com.agriculture.platform.pojo.base.Qo.ProductInfoQo;
import com.agriculture.platform.service.user.UserService;
import com.agriculture.platform.service.validate.ValidateService;
import com.agriculture.platform.utils.MD5;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Junbo Wang
 * @description
 * @date 2019/4/5
 */
@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    private UserDao userDao;

    @Autowired
    private ValidateService validateService;
    /**
     * 登录:
     *  1. 验证用户名和密码是否为空
     *  2. 根据用户名查询数据库
     *  3. 若返回值为空，则用户不存在。
     *  4. 若返回值不为空，判断密码是否正确。
     * @param userDo
     * @return
     */
    @Override
    public Result login(UserDo userDo) throws IllegalArgumentException {
        validateService.validateUser(userDo);
        UserDo queryUser = new UserDo();
        queryUser.setUsername(userDo.getUsername());
        queryUser.setIsActive(1);
        List<UserDo> resultList = userDao.selectUserList(queryUser);
        if (resultList == null || resultList.size() == 0) {
            return Result.USER_NOT_EXIST;
        } else if (!resultList.get(0).getPassword().equals(MD5.getMD5Code(userDo.getPassword()))) {
            return  Result.PASSWORD_ERROR;
        } else if (resultList.get(0).getPower().equals("user")) {
            return Result.NOT_ALLOW_USER_LOGIN;
        } else {
            return Result.LOGIN_SUCCESS;
        }
    }

    /**
     * 注册:
     *  1. 验证用户名和密码是否为空
     *  2. 根据用户名查询数据库,判断用户名是否已存在
     *  3. 若用户名不存在，则插入新用户，返回值为受影响的行数
     *  4. 若行数为1，则插入成功，其他情况为插入失败
     * @param userDo
     * @return
     */
    @Override
    public Result register(UserDo userDo) throws IllegalArgumentException {
        validateService.validateUser(userDo);
        UserDo queryUser = new UserDo();
        queryUser.setUsername(userDo.getUsername());
        //查询用户名是否存在
        List<UserDo> resultList = userDao.selectUserList(queryUser);
        if (resultList != null && ((List) resultList).size() > 0) {
            return Result.USER_IS_EXIST;
        }
        //密码加密
        userDo.setPassword(MD5.getMD5Code(userDo.getPassword()));
        //设置初始化参数
        if (userDo.getPower() == null ||userDo.getPower().equals("")) {
            userDo.setPower("user");
        }
        userDo.setScore(0);
        userDo.setBalance(0.0);
        userDo.setIsActive(1);
        //添加用户
        Integer count = userDao.addUser(userDo);
        if (count == 1) {
            return Result.REGISTER_SUCCESS;
        } else {
            return Result.REGISTER_FAILED;
        }
    }

    @Override
    public List<UserDo> selectUserList(UserDo userDo) {
        return userDao.selectUserList(userDo);
    }

    @Override
    public UserDo selectUser(UserDo userDo) throws IllegalArgumentException {
        if (!validateService.validateUserSearch(userDo)) {
            return null;
        }
        List<UserDo> list = this.selectUserList(userDo);
        if (list.size() >= 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    @Override
    public Result updateUser(UserDo userDo) {
        //通过用户id查找用户
        UserDo queryUser = new UserDo();
        queryUser.setUserId(userDo.getUserId());
        UserDo resultUser = this.selectUser(queryUser);
        this.checkUser(resultUser, userDo);
        Integer result = userDao.updateUser(resultUser);
        if (result == 1) {
            return Result.UPDATE_USER_SUCCESS;
        }
        return Result.UPDATE_USER_FAILED;
    }

    /**
     * 检查修改的信息
     * @param oldUser
     * @param newUser
     */
    private void checkUser(UserDo oldUser, UserDo newUser) {
        if (oldUser.getPower() != null) {
            if (!oldUser.getPower().equals(newUser.getPower())) {
                oldUser.setPower(newUser.getPower());
            }
        } else if (newUser != null) {
            oldUser.setPower(newUser.getPower());
        }

        if (oldUser.getAddress() != null) {
            if (!oldUser.getAddress().equals(newUser.getAddress())) {
                oldUser.setAddress(newUser.getAddress());
            }
        } else if (newUser != null) {
            oldUser.setAddress(newUser.getAddress());
        }
    }

    @Override
    public Map<String, Object> selectEasyUIUserDataList(int page, int rows) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        PageHelper.startPage(page, rows);
        UserDo queryUser = new UserDo();
        queryUser.setIsActive(1);
        List<UserDo> resultList = this.selectUserList(queryUser);
        PageInfo<UserDo> pageList = new PageInfo<UserDo>(resultList);
        resultMap.put("total", pageList.getTotal());
        resultMap.put("rows",resultList);
        return resultMap;
    }

    @Override
    public String resetPWD(Integer userId) {
        String password = Integer.valueOf((int) (Math.random() * 1000000)).toString();
        //通过用户id查找用户
        UserDo userDo = new UserDo();
        userDo.setUserId(userId);
        userDo.setPassword(MD5.getMD5Code(password));
        Integer result = userDao.updatePWD(userDo);
        if (result == 1) {
            return password;
        }
        return null;
    }

    @Override
    public Result deleteUsers(Integer[] userIds) {
        if (userIds == null || userIds.length == 0) {
            throw new IllegalArgumentException("未选中用户");
        }
        boolean flag = true;
        for (int i = 0; i < userIds.length; i++) {
            Integer result = userDao.deleteUser(userIds[i]);
            if (result.intValue() == 0) {
                flag = false;
            }
        }
        if (flag) {
            return Result.DELETE_USER_SUCCESS;
        }
        return Result.DELETE_USER_FAILED;
    }
}
