package com.agriculture.platform.service.order.impl;

import com.agriculture.platform.common.constant.Result;
import com.agriculture.platform.dao.order.OrderDao;
import com.agriculture.platform.dao.product.ProductDao;
import com.agriculture.platform.pojo.base.Do.*;
import com.agriculture.platform.pojo.base.Qo.EasyUIOrderDataQo;
import com.agriculture.platform.pojo.base.Qo.OrderInfoQo;
import com.agriculture.platform.service.order.AuctionRecordService;
import com.agriculture.platform.service.order.OrderService;
import com.agriculture.platform.service.product.ProdTypeService;
import com.agriculture.platform.service.product.ProductService;
import com.agriculture.platform.service.product.SaleWayService;
import com.agriculture.platform.service.user.UserService;
import com.agriculture.platform.utils.AutoGenerateNumberUtil;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Junbo Wang
 * @description
 * @date 2019/5/7
 */
@Service
public class OrderServiceImpl implements OrderService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Resource
    private OrderDao orderDao;

    @Autowired
    private ProductService productService;

    @Resource
    private ProductDao productDao;

    @Autowired
    private AuctionRecordService auctionRecordService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProdTypeService prodTypeService;

    @Autowired
    private SaleWayService saleWayService;

    @Override
    public void addOrder(ProductDo productDo) {
        Integer result = null;
        //查找竞拍记录表
        AuctionRecordDo queryAuctionRecord = new AuctionRecordDo();
        queryAuctionRecord.setProdNumber(productDo.getNumber());
        List<AuctionRecordDo> auctionRecordDos = auctionRecordService.selectAuctionRecordList(queryAuctionRecord);
        //若记录为空，则设置商品下架（流拍）
        if (auctionRecordDos == null || auctionRecordDos.size() == 0) {
            productDo.setSellStatus(-1);
            productDo.setModifyTime(new Timestamp(System.currentTimeMillis()));
            result = productDao.updateProduct(productDo);
        } else {
            //取出价最高的记录
            AuctionRecordDo maxPriceRecord = auctionRecordDos.get(0);
            for (int i = 0; i < auctionRecordDos.size(); i++) {
                if (maxPriceRecord.getPrice().doubleValue() < auctionRecordDos.get(i).getPrice().doubleValue()) {
                    maxPriceRecord = auctionRecordDos.get(i);
                }
            }
            //生成订单(库存数量即为交易数量，物流信息暂空，支付后设置已下单)
            OrderDo orderDo = new OrderDo();
            orderDo.setIsActive(1);
            String orderNumber = AutoGenerateNumberUtil.getAutoGenerateId("ORDER");
            orderDo.setOrderNumber(orderNumber);
            orderDo.setProdNumber(productDo.getNumber());
            orderDo.setQuantity(productDo.getQuantity());
            orderDo.setPrice(productDo.getQuantity().intValue() * maxPriceRecord.getPrice().doubleValue());
            orderDo.setUserId(maxPriceRecord.getUserId());
            orderDo.setIsPaid(0);
            orderDo.setLogisticInfoCode("USHIP");
            result = orderDao.addOrder(orderDo);
            //生成成功设置已售出
            if (result == 1) {
                productDo.setSellStatus(0);
                productDo.setModifyTime(new Timestamp(System.currentTimeMillis()));
                productDao.updateProduct(productDo);
            }
        }
    }

    @Override
    public List<OrderInfoQo> selectOrderInfoQoList(OrderDo orderDo) {
        List<OrderInfoQo> orderInfoQos = new ArrayList<OrderInfoQo>();
        orderDo.setIsActive(1);
        List<OrderDo> resultList = this.selectOrderList(orderDo);
        if (resultList == null) {
            return null;
        }
        for (int i = 0; i < resultList.size(); i++) {
            OrderInfoQo orderInfoQo = new OrderInfoQo();
            orderInfoQo.setOrderDo(resultList.get(i));
            //查找商品信息
            ProductDo queryProd = new ProductDo();
            queryProd.setNumber(resultList.get(i).getProdNumber());
            ProductDo resultProd = productService.selectProduct(queryProd);
            orderInfoQo.setProductDo(resultProd);
            orderInfoQos.add(orderInfoQo);
        }
        return orderInfoQos;
    }

    @Override
    public List<OrderDo> selectOrderList(OrderDo orderDo) {
        if (orderDo == null) {
            return null;
        }
        List<OrderDo> resultList = orderDao.selectOrderList(orderDo);
        return resultList;
    }

    @Override
    public OrderDo selectOrder(OrderDo orderDo) {
        List<OrderDo> resultList = this.selectOrderList(orderDo);
        if (resultList == null) {
            return null;
        }
        return resultList.get(0);
    }

    @Override
    public Result updateOrderPrice(String orderNumber, Double price) {
        if (orderNumber == null || price == null) {
            return Result.FAILED;
        }
        OrderDo orderDo = new OrderDo();
        orderDo.setOrderNumber(orderNumber);
        orderDo.setPrice(price);
        Integer result = orderDao.updateOrderPrice(orderDo);
        if (result == 1) {
            return Result.SUCCESS;
        }
        return Result.FAILED;
    }

    @Override
    public Map<String, Object> selectEasyUIOrderOoList(int page, int rows) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<EasyUIOrderDataQo> easyUIOrderQoList = new ArrayList<EasyUIOrderDataQo>();
        List<OrderInfoQo> orderInfoQoList = this.selectOrderInfoQoList(new OrderDo());
        if (orderInfoQoList == null || orderInfoQoList.size() == 0) {
            resultMap.put("total", 0);
            resultMap.put("rows", null);
            return resultMap;
        }
        //取分页信息
        PageInfo<OrderInfoQo> pageList = new PageInfo<OrderInfoQo>(orderInfoQoList);
        resultMap.put("total", pageList.getTotal());
        //封装页面展示信息
        //取出商品类型和出售形式列表
        List<ProductTypeDo> productTypeList = prodTypeService.selectProdTypeList(new ProductTypeDo());
        List<SaleWayDo> saleWayList = saleWayService.selectSaleWayList(new SaleWayDo());
        for (int i = 0; i < orderInfoQoList.size(); i++) {
            EasyUIOrderDataQo easyUIOrderDataQo = this.setEasyUIOrderDataQo(orderInfoQoList.get(i), productTypeList, saleWayList);
            easyUIOrderQoList.add(easyUIOrderDataQo);
        }
        resultMap.put("rows", easyUIOrderQoList);
        return resultMap;
    }

    /**
     * 封装后台管理EasyUI页面订单信息
     * @param orderInfoQo
     * @return
     */
    private EasyUIOrderDataQo setEasyUIOrderDataQo(OrderInfoQo orderInfoQo, List<ProductTypeDo> productTypeList, List<SaleWayDo> saleWayList) {
        EasyUIOrderDataQo easyUIOrderDataQo = new EasyUIOrderDataQo();
        easyUIOrderDataQo.setOrderNumber(orderInfoQo.getOrderDo().getOrderNumber());
        easyUIOrderDataQo.setProdNumber(orderInfoQo.getOrderDo().getProdNumber());
        easyUIOrderDataQo.setProdName(orderInfoQo.getProductDo().getName());
        easyUIOrderDataQo.setOrderQuantity(orderInfoQo.getOrderDo().getQuantity());
        easyUIOrderDataQo.setProdPrice(orderInfoQo.getProductDo().getPrice());
        if (productTypeList != null && productTypeList.size() > 0) {
            for (int j = 0; j < productTypeList.size(); j++) {
                if (productTypeList.get(j).getCode().equals(orderInfoQo.getProductDo().getTypeCode())) {
                    easyUIOrderDataQo.setProdTypeName(productTypeList.get(j).getName());
                }
            }
        }
        if (saleWayList != null && saleWayList.size() > 0) {
            for (int j = 0; j < saleWayList.size(); j++) {
                if (saleWayList.get(j).getCode().equals(orderInfoQo.getProductDo().getSaleWayCode())) {
                    easyUIOrderDataQo.setSaleWayName(saleWayList.get(j).getName());
                }
            }
        }
        easyUIOrderDataQo.setOrderPrice(orderInfoQo.getOrderDo().getPrice());
        UserDo queryUser = new UserDo();
        queryUser.setUserId(orderInfoQo.getOrderDo().getUserId());
        UserDo resultUser = userService.selectUser(queryUser);
        easyUIOrderDataQo.setOrderUsername(resultUser.getUsername());
        if (orderInfoQo.getOrderDo().getIsPaid().intValue() == 0) {
            easyUIOrderDataQo.setOrderStatus("待支付");
        }
        if (orderInfoQo.getOrderDo().getIsPaid().intValue() == 1) {
            easyUIOrderDataQo.setOrderStatus("已支付");
        }
        easyUIOrderDataQo.setCreateTime(orderInfoQo.getOrderDo().getCreateTime());
        easyUIOrderDataQo.setModifyTime(orderInfoQo.getOrderDo().getModifyTime());
        return easyUIOrderDataQo;
    }
}
