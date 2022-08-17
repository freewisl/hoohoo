<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
	href="https://fonts.googleapis.com/css2?family=Josefin+Sans:wght@600&family=Lato:wght@400;700&family=Nanum+Gothic&display=swap"
	rel="stylesheet"
>
<link href="${pageContext.request.contextPath}/resources/css/optimizer2.css" rel="stylesheet" />
</head>
<script>
    function check_pw(){

        var pw = document.getElementById('passwd').value;
        var SC = ["!","@","#","$","%"];
        var check_SC = 0;

        if(pw.length < 6 || pw.length>16){
            window.alert('비밀번호는 6글자 이상, 16글자 이하만 이용 가능합니다.');
            document.getElementById('passwd').value='';
        }
        for(var i=0;i<SC.length;i++){
            if(pw.indexOf(SC[i]) != -1){
                check_SC = 1;
            }
        }
        if(check_SC == 0){
            window.alert('!,@,#,$,% 의 특수문자가 들어가 있지 않습니다.')
            document.getElementById('passwd').value='';
        }
        if(document.getElementById('passwd').value !='' && document.getElementById('user_passwd_confirm').value!=''){
            if(document.getElementById('passwd').value==document.getElementById('user_passwd_confirm').value){
                document.getElementById('pwConfirmMsg').innerHTML='비밀번호가 일치합니다.'
                document.getElementById('pwConfirmMsg').style.color='blue';
                const target = document.getElementById('joinMember');
                target.disabled = false;
            }
            else{
                document.getElementById('pwConfirmMsg').innerHTML='비밀번호가 일치하지 않습니다.';
                document.getElementById('pwConfirmMsg').style.color='red';
                const target = document.getElementById('joinMember');
                target.disabled = true;
            }
        }
    }
</script>
<body>
	<div id="w_all">
		<div id="container">
			<jsp:include page="../menu.jsp" flush="false" />
			<div id="contents">
				<div class="path">
					<span>현재 위치</span>
					<ol>
						<li><a href="/">home</a></li>
						<li title="현재 위치"><strong>join member</strong></li>
					</ol>
				</div>

				<div class="titleArea">
					<h2>join member</h2>
				</div>

				<form id="joinForm" name="joinForm" action="${pageContext.request.contextPath}/member/join" method="post">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					<div class="xans-element- xans-member xans-member-join">
						<div class="ec-base-table typeWrite">
							<table border="1" summary="">
								<caption>회원 기본정보</caption>
								<colgroup>
									<col style="width: 150px;">
									<col style="width: auto;">
								</colgroup>
								<tbody>
									<tr>
									
										<th scope="row">아이디 <img src="${pageContext.request.contextPath}/resources/img/ico_required.gif" alt="필수"></th>
										<td>
										<input id="member_id" name="id" class="inputTypeText" placeholder="" value="" type="text" autocomplete="off" required>
											</td>
									</tr>
									<tr>
										<th scope="row">비밀번호 <img src="${pageContext.request.contextPath}/resources/img/ico_required.gif" alt="필수"></th>
										<td>
											<div class="eTooltip">
												<input id="passwd" name="pw" autocomplete="off" maxlength="16" value="" type="password" onchange="check_pw()" required>
												(영문 대소문자/숫자/특수문자 중 2가지 이상 조합, 10자~16자)
											</div>
										</td>
									</tr>
									<tr>
										<th scope="row">비밀번호 확인 <img src="${pageContext.request.contextPath}/resources/img/ico_required.gif" alt="필수"></th>
										<td><input id="user_passwd_confirm" name="pw1" autocomplete="off" maxlength="16" value="" type="password"
											onchange="check_pw()" required
										> <span id="pwConfirmMsg"></span></td>
									</tr>
									<tr>
										<th scope="row" id="name">이름 <img src="${pageContext.request.contextPath}/resources/img/ico_required.gif" alt="필수"></th>
										<td><span id="nameContents"><input type="text" name="name" id="name" maxlength="20" required></span></td>
									</tr>
									<tr>
										<th scope="row">이메일 <img src="${pageContext.request.contextPath}/resources/img/ico_required.gif" alt="필수"></th>
										<td><input id="email1" name="email" value="" type="text" required> <span id="emailMsg"></span></td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="ec-base-button">
							<input id="joinMember" class="ec-base-button btl" style="cursor:pointer;"title="회원가입" value="join member" type="submit"/>
						</div>
					</div>
				</form>
			</div>
			<jsp:include page="../footer.jsp" flush="false" />
		</div>
	</div>
</body>
</html>