//用户管理页面 start
function getSelectionsNumbers(){
    var userList = $("#userList");
    var sels = userList.datagrid("getSelections");
    var userIds = [];
    for(var i in sels){
        userIds.push(sels[i].userId);
    }
    userIds = userIds.join(",");
    return userIds;
}

var toolbar = [{
    text:'新增',
    iconCls:'icon-add',
    handler:function(){
        $(".tree-title:contains('新增账号')").parent().click();
    }
},{
    text:'编辑',
    iconCls:'icon-edit',
    handler:function(){
        var userIds = getSelectionsNumbers();
        if(userIds.length == 0){
            $.messager.alert('提示','必须选择一个用户才能编辑!');
            return ;
        }
        if(userIds.indexOf(',') > 0){
            $.messager.alert('提示','只能选择一个用户!');
            return ;
        }

        $("#userEditWindow").window({
            onLoad :function(){
                //回显数据
                var params = {"userId" : userIds};
                $.post("/businessPlatformManage/data/manage/user/selectUser",params, function(result){
                    if (result.code == 1000) {
                        var data = result.data;
                        $("#editUserId").val(data.userId);
                        $("#editUsername").val(data.username);
                        $("#editPassword").val(data.password);
                        $("#editPower").val(data.power);
                        $("#editScore").val(data.score);
                        $("#editBalance").val(data.balance);
                        $("#editAddress").val(data.address);
                    }
                });
            }
        }).window("open");
    }
},{
    text:'删除',
    iconCls:'icon-cancel',
    handler:function(){
        var userIds = getSelectionsNumbers();
        if(userIds.length == 0){
            $.messager.alert('提示','未选中用户!');
            return ;
        }
        $.messager.confirm('确认','确定删除ID为 '+userIds+' 的用户吗？',function(r){
            if (r){
                var params = {"userIds":userIds};
                $.post("/businessPlatformManage/data/manage/user/deleteUser",params, function(data){
                    if(data.code == 1013){
                        $.messager.alert('提示','删除用户成功!',undefined,function(){
                            $("#userList").datagrid("reload");
                        });
                    } else {
                        $.messager.alert('提示', data.message,undefined,function(){
                            $("#userList").datagrid("reload");
                        });
                    }
                });
            }
        });
    }
}];
//用户管理页面 end

function clearForm(){
    $('#releaseProdForm').form('reset');
    releaseProdEditor.html('');
}


