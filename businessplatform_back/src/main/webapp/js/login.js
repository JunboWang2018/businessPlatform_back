var LOGIN = {
    checkInput:function() {
        $("#sfbestNameErr").attr("class", "").html("").prev().attr("class", "border");
        $("#sfbestPwdErr").attr("class", "").html("").prev().attr("class", "border");

        if(!$("#formlogin #loginname").val()) {
            $("#sfbestNameErr").attr("class", "error").show().html("请输入用户名").prev().attr("class", "border-error");
            return false;
        }
        if(!$("#formlogin input[name='password']").val()) {
            $("#sfbestPwdErr").attr("class", "error").show().html("请输入密码").prev().attr("class", "border-error");
            return false;
        }
        $("#username").val($("#loginname").val());
        return true;
    },
    doLogin:function() {
        $.post("/businessPlatform/data/user/login", $("#formlogin").serialize(),function(data){
            console.log("登陆成功");
            if (data.code == 1001) {
                jAlert('登录成功!',"提示", function(){
                    location.href = "/businessPlatform/";
                });

            } else {
                jAlert("登录失败，原因是：" + data.message, "失败");
            }
        });
    },
    login:function() {
        if (this.checkInput()) {
            this.doLogin();
        }
    }

};
$(function(){
    $("#login_sub").click(function(){
        LOGIN.login();
    });
});