package com.agriculture.platform.controller.data;

import com.agriculture.platform.common.constant.RespJson;
import com.agriculture.platform.controller.data.BaseDataController;
import com.agriculture.platform.pojo.base.Do.ProductTypeDo;
import com.agriculture.platform.pojo.base.Do.SaleWayDo;
import com.agriculture.platform.service.product.ProdTypeService;
import com.agriculture.platform.service.product.SaleWayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author Junbo Wang
 * @description
 * @date 2019/5/5
 */
@Controller
@RequestMapping("/data/saleWay")
public class SaleWayController extends BaseDataController {

    @Autowired
    private SaleWayService saleWayService;

    @ResponseBody
    @RequestMapping("/selectSaleWayList")
    public RespJson selectSaleWayList(SaleWayDo saleWayDo) {
        List<SaleWayDo> resultList = saleWayService.selectSaleWayList(saleWayDo);
        return this.responseData(resultList);
    }
}
