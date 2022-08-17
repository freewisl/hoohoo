<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
			<h3>물품등록</h3>
			<hr />
				<form action="${pageContext.request.contextPath}/admin/item_insert" method="post" enctype="multipart/form-data">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					<div class="row">
						<div class="col=sm-9" style="margin-bottom: 5px">
							<input type="text" class="form-control" placeholder="물품이름" autofocus name="name" />
						</div>
					</div>

					<div class="row">
						<div class="col=sm-9" style="margin-bottom: 5px">
							<textarea rows="3" cols="50" placeholder="물품내용" name="detail"></textarea>
						</div>
					</div>

					<div class="row">
						<div class="col=sm-9" style="margin-bottom: 5px">
							<input type="text" class="form-control" placeholder="물품가격" name="price" />
						</div>
					</div>
					
					<div class="row">
						<div class="col=sm-9" style="margin-bottom: 5px">
							<select class="form-select" name="itemcateid">
								<option selected>물품 카테고리 선택</option>
								<c:forEach var="vo" items="${cateList}">
									<option value="${vo.id}">${vo.id}</option>
								</c:forEach>
							</select><br />
						</div>
					</div>
					
					<div class="row">
						<div class="col=sm-9" style="margin-bottom: 5px">
							<input type="file" class="form-control" name="img1" />
						</div>
					</div>

					<hr/>
					<input type="submit" class="btn btn-success" value="물품등록" />
					<a href="${pageContext.request.contextPath}/home" class="btn btn-primary">홈으로</a>
				</form>
			</div>
			<jsp:include page="../footer.jsp" flush="false" />
		</div>
	</div>
</body>
</html>