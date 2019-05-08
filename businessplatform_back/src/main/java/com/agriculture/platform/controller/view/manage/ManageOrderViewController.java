package com.agriculture.platform.controller.view.manage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Junbo Wang
 * @description
 * @date 2019/5/6
 */
@Controller
@RequestMapping("/view/manage/order")
public class ManageOrderViewController {

    @RequestMapping("/toAuctionRecordWindow")
    public String toAuctionRecordWindow() {
        return "/managePage/auctionRecord.jsp";
    }

    @RequestMapping("/toManageOrder")
    public String toManageOrder() {
        return "/managePage/orderList.jsp";
    }

    @RequestMapping("/toEditOrderPriceWindow")
    public String toEditOrderPriceWindow() {
        return "/managePage/editOrderPrice.jsp";
    }
}
