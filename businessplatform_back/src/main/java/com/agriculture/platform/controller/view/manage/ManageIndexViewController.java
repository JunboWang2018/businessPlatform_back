package com.agriculture.platform.controller.view.manage;

import com.agriculture.platform.controller.data.BaseDataController;
import com.agriculture.platform.pojo.base.Do.UserDo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @author Junbo Wang
 * @description
 * @date 2019/5/2
 */
@Controller
@RequestMapping("/manage")
public class ManageIndexViewController extends BaseDataController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ManageIndexViewController.class);

    @RequestMapping("/")
    public String index(HttpSession session) {
        UserDo sessionUser = (UserDo) session.getAttribute("user");
        if (sessionUser == null) {
            return "/managePage/adminLogin.jsp";
        }
        return "/managePage/manageIndex.jsp";
    }
}
