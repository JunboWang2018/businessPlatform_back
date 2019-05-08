package com.agriculture.platform.dao.order;

import com.agriculture.platform.pojo.base.Do.AuctionRecordDo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AuctionRecordDao {
    /**
     * 根据指定条件查询竞拍记录
     * @param auctionRecordDo
     * @return
     */
    List<AuctionRecordDo> selectAuctionRecordList(AuctionRecordDo auctionRecordDo);

    /**
     * 添加一条竞拍记录
     * @param auctionRecordDo
     * @return
     */
    Integer addAuctionRecord(AuctionRecordDo auctionRecordDo);
}
