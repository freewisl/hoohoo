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
			<jsp:include page="../menu.jsp" flush="false" />
			<div id="contents">
				<form action="${pageContext.request.contextPath}/admin/cate_insert" method="post">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					<div class="container">
						<h3>카테고리등록</h3>
					
						<hr />
				       	<label style="width:100px; display:inline-block">카테고리ID</label>:
						<input type="text" name="id" placeholder="카테고리ID" /><br />
						<hr />
						<input type="submit" class="btn btn-primary" value="카테고리등록" />
				    </div>
			    </form>
			</div>
			<jsp:include page="../footer.jsp" flush="false" />
		</div>
	</div>
</body>
</html>