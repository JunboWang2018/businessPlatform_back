package com.agriculture.platform.controller.data;

import com.agriculture.platform.common.constant.RespJson;
import com.agriculture.platform.controller.data.BaseDataController;
import com.agriculture.platform.pojo.base.Do.ProductTypeDo;
import com.agriculture.platform.service.product.ProdTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author Junbo Wang
 * @description
 * @date 2019/5/5
 */
@Controller
@RequestMapping("/data/prodType")
public class ProdTypeDataController extends BaseDataController {

    @Autowired
    private ProdTypeService prodTypeService;

    @ResponseBody
    @RequestMapping("/selectProdTypeList")
    public RespJson selectProdTypeList(ProductTypeDo productTypeDo) {
        List<ProductTypeDo> resultList = prodTypeService.selectProdTypeList(productTypeDo);
        return this.responseData(resultList);
    }
}
