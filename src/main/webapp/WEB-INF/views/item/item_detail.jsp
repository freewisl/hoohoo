<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link href="https://fonts.googleapis.com/css2?family=Josefin+Sans:wght@600&family=Lato:wght@400;700&family=Nanum+Gothic&display=swap"	rel="stylesheet">
	<link href="${pageContext.request.contextPath}/resources/css/optimizer5.css" rel="stylesheet" />
	<script type="text/javascript">
	function form_btn(n){
		var text = document.getElementById("item_quantity"); 
		var resultElement1 = document.getElementById("totalprice");
		var resultElement2 = document.getElementById("totalprice2");
		var resultElement3 = document.getElementById("totalquantity");
		
		text_val = parseInt(text.value); // 폼 값을 숫자열로 변환
		text_val += n; // 계산
		text.value = text_val; // 계산된 값을 바꾼다
		if(text_val <= 0){
		   text.value = 1;   // 만약 값이 0 이하면 1로 되돌려준다, 1보다 작은 수는 나타나지 않게하기 위해
		   if(text.value <= 1){
			   resultElement1.innerText = ${vo.price};
			   resultElement2.innerText = ${vo.price};
			   resultElement3.innerText = 1;
		   }
		   return;
		}
		resultElement1.innerText = ${vo.price}*text_val;
		resultElement2.innerText = ${vo.price}*text_val;
		resultElement3.innerText = text_val;
		
		if(!isNaN(text)){
			text.value= 1;
			resultElement1.innerText = ${vo.price};
		    resultElement2.innerText = ${vo.price};
		    resultElement3.innerText = 1;
		}
	}
	function formDelSubmit(){
    	document.getElementById("ItemDelForm").submit();
    }
	function formcartSubmit(){
    	document.getElementById("CartInsertForm").submit();
    }
	</script>
</head>
<body>
	<div id="w_all">
		<div id="container">
			<jsp:include page="../menu.jsp" flush="false" />
			<div id="contents">
				<div class="xans-element- xans-product xans-product-detail">
					<div class="detailArea ">
						<div class="xans-element- xans-product xans-product-image imgArea ">
							<div class="keyImg">
								<div class="thumbnail">
									<img src="data:image/png;base64,${vo.base64}" />
								</div>
							</div>
						</div>
						<!-- 상세정보 내역 -->
						<div class="infoArea">
							<form id="CartInsertForm" action="${pageContext.request.contextPath}/order/cart_insert" method="POST">
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
							<h2>${vo.name}</h2>
							<input type="hidden" name="no" value="${vo.no}"/>
							<div class="xans-element- xans-product xans-product-detaildesign">
								<table border="1" summary="">
									<caption>기본 정보</caption>
									<tbody>
										<tr class=" xans-record-">
											<!--th scope="row"><span style="font-size:12px;color:#000000;"></span></th-->
											<td><span style="font-size: 12px; color: #000000;"></span><span style="font-size: 12px; color: #000000;">${vo.name}</span></td>
										</tr>
										<tr class=" xans-record-">
											<!--th scope="row"><span style="font-size:12px;color:#000000;"></span></th-->
											<td><span style="font-size: 12px; color: #000000;"></span><span style="font-size: 12px; color: #000000;"><strong
													id="span_product_price_text"
												>${vo.price}</strong><input id="product_price" name="product_price" value="" type="hidden"></span></td>
										</tr>
									</tbody>
								</table>
							</div>

							<div class="xans-element- xans-product xans-product-additional come  ">
							${vo.detail}
							</div>
							<div id="totalProducts" class="">
								<table border="1" summary="">
									<caption>상품 목록</caption>
									<colgroup>
										<col style="width: 284px;">
										<col style="width: 80px;">
										<col style="width: 110px;">
									</colgroup>
									<thead>
										<tr>
											<th scope="col">상품명</th>
											<th scope="col">상품수</th>
											<th scope="col">가격</th>
										</tr>
									</thead>
									<tbody class="displaynone">
									</tbody>
									<tbody class="option_products">
										<tr class="option_product " data-option-index="1">
											<td><input type="hidden" class="option_box_id" id="option_box1_id" value="P0000BTQ000A" name="item_code[]"
												data-item-add-option="" data-item-reserved="N" data-option-id="000A" data-option-index="1"
											>
											<p class="product">${vo.name}<br> - <span>-</span></p>
											</td>
											<td>
												<span class="quantity" style="width: 65px;">
												<input type="text" id="item_quantity" name="ordercnt" onchange="form_btn(0)" class="quantity_opt eProductQuantityClass" value="1" >
													<a href="#" onclick="form_btn(1)" class="up eProductQuantityUpClass" style="bottom: 14px; width: 21px; height: 11px;">
														<img src="${pageContext.request.contextPath}/resources/img/btn_count_up.gif" style="vertical-align: top">
													</a>
													<a href="#" onclick="form_btn(-1)" class="down eProductQuantityDownClass" style=" top: 10px; width: 21px; height: 11px;">
														<img src="${pageContext.request.contextPath}/resources/img/btn_count_down.gif" style="vertical-align: top">
													</a>
												</span>
											</td>
											<td class="right">
												<span id="option_box1_price">
													<input type="hidden" class="option_box_price" value="${vo.price}" >
													<span class="ec-front-product-item-price" >
														<em id="totalprice">${vo.price}</em>
													</span>
												</span>
											</td>
										</tr>
										</tbody>
										<tbody class="add_products">
										</tbody>
									</tbody>
									<tfoot>
										<tr class="tod">
											<td colspan="3"><span class="tot">TOTAL(QUANTITY) : </span> <span class="total"><strong><em id="totalprice2"></em></strong>
													(<em id="totalquantity"></em>)</span></td>
										</tr>
									</tfoot>
								</table>
							</div>
							</form>
							<form id="ItemDelForm" action="${pageContext.request.contextPath}/admin/item_delete?no=${vo.no}" method="POST">
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
							</form>
							<form action="${pageContext.request.contextPath}/admin/item_delete?no=${vo.no}" method="POSt">
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
							</form>
							<div class="xans-element- xans-product xans-product-action ">
								<div class="btnArea">
									<ul>
										<li class="first "><a href="#" onclick="" class="ec-base-button btl">buy</a></li>
										<li class=""><a href="#" onclick="formcartSubmit(); return false;"	class="ec-base-button btl w">shopping cart</a></li>
										<security:authorize access="hasAuthority('ADMIN')">
										<li class=""><a href="#" onclick="formDelSubmit(); return false;" class="ec-base-button btl b">item delete</a></li>
										</security:authorize>
									</ul>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<jsp:include page="../footer.jsp" flush="false" />
		</div>
	</div>
</body>
</html>