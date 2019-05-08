<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="mainNav">
  <div class="navmenu">
    <div class="categories" id="public_cate">
      <div class="dt"><a name="sfbest_Uhead_Uhead_menu_all" class="trackref topall" href="http://www.e3mall.cn/html/web/categorys.html">精选商品分类</a></div>
      <div id="allSort" class="dd">
    <div id="booksort">
</div>

<!----百度统计代码开始------->

<!----百度统计代码结束-------></div>
    </div>
    <!----menufloat------->
    <div class="menu1">
		<ul>
			<li><a name="sfbest_Uhead_Uhead_nav_nav1" class="trackref" href="/businessPlatform/">首页</a></li>
			<c:forEach items="${prodTypeList}" var="prodType">
				<li id="${prodType.code}">
					<a name="sfbest_hp_hp_nav_nav3" class="trackref" href="/businessPlatform/data/product/selectProdList?typeCode=${prodType.code}">${prodType.name}</a>
				</li>
			</c:forEach>
     	 </ul>
    </div>
    <span class="clear"></span>
  </div>
</div>