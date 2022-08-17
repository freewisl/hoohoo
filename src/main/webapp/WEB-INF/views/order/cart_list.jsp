<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link href="https://fonts.googleapis.com/css2?family=Josefin+Sans:wght@600&family=Lato:wght@400;700&family=Nanum+Gothic&display=swap" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/resources/css/cart.css" rel="stylesheet" />
	<title>후후</title>
	<!-- jquery cdn -->
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<!-- twbspagination cdn -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/twbs-pagination/1.4.2/jquery.twbsPagination.min.js"></script>
	
	<script type="text/javascript">
	function formBatchDelSubmit(){
		if ($("input:checkbox[name='chk[]']").is(":checked")==false) {
			alert("체크 박스 선택후 눌러주세요.");
			return;
		}
    	document.getElementById("CartBatchDelForm").submit();
    }
	
	function formEmptyDelSubmit(){
		document.getElementById("CartEmptyDelForm").submit();
    }
	
	</script>
</head>
<body>
	<div id="w_all">
		<div id="container">
			<jsp:include page="../menu.jsp" flush="false" />
			<div id="contents">

				<div class="path">
					<span>현재 위치</span>
					<ol>
						<li><a href="/">home</a></li>
						<li title="현재 위치"><strong>shopping cart</strong></li>
					</ol>
				</div>

				<div class="titleArea">
					<h2>shopping cart</h2>
				</div>

				<div class="xans-element- xans-order xans-order-basketpackage ">
					<!-- 장바구니 비어있을 때 -->
					<c:if test="${empty list}">
					<div class="xans-element- xans-order xans-order-empty "><p>장바구니가 비어 있습니다</p></div>
					</c:if>
					<!-- 일반상품 -->
					<c:if test="${!empty list}">
					<div class="orderListArea ec-base-table typeList">
					<form id="CartBatchDelForm" action="${pageContext.request.contextPath}/order/cart_batch_delete" method="POST">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
						<div class="xans-element- xans-order xans-order-normtitle title ">
							<h3>일반상품 (1)</h3>
						</div>

						<!-- 일반상품 (기본배송) -->
						
						<table border="1" summary="" class="xans-element- xans-order xans-order-normnormal xans-record-">
							<caption>기본배송</caption>
							<colgroup>
								<col style="width: 27px">
								<col style="width: 92px">
								<col style="width: auto">
								<col style="width: 98px">
								<col style="width: 60px">
								<col style="width: 80px">
								<col style="width: 80px">
								<col style="width: 70px">
								<col style="width: 70px">
								<!-- <col style="width:110px" />-->
							</colgroup>
							<thead>
								<tr>
									<th scope="col">
									<input type="checkbox" onclick="Basket.setCheckBasketList('basket_product_normal_type_normal', this);"></th>
									<th scope="col">image</th>
									<th scope="col">info</th>
									<th scope="col">price</th>
									<th scope="col">qty</th>
									<th scope="col">total</th>
									<!-- <th scope="col">button</th>-->
								</tr>
							</thead>
							<tbody class="xans-element- xans-order xans-order-list center">
							<c:forEach var="vo" items="${list}">
								<tr class="xans-record-">
									<td><input type="checkbox" id="chk[]" name="chk[]" value="${vo.no}" title="${vo.no}">
									<input type="hidden" name="memno" value="${vo.member.no}"/>
									</td>
									<td class="thumb">
										<a href="${pageContext.request.contextPath}/item/item_detail?no=${vo.item.no}">
											<img src="data:image/png;base64,${vo.item.base64}">
										</a>
									</td>
									<td class="left">
										<a href="${pageContext.request.contextPath}/item/item_detail?no=${vo.item.no}">
											<strong>${vo.item.name}</strong> 
										</a>
										<ul class="xans-element- xans-order xans-order-optionall option">
											<li class="xans-record-"><strong class="displaynone">${vo.item.name}</strong>
										</ul>
									</td>
									<td class="right">
										<div class="">
											<strong>${vo.item.price}</strong>
											<p class="displaynone"></p>
										</div>
									</td>
									<td><span class=""> <span class="ec-base-qty"><input id="cart_cnt_input[]" name="cart_cnt_input[]"
												size="2" onchange="form_btn(0)" value="${vo.cnt}" type="text"
											><a href="#" onclick="form_btn(1)"><img
													src="${pageContext.request.contextPath}/resources/img/btn_quantity_up.gif" alt="수량증가" class="up"
												></a><a href="#" onclick="form_btn(-1)"><img
													src="${pageContext.request.contextPath}/resources/img/btn_quantity_down.gif" alt="수량감소" class="down"
												></a></span>

									</span> <span class="displaynone">1</span></td>
									<td class="right"><fmt:formatNumber type="number" value="${vo.cnt*vo.item.price}"/>
									<div class="displaynone"></div></td>
									<!--  <td class="button">
                  					</td>-->
								</tr>
								</c:forEach>
							</tbody>
						</table>
						</form>
					</div>
					<!-- 선택상품 제어 버튼 -->
					<div class="xans-element- xans-order xans-order-selectorder ec-base-button ">
						<span class="gLeft"> <strong class="text">선택상품을</strong> 
						<a href="#" onclick="formBatchDelSubmit(); return false;" class="ec-base-button btxs" title="선택상품 삭제">delete</a>
						</span> 
						<form id="CartEmptyDelForm" action="${pageContext.request.contextPath}/order/cart_empty" method="POST">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
						<span class="gRight"> <a href="#" onclick="formEmptyDelSubmit(); return false;" class="ec-base-button btxs" title="장바구니 비우기">empty</a>
						</span>
						</form>
					</div>
					<!-- 주문 버튼 -->
					<div class="xans-element- xans-order xans-order-totalorder ec-base-button justify bts">
						<p class="gRight">
							<a href="#" onclick="" title="선택상품 주문" class="w ">select order</a> 
							<a href="#" onclick="" title="전체상품 주문" class=" ">all&nbsp; order</a>
						</p>
					</div>
					</c:if>					
				</div>
			</div>
			<jsp:include page="../footer.jsp" flush="false" />
		</div>
	</div>

</body>
</html>