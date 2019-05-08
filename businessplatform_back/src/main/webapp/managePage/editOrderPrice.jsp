<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="/businessPlatform/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/businessPlatform/js/manageOrder.js"></script>
<div style="padding:10px 10px 10px 10px">
	<form id="orderPriceEditForm" class="itemForm" method="post">
		<input type="hidden" name="id"/>
	    <table cellpadding="5">
			<tr>
				<td>订单编号:</td>
				<td>
					<input id="editOrderNumber" readonly="readonly" type="text" style="width: 280px;"/>
				</td>
			</tr>
			<tr>
				<td>修改前价格:</td>
				<td><input id="editOldPrice" readonly="readonly" type="text" style="width: 280px;"></input></td>
			</tr>
			<tr>
				<td>修改后价格:</td>
				<td>
					<input id="editNewPrice" type="text" class="form-control" style="width: 280px;" />
				</td>
			</tr>
	    </table>
	    <input type="hidden" name="itemParams"/>
	    <input type="hidden" name="itemParamId"/>
	</form>
	<div style="padding:5px">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitEditForm()">提交改价</a>
	</div>
</div>

<script>
    function submitEditForm(){
        var formData = new FormData();
        formData.append("orderNumber", $("#editOrderNumber").val())
        formData.append("price", $("#editNewPrice").val());
        $.ajax({
            type : "post",
            url : "/businessPlatform/data/manage/order/updateOrderPrice",
            catch: false,
            processData: false,
            contentType: false,
            dataType: "json",
            data : formData,
            success: function(data) {
                if (data.code == 1000) {
                    $.messager.alert('提示','改价成功!');
                    $("#orderPriceEditForm").datagrid("reload");
                } else {
                    $.messager.alert('提示','改价失败!');
                }
            },
            error: function () {
                $.messager.alert('提示','请求失败!');
            }
        });
    }
</script>

