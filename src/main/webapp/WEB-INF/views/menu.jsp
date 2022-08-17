<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
	<meta charset="UTF-8">
	<link href="https://fonts.googleapis.com/css2?family=Josefin+Sans:wght@600&family=Lato:wght@400;700&family=Nanum+Gothic&display=swap" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/resources/css/optimizer.css" rel="stylesheet" />
	<script>
    function formSubmit(){
	    	document.getElementById("frm1").submit();
	    }
	</script>
</head>
<body>
	<div id="wleft">
		<div class="wleftin">
			<h1 class="xans-element- xans-layout xans-layout-logotop ">
				<a href="${pageContext.request.contextPath}/">HOOHOO</a>
			</h1>
			<div class="rog">
				<ul>
					<li class="xans-element- xans-layout xans-layout-statelogoff ">
					<security:authorize access="isAuthenticated()">
						<form action="${pageContext.request.contextPath}/member/logout" id="frm1" method="post">
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
							<a href="#" onclick="formSubmit(); return false;">logout</a>
							<a href="${pageContext.request.contextPath}/member/mypage">mypage</a>
							<a href="#">order</a> 
						</form>
					</security:authorize>
					<security:authorize access="!isAuthenticated()">
						<a href="${pageContext.request.contextPath}/member/login">login</a>
						<a href="${pageContext.request.contextPath}/member/join">join</a> 
						<a href="${pageContext.request.contextPath}/member/login">order</a>
					</security:authorize>
					</li>
				</ul>
			</div>
			<div class="menu0">
			<% long cartCntLong = (Long) session.getAttribute("cartCnt");%>
				
				<ul>
				<security:authorize access="isAuthenticated()">
					<li><a href="${pageContext.request.contextPath}/order/cart_list" class="xans-element- xans-layout xans-layout-orderbasketcount ">cart : <% out.println(cartCntLong); %> </a></li>
				</security:authorize>
				</ul>
			</div>
			<div class="xans-element- xans-layout xans-layout-category menu1">
				<ul>
					<li class="xans-record-"><a href="#">&nbsp;</a></li>
					<li class="xans-record-"><a href="${pageContext.request.contextPath}/item/list?cate=all">All</a></li>
					<li class="xans-record-"><a href="${pageContext.request.contextPath}/item/list?cate=outer">Outer</a></li>
					<li class="xans-record-"><a href="${pageContext.request.contextPath}/item/list?cate=top">Top</a></li>
					<li class="xans-record-"><a href="${pageContext.request.contextPath}/item/list?cate=pants">Pants</a></li>
					<li class="xans-record-"><a href="${pageContext.request.contextPath}/item/list?cate=jean">Jean</a></li>
					<li class="xans-record-"><a href="${pageContext.request.contextPath}/item/list?cate=acc">Acc</a></li>
					<li class="xans-record-"><a href="#">&nbsp;</a></li>
					<security:authorize access="hasAuthority('ADMIN')">
					<li class="xans-record-"><a href="#">=====ADMIN=====</a>
					<li class="xans-record-"><a href="${pageContext.request.contextPath}/admin/cate_insert">Cate_Insert</a>
					<li class="xans-record-"><a href="${pageContext.request.contextPath}/admin/item_insert">Item_Insert</a>
					<li class="xans-record-"><a href="${pageContext.request.contextPath}/admin/member_list">Member_List</a>
					<li class="xans-record-"><a href="${pageContext.request.contextPath}/admin/order_list">Order_List</a>
					</security:authorize>
				</ul>                                 
			</div>

			<div class="xans-element- xans-layout xans-layout-boardinfo menu2">
				<ul>
					<li class="xans-record-"><a href="${pageContext.request.contextPath}/board/notice">NOTICE</a></li>
					<li class="xans-record-"><a href="${pageContext.request.contextPath}/board/qa_list">Q&amp;A</a></li>

				</ul>
			</div>

			<%-- <div class="menu3">
				<ul>
					<li><a href="#" id="favorite" class="xans-element- xans-layout xans-layout-bookmark ">ADD BOOKMARK </a></li>
				</ul>
			</div>
			<form id="searchBarForm" name="" action="#" method="get" target="_self" enctype="multipart/form-data">
				<input id="banner_action" name="banner_action" value="" type="hidden">
				<div class="xans-element- xans-layout xans-layout-searchheader ">
					<fieldset>
						<input id="keyword" name="keyword" class="inputTypeText" placeholder=""	onmousedown="" value="" type="text">
						<a href="#" onclick=""><img src="${pageContext.request.contextPath}/resources/img/search.png" alt="search"></a>
					</fieldset>
				</div>
			</form> --%>
		</div>
	</div>
</body>
</html>