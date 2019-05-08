package com.agriculture.platform.controller.data.manage;

import com.agriculture.platform.common.constant.RespJson;
import com.agriculture.platform.common.constant.Result;
import com.agriculture.platform.controller.data.BaseDataController;
import com.agriculture.platform.pojo.base.Do.UserDo;
import com.agriculture.platform.service.user.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author Junbo Wang
 * @description
 * @date 2019/5/6
 */
@Controller
@RequestMapping("/data/manage/user")
public class ManageUserDataController extends BaseDataController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ManageUserDataController.class);

    @Autowired
    private UserService userService;
    /**
     * 用户登录：
     *  1. 登录成功将用户名存入session
     *  2. 返回登录结果信息
     * @param userDo
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/login")
    public RespJson login(UserDo userDo, HttpSession session) {
        Result result = null;
        try {
            result = userService.login(userDo);
        } catch (IllegalArgumentException e) {
            return this.handleIllegalArgumentException(e);
        }
        //若登录成功，将用户放入session
        if (result == Result.LOGIN_SUCCESS) {
            UserDo queryUser = new UserDo();
            queryUser.setUsername(userDo.getUsername());
            UserDo resultUser = userService.selectUser(queryUser);
            session.setAttribute("user", resultUser);
        }
        return this.responseMsg(result.getCode(), result.getMessage());
    }

    /**
     * 加载用户
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping("/selectUser")
    public RespJson selectUser(Integer userId) {
        UserDo queryUser = new UserDo();
        queryUser.setUserId(userId);
        UserDo resultUser = userService.selectUser(queryUser);
        return  this.responseData(resultUser);
    }

    /**
     * 加载用户列表
     * @param page
     * @param rows
     * @return
     */
    @ResponseBody
    @RequestMapping("/selectUserList")
    public Map<String, Object> selectUserList(int page, int rows) {
        Map<String, Object> resultMap = userService.selectEasyUIUserDataList(page, rows);
        return  resultMap;
    }

    /**
     * 添加用户
     * @param userDo
     * @return
     */
    @ResponseBody
    @RequestMapping("/addUser")
    public RespJson addUser(UserDo userDo, HttpSession session) {
        UserDo sessionUser = (UserDo) session.getAttribute("user");
        if (!sessionUser.getPower().equals("superAdmin")) {
            return this.responseMsg(Result.FAILED.getCode(), "权限不足，无法添加");
        }
        Result result = userService.register(userDo);
        return  this.responseMsg(result.getCode(), result.getMessage());
    }

    /**
     * 修改用户
     * @param userDo
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateUser")
    public RespJson updateUser(UserDo userDo, HttpSession session) {
        UserDo sessionUser = (UserDo) session.getAttribute("user");
        if (!sessionUser.getPower().equals("superAdmin")) {
            return this.responseMsg(Result.FAILED.getCode(), "权限不足，无法修改");
        }
        Result result = userService.updateUser(userDo);
        return this.responseMsg(result.getCode(), result.getMessage());
    }

    /**
     * 重置密码
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping("/resetPWD")
    public RespJson resetPWD(Integer userId, HttpSession session) {
        UserDo sessionUser = (UserDo) session.getAttribute("user");
        if (!sessionUser.getPower().equals("superAdmin")) {
            return this.responseMsg(Result.FAILED.getCode(), "权限不足，无法重置密码");
        }
        String password = userService.resetPWD(userId);
        if (!StringUtils.isEmpty(password)) {
            return this.responseMsg(Result.SUCCESS.getCode(), password);
        }
        return this.responseMsg(Result.FAILED.getCode(), Result.FAILED.getMessage());
    }

    /**
     * 删除用户
     * @param userIds
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteUser")
    public RespJson deleteUser(Integer[] userIds, HttpSession session) {
        UserDo sessionUser = (UserDo) session.getAttribute("user");
        if (!sessionUser.getPower().equals("superAdmin")) {
            return this.responseMsg(Result.FAILED.getCode(), "权限不足，无法删除用户");
        }
        Result result = null;
        try {
            result = userService.deleteUsers(userIds);
        } catch (IllegalArgumentException e) {
            LOGGER.error(e.getMessage());
        }
        return this.responseMsg(result.getCode(), result.getMessage());
    }

    /**
     * 用户退出:
     *  1. sesssion中清除用户信息
     * @return
     */
    @RequestMapping("/logout")
    public String register(HttpSession session, HttpServletResponse response) {
        //设置返回数据格式为json
        response.setContentType("application/json");
        session.removeAttribute("user");
        return "/managePage/adminLogin.jsp";
    }
}
