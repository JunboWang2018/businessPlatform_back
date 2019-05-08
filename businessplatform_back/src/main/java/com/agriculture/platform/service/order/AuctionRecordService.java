package com.agriculture.platform.service.order;

import com.agriculture.platform.common.constant.Result;
import com.agriculture.platform.pojo.base.Do.AuctionRecordDo;
import com.agriculture.platform.pojo.base.Do.UserDo;
import com.agriculture.platform.pojo.base.Qo.EasyUIAuctionRecordQo;

import java.util.List;
import java.util.Map;

public interface AuctionRecordService {
    /**
     * 根据指定条件查询竞拍记录
     * @param auctionRecordDo
     * @return
     */
    List<AuctionRecordDo> selectAuctionRecordList(AuctionRecordDo auctionRecordDo);

    /**
     * 查询商品的最高竞拍纪录
     * @param prodNumber
     * @return
     */
    Double selectMaxPrice(String prodNumber);

    /**
     * @param prodNumber
     * @return
     */
    List<EasyUIAuctionRecordQo> selectEasyUIData(String prodNumber);
}
