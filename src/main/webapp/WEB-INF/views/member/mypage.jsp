<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link href="https://fonts.googleapis.com/css2?family=Josefin+Sans:wght@600&family=Lato:wght@400;700&family=Nanum+Gothic&display=swap" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/resources/css/optimizer4.css" rel="stylesheet" />
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
						<li title="현재 위치"><strong>my page</strong></li>
					</ol>
				</div>

				<div class="titleArea">
					<h2>my page</h2>
				</div>
				
				<div class="xans-element- xans-myshop xans-myshop-asyncbenefit">
					<div class="">
				        <div class="information">
				        <div class="displaynone">
				        <security:authentication property="principal"/>
				        </div>
				            <p class="thumbnail">
				            <img src="${pageContext.request.contextPath}/resources/img/img_member_default.gif" alt="" onerror="this.src='${pageContext.request.contextPath}/resources/img/img_member_default.gif';" class="myshop_benefit_group_image_tag">
				            </p>
				            
				            <div class="description">
				                <p>
				                저희 쇼핑몰을 이용해 주셔서 감사합니다. 
					                <strong class="txtEm">
					                <span>
					                	<span class="xans-member-var-name">${vo.name}</span>
					                </span></strong> 님은 <strong>[
					                <span class="xans-member-var-group_name"><security:authentication property="authorities"/></span>
					                <span class="myshop_benefit_ship_free_message"></span>]
					                </strong> 
					            회원이십니다.
				                </p>
				            </div>
				        </div>
				     </div>
				</div>

				<form id="mypageForm" name="mypageForm" action="${pageContext.request.contextPath}/member/mypage" method="post" onSubmit="return checkForm()" >
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					<div class="xans-element- xans-member xans-member-edit">
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
											<input type="password" name="fakepw" style="display: block; width:0px; height:0px; border: 0"/>
											<input type="hidden" name="no" value="${vo.no}"/>
											<input type="hidden" name="id" value="${vo.id}"/>
											<input type="hidden" name="date" value="${vo.date}"/>
											<input type="hidden" name="role" value="${vo.role}"/>
											<security:authentication property="name"/>
										</td>
									</tr>
									<tr>
										<th scope="row">현재 비밀번호 <img src="${pageContext.request.contextPath}/resources/img/ico_required.gif" alt="필수"></th>
										<td>
											<div class="eTooltip">
												<input id="currpasswd" name="currpw" maxlength="16" value="" type="password" required>
											</div>
										</td>
									</tr>
									<tr>
										<th scope="row">새 비밀번호 <img src="${pageContext.request.contextPath}/resources/img/ico_required.gif" alt="필수"></th>
										<td>
											<div class="eTooltip">
												<input id="passwd" name="pw" maxlength="16" value="" type="password" onchange="check_pw()">
												(영문 대소문자/숫자/특수문자 중 2가지 이상 조합, 10자~16자)
											</div>
										</td>
									</tr>
									<tr>
										<th scope="row">새 비밀번호 확인 <img src="${pageContext.request.contextPath}/resources/img/ico_required.gif" alt="필수"></th>
										<td><input id="user_passwd_confirm" name="newpwconfirm" maxlength="16" value="" type="password"
											onchange="check_pw()"> <span id="pwConfirmMsg"></span></td>
									</tr>
									<tr>
										<th scope="row" id="name">이름 <img src="${pageContext.request.contextPath}/resources/img/ico_required.gif" alt="필수"></th>
										<td><span id="nameContents"><input type="text" name="name" id="name" maxlength="20" value="${vo.name}" required></span></td>
									</tr>
									<tr>
										<th scope="row">이메일 <img src="${pageContext.request.contextPath}/resources/img/ico_required.gif" alt="필수"></th>
										<td><input type="text" name="email" value="${vo.email}"/> <span id="emailMsg"></span></td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="ec-base-button bts">
			                <input id="mypage-modify" class="ec-base-button btc" title="회원 정보 수정" value="info modify" type="submit"/>
			                <input id="leave-member" type="submit" class="ec-base-button btc1" title="회원 탈퇴" formmethod="post" formaction="${pageContext.request.contextPath}/member/delete" value="leave member" />
					    </div>
					</div>
				</form>
			</div>
			<jsp:include page="../footer.jsp" flush="false" />
		</div>
	</div>
</body>
<script type="text/javascript">
    function check_pw(){

        var pw = document.getElementById('passwd').value;
        var SC = ["!","@","#","$","%"];
        var check_SC = 0;
        var user_passwd_confirm = document.mypageForm.user_passwd_confirm;

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
                const target1 = document.getElementById('mypage-modify');
               	const target2 = document.getElementById('leave-member');
                target1.disabled = false;
                target2.disabled = false;
            } else {
                document.getElementById('pwConfirmMsg').innerHTML='비밀번호가 일치하지 않습니다.';
                document.getElementById('pwConfirmMsg').style.color='red';
                const target1 = document.getElementById('mypage-modify');
               	const target2 = document.getElementById('leave-member');
                target1.disabled = true;
                target2.disabled = true;
                console.log(document.mypageForm.user_passwd_confirm.value);
            }
        } else if (document.getElementById('passwd').value !='' && document.getElementById('user_passwd_confirm').value==''){
        	document.getElementById('pwConfirmMsg').style.color='red';
        	document.getElementById('pwConfirmMsg').innerHTML='새 비밀번호 입력 해주세요.';
        	const target1 = document.getElementById('mypage-modify');
           	const target2 = document.getElementById('leave-member');
            target1.disabled = true;
            target2.disabled = true;
        }
    }
    
    function checkForm() {
	    var passwd = document.mypageForm.passwd;
	    var user_passwd_confirm = document.mypageForm.user_passwd_confirm;
	    // 비밀번호 입력 유무 체크
	    if(document.mypageForm.passwd.value.length != 0){
		    	if(document.mypageForm.user_passwd_confirm.value.length == 0){
		        alert('새로운 비밀번호를 입력하세요.');
		        newpasswd.focus();
		        return false;
	    	}
	    }
	    return true;
	}
    
</script>
</html>