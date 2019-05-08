var hostUrl = document.location.host;
var urlArr = hostUrl.split('.');
var domain = urlArr[1]+'.'+urlArr[2];
var SF_STATIC_URL = 'http://i.'+domain;
var SF_STATIC_URL_HTML = 'http://i.'+domain+'/html/';
var cartHostUrl = 'http://cart.'+domain;
var wwwHostUrl  = 'http://www.'+domain;

var sfAddCart ={
  pid:null,
  init:function(){
    var win_w = $(window).width();
    if (win_w <= 1400) {$(".side-wrap").addClass("side_pos");}
    else {$(".side-wrap").removeClass("side_pos");}
    $(window).resize(function() {
      var win_width = $(window).width();
      if (win_width <= 1400) {$(".side-wrap").addClass("side_pos");}
      else {$(".side-wrap").removeClass("side_pos");}
    });
    sfAddCart.cartHover();
    $(".p-btn a,.rankBtn a,.rushBuy").live("click",function(){
      $(".listpic-mini").html("");
      This = $(this);
      //是否是首页商品添加购物车标识
      var indexFlag = This.attr("indexFlag");
	  //是否是生鲜频道页添加购物车标识
	  var freshFlag = This.attr("freshFlag");
      sfAddCart.pid = This.attr("pid");
      if(typeof(indexFlag) == "undefined"){
		if(typeof(freshFlag) == "undefined"){
			 //其它页面商品添加购物车
			setTimeout(function(){cartAdd(sfAddCart.pid, 0, 1, 5);},100);
		}else{
			//生鲜频道页商品添加购物车
			setTimeout(function(){cartAdd(sfAddCart.pid, 0, 1, 13,1,This);},100);
		}
       
      }else{
        //首页页商品添加购物车
        setTimeout(function(){cartAdd(sfAddCart.pid, 0, 1, 7,1,This);},100);
      }	
    });
  },
  cartHover:function(){
    $("#side_cart").bind("mouseenter",function(){
      clearTimeout(sfAddCart.cartHover.timer1);
      sfAddCart.cartHover.timer1 = setTimeout(function(){sfAddCart.cartList("show","1")},500);
    }).bind("mouseleave",function(){
      clearTimeout(sfAddCart.cartHover.timer1);
      sfAddCart.cartHover.timer1 = setTimeout(function(){sfAddCart.cartList("hide","1")},500);
    });
    $(".cart-list").live("mouseenter",function(){
      clearTimeout(sfAddCart.cartHover.timer1);
      sfAddCart.cartHover.timer1 = setTimeout(function(){sfAddCart.cartList("show","1")},500);
    }).live("mouseleave",function(){
      clearTimeout(sfAddCart.cartHover.timer1);
      sfAddCart.cartHover.timer1 = setTimeout(function(){sfAddCart.cartList("hide","1")},500);
    });
    $("#side_guang").bind("mouseenter",function(){
      clearTimeout(sfAddCart.cartHover.timer2);
      sfAddCart.cartHover.timer2 = setTimeout(function(){sfAddCart.cartList("show","2")},500);
    }).bind("mouseleave",function(){
      clearTimeout(sfAddCart.cartHover.timer2);
      sfAddCart.cartHover.timer2 = setTimeout(function(){sfAddCart.cartList("hide","2")},500);
    });
    $(".his-list").live("mouseenter",function(){
      clearTimeout(sfAddCart.cartHover.timer2);
      sfAddCart.cartHover.timer2 = setTimeout(function(){sfAddCart.cartList("show","2")},500);
    }).live("mouseleave",function(){
      clearTimeout(sfAddCart.cartHover.timer2);
      sfAddCart.cartHover.timer2 = setTimeout(function(){sfAddCart.cartList("hide","2")},500);
    });
    $("#side_app").bind("mouseenter",function(){
      clearTimeout(sfAddCart.cartHover.timer3);
      sfAddCart.cartHover.timer3 = setTimeout(function(){sfAddCart.cartList("show","3")},500);
    }).bind("mouseleave",function(){
      clearTimeout(sfAddCart.cartHover.timer3);
      sfAddCart.cartHover.timer3 = setTimeout(function(){sfAddCart.cartList("hide","3")},500);
    });
    $(".appDown").live("mouseenter",function(){
      clearTimeout(sfAddCart.cartHover.timer3);
      sfAddCart.cartHover.timer3 = setTimeout(function(){sfAddCart.cartList("show","3")},500);
    }).live("mouseleave",function(){
      clearTimeout(sfAddCart.cartHover.timer3);
      sfAddCart.cartHover.timer3 = setTimeout(function(){sfAddCart.cartList("hide","3")},500);
    });
  },
  cartList: function(type,i) {
    var right, time;
    if (type == "hide") {
      right = "-101%";
      time = 800
    } else {
      right = 0;
      time = 300;
      if ("1"==i){
        $(".cart-wrap").show()
      }else if("2"==i){
        $(".guang").show()
      }else if("3"==i){
        $(".appInfo").show()
      }
    }
    if ("1"==i){
      $(".cart-wrap").find(".cart-list").animate({
        "right": right
      }, time, function() {
        if (type == "hide") $(".cart-wrap").hide()
      })
    }else if ("2"==i){
      $(".guang").find(".his-list").animate({
        "right": right
      }, time, function() {
        if (type == "hide") $(".guang").hide();
      })
    }else if ("3"==i){
      $(".appInfo").find(".appItem").animate({
        "right": right
      }, time, function() {
        if (type == "hide") $(".appInfo").hide();
      })
    }
  },
  cartNumShow:function(){
    var addNum = $("#number_" + sfAddCart.pid).val();
    var tips_obj = $(".cart-list").find(".cart-num");
    $("#add-num").html(addNum);
    tips_obj.height(48).show();
    var addOne_timer = setTimeout(function() {
      tips_obj.animate({
        "height": 0
      }, 300, function() {
        tips_obj.hide();
        $("#side_cart").trigger("mouseleave");
      })
    }, 3E3);
  }	
};

