package com.agriculture.platform.service.user;

import com.agriculture.platform.common.constant.Result;
import com.agriculture.platform.pojo.base.Do.UserDo;

import java.util.List;
import java.util.Map;

/**
 * 用户登录注册接口
 */
public interface UserService {


    /**
     * 用户登录
     * @param userDo
     * @return
     */
    Result login(UserDo userDo);

    /**
     * 用户注册
     * @param userDo
     * @return
     */
    Result register(UserDo userDo);

    /**
     * 根据自定义条件查找用户list
     * @param userDo
     * @return
     */
    List<UserDo> selectUserList(UserDo userDo);

    /**
     * 根据自定义条件查找用户
     * @param userDo
     * @return
     */
    UserDo selectUser(UserDo userDo);

    /**
     * 修改用户信息
     * @param userDo
     * @return
     */
    Result updateUser(UserDo userDo);

    /**
     * 账号管理页面数据
     * @return
     */
    Map<String, Object> selectEasyUIUserDataList(int page, int rows);

    /**
     * 密码重置
     * @param userId
     * @return
     */
    String resetPWD(Integer userId);

    /**
     * 删除指定用户
     * @param userIds
     * @return
     */
    Result deleteUsers(Integer[] userIds);
}
