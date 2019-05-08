package com.agriculture.platform.dao.user;

import com.agriculture.platform.pojo.base.Do.UserDo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {

    /**
     * 根据指定条件查询用户列表
     * @param userDo
     * @return
     */
    List<UserDo> selectUserList(UserDo userDo);

    /**
     * 添加用户
     * @param userDo
     * @return
     */
    Integer addUser(UserDo userDo);

    /**
     * 根据指定条件更新用户
     * @param userDo
     * @return
     */
    Integer updateUser(UserDo userDo);

    /**
     * 根据指定条件删除用户
     * @param userId
     * @return
     */
    Integer deleteUser(Integer userId);

    /**
     * 修改密码
     * @param userDo
     * @return
     */
    Integer updatePWD(UserDo userDo);
}
