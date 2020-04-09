<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/insert_user.css">
<style type="text/css">
#user_info_table {
	border-top: 1px solid;
	border-left: 1px solid;
	border-spacing: 0px;
}

#user_info_table tr td {
	border-right: 1px solid;
	border-bottom: 1px solid;
}

#update_password {
	background-color: gainsboro;
}
</style>
</head>
<body>

<h2>회원 상세 정보</h2>

<form id="update_frm" action="../user/update_user" method="post">
	<table id="user_info_table">
		<tr>
			<td>아이디</td>
			<td><p id="update_username" name="update_username">${user.username}</p>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><input type="password" id="update_password" name="update_password" value="${user.password}" readonly="readonly"/></td>
		</tr>
		<tr>
			<td>이름</td>
			<td>
				<input type="text" id="update_name" name="update_name" maxlength="8" value="${user.name}" onfocusout="check_name()">
				<div class="validation" id="name_validation"></div>
			</td>
		</tr>
		<tr>
			<td>생년월일</td>
			<td><input type="date" id="update_birth" name="update_birth" value="<fmt:formatDate value="${user.birth}" pattern="yyyy-MM-dd"/>" onfocusout="check_bitrh()">
				<div class="validation" id="birth_validation"></div></td>
		</tr>
		<tr>
			<td>연락처</td>
			<td style="display: block;">
				<div class="input_section">
					<select name="telnum1" id="telnum1">
						<option value="010">010</option>
						<option value="011">011</option>
						<option value="017">017</option>
					</select>
					<p>-</p>
					<input type="number" name="telnum2" id="telnum2" maxLength="4"
						oninput="next_focus()">
					<p>-</p>
					<input type="number" name="telnum3" id="telnum3" maxLength="4"
						oninput="next_focus()">
				</div>
				<div class="validation" id="tel_validation"></div> 
				<input type="hidden" id="update_tel" name="update_tel">
			</td>
		</tr>
		<tr>
			<td>이메일</td>
			<td style="display: block;">
				<div class="input_section">
					<input type="text" id="eMail_front" name="eMail_front"
						onfocusout="check_eMail()">
					<p>@</p>
					<select id="eMail_end" name="eMail_end">
						<option value="naver.com">naver.com</option>
						<option value="gmail.com">gmail.com</option>
					</select>
				</div>
				<div class="validation" id="eMail_validation"></div> <input
				type="hidden" id="update_eMail" name="update_eMail">
			</td>
		</tr>
		<tr>
			<td>성별</td>
			<td>
				<input type="radio" id="man" name="update_sType" value="남">남 
				<input type="radio" id="woman" name="update_sType" value="여">여</td>
		</tr>
	</table>
	<input type="hidden" id="id" name="id" value="${user.id}">
	<input type="hidden" id="username" name="username" value="${user.username}">
	<input type="hidden" id="password" name="password" value="${user.password}">
	<input type="hidden" id="name" name="name" value="${user.name}">
	<input type="hidden" id="birth" name="birth" value="${user.birth}">
	<input type="hidden" id="tel" name="tel" value="${user.tel}">
	<input type="hidden" id="sType" name="sType" value="${user.sType}">
	<input type="hidden" id="created" name="created" value="${user.created}">
</form>


	<button id="delete_user" class = "btn" onclick="location.href='../user/delete_user/${user.username}'">삭제</button>
	<button class = "btn" onclick="update()">수정</button>
	<button class = "btn" onclick="location.href='../go_user'">목록</button>


	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/user_page.js"></script>
	<script src="${pageContext.request.contextPath}/js/update_user.js"></script>

<script>
	//데이터 갖고오는 js문
	$( document ).ready(function() {
		//남, 녀 데이터 체크
	    if('${user.sType}' === '남'){
			document.getElementById('man').checked = true; 	    
		}else{
			document.getElementById('woman').checked = true;
		}

		//전화번호 입력하기
		let tel1 = '${user.tel}'.substring(0,3);
		let tel2 = '${user.tel}'.substring(4,8);
		let tel3 = '${user.tel}'.substring(9,13);
		let telnum1 = document.getElementById('telnum1');

		for(let i =0; i < telnum1.length; i++){
			if(telnum1.options[i].value === tel1){
				telnum1.options[i].checked = true;
			}
		}
		document.getElementById('telnum2').value = tel2; 
		document.getElementById('telnum3').value = tel3;

		//이메일 가져오기
		console.log('${user.eMail}');
		console.log('${user.eMail}'.length);
		console.log('${user.eMail}'.indexOf('@'));
		console.log('${user.eMail}'.substring('${user.eMail}'.indexOf('@')+1,'${user.eMail}'.length));

		var eMail_front = '${user.eMail}'.substring(0,'${user.eMail}'.indexOf('@'));
		var eMail_end =  document.getElementById('eMail_end');
		var eMail_param = '${user.eMail}'.substring('${user.eMail}'.indexOf('@')+1,'${user.eMail}'.length);

		console.log(eMail_front);
		//console.log(eMail_end);
		//console.log(eMail_param);
		
		document.getElementById('eMail_front').value = eMail_front;
		
		for(var j = 0; j < eMail_end.length; j++){
			if(eMail_end.options[j].value === eMail_param){
				eMail_end.options[j].checked = true; 
			}
		}
	});
</script>

</body>
</html>