/**
  获取每个页面头部的购物列表的方法
  */
function getCartList(){
  $.ajax({
    url  : cartHostUrl+'/cart/headerCart/',
  type : 'GET',
  dataType: "jsonp",  //返回json格式的数据   
  jsonp:"callback",
  data : {},
  success: function(msg){
    if(msg.error==1){
      $('#cartNum').html(msg.info.num);
      $('#cart_lists').html(msg.data);
      if(msg.info.num>0){
        $("#topCart").find("s").addClass("setCart");
      }
      if($('#showcart').length > 0){
        $('#showcart').html('购物车共计'+msg.info.num+'件商品，合计 '+msg.info.price+'元');
      }
      if($('#list_cart').length > 0){
        //$('#list_cart').html(msg.data);
        $('#list_cart').html(msg.data1);
        $('.s-cart-num').html(msg.info.num);
        var numList = $("li","#list_cart").length;
        0 !== numList && $('.s-cart-num').addClass("s-cart-add");
        switch(numList)
        {
          case 0:
            $(".cart-shopping").css("bottom","152px");
            $(".cart-wrap .cart-arr").css("bottom","10px");
            $('.s-cart-num').removeClass("s-cart-add");
            $('.s-cart-num').hide();
            break;
          case 1:
            $(".cart-shopping").css("bottom","50px");
            $(".cart-wrap .cart-arr").css("bottom","110px");
            $('.s-cart-num').show();
            break;
          default:
            $(".cart-shopping").css("bottom",0);
            $(".cart-wrap .cart-arr").css("bottom","160px");
            $('.s-cart-num').show();
        }
      }
    }else{
      jAlert(msg.info);
    }
  }
  });
}

//商品加入购物车
//@param prodNumber 商品编号
function cartAdd(prodNumber){
    //取购物车商品数量
	var num = $("#number_" + prodNumber).val();
	if (num == 0 || num == null) {
	  num = 1;
    }
    $.ajax({
        type : "get",
        url : "/businessPlatform/data/cart/addCart",
        dataType : "json",
        data : {
            "prodNumber" : prodNumber,
            "quantity" : num
        },
        success : function (result) {
            if (result.code == 1000) {
                window.location.href = getRootPath() + "/view/cart/toAddCartSuccess";
            } else if (result.code == 10) {
                window.location.href = getRootPath() + "/view/user/toLogin";
            } else {
                jAlert("添加购物车失败，原因是：" + result.message, "失败");
            }
        },
        error : function () {
            jAlert("请求失败", "失败");
        }
    });
}


//首页添加购物车
function cartIndex(prodNumber, num){
    $.ajax({
        type : "get",
        url : "/businessPlatform/data/cart/addCart",
        dataType : "json",
        data : {
            "prodNumber" : prodNumber,
            "quantity" : num
        },
        success : function (result) {
            if (result.code == 1000) {
                window.location.href = getRootPath() + "/view/cart/toAddCartSuccess";
            } else if (result.code == 10) {
                window.location.href = getRootPath() + "/view/user/toLogin";
            } else {
                jAlert("添加购物车失败，原因是：" + result.message, "失败");
            }
        },
        error : function () {
            jAlert("请求失败", "失败");
        }
    });
}

//carwindow遮罩
function car_ie6hack(){
  if ($.browser.msie && ($.browser.version == "6.0") && !$.support.style) {
    var iframehide='<iframe id="car_iframe" style="width:340px;height:50px;z-index:99;position:absolute;left:0;top:200px;"></iframe>';
    $(iframehide).appendTo("#add-cart-box-sf");
  }
}

//判断正整数
function checkRate(input)
{
  var re = /^[0-9]*[1-9][0-9]*$/;    
  if (!re.test(input))
  {
    return false;
  } else {
    return true;
  }
}

//首页关闭
function closeCart(obj)
{
  var This = $(obj);
  $('.gWindow').remove();
  $(".gBtn").hide();

}

//结算
function generOrder() {
    var arr = [];
    $("input:checked").each(function () {
        //把选中的数据添加到数组变量中
        arr.push($(this).val());
    });
    if (arr === undefined || arr.length == 0) {
        alert("未选中商品");
        return;
    }
    $.ajax({
        type : "post",
        url : "/businessPlatform/data/order/addOrder",
        dataType : "json",
        data : {
            "prodNumbers" : arr
        },
        success : function (result) {
            if (result.code == 1000) {
                window.location.href = getRootPath() + "/view/order/toAddOrderSuccess";
            }  else {
                jAlert("提交订单失败，原因是：" + result.message, "失败");
            }
        },
        error : function () {
            jAlert("请求失败", "失败");
        }
    });
}
