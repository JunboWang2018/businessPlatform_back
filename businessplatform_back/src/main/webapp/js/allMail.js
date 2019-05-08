/*
	Author zhaoyinfan
	2013-08-09
	注册公用js库
*/
var timeout = 120;
var ctDown = function (id) {
	--timeout;
	if(timeout > 0) {
		$("#"+id).attr("value",timeout+"秒后重新获取").removeClass("gvCode").addClass("gvCodeNo").attr("disabled", true);
		setTimeout("ctDown('"+id+"');", 1000);
	} else{
		$("#"+id).attr("value","点击获取验证码").attr("disabled", false).removeClass("gvCodeNo").addClass("gvCode").next().html('');
		timeout = 120;
	}
}
var timeoutajax = 120;
var ctAjaxDown = function (id) {
	--timeoutajax;
	if(timeoutajax > 0) {
		$("#"+id).attr("value",timeoutajax+"秒后重新获取").removeClass("reg_gvCode").addClass("reg_gvCodeNo").attr("disabled", true);
		setTimeout("ctAjaxDown('"+id+"');", 1000);
	} else{
		$("#"+id).attr("value","点击获取验证码").attr("disabled", false).removeClass("reg_gvCodeNo").addClass("reg_gvCode").next().html('');
		timeoutajax = 120;
	}
}
//check telphone
var tel_preg =function(tel){
	var preg=/^0[1-9][0-9]{1,2}-[0-9]{7,8}$/ ;
	var string = $.trim(tel);
	if(preg.test(string)){
		return 1;
	}
	return false;
}
//check mobile
var mobile_preg = function(mobile){
	var mob_preg = /^1[3|4|5|7|8][0-9]{9}$/;
	var string = $.trim(mobile);
	if(mob_preg.test(string)){
		return 1;
	}
	return false;
}
//check mail
var mail_preg = function(mail){
	if(mail.length>80){
		return false;
	}
	
	var ma_preg = /^\w+([-+.\']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
	var string = $.trim(mail);
	if(ma_preg.test(string)){
		return 1;
	}
	return false;
}
//去掉中间的空格
function clsTrim(str){
	return str.replace(/\s/g,"");
}
var url_preg =function(url){
	if( url.length>70){
		return false;
	}
	var u_preg = /^[a-zA-z]+:\/\/[^\s]*$/;
	var string =$.trim(url); 
	if(u_preg.test(string)){
		return 1;
	}
	return false;
}
var GetLen = function(str) {
    var realLength = 0, len = str.length, charCode = -1;
    for (var i = 0; i < len; i++) {
        charCode = str.charCodeAt(i);
        if (charCode >= 0 && charCode <= 128) realLength += 1;
        else realLength += 2;
    }
    return realLength;
};
var lenpoints = function(pwd) {
	if (pwd.length <6||pwd.length >20) {
		return 0;
	};
	if (pwd.length >= 6 && pwd.length <= 7) {
		return 10;
	};
	if (pwd.length >= 8) {
		return 25;
	};
	return 0;
};
var pwdTotal = function(pwd) {
	if (!pwd || pwd == 'undefined') {
		return - 1;
	};
	if(lenpoints(pwd)==0){
		return 0;
	}
	var digit01 = /^[0-9]+$/;
	var digit10 = /[0-9]+/;
	var digit02 = /^[a-z]+$/;
	var digit20 = /[a-z]+/;
	var digit03 = /^[A-Z]+$/;
	var digit30 = /[A-Z]+/;
	var digitStr = /[a-zA-Z]/;
	var digitOther = /[_]+/;
	var safeStr =/^[0-9a-zA-z_]+$/;
	var totalPoints =0;
	if(!safeStr.test(pwd)){
		return -1;
	}

	if (digit20.test(pwd) && digit30.test(pwd)) {
		totalPoints += 20;
	};
	var pwd_num = 0;
	var t_num = 0;
	var pwd_mi=0;
	var pwd_max=0;
	for (var i = 0; i <= pwd.length; i++) {
		if (digit01.test(pwd.substr(i, 1))) {
			pwd_num++;
		}
		if (digitOther.test(pwd.substr(i, 1))) {
			t_num++;
		}
		if (digit02.test(pwd.substr(i, 1))) {
			pwd_mi ++;
		}
		if (digit03.test(pwd.substr(i, 1))) {
			pwd_max ++;
		}
	};
	if(pwd_mi&&!pwd_max){
		totalPoints += 10;
	}
	if(!pwd_mi&&pwd_max){
		totalPoints += 10;
	}
	if (pwd_num >= 1 && pwd_num < 3) {
		totalPoints += 10;
	};
	if (pwd_num >= 3) {
		totalPoints += 20;
	};
	if (t_num == 1) {
		totalPoints += 10;
	};
	if (t_num > 1) {
		totalPoints += 25;
	};
	if (digit20.test(pwd) && digit30.test(pwd) && digit10.test(pwd) && digitOther.test(pwd)) {
		totalPoints+=lenpoints(pwd);
		return totalPoints += 20;
	}
	if (digitStr.test(pwd) && digit10.test(pwd) && digitOther.test(pwd)) {
		totalPoints+=lenpoints(pwd);
		return totalPoints += 3;
	};
	if (digitStr.test(pwd) && digit10.test(pwd)) {
		totalPoints+=lenpoints(pwd);
		return totalPoints += 2;
	};
	if(totalPoints==0){
		return -1;
	}
	totalPoints+=lenpoints(pwd);
	return totalPoints;
}
/*-----------------个人注册数据------------------*/
var MailMId 	= "userMam";
var MailMErrId= "userMamErr";
var PwdId 		= "password";
var PwdErrId	=	"passwordErr";
var pwdStrong	=	"pwdStrong";
var PwdId2		= "password2";
var PwdErrId2	= "password2Err";
var pageCodeId 		="auth_code";
var pageCodeErrId	="auth_codeErr";
var codeimgid			= "code_img1";
var MobileCodeId	="sms_code";
var MobileCodeErrId="sms_codeErr";
var sfCodeId 			="dm_number";
var sfCodeErrId 		="dm_numberErr";
var AgreementId 	="AgreeId";
var AgreementErrId="AgreeIdErr";
var defaultArr =[],OkArr=[],memArr =[],pwdArr=[],pwd2Arr=[],mcodeArr = [],codeArr=[],dmArr=[],agreeArr=[];
OkArr[0]= '通过信息验证！';
memArr[0]	= '请输入您的手机号码';
memArr[1]	= '请输入正确的手机号码';
memArr[2]	= '请输入正确的邮箱地址';
pwdArr[0]	= '请输入登录密码';
pwdArr[1]	='密码只能为6-20位字母数字下划线组合';
pwdArr[2]	='密码太简单，建议使用数字、字母、下划线组合';
pwdArr[3]	='密码只能为6-20位字母数字下划线组合';
pwd2Arr[0]	= '请再次输入密码';
pwd2Arr[1]	='两次输入不一致，请重新输入';
mcodeArr[0]='请输入短信验证码';
mcodeArr[1]='短信验证码不正确';
codeArr[0]	= '请输入验证码';
codeArr[1]	='网站验证码不正确';
dmArr[0]		='邀请码错误';
agreeArr[0]	='请阅读并同意注册协议';
defaultArr[1]	='请输入您的手机号码';
defaultArr[2]	='6-20位字符,可使用字母、数字、下划线。不建议使用纯数字或字母组合。';
defaultArr[3]	='请再次输入密码';
defaultArr[4]	='请输入短信验证码';
defaultArr[5]	='请输入验证码';
defaultArr[6]	='请输入您的优选单邀请码';
defaultArr[7]	='';
defaultArr[8]	='请输入您的用户名。可使用字母、数字、下划线。';
defaultArr[9]	='此手机号已经被注册！';
defaultArr[10]	='此手用户名已经被注册！请重新输入。';