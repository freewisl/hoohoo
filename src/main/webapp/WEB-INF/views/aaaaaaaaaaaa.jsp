<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link href="https://fonts.googleapis.com/css2?family=Josefin+Sans:wght@600&family=Lato:wght@400;700&family=Nanum+Gothic&display=swap" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/resources/css/optimizer.css" rel="stylesheet" />
</head>
<body>
	<div id="w_all">
		<div id="container">
			<jsp:include page="./menu.jsp" flush="false" />
			<div id="contents">
				<div class="mainimg">
					<p>
						<a href="#"> 
							<img src="${pageContext.request.contextPath}/resources/img/main_img.jpg">
						</a>
					</p>
		
					<p>
					</p>
				</div>
			</div>
			<jsp:include page="./footer.jsp" flush="false" />
		</div>
	</div>
	
</body>
</html>