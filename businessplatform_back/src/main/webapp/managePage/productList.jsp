<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="/businessPlatformManage/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/businessPlatformManage/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/businessPlatformManage/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<script type="text/javascript" charset="utf-8" src="/businessPlatformManage/js/manageProd.js"></script>
<table class="easyui-datagrid" id="productList" title="商品列表"
       data-options="singleSelect:false,collapsible:true,pagination:true,url:'/businessPlatformManage/data/manage/product/selectProdList',method:'get',pageSize:30,toolbar:toolbar">
    <thead>
        <tr>
        	<th data-options="field:'ck',checkbox:true"></th>
        	<th data-options="field:'prodNumber',align:'center',width:200">商品编号</th>
            <th data-options="field:'name',align:'center',width:300">商品标题</th>
            <th data-options="field:'quantity',align:'center',width:70">库存数量</th>
            <th data-options="field:'price',align:'center',width:120">出售价格/起拍价格</th>
            <th data-options="field:'prodTypeName',align:'center',width:100">商品类型</th>
            <th data-options="field:'saleWayName',align:'center',width:100">出售形式</th>
            <th data-options="field:'deadline',align:'center',width:100">竞拍期限(秒)</th>
            <th data-options="field:'maxAuctionPrice',align:'center',width:100">当前竞拍最高价</th>
            <th data-options="field:'addPrice',align:'center',width:70">加价幅度</th>
            <th data-options="field:'userId',align:'center',width:80">发布用户ID</th>
            <th data-options="field:'username',align:'center',width:80">发布用户名</th>
            <th data-options="field:'sellStatusName',width:100,align:'center'">状态</th>
            <th data-options="field:'createTime',width:130,align:'center',formatter:E3.formatDateTime">创建日期</th>
            <th data-options="field:'modifyTime',width:130,align:'center',formatter:E3.formatDateTime">更新日期</th>
        </tr>
    </thead>
</table>
<div id="productEditWindow" class="easyui-window" title="编辑商品" data-options="modal:true,closed:true,iconCls:'icon-save',href:'/businessPlatformManage/view/manage/product/toEditProduct'" style="width:80%;height:80%;padding:10px;">
</div>
<div id="auctionRecordWindow" class="easyui-window" title="查看竞拍记录" data-options="modal:true,closed:true,iconCls:'icon-save',href:'/businessPlatformManage/view/manage/order/toAuctionRecordWindow'" style="width:30%;height:80%;padding:10px;">
</div>
