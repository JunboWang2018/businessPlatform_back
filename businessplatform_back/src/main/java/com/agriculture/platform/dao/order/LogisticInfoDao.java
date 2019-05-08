package com.agriculture.platform.dao.order;

import com.agriculture.platform.pojo.base.Do.LogisticInfoDo;
import org.springframework.stereotype.Repository;

@Repository
public interface LogisticInfoDao {
    /**
     * 根据编码查询物流信息
     * @param code
     * @return
     */
    LogisticInfoDo selectLogisticInfo(String code);
}
