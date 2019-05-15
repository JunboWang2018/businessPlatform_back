//发布和编辑商品的展示图片预览
function previewImage(inputObject, divPreviewId){
    var reader = new FileReader();
    reader.readAsDataURL(inputObject.files[0]);
    reader.onload = function() {
        document.getElementById(divPreviewId).src = reader.result;
    }
}

//商品发布页面 start

// 初始化选择类目组件
function initProdType(id){
    $.ajax({
        type : "get",
        url : "/businessPlatformManage/data/prodType/selectProdTypeList",
        dataType : "json",
        success : function (result) {
            if (result.code == 1000) {
                var resultHTML = initProdTypeDropDownHTML(result.data);
                document.getElementById(id).innerHTML = resultHTML;
            } else {
                alert(result.message);
            }
        },
        error : function () {
            alert("获取商品类别失败");
        }
    });
}
// 初始化选择出售形式组件
function initSaleWay(id){
    $.ajax({
        type : "get",
        url : "/businessPlatformManage/data/saleWay/selectSaleWayList",
        dataType : "json",
        success : function (result) {
            if (result.code == 1000) {
                var resultHTML = initSaleWayDropDownHTML(result.data);
                document.getElementById(id).innerHTML = resultHTML;
            } else {
                alert(result.message);
            }
        },
        error : function () {
            alert("获取出售形式失败");
        }
    });
}

function clearForm(){
    $('#releaseProdForm').form('reset');
    releaseProdEditor.html('');
}

/*商品类别下拉框HTML*/
function initProdTypeDropDownHTML(data) {
    var resultHTML = "<option>请选择商品类别</option>";
    for (var i = 0; i < data.length; i++) {
        resultHTML += "<option value=" + data[i].code + ">" + data[i].name + "</option>";
    }
    return resultHTML;
}
/*出售形式下拉框HTML*/
function initSaleWayDropDownHTML(data) {
    var resultHTML = "<option>请选择出售形式</option>";
    for (var i = 0; i < data.length; i++) {
        resultHTML += "<option value=" + data[i].code + ">" + data[i].name + "</option>";
    }
    return resultHTML;
}

//竞拍商品属性
function auctionProdDisplay() {
    if($("#sale_way").val() == "AUCTI") {
        document.getElementById('tr_auction_time_limit').removeAttribute("hidden");
        document.getElementById('tr_add_price').removeAttribute("hidden");
        document.getElementById('td_name_price').innerText = "起拍价格:";
        document.getElementById('td_name_quantity').innerText = "拍卖数量:";
    } else {
        document.getElementById('tr_auction_time_limit').setAttribute("hidden", "hidden");
        document.getElementById('tr_add_price').setAttribute("hidden", "hidden");
        document.getElementById('td_name_price').innerText = "商品价格:";
        document.getElementById('td_name_quantity').innerText = "库存数量:";
    }
}

//商品发布页面 end

//商品管理页面 start
function getSelectionsNumbers(){
    var productList = $("#productList");
    var sels = productList.datagrid("getSelections");
    var prodNumbers = [];
    for(var i in sels){
        prodNumbers.push(sels[i].prodNumber);
    }
    prodNumbers = prodNumbers.join(",");
    return prodNumbers;
}

