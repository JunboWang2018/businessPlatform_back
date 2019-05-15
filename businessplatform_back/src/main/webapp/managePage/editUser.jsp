<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="/businessPlatformManage/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/businessPlatformManage/js/manageUser.js"></script>
<div style="padding:10px 10px 10px 10px">
	<form id="userEditForm" class="itemForm" method="post">
		<input type="hidden" name="id"/>
	    <table cellpadding="5">
			<tr>
				<td>用户编号:</td>
				<td>
					<input id="editUserId" readonly="readonly" type="text" style="width: 280px;"/>
				</td>
			</tr>
			<tr>
				<td>用户名:</td>
				<td><input id="editUsername" readonly="readonly" type="text" style="width: 280px;"></input></td>
			</tr>
			<tr>
				<td>密码:</td>
				<td>
					<input id="editPassword" readonly="readonly" type="text" class="form-control" style="width: 280px;" />
				</td>
			</tr>
			<tr>
				<td>用户权限:</td>
				<td>
					<select id="editPower" data-options="required:true">
						<option>请选择用户权限</option>
						<option value="admin">管理员</option>
						<option value="user">用户</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>用户积分:</td>
				<td>
					<input id="editScore" readonly="readonly" type="text" style="width: 280px;"/>
				</td>
			</tr>
			<tr>
				<td>账户余额:</td>
				<td>
					<input id="editBalance" readonly="readonly" type="text" style="width: 280px;"/>
				</td>
			</tr>
			<tr>
				<td>收货地址:</td>
				<td>
					<input id="editAddress" type="text" style="width: 280px;" placeholder="多个地址请用逗号分隔"/>
				</td>
			</tr>
	    </table>
	    <input type="hidden" name="itemParams"/>
	    <input type="hidden" name="itemParamId"/>
	</form>
	<div style="padding:5px">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitEditForm()">提交</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="resetPWD()">重置密码</a>
	</div>
</div>

<script>
    function submitEditForm(){
        var formData = new FormData();
        formData.append("userId", $("#editUserId").val())
        formData.append("power", $("#editPower").val());
        formData.append("address", $("#editAddress").val());
        $.ajax({
            type : "post",
            url : "/businessPlatformManage/data/manage/user/updateUser",
            catch: false,
            processData: false,
            contentType: false,
            dataType: "json",
            data : formData,
            success: function(data) {
                if (data.code == 1011) {
                    $.messager.alert('提示','修改账号信息成功!');
                } else {
                    $.messager.alert('提示', data.message);
                }
            },
            error: function () {
                $.messager.alert('提示','请求失败!');
            }
        });
    }
    //密码重置
    function resetPWD() {
        var params = {"userId":$("#editUserId").val()};
        $.post("/businessPlatformManage/data/manage/user/resetPWD", params, function(result){
            if (result.code == 1000) {
                var str = "密码重置成功，新密码是 " + result.message + "，请牢记！";
                $.messager.alert('提示', str, undefined,function(){
                    $("#userEditForm").datagrid("reload");
                });
            } else {
                $.messager.alert('提示', data.message);
			}
        });
    }
</script>

