<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
	href="https://fonts.googleapis.com/css2?family=Josefin+Sans:wght@600&family=Lato:wght@400;700&family=Nanum+Gothic&display=swap"
	rel="stylesheet"
>
<link href="${pageContext.request.contextPath}/resources/css/optimizer6.css" rel="stylesheet" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" />
	<!-- jquery cdn -->
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<!-- twbspagination cdn -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/twbs-pagination/1.4.2/jquery.twbsPagination.min.js"></script>
	<script>
	function formSearchSubmit(){
    	document.getElementById("SearchForm").submit();
    }
	</script>
</head>
<body>
	<div id="w_all">
		<div id="container">
			<jsp:include page="../menu.jsp" flush="false" />
			<div id="contents">
				<div class="xans-element- xans-board xans-board-listpackage-1002 xans-board-listpackage xans-board-1002 ">
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
						<p class="imgArea"></p>
					</div>
					<div class="boardSort">
						<span class="xans-element- xans-board xans-board-replysort-1002 xans-board-replysort xans-board-1002 "></span>
					</div>
					<div class="boardList">
						<table border="1" summary="">
							<caption>게시판 목록</caption>

							<thead class="xans-element- xans-board xans-board-listheader-1002 xans-board-listheader xans-board-1002 ">
								<tr>
									<th scope="col" style="width: 70px; text-align: center;">no</th>
									<th scope="col" style="width: 135px; text-align: center;" class="displaynone">category</th>
									<th scope="col" style="width: auto; . width: 800px; text-align: center;">subject</th>
									<th scope="col" style="width: 100px; text-align: center;">writer</th>
									<th scope="col" style="width: 90px; text-align: center;" class="">date</th>
									<th scope="col" style="width: 70px; text-align: center;" class="">hit</th>
								</tr>
							</thead>
							<tbody class="xans-element- xans-board xans-board-notice-1002 xans-board-notice xans-board-1002 notice">
								<c:forEach var="vo" items="${list}">
									<tr class="xans-record-">
										<td>공지</td>
										<td class="displaynone"></td>
										<td class="subject"><a href="${pageContext.request.contextPath}/board/notice_detail?no=${vo.no}&txt=${param.txt}&page=${param.page}" style="color: #000000;">${vo.title}</a> 
										<span class="comment"></span>
										</td>
										<td>${vo.writer}</td>
										<td class="txtLess ">${vo.date}</td>
										<td class="txtLess ">${vo.hit}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>

				<form id="SearchForm"action="" method="get">
					<div class="xans-element- xans-board xans-board-read-1002 xans-board-read xans-board-1002">
						<div class="btnArea ">
							<span class="left" style="margin-top: 5px;">
								<input id="search" name="txt" class="inputTypeText" placeholder="" value="" type="text"> 
								<a href="#none" onclick="formSearchSubmit(); return false;">SEARCH</a>
							</span>
							<span class="right">
								<security:authorize access="hasAuthority('ADMIN')">
									<div class="xans-element- xans-board xans-board-buttonlist-4 xans-board-buttonlist xans-board-4  ">
										<a href="${pageContext.request.contextPath}/admin/notice_write" class="ec-base-button bts3 ">write</a>
									</div>
								</security:authorize>
							</span>
						</div>
						<div id="pagination" class="pagination justify-content-center"></div>
					</div>
				</form>
			</div>
			<jsp:include page="../footer.jsp" flush="false" />
		</div>
	</div>
	<script>
		$(function() {
			$('#pagination')
					.twbsPagination(
							{
								totalPages : Number('${totPages}'),
								visiblePages : 10,
								startPage : Number('${param.page}'),
								initiateStartPageClick : false,
								onPageClick : function(event, page) {
									window.location.href = "${pageContext.request.contextPath}/board/notice?txt="
											+ '${param.txt}' + "&page=" + page;
								}
							});
		});
	</script>
</body>
</html>