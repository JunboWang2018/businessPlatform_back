/*Author: 	zhaoyinfan  Date:2014-05-05 version 2.0*/
var REGISTER={
    inputcheck:function(){
        var flag = true;
        //不能为空检查
        if ($("#username").val() == "") {
            showError("username","userMamErr",defaultArr[8]);
            flag = false;
        }
        if ($("#password").val() == "") {
            showError("password","passwordErr",pwdArr[0]);
            flag = false;
        }
        //密码检查
        if ($("#password").val() != $("#pwdRepeat").val()) {
            showError("pwdRepeat","password2Err",pwd2Arr[1]);
            flag = false;
        }
        return flag;
    },
    doSubmit:function() {
        $.post("/businessPlatform/data/user/register", $("#regForm_mod").serialize(), function(data){
            if(data.code == 1002){
                jAlert('用户注册成功!',"提示", function(){
                    location.href = "/businessPlatform/";
                });
            } else {
                jAlert("注册失败，原因是：" + data.message, "失败");
            }
        });
    },
    login:function() {
        location.href = "/businessPlatform/";
        return false;
    },
    reg:function() {
        if (this.inputcheck()) {
            this.doSubmit();
        }
    }
};

var comMsg=function(msg){
    return GetLen(msg)>=38? "<font class='f_1'>"+msg+"</font>":"<font class='f_0'>"+msg+"</font>";
}
//show error massage
var showError =function(inputid,errid,msg){
    $("#"+inputid).parent().attr("class","").addClass("regM errorBorder");
    $("#"+inputid).next().attr("class","");
    $("#"+errid).attr("class","reg_error").html(comMsg(msg));
}
//show success massage
var showSuccess = function(inputid,errid,msg){
    $("#"+inputid).parent().attr("class","").addClass("regM defaultBorder");
    $("#"+inputid).next().attr("class","regOk");
    $("#"+errid).attr("class","").html("");
}
var showRestore =function(inputid,errid){
    $("#"+inputid).parent().attr("class","").addClass("regM defaultBorder");
    $("#"+inputid).next().attr("class","");
    $("#"+errid).attr("class","").html('');
}

var showtip =function(inputid,spanid,key){
    var ms = defaultArr[key];
    $("#"+inputid).parent().attr("class","").addClass("regM okBorder");
    $("#"+inputid).next().attr("class","");
    $("#"+spanid).attr("class","reg_error1").html(comMsg(ms));
    return ;
}
var showPwdtip =function(inputid,spanid,key){
    var ms = defaultArr[key];
    $("#"+inputid).parent().attr("class","").addClass("regM okBorder");
    $("#"+inputid).next().attr("class","");
    $("#"+spanid).attr("class","reg_error2").html(comMsg(ms));
    return ;
}

//密码强度显示
var showStrong=function(errid,rankid,rank){
    var def ="<font style='font-size:12px;'>安全程度：</font>";
    var midstr='<em class="default">弱</em><em class="default">中</em><em class="default">强</em>';
    if(parseInt(rank)==1){
        midstr ='<em class="ok">弱</em><em class="default">中</em><em class="default">强</em>';
        $("#"+rankid).html(def+midstr);
        $("#"+errid).attr("class","reg_error2").html(pwdArr[2]);
        return;
    }
    if(parseInt(rank)==2){
        midstr ='<em class="ok">弱</em><em class="ok">中</em><em class="default">强</em>';
    }
    if(parseInt(rank)==3){
        midstr ='<em class="ok">弱</em><em class="ok">中</em><em class="ok">强</em>';
    }
    $("#"+rankid).html(def+midstr);
}

//check password
var ckpwd=function(isCk,keyup){
    var val =$.trim( $("#"+PwdId).val() );
    if(val.length<6&&keyup==1){
        showStrong(PwdErrId,pwdStrong,0);
        showRestore(PwdId,PwdErrId);
        return false;
    }
    if(!isCk && !val){
        showRestore(PwdId,PwdErrId);
        return false;
    }
    if(val == ""){
        showError(PwdId,PwdErrId,pwdArr[0]);
        return false;
    }else if( pwdTotal(val) ==0){
        showError(PwdId,PwdErrId,pwdArr[3]);
        return false;
    }else if( pwdTotal(val) ==-1){
        showError(PwdId,PwdErrId,pwdArr[3]);
        return false;
    }else if( pwdTotal(val) <=50){
        $("#"+PwdId).parent().attr("class","").addClass("regM defaultBorder");
        $("#"+PwdId).next().attr("class","regOk");
        showStrong(PwdErrId,pwdStrong,1);
        return 1;
    }else if( pwdTotal(val)>51&&pwdTotal(val) <=60){
        showStrong(PwdErrId,pwdStrong,2);
        showSuccess(PwdId,PwdErrId,OkArr[0]);
        return 1;
    }else if( pwdTotal(val)>60){
        showStrong(PwdErrId,pwdStrong,3);
        showSuccess(PwdId,PwdErrId,OkArr[0]);
        return 1;
    }
    return false;
}
// check password 2
var ckpwd2=function(isCk){
    var val =$.trim( $("#"+PwdId).val() );
    var val2 =$.trim( $("#"+PwdId2).val() );
    if(!isCk && !val2){
        showRestore(PwdId2,PwdErrId2);
        return false;
    }
    if(val2 == ''){
        showError(PwdId2,PwdErrId2,pwd2Arr[0]);
        return false;
    }else if(val2 != val){
        showError(PwdId2,PwdErrId2,pwd2Arr[1]);
        return false;
    }
    showSuccess(PwdId2,PwdErrId2,OkArr[0]);
    return 1;
}



