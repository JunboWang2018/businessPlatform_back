<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="/businessPlatform/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/businessPlatform/js/manageUser.js"></script>
<div style="padding:10px 10px 10px 10px">
	<form id="releaseProdForm" class="itemForm" method="post">
	    <table cellpadding="5">
	        <tr>
	            <td>用户名:</td>
	            <td>
					<input id="username" class="easyui-textbox" type="text" data-options="required:true" style="width: 280px;" />
	            </td>
	        </tr>
			<tr>
				<td>密码:</td>
				<td>
					<input id="password" class="easyui-textbox" type="text" data-options="required:true" style="width: 280px;" />
				</td>
			</tr>
	        <tr>
	            <td>用户权限:</td>
	            <td>
					<select id="power" data-options="required:true">
						<option>请选择用户权限</option>
						<option value="admin">管理员</option>
						<option value="user">用户</option>
					</select>
				</td>
	        </tr>
			<tr>
				<td>收货地址:</td>
				<td>
					<input id="address" class="easyui-textbox" type="text" style="width: 280px;" />
				</td>
			</tr>
	    </table>
	    <input type="hidden" name="itemParams"/>
	</form>
	<div style="padding:5px">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">重置</a>
	</div>
</div>

<script>
    //提交用户品发布表单
    function submitForm(){
        var formData = new FormData();
        formData.append("username", $("#username").val());
        formData.append("password", $("#password").val());
        formData.append("power", $("#power").val());
        formData.append("address", $("#address").val());
        $.ajax({
            type : "post",
            url : "/businessPlatform/data/manage/user/addUser",
            catch: false,
            processData: false,
            contentType: false,
            dataType: "json",
            data : formData,
            success: function(data) {
                if (data.code == 1002) {
                    $.messager.alert('提示','添加账号成功!');
                } else {
                    $.messager.alert('提示', data.message);
                }
            },
            error: function () {
                alert("请求失败");
            }
        });
    }
</script>