var toolbar = [{
    text:'新增',
    iconCls:'icon-add',
    handler:function(){
        $(".tree-title:contains('新增商品')").parent().click();
    }
},{
    text:'编辑',
    iconCls:'icon-edit',
    handler:function(){
        var prodNumbers = getSelectionsNumbers();
        if(prodNumbers.length == 0){
            $.messager.alert('提示','必须选择一个商品才能编辑!');
            return ;
        }
        if(prodNumbers.indexOf(',') > 0){
            $.messager.alert('提示','只能选择一个商品!');
            return ;
        }

        $("#productEditWindow").window({
            onLoad :function(){
                //回显数据
                var params = {"prodNumber" : prodNumbers};
                $.post("/businessPlatformManage/data/manage/product/selectProduct",params, function(result){
                    if (result.code == 1000) {
                        var data = result.data;
                        $("#editProdNumber").val(data.product.number);
                        $("#editName").val(data.product.name);
                        $("#editImage").attr("src", "/businessPlatformManage/prodImage/" + data.product.imageMain);
                        editProdEditor.html(data.product.description);
                        $("#editPrice").val(data.product.price);
                        $("#editQuantity").val(data.product.quantity);
                        $("#editProdType").val(data.product.typeCode);
                        $("#editSaleWay").val(data.product.saleWayCode);
                        if (data.product.saleWayCode == "AUCTI") {
                            $("#editAuctionTimeLimit").val(data.auctionInfo.deadline);
                            $("#editAddPrice").val(data.auctionInfo.addPrice);
                            document.getElementById('edit_tr_auction_time_limit').removeAttribute("hidden");
                            document.getElementById('edit_tr_add_price').removeAttribute("hidden");
                            document.getElementById('edit_td_name_price').innerText = "起拍价格:";
                            document.getElementById('edit_td_name_quantity').innerText = "拍卖数量:";
                        }
                    }
                });
            }
        }).window("open");
    }
},{
    text:'删除',
    iconCls:'icon-remove',
    handler:function(){
        var prodNumbers = getSelectionsNumbers();
        if(prodNumbers.length == 0){
            $.messager.alert('提示','未选中商品!');
            return ;
        }
        $.messager.confirm('确认','确定删除ID为 '+prodNumbers+' 的商品吗？',function(r){
            if (r){
                var params = {"prodNumbers":prodNumbers};
                $.post("/businessPlatformManage/data/manage/product/deleteProduct",params, function(data){
                    if(data.code == 1008){
                        $.messager.alert('提示','删除商品成功!',undefined,function(){
                            $("#productList").datagrid("reload");
                        });
                    } else {
                        $.messager.alert('提示','删除商品失败!',undefined,function(){
                            $("#productList").datagrid("reload");
                        });
                    }
                });
            }
        });
    }
},'-',{
    text:'下架',
    iconCls:'icon-undo',
    handler:function(){
        var prodNumbers = getSelectionsNumbers();
        if(prodNumbers.length == 0){
            $.messager.alert('提示','未选中商品!');
            return ;
        }
        $.messager.confirm('确认','确定下架ID为 '+prodNumbers+' 的商品吗？',function(r){
            if (r){
                var params = {"prodNumbers":prodNumbers, "sellStatus":-1};
                $.post("/businessPlatformManage/data/manage/product/takeDownOrUpProd",params, function(data){
                    if(data.code == 1009){
                        $.messager.alert('提示','下架商品成功!',undefined,function(){
                            $("#productList").datagrid("reload");
                        });
                    } else {
                        $.messager.alert('提示','下架商品失败!',undefined,function(){
                            $("#productList").datagrid("reload");
                        });
                    }
                });
            }
        });
    }
},{
    text:'上架',
    iconCls:'icon-redo',
    handler:function(){
        var prodNumbers = getSelectionsNumbers();
        if(prodNumbers.length == 0){
            $.messager.alert('提示','未选中商品!');
            return ;
        }
        $.messager.confirm('确认','确定上架ID为 '+prodNumbers+' 的商品吗？',function(r){
            if (r){
                var params = {"prodNumbers":prodNumbers, "sellStatus":1};
                $.post("/businessPlatformManage/data/manage/product/takeDownOrUpProd",params, function(data){
                    if(data.code == 1009){
                        $.messager.alert('提示','上架商品成功!',undefined,function(){
                            $("#productList").datagrid("reload");
                        });
                    } else {
                        $.messager.alert('提示','上架商品失败!',undefined,function(){
                            $("#productList").datagrid("reload");
                        });
                    }
                });
            }
        });
    }
},'-',{
    text:'查看竞拍记录',
    iconCls:'icon-search',
    handler:function(){
        $("#auctionRecordWindow").window({
            onLoad :function(){

            }
        }).window("open");

        var prodNumbers = getSelectionsNumbers();
        if(prodNumbers.length == 0){
            $.messager.alert('提示','未选中商品!');
            return ;
        }
        var param = {"prodNumber":prodNumbers};
        $.post("/businessPlatformManage/data/manage/order/selectAuctionRecord",param, function(result){
            if(result.code == 1000){
                $("#auctionRecordBody").html(initAuctionRecordTableHTML(result.data));
            }
        });
    }
}];

function initAuctionRecordTableHTML(data) {
    var strHtml = ""
    for (var i = 0; i < data.length; i++) {
        strHtml += "<tr><td align='center'>" + data[i].auctionRecordId + "</td><td align='center'>" + data[i].price + "</td>" +
            "<td align='center'>" + data[i].username + "</td><td align='center'>" + data[i].createTime + "</td></tr>"
    }
    return strHtml;
}
//商品管理页面 end
