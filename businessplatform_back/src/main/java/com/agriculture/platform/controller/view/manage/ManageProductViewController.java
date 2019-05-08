package com.agriculture.platform.controller.view.manage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Junbo Wang
 * @description
 * @date 2019/5/5
 */
@Controller
@RequestMapping("/view/manage/product")
public class ManageProductViewController {

    @RequestMapping("/toReleaseProduct")
    public String toReleaseProduct() {
        return "/managePage/releaseProduct.jsp";
    }

    @RequestMapping("/toManageProduct")
    public String toManageProduct() {
        return "/managePage/productList.jsp";
    }

    @RequestMapping("/toEditProduct")
    public String toEditProduct() {
        return "/managePage/editProduct.jsp";
    }

}
