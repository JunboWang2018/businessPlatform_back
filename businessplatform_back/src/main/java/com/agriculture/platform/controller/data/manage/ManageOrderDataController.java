package com.agriculture.platform.controller.data.manage;

import com.agriculture.platform.common.constant.RespJson;
import com.agriculture.platform.common.constant.Result;
import com.agriculture.platform.controller.data.BaseDataController;
import com.agriculture.platform.pojo.base.Do.AuctionRecordDo;
import com.agriculture.platform.pojo.base.Do.OrderDo;
import com.agriculture.platform.pojo.base.Qo.EasyUIAuctionRecordQo;
import com.agriculture.platform.service.order.AuctionRecordService;
import com.agriculture.platform.service.order.OrderService;
import com.agriculture.platform.service.product.AuctionInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Junbo Wang
 * @description
 * @date 2019/5/6
 */
@Controller
@RequestMapping("/data/manage/order")
public class ManageOrderDataController extends BaseDataController {

    @Autowired
    private AuctionRecordService auctionRecordService;

    @Autowired
    private OrderService orderService;

    /**
     * 查询竞价记录
     * @param prodNumber
     * @return
     */
    @ResponseBody
    @RequestMapping("/selectAuctionRecord")
    public RespJson selectAuctionRecord(String prodNumber) {
        List<EasyUIAuctionRecordQo> resultList = auctionRecordService.selectEasyUIData(prodNumber);
        return this.responseData(resultList);
    }

    /**
     * 修改订单价格
     * @param orderNumber
     * @param price
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateOrderPrice")
    public RespJson updateOrderPrice(String orderNumber, Double price) {
        Result result = orderService.updateOrderPrice(orderNumber, price);
        return this.responseMsg(result.getCode(), result.getMessage());
    }

    /**
     * 查询订单列表
     * @param page
     * @param rows
     * @return
     */
    @ResponseBody
    @RequestMapping("/selectOrderList")
    public Map<String, Object> selectOrderList(@RequestParam("page") int page, @RequestParam("rows") int rows) {
        Map<String, Object> resultMap = orderService.selectEasyUIOrderOoList(page, rows);
        return resultMap;
    }

    /**
     * 查询订单
     * @param orderDo
     * @return
     */
    @ResponseBody
    @RequestMapping("/selectOrder")
    public RespJson selectOrder(OrderDo orderDo) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        OrderDo resultOrder = orderService.selectOrder(orderDo);
        resultMap.put("order", resultOrder);
        return this.responseData(resultMap);
    }
}
