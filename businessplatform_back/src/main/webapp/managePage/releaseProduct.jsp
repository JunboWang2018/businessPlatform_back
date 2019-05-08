<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="/businessPlatform/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/businessPlatform/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/businessPlatform/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<script type="text/javascript" charset="utf-8" src="/businessPlatform/js/manageProd.js"></script>
<div style="padding:10px 10px 10px 10px">
	<form id="releaseProdForm" class="itemForm" method="post">
	    <table cellpadding="5">
	        <tr>
	            <td>商品类目:</td>
	            <td>
					<select id="product_type" >

					</select>
	            </td>
	        </tr>
			<tr>
				<td>出售形式:</td>
				<td>
					<select id="sale_way" onchange="auctionProdDisplay()" >

					</select>
				</td>
			</tr>
			<tr id="tr_auction_time_limit" hidden="hidden">
				<td>竞拍期限:</td>
				<td>
					<input class="easyui-textbox" type="text" id="auction_time_limit" data-options="required:true" style="width: 280px;" placeholder="请输入整数，以秒为单位"/>
				</td>
			</tr>
			<tr id="tr_add_price" hidden="hidden">
				<td>加价幅度:</td>
				<td>
					<input class="easyui-textbox" type="text" id="add_price" data-options="required:true" style="width: 280px;" placeholder="最小1元"/>
				</td>
			</tr>
	        <tr>
	            <td>商品标题:</td>
	            <td><input class="easyui-textbox" type="text" id="name" data-options="required:true" style="width: 280px;"></input></td>
	        </tr>
			<tr>
				<td>展示图片:</td>
				<td>
					<input id="imageFile" type="file" class="form-control" onchange="previewImage(this, 'image');">
				</td>
			</tr>
			<tr>
				<td>图片预览:</td>
				<td>
					<img id="image" style="width: 200px; height: 200px;">
				</td>
			</tr>
			<tr>
				<td>商品描述:</td>
				<td>
					<textarea style="width:800px;height:300px;visibility:hidden;" id="description"></textarea>
				</td>
			</tr>
	        <tr>
	            <td id="td_name_price">商品价格:</td>
	            <td><input class="easyui-numberbox" type="text" id="price" data-options="min:1,max:99999999,precision:2,required:true" />
	            	<input type="hidden" name="price"/>
	            </td>
	        </tr>
	        <tr>
	            <td id="td_name_quantity">库存数量:</td>
	            <td><input class="easyui-numberbox" type="text" id="quantity" data-options="min:1,max:99999999,precision:0,required:true" /></td>
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
    var releaseProdEditor ;
    //页面初始化完毕后执行此方法
    $(function(){
        //创建富文本编辑器
        releaseProdEditor = E3.createEditor("#releaseProdForm [id=description]");
        //初始化类目选择和图片上传器
        E3.init({fun:function(node){
                //根据商品的分类id取商品 的规格模板，生成规格信息。
                //E3.changeItemParam(node, "itemAddForm");
            }});
        initProdType("product_type");
        initSaleWay("sale_way");
    });
    //提交商品发布表单
    function submitForm(){
        //同步文本框中的商品描述
        releaseProdEditor.sync();
        //ajax的post方式提交表单
        //$("#itemAddForm").serialize()将表单序列号为key-value形式的字符串
        var typeCode = $("#product_type").val();
        var saleWayCode = $("#sale_way").val();
        var imageFile = $("#imageFile")[0].files[0];
        var name = $("#name").val();
        var description = $("#description").val();
        var price = $("#price").val();
        var quantity = $("#quantity").val();
        var auctionTimeLimit = $("#auction_time_limit").val();
        var addPrice = $("#add_price").val();

        var formData = new FormData();
        formData.append("name", name);
        formData.append("typeCode", typeCode);
        formData.append("imageFile", imageFile);
        formData.append("description", description);
        formData.append("quantity", quantity);
        formData.append("price", price);
        formData.append("saleWayCode", saleWayCode);
        formData.append("auctionTimeLimit", auctionTimeLimit);
        formData.append("addPrice", addPrice);
        $.ajax({
            type : "post",
            url : "/businessPlatform/data/manage/product/releaseProduct",
            catch: false,
            processData: false,
            contentType: false,
            dataType: "json",
            data : formData,
            success: function(data) {
                if (data.code == 1003) {
                    $.messager.alert('提示','新增商品成功!');
                    clearForm();
                } else {
                    $.messager.alert('提示','新增商品失败!');
                }
            },
            error: function () {
                alert("请求失败");
            }
        });
    }
</script>
