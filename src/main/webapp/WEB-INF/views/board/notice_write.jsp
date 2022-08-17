<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link href="https://fonts.googleapis.com/css2?family=Josefin+Sans:wght@600&family=Lato:wght@400;700&family=Nanum+Gothic&display=swap"	rel="stylesheet">
	<link href="${pageContext.request.contextPath}/resources/css/optimizer7.css" rel="stylesheet" />
</head>
<body>
	<div id="w_all">
		<div id="container">
			<jsp:include page="../menu.jsp" flush="false" />
			<div id="contents">
				<div class="xans-element- xans-board xans-board-writepackage-4 xans-board-writepackage xans-board-4 ">
					<div class="xans-element- xans-board xans-board-title-4 xans-board-title xans-board-4">
						<div class="path ">
							<span>현재 위치</span>
							<ol>
								<li><a href="/">home</a></li>
								<li title="현재 위치"><strong>NOTICE</strong></li>
							</ol>
						</div>
						<div class="title ">
							<h2>NOTICE</h2>
						</div>
					</div>
					<form action="${pageContext.request.contextPath}/admin/notice_write" method="post">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
						<div class="input-group">
							<span class="input-group-text" id="basic-addon1">글제목</span> 
							<input type="text" class="form-control" name="title" style="background-color: white">
						</div>
						<div class="input-group">
							<span class="input-group-text">글내용</span>
							<textarea class="form-control" name="content" style="background-color: white"></textarea>
						</div>
						<div class="input-group">
							<span class="input-group-text">작성자</span> 
							<input type="text" class="form-control" name="writer" value="HOOHOO"/>
						</div>
						<hr />
						<button type="submit" class="btn btn-primary">글작성</button>

					</form>
				</div>
			</div>
			<jsp:include page="../footer.jsp" flush="false" />
		</div>
	</div>

</body>
</html>