<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="topMenu">
  <div class="pW">
    
    <ul class="fr topTh">
        <c:if test="${empty sessionScope.user}">
            <li class="login" id="login">
                <span id="loginbar" style="margin-right: 15px;">
                    <a href="/businessPlatform/view/user/toLogin">请登录</a>
                </span>
                <a href="/businessPlatform/view/user/toRegister">免费注册</a>
            </li>
        </c:if>
        <c:if test="${!empty sessionScope.user}">
            <li>你好，${sessionScope.user.username}</li>
            <li class="login"><a href="/businessPlatform/data/user/logout">退出</a></li>
            <li class="myOrder"><a name="sfbest_hp_hp_head_OrderList" class="trackref" href="/businessPlatform/data/order/selectOrderInfoQoList" rel="nofollow">我的订单</a></li>
        </c:if>

      <!--<li id='qiyeLogin'><a href='http://www.sfme.me/login.jhtml' target='_blank' rel='nofollow'>员工福利</a></li>-->

      <li class="d3 tShow"><a name="sfbest_hp_hp_head_weibo" class="trackref" title="关注XX商城微博" href="http://weibo.com/sfbest" rel="nofollow" target="_blank"><q></q></a></li>
      <li class="d4 tShow"><q></q><!--微信-->
         <div class="dd">
         <div class="sf_wx_t">关注XX商城微信</div>
         <div class="sf_wx"></div>
        </div>
        <div class="corner">
          <div class="aBg"></div>
          <div class="aCt"></div>
        </div>
       </li>
    </ul>
    <span class="clear"></span>
  </div>
 <%-- <script type="text/javascript" src="/businessPlatform/js/e3mall.js"></script>--%>
  <script type="text/javascript" src="/businessPlatform/js/lib/jquery.cookie.js"></script>
</div>