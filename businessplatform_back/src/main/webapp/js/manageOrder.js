
//订单管理页面 start
function getSelectionsNumbers(){
    var orderList = $("#orderList");
    var sels = orderList.datagrid("getSelections");
    var orderNumbers = [];
    for(var i in sels){
        orderNumbers.push(sels[i].orderNumber);
    }
    orderNumbers = orderNumbers.join(",");
    return orderNumbers;
}

var toolbar = [{
    text:'改价',
    iconCls:'icon-edit',
    handler:function(){
        var orderNumber = getSelectionsNumbers();
        if(orderNumber.length == 0){
            $.messager.alert('提示','必须选择一个商品才能改价!');
            return ;
        }
        if(orderNumber.indexOf(',') > 0){
            $.messager.alert('提示','只能选择一个商品!');
            return ;
        }

        $("#orderPriceEditWindow").window({
            onLoad :function(){
                //回显数据
                var params = {"orderNumber" : orderNumber};
                $.post("/businessPlatform/data/manage/order/selectOrder", params, function(result){
                    if (result.code == 1000) {
                        var data = result.data;
                        $("#editOrderNumber").val(data.order.orderNumber);
                        $("#editOldPrice").val(data.order.price);
                    }
                });
            }
        }).window("open");
    }
}];
//订单管理页面 end
