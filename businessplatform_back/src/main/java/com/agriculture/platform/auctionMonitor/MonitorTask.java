package com.agriculture.platform.auctionMonitor;

import com.agriculture.platform.pojo.base.Do.AuctionInfoDo;
import com.agriculture.platform.pojo.base.Do.ProductDo;
import com.agriculture.platform.pojo.base.Do.UserDo;
import com.agriculture.platform.service.order.AuctionRecordService;
import com.agriculture.platform.service.order.OrderService;
import com.agriculture.platform.service.product.AuctionInfoService;
import com.agriculture.platform.service.product.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.TimerTask;

/**
 * @author Junbo Wang
 * @description 监控线程类
 * @date 2019/5/9
 */

@Component("monitorTask")
@Scope("prototype")
public class MonitorTask extends TimerTask {
    private static final Logger LOGGER = LoggerFactory.getLogger(MonitorTask.class);

    private ProductService productService;
    private AuctionInfoService auctionInfoService;
    private OrderService orderService;
    private AuctionRecordService auctionRecordService;

    @Override
    public void run() {
        LOGGER.debug("开始检查竞拍商品....");
        productService = ApplicationContextProvider.getBean(ProductService.class);
        auctionInfoService = ApplicationContextProvider.getBean( AuctionInfoService.class);
        orderService = ApplicationContextProvider.getBean(OrderService.class);
        auctionRecordService = ApplicationContextProvider.getBean(AuctionRecordService.class);
        if (productService != null && auctionInfoService != null && orderService != null && auctionRecordService != null) {
            this.checkAuctionProd();
        } else {
            LOGGER.error("没有获取到相关的bean");
        }
        LOGGER.debug("结束检查竞拍商品....");
    }

    /**
     * 检查竞拍商品情况
     */
    private void checkAuctionProd() {
        ProductDo queryProd = new ProductDo();
        queryProd.setIsActive(1);
        queryProd.setSaleWayCode("AUCTI");
        queryProd.setSellStatus(1);
        List<ProductDo> auctionProdList = productService.selectProductList(queryProd);
        if (auctionProdList != null) {
            for (int i = 0; i < auctionProdList.size(); i++) {
                if (this.isExpire(auctionProdList.get(i))) {
                    //已到期自动交易
                    orderService.addOrder(auctionProdList.get(i));
                }
            }
        }
    }

    /**
     * 检查商品是否到达竞拍期限
     * @param productDo
     * @return
     */
    private boolean isExpire(ProductDo productDo) {
        AuctionInfoDo queryAuctionIfo = new AuctionInfoDo();
        queryAuctionIfo.setProdNumber(productDo.getNumber());
        AuctionInfoDo resultAuctionInfo = auctionInfoService.selectAuctionInfo(queryAuctionIfo);
        if (resultAuctionInfo == null) {
            LOGGER.error("商品编号为 " + productDo.getNumber() + " 的竞拍商品没有竞拍信息！");
            return false;
        }
        //如果竞拍信息的修改时间为空，则按创建时间检查；否则，按修改时间检查
        if (resultAuctionInfo.getModifyTime() == null) {
            if (System.currentTimeMillis() > (resultAuctionInfo.getCreateTime().getTime() + resultAuctionInfo.getDeadline() * 1000)) {
                return true;
            }
        } else {
            if (System.currentTimeMillis() > (resultAuctionInfo.getModifyTime().getTime() + resultAuctionInfo.getDeadline() * 1000)) {
                return true;
            }
        }

        return false;
    }
}
