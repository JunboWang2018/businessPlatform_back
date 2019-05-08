package com.agriculture.platform.controller.view.manage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Junbo Wang
 * @description
 * @date 2019/5/6
 */
@Controller
@RequestMapping("/view/manage/user")
public class ManageUserViewController {

    @RequestMapping("/toManageUser")
    public String toManageUser() {
        return "/managePage/userList.jsp";
    }

    @RequestMapping("/toAddUser")
    public String toAddUser() {
        return "/managePage/addUser.jsp";
    }

    @RequestMapping("/toEditUser")
    public String toEditUser() {
        return "/managePage/editUser.jsp";
    }
}
