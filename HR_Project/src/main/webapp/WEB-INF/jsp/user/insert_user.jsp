<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/insert_user.css">
</head>
<body>
<%
LocalDate currentDate = LocalDate.now();		//localdate로 오늘 날짜를 구함
String today = currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));		//구한 오늘 날짜를 String형으로 변환
%>
<form id="insert_frm" action="./user/insert_logic" method="post">
	<h2>회원 추가</h2>
	<table class="insert_table">
		<tr>
			<td>아이디</td>
			<td>
				<input type="text" id="username" name="username" maxlength="15" onfocusout="check_username()">
				<div class="validation" id="username_validation"></div>
			</td>
					
		</tr>
		<tr>
			<td>비밀번호</td>
			<td>
				<input type="password" id = "password" name="password" onfocusout="check_password()">
				<div class="validation" id="password_validation"></div>
			</td>
		</tr>
		<tr>
			<td>비밀번호 확인</td>
			<td>
				<input type="password" id = "password_confirm" name="password_confirm" onfocusout="check_password()">
				<div class="validation" id="password_confirm_validation"></div>
			</td>
		</tr>
		<tr>
			<td>이름</td>
			<td>
				<input type="text" id="name" name="name" maxlength="8" onfocusout="check_name()">
				<div class="validation" id="name_validation"></div>
			</td>
		</tr>
		<tr>
			<td>생년월일</td>
			<td>
				<input type="date" id="birth" name="birth" onfocusout="check_bitrh()">
				<div class="validation" id="birth_validation"></div>
			</td>
		</tr>
		<tr>
			<td>연락처</td>
			<td style="display : block;">
				<div class="input_section">
					<select name="telnum1" id="telnum1">
					    <option value="010">010</option>
					    <option value="011">011</option>
					    <option value="017">017</option>
					</select>
					<p>-</p><input type="number" name="telnum2" id="telnum2" maxLength="4" oninput="next_focus()">
					<p>-</p><input type="number" name="telnum3" id="telnum3" maxLength="4" oninput="next_focus()">
				</div>
				<div class="validation" id="tel_validation"></div>
				<input type="hidden" id="tel" name="tel" >
			</td>
		</tr>
		<tr>
			<td>이메일</td>
			<td style="display : block;">
				<div class="input_section">
					<input type="text" id="e_mail_front" name="e_mail_front" onfocusout="check_e_mail()">
					<p>@</p>
					<select id="e_mail_end" name="e_mail_end">
						<option value="naver">naver.com</option>
						<option value="gmail">gmail.com</option>
					</select>
				</div>
				<div class="validation" id="e_mail_validation"></div>
				<input type="hidden" id="e_mail" name="e_mail">
			</td>
		</tr>
		<tr>
			<td>성별</td>
			<td>
				<input type="radio" name="S_type" value="남" checked="checked"/>남
				<input type="radio" name="S_type" value="여">여
			</td>
		</tr>
	</table>
	<input type="hidden" name="created" value="<%=today %>">
</form>
<button onclick="insert()">등록</button>
<button onclick="location.href='./go_user'">목록</button>

<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="${pageContext.request.contextPath}/js/insert_user.js"></script>

</body>
</html>