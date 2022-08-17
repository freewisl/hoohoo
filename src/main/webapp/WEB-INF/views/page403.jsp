<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
			<jsp:include page="./menu.jsp" flush="false" />
			<div id="contents">
				<div class="mainimg">
						<h1>Sorry, You don't have sufficient permission to visit this page.</h1><br /> <a href="${pageContext.request.contextPath}/home">홈으로 이동</a>
				</div>
			</div>
			<jsp:include page="./footer.jsp" flush="false" />
		</div>
	</div>

</body>
</html>