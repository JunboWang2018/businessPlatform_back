package com.agriculture.platform.dao.product;

import com.agriculture.platform.pojo.base.Do.AuctionInfoDo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AuctionInfoDao {
    /**
     * 根据指定条件查询竞价信息列表
     * @param auctionInfoDo
     * @return
     */
    List<AuctionInfoDo> selectAuctionInfoList(AuctionInfoDo auctionInfoDo);

    /**
     * 添加商品的竞价信息
     * @param auctionInfoDo
     * @return
     */
    Integer addAuctionInfo(AuctionInfoDo auctionInfoDo);

    /**
     * 根据指定条件删除竞价信息
     * @param prodNumber
     * @return
     */
    Integer deleteAuctionInfo(String prodNumber);
}
