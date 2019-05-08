package com.agriculture.platform.service.product.impl;

import com.agriculture.platform.common.constant.Result;
import com.agriculture.platform.dao.product.AuctionInfoDao;
import com.agriculture.platform.pojo.base.Do.AuctionInfoDo;
import com.agriculture.platform.service.product.AuctionInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Junbo Wang
 * @description
 * @date 2019/5/6
 */
@Service
public class AuctionInfoServiceImpl implements AuctionInfoService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuctionInfoServiceImpl.class);

    @Resource
    private AuctionInfoDao auctionInfoDao;

    @Override
    public List<AuctionInfoDo> selectAuctionInfoList(AuctionInfoDo auctionInfoDo) {
        if (auctionInfoDo == null) {
            auctionInfoDo = new AuctionInfoDo();
        }
        return auctionInfoDao.selectAuctionInfoList(auctionInfoDo);
    }

    @Override
    public AuctionInfoDo selectAuctionInfo(AuctionInfoDo auctionInfoDo) {
        List<AuctionInfoDo> resultList = this.selectAuctionInfoList(auctionInfoDo);
        if (resultList == null || resultList.size() == 0) {
            return null;
        }
        return resultList.get(0);
    }

    @Override
    public Result addAuctionInfo(AuctionInfoDo auctionInfoDo) throws IllegalArgumentException {
        if (auctionInfoDo == null) {
            throw new IllegalArgumentException();
        }
        Integer result = auctionInfoDao.addAuctionInfo(auctionInfoDo);
        if (result == 1) {
            return Result.AUCTION_INFO_ADD_SUCCESS;
        }
        return Result.AUCTION_INFO_ADD_FAILED;
    }

    @Override
    public Result deleteAuctionInfo(String prodNumber) {
        Integer result = auctionInfoDao.deleteAuctionInfo(prodNumber);
        if (result == 1) {
            return Result.AUCTION_INFO_DELETE_SUCCESS;
        }
        return Result.AUCTION_INFO_DELETE_FAILED;
    }
}
