package com.agriculture.platform.service.order.impl;

import com.agriculture.platform.common.constant.Result;
import com.agriculture.platform.dao.order.AuctionRecordDao;
import com.agriculture.platform.pojo.base.Do.AuctionRecordDo;
import com.agriculture.platform.pojo.base.Do.UserDo;
import com.agriculture.platform.pojo.base.Qo.EasyUIAuctionRecordQo;
import com.agriculture.platform.service.order.AuctionRecordService;
import com.agriculture.platform.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Junbo Wang
 * @description
 * @date 2019/5/6
 */
@Service
public class AuctionRecordServiceImpl implements AuctionRecordService {

    @Resource
    private AuctionRecordDao auctionRecordDao;

    @Autowired
    private UserService userService;


    @Override
    public List<AuctionRecordDo> selectAuctionRecordList(AuctionRecordDo auctionRecordDo) {
        if (auctionRecordDo == null) {
            auctionRecordDo = new AuctionRecordDo();
        }
        return auctionRecordDao.selectAuctionRecordList(auctionRecordDo);
    }

    @Override
    public Double selectMaxPrice(String prodNumber) {
        AuctionRecordDo queryAuctionRecord = new AuctionRecordDo();
        queryAuctionRecord.setProdNumber(prodNumber);
        List<AuctionRecordDo> resultList = this.selectAuctionRecordList(queryAuctionRecord);
        if (resultList == null || resultList.size() == 0) {
            return null;
        }
        Double maxPrice = resultList.get(0).getPrice();
        for (int i = 1; i < resultList.size(); i++) {
            if (resultList.get(i).getPrice().doubleValue() > maxPrice.doubleValue()) {
                maxPrice = resultList.get(i).getPrice();
            }
        }
        return maxPrice;
    }

    @Override
    public List<EasyUIAuctionRecordQo> selectEasyUIData(String prodNumber) {
        List<EasyUIAuctionRecordQo> easyUIAuctionRecordQos = new ArrayList<EasyUIAuctionRecordQo>();
        AuctionRecordDo queryAuctionInfo = new AuctionRecordDo();
        queryAuctionInfo.setProdNumber(prodNumber);
        List<AuctionRecordDo> resultList = this.selectAuctionRecordList(queryAuctionInfo);
        if (resultList == null || resultList.size() == 0) {
            return null;
        }
        for (int i = 0; i < resultList.size(); i++) {
            EasyUIAuctionRecordQo easyUIProdDataQo = this.selectEasyUIProdDataQo(resultList.get(i));
            easyUIAuctionRecordQos.add(easyUIProdDataQo);
        }
        return easyUIAuctionRecordQos;
    }

    private EasyUIAuctionRecordQo selectEasyUIProdDataQo(AuctionRecordDo auctionRecordDo) {
        EasyUIAuctionRecordQo easyUIAuctionRecordQo = new EasyUIAuctionRecordQo();
        easyUIAuctionRecordQo.setAuctionRecordId(auctionRecordDo.getAuctionRecordId());
        easyUIAuctionRecordQo.setPrice(auctionRecordDo.getPrice());
        easyUIAuctionRecordQo.setCreateTime(auctionRecordDo.getCreateTime());
        //查询用户
        UserDo queryUser = new UserDo();
        queryUser.setUserId(auctionRecordDo.getUserId());
        UserDo resultUser = userService.selectUser(queryUser);
        easyUIAuctionRecordQo.setUsername(resultUser.getUsername());
        return easyUIAuctionRecordQo;
    }
}
