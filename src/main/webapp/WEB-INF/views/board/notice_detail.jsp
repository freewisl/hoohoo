<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link href="https://fonts.googleapis.com/css2?family=Josefin+Sans:wght@600&family=Lato:wght@400;700&family=Nanum+Gothic&display=swap" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/resources/css/optimizer7.css" rel="stylesheet" />
	<script>
    function formDelSubmit(){
	    	document.getElementById("BoardDelForm").submit();
	    }
	</script>
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
								<li title="현재 위치"><strong>NOTICE</strong></li>
							</ol>
						</div>
						<div class="title ">
							<h2>NOTICE</h2>
						</div>
					</div>
					<!-- 글 내용-->
					<form id="BoardDelForm" action="${pageContext.request.contextPath}/board/delete?no=${vo.no}" method="post">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
						<div class="xans-element- xans-board xans-board-read-1002 xans-board-read xans-board-1002">
							<div class="boardView ">
								<table border="1" summary="">
									<caption>게시판 상세</caption>
									<tbody>
										<tr>
											<th scope="row">subject</th>
											<td>${vo.title}</td>
										</tr>
										<tr>
											<th scope="row">writer</th>
											<td>${vo.writer}</td>
										</tr>
										<tr class="etcArea">
											<td colspan="2">
												<ul>
													<li class="date "><strong class="th">date</strong> <span class="td">${vo.date}</span></li>
													<li class="hit "><strong class="th">hit</strong> <span class="td">${vo.hit}</span></li>
												</ul>
											</td>
										</tr>
										<tr class="view">
											<td colspan="2">
												<div class="detail">
													<div class="fr-view fr-view-article">
														<pre><c:out value="${vo.content}"/></pre>
													</div>
												</div>
											</td>
										</tr>
									</tbody>
								</table>
							</div>
							<div class="btnArea ">
								<span class="left">
									<a href="${pageContext.request.contextPath}/board/notice?txt=${param.txt}&page=${param.page}" class="ec-base-button bts3 w">list</a>
								</span>
								<security:authorize access="hasAuthority('ADMIN')">
								<span class="right"> 
									<a href="#" onclick="formDelSubmit(); return false;" class="ec-base-button bts3 b">delete</a> 
									<a href="${pageContext.request.contextPath}/admin/notice_update?no=${vo.no}&txt=${param.txt}&page=${param.page}" class="ec-base-button bts3 b">modify</a>
								</span> 
								</security:authorize>
							</div>
						</div>
					</form>
				</div>


				<div class="xans-element- xans-board xans-board-movement-1002 xans-board-movement xans-board-1002 ">
					<ul>
					<c:if test="${prev ne null}">
						<li class="prev "><strong>prev</strong><a href="${pageContext.request.contextPath}/board/notice_detail?no=${prev.no}&txt=${param.txt}&page=${param.page}">${prev.title}</a></li>
					</c:if>
					<c:if test="${next ne null}">
						<li class="next "><strong>next</strong><a href="${pageContext.request.contextPath}/board/notice_detail?no=${next.no}&txt=${param.txt}&page=${param.page}">${next.title}</a></li>
					</c:if>
					</ul>
				</div>

				<!-- 관리자 전용 메뉴 -->
				<!-- //관리자 전용 메뉴 -->

			</div>
			<jsp:include page="../footer.jsp" flush="false" />
		</div>
	</div>

</body>
</html>