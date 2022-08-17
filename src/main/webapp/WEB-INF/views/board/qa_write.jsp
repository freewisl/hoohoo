<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link href="https://fonts.googleapis.com/css2?family=Josefin+Sans:wght@600&family=Lato:wght@400;700&family=Nanum+Gothic&display=swap" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/resources/css/optimizer7.css" rel="stylesheet" />
</head>
<body>
	<div id="w_all">
		<div id="container">
			<jsp:include page="../menu.jsp" flush="false" />
			<div id="contents">


				<div class="xans-element- xans-board xans-board-readpackage-1002 xans-board-readpackage xans-board-1002 ">
					<div class="xans-element- xans-board xans-board-title-1002 xans-board-title xans-board-1002">
						<div class="path ">
							<span>현재 위치</span>
							<ol>
								<li><a href="/">home</a></li>
								<li title="현재 위치"><strong>Q&amp;A</strong></li>
							</ol>
						</div>
						<div class="title ">
							<h2>Q&amp;A</h2>
						</div>
					</div>
					<!-- 글 내용-->
					<form id="BoardWriteForm" action="${pageContext.request.contextPath}/board/qa_write" method="post">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
						<div class="xans-element- xans-board xans-board-read-1002 xans-board-read xans-board-1002">
							<div class="boardView ">
								<table border="1" summary="">
									<caption>게시판 상세</caption>
									<tbody>
										<tr>
											<th scope="row">subject</th>
											<td>
												<input name="title" class="inputTypeText" type="text" required="required"/>
											</td>
										</tr>
										<tr>
											<th scope="row">writer</th>
											<td><input name="writer" class="inputTypeText" type="hidden" value="${currentUser}"/>
											${currentUser}</td>
										</tr>
										<tr class="view">
											<td colspan="2">
												<div class="detail">
													<div class="fr-view fr-view-article">
														<textarea class="form-control" rows="20" cols="100" wrap="hard" name="content" style="background-color: white; width: 96.7%; resize: none;"></textarea>
													</div>
												</div>
											</td>
										</tr>
									</tbody>
								</table>
							</div>
							<div class="btnArea ">
								<span class="left">
									<input type="button" class="ec-base-button bts3 w" value="list" onclick="location.href='${pageContext.request.contextPath}/board/qa_list?txt=${param.txt}&page=${param.page}'"/>
								</span> 
								<span class="right"> 
									<input type="submit" class="ec-base-button bts3 b" value="submit"/>
								</span> 
							</div>
						</div>
					</form>
				</div>
			</div>
			<jsp:include page="../footer.jsp" flush="false" />
		</div>
	</div>

</body>
</html>