<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link href="https://fonts.googleapis.com/css2?family=Josefin+Sans:wght@600&family=Lato:wght@400;700&family=Nanum+Gothic&display=swap" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/resources/css/optimizer.css" rel="stylesheet" />
	<title>후후</title>
</head>
<body>
	<div id="w_all">
		<div id="container">
			<jsp:include page="../menu.jsp" flush="false" />
			<div id="contents">
				<div class="xans-element- xans-product xans-product-menupackage "></div>
				<div class="xans-element- xans-product xans-product-normalpackage ">
					<div class="xans-element- xans-product xans-product-listnormal proct">
						<ul class="prdList grid4">
							<c:forEach var="vo" items="${list}">
								<li class="ovr xans-record-">
									<div class="thumbnail">
										<a href="${pageContext.request.contextPath}/item/item_detail?no=${vo.no}">
											<c:if test="${vo.base64 ne null}">
												<img src="data:image/png;base64,${vo.base64}"/>
											</c:if>
											<c:if test="${vo.base64 eq null}">
											 	No이미지
											</c:if>
										</a>
									</div>
									<div class="description">
										<p class="name">
											<a href="${pageContext.request.contextPath}/item/item_detail?no=${vo.no}">
												<span style="font-size: 12px; color: #000000;">${vo.name}</span>
											</a>
										</p>
										<ul class="xans-element- xans-product xans-product-listitem spec">
											<li class=" xans-record-">
												<span style="font-size:12px;color:#000000;"><fmt:formatNumber type="number" value="${vo.price}"/></span>
												<span id="span_product_tax_type_text" style=""> </span>
											</li>
										</ul>
									</div>
								</li>
							</c:forEach>
						</ul>
					</div>
				</div>
				<!-- <div class="xans-element- xans-product xans-product-normalpaging ec-base-paginate">
					<a href="#none" class="first"><img src="/web/upload/cooke/su/prev2.jpg" alt="첫 페이지"></a> <a href="#none"><img
						src="/web/upload/cooke/su/prev1.jpg" alt="이전 페이지"
					></a>
					<ol>
						<li class="xans-record-"><a href="?cate_no=107&amp;page=1" class="this">1</a></li>
						<li class="xans-record-"><a href="?cate_no=107&amp;page=2" class="other">2</a></li>
						<li class="xans-record-"><a href="?cate_no=107&amp;page=3" class="other">3</a></li>
						<li class="xans-record-"><a href="?cate_no=107&amp;page=4" class="other">4</a></li>
						<li class="xans-record-"><a href="?cate_no=107&amp;page=5" class="other">5</a></li>
					</ol>
					<a href="?cate_no=107&amp;page=2"><img src="/web/upload/cooke/su/next1.jpg" alt="다음 페이지"></a> <a
						href="?cate_no=107&amp;page=12" class="last"
					><img src="/web/upload/cooke/su/next2.jpg" alt="마지막 페이지"></a>
				</div> -->
			</div>
			<jsp:include page="../footer.jsp" flush="false" />
		</div>
	</div>

</body>
</html>