package com.agriculture.platform.service.product.impl;

import com.agriculture.platform.dao.product.SaleWayDao;
import com.agriculture.platform.pojo.base.Do.SaleWayDo;
import com.agriculture.platform.service.product.SaleWayService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Junbo Wang
 * @description
 * @date 2019/5/5
 */
@Service
public class SaleWayServiceImpl implements SaleWayService {

    @Resource
    private SaleWayDao saleWayDao;

    @Override
    public List<SaleWayDo> selectSaleWayList(SaleWayDo saleWayDo) {
        if (saleWayDo == null) {
            return saleWayDao.selectSaleWayList(new SaleWayDo());
        } else {
            return saleWayDao.selectSaleWayList(saleWayDo);
        }
    }

    @Override
    public SaleWayDo selectSaleWay(String code) {
        if (StringUtils.isEmpty(code)) {
            return null;
        }
        SaleWayDo querySaleWay = new SaleWayDo();
        querySaleWay.setCode(code);
        List<SaleWayDo> resultList = this.selectSaleWayList(querySaleWay);
        if (resultList != null && resultList.size() > 0) {
            return resultList.get(0);
        }
        return null;
    }
}
