<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link href="https://fonts.googleapis.com/css2?family=Josefin+Sans:wght@600&family=Lato:wght@400;700&family=Nanum+Gothic&display=swap" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/resources/css/optimizer3.css" rel="stylesheet" />
	<title>후후</title>
</head>
<body>
	<div id="w_all">
		<div id="container">
			<jsp:include page="../menu.jsp" flush="false" />
			<div id="contents">
				<div class="path">
				    <span>현재 위치</span>
				    <ol><li><a href="/">home</a></li>
				        <li title="현재 위치"><strong>join result</strong></li>
				    </ol>
			    </div>
				
				<div class="titleArea">
				    <h2>join result</h2>
				</div>
				
				<div class="xans-element- xans-member xans-member-joincomplete ">
					<div class="joinComplete">
		          	<ul>
						<div class="ec-base-box">
			                <div class="information">
			               		<div class="description">
						        <p class="thumb"><img src="${pageContext.request.contextPath}/resources/img/img_member_default.gif" alt="일반회원" onerror="this.src='${pageContext.request.contextPath}/resources/img/img_member_default.gif';"></p>
						        <p class="desc">회원가입이 완료 되었습니다.<br>저희 쇼핑몰을 이용해 주셔서 감사합니다.</p>
					                 <dl class="ec-base-desc typeBullet gSmall gBlank5">
										<dt>아이디</dt>
											<dd><span>${vo.id}</span></dd>
			                        	<dt>이름</dt>
											<dd><span>${vo.name}</span></dd>
				                        <dt>이메일</dt>
											<dd><span>${vo.email}</span></dd>
				                    </dl>
								</div>
			            	</div>
		      	  		</div>
		        	</ul>
				    </div>
				</div>
                </div>
			<jsp:include page="../footer.jsp" flush="false" />
		</div>
	</div>
</body>
</html>