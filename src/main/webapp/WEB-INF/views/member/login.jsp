<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link href="https://fonts.googleapis.com/css2?family=Josefin+Sans:wght@600&family=Lato:wght@400;700&family=Nanum+Gothic&display=swap" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/resources/css/optimizer1.css" rel="stylesheet" />
	<script>
    function formLoginSubmit(){
	    	document.getElementById("LoginForm").submit();
	    }
	</script>
</head>
<body>
	<div id="w_all">
			<div id="container">
			<jsp:include page="../menu.jsp" flush="false" />
				<div id="contents">
			
					<div class="path">
						<span>현재 위치</span>
						<ol>
							<li><a href="/">home</a></li>
							<li title="현재 위치"><strong>login</strong></li>
						</ol>
					</div>
			
					<div class="titleArea">
						<h2>member &nbsp;/&nbsp; guest login</h2>
					</div>
			
					<form id="member_form_2219627411" name="" action="${pageContext.request.contextPath}/loginProcess" method="post" >
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
						<div class="xans-element- xans-member xans-member-login ">
						
						<div class="login">
							<fieldset>
								<legend>회원로그인</legend>
								<p class="nna">id</p>
								<label class="id ePlaceholder"><input id="member_id" name="id" class="inputTypeText" value="" type="text"></label>
								<p class="nna">password</p>
								<label class="password ePlaceholder">
								<input id="member_passwd" name="pw" autocomplete="off" value="" type="password"></label>
								<p class="btx">
									<input type="submit" value="login" class="ec-base-button btl" style="margin-bottom: 5px; font-size: 10px;font-family: Lato; cursor:pointer;"/>
									<input type="button" onclick="location.href='${pageContext.request.contextPath}/member/join'" value="join member" class="ec-base-button btl" style="margin-bottom: 5px; font-size: 10px;font-family: Lato; cursor:pointer;"/>
									<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
										<font color="red">
												Your login attempt was not successful due to <br /> ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
											<c:remove var="SPRING_SECURITY_LAST_EXCEPTION" scope="session" />
										</font>
									</c:if>
								</p>
							</fieldset>
						</div>
						<p class="snsArea">
						</p>
					</div>
				</form>
			</div>
			<jsp:include page="../footer.jsp" flush="false" />
		</div>
	</div>
</body>
</html>