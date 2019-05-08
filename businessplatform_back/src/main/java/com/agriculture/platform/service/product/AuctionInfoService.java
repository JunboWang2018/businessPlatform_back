package com.agriculture.platform.service.product;

import com.agriculture.platform.common.constant.Result;
import com.agriculture.platform.pojo.base.Do.AuctionInfoDo;

import java.util.List;

public interface AuctionInfoService {
    /**
     * 根据指定条件查询竞价信息列表
     * @param auctionInfoDo
     * @return
     */
    List<AuctionInfoDo> selectAuctionInfoList(AuctionInfoDo auctionInfoDo);

    /**
     * 根据指定条件查询竞价信息
     * @param auctionInfoDo
     * @return
     */
    AuctionInfoDo selectAuctionInfo(AuctionInfoDo auctionInfoDo);

    /**
     * 添加商品的竞价信息
     * @param auctionInfoDo
     * @return
     */
    Result addAuctionInfo(AuctionInfoDo auctionInfoDo) throws IllegalArgumentException;

    /**
     * 根据指定条件删除竞价信息
     * @param prodNumber
     * @return
     */
    Result deleteAuctionInfo(String prodNumber);

}
