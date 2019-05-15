<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="/businessPlatformManage/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/businessPlatformManage/js/manageUser.js"></script>
<table class="easyui-datagrid" id="userList" title="用户列表"
       data-options="singleSelect:false,collapsible:true,pagination:true,url:'/businessPlatformManage/data/manage/user/selectUserList',method:'get',pageSize:30,toolbar:toolbar">
    <thead>
        <tr>
        	<th data-options="field:'ck',checkbox:true"></th>
        	<th data-options="field:'userId',align:'center',width:70">用户编号</th>
            <th data-options="field:'username',align:'center',width:150">用户名</th>
            <th data-options="field:'password',align:'center',width:300">密码</th>
            <th data-options="field:'power',align:'center',width:100">权限</th>
            <th data-options="field:'score',align:'center',width:70">积分</th>
            <th data-options="field:'balance',align:'center',width:100">账户余额</th>
            <th data-options="field:'address',align:'center',width:300">收货地址</th>
            <th data-options="field:'createTime',width:130,align:'center',formatter:E3.formatDateTime">创建日期</th>
            <th data-options="field:'modifyTime',width:130,align:'center',formatter:E3.formatDateTime">更新日期</th>
        </tr>
    </thead>
</table>
<div id="userEditWindow" class="easyui-window" title="编辑用户" data-options="modal:true,closed:true,iconCls:'icon-save',href:'/businessPlatformManage/view/manage/user/toEditUser'" style="width:80%;height:80%;padding:10px;">
</div>
