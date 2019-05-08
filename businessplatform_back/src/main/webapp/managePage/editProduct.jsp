<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="/businessPlatform/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/businessPlatform/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/businessPlatform/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<script type="text/javascript" charset="utf-8" src="/businessPlatform/js/manageProd.js"></script>
<div style="padding:10px 10px 10px 10px">
	<form id="productEditForm" class="itemForm" method="post">
		<input type="hidden" name="id"/>
	    <table cellpadding="5">
			<tr>
				<td>商品编号:</td>
				<td>
					<input id="editProdNumber" readonly="readonly" type="text" style="width: 280px;"/>
				</td>
			</tr>
			<tr>
				<td>商品标题:</td>
				<td><input id="editName" type="text" style="width: 280px;"></input></td>
			</tr>
			<tr>
				<td>展示图片:</td>
				<td>
					<input id="editImageFile" type="file" class="form-control" onchange="previewImage(this, 'editImage');">
				</td>
			</tr>
			<tr>
				<td>图片预览:</td>
				<td>
					<img id="editImage" style="width: 200px; height: 200px;">
				</td>
			</tr>
			<tr>
				<td>商品描述:</td>
				<td>
					<textarea style="width:800px;height:300px;visibility:hidden;" id="editDescription"></textarea>
				</td>
			</tr>
			<tr>
				<td id="edit_td_name_price">商品价格:</td>
				<td><input  type="text" id="editPrice" />
					<input type="hidden" name="price"/>
				</td>
			</tr>
			<tr>
				<td id="edit_td_name_quantity">库存数量:</td>
				<td><input type="text" id="editQuantity"/></td>
			</tr>

			<tr>
				<td>商品类目:</td>
				<td>
					<select id="editProdType" >

					</select>
				</td>
			</tr>
			<tr>
				<td>出售形式:</td>
				<td>
					<select id="editSaleWay" onchange="auctionProdDisplay()" >

					</select>
				</td>
			</tr>
			<tr id="edit_tr_auction_time_limit" hidden="hidden">
				<td>竞拍期限:</td>
				<td>
					<input type="text" id="editAuctionTimeLimit" style="width: 280px;" placeholder="请输入整数，以秒为单位"/>
				</td>
			</tr>
			<tr id="edit_tr_add_price" hidden="hidden">
				<td>加价幅度:</td>
				<td>
					<input type="text" id="editAddPrice" style="width: 280px;" placeholder="最小1元"/>
				</td>
			</tr>
	    </table>
	    <input type="hidden" name="itemParams"/>
	    <input type="hidden" name="itemParamId"/>
	</form>
	<div style="padding:5px">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitEditForm()">提交</a>
	</div>
</div>

<script>

    var editProdEditor ;
    //页面初始化完毕后执行此方法
    $(function(){
        //创建富文本编辑器
        editProdEditor = E3.createEditor("#productEditForm [id=editDescription]");
        //初始化类目选择和图片上传器
        E3.init({fun:function(node){
                //根据商品的分类id取商品 的规格模板，生成规格信息。
                //E3.changeItemParam(node, "itemAddForm");
            }});
            initProdType("editProdType");
            initSaleWay("editSaleWay");
    });

    //竞拍商品属性
    function auctionProdDisplay() {
        if($("#editSaleWay").val() == "AUCTI") {
            document.getElementById('edit_tr_auction_time_limit').removeAttribute("hidden");
            document.getElementById('edit_tr_add_price').removeAttribute("hidden");
            document.getElementById('edit_td_name_price').innerText = "起拍价格:";
            document.getElementById('edit_td_name_quantity').innerText = "拍卖数量:";
        } else {
            document.getElementById('edit_tr_auction_time_limit').setAttribute("hidden", "hidden");
            document.getElementById('edit_tr_add_price').setAttribute("hidden", "hidden");
            document.getElementById('edit_td_name_price').innerText = "商品价格:";
            document.getElementById('edit_td_name_quantity').innerText = "库存数量:";
        }
    }

    function submitEditForm(){
        editProdEditor.sync();

        var editProdNumber = $("#editProdNumber").val();
        var editName = $("#editName").val();
        var editImageFile = $("#editImageFile")[0].files[0];
        var editDescription = $("#editDescription").val();
        var editPrice = $("#editPrice").val();
        var editQuantity = $("#editQuantity").val();
        var editProdType = $("#editProdType").val();
        var editSaleWay = $("#editSaleWay").val();
        var editAuctionTimeLimit = $("#editAuctionTimeLimit").val();
        var editAddPrice = $("#editAddPrice").val();

        var formData = new FormData();
        formData.append("number", editProdNumber);
        formData.append("name", editName);
        formData.append("typeCode", editProdType);
        formData.append("imageFile", editImageFile);
        formData.append("description", editDescription);
        formData.append("quantity", editQuantity);
        formData.append("price", editPrice);
        formData.append("saleWayCode", editSaleWay);
        formData.append("auctionTimeLimit", editAuctionTimeLimit);
        formData.append("addPrice", editAddPrice);
        $.ajax({
            type : "post",
            url : "/businessPlatform/data/manage/product/updateProduct",
            catch: false,
            processData: false,
            contentType: false,
            dataType: "json",
            data : formData,
            success: function(data) {
                if (data.code == 1010) {
                    $.messager.alert('提示','修改商品成功!');
                } else {
                    $.messager.alert('提示','修改商品失败!');
                }
            },
            error: function () {
                $.messager.alert('提示','请求失败!');
            }
        });
    }
</script>

