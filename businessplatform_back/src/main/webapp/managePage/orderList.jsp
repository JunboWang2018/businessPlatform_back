<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="/businessPlatformManage/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/businessPlatformManage/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/businessPlatformManage/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<script type="text/javascript" charset="utf-8" src="/businessPlatformManage/js/manageOrder.js"></script>
<table class="easyui-datagrid" id="orderList" title="订单列表"
       data-options="singleSelect:false,collapsible:true,pagination:true,url:'/businessPlatformManage/data/manage/order/selectOrderList',method:'get',pageSize:30,toolbar:toolbar">
    <thead>
        <tr>
        	<th data-options="field:'ck',checkbox:true"></th>
        	<th data-options="field:'orderNumber',align:'center',width:200">订单编号</th>
            <th data-options="field:'prodNumber',align:'center',width:200">商品编号</th>
            <th data-options="field:'prodName',align:'center',width:400">商品标题</th>
            <th data-options="field:'orderQuantity',align:'center',width:70">交易数量</th>
            <th data-options="field:'prodPrice',align:'center',width:100">商品单价</th>
            <th data-options="field:'prodTypeName',align:'center',width:90">商品类型</th>
            <th data-options="field:'saleWayName',align:'center',width:90">出售形式</th>
            <th data-options="field:'orderPrice',align:'center',width:70">交易价格</th>
            <th data-options="field:'orderUsername',align:'center',width:100">交易用户</th>
            <th data-options="field:'orderStatus',width:80,align:'center'">交易状态</th>
            <th data-options="field:'createTime',width:130,align:'center',formatter:E3.formatDateTime">创建日期</th>
            <th data-options="field:'modifyTime',width:130,align:'center',formatter:E3.formatDateTime">更新日期</th>
        </tr>
    </thead>
</table>
<div id="orderPriceEditWindow" class="easyui-window" title="改价" data-options="modal:true,closed:true,iconCls:'icon-save',href:'/businessPlatformManage/view/manage/order/toEditOrderPriceWindow'" style="width:25%;height:25%;padding:10px;">
</div>