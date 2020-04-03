<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/user.css">
</head>
<body>

<!-- 조건 정하는 화면 -->
<h2>회원관리</h2>
<hr>
<section>
	<table class="filter">
		<tr>
			<td>회원 조회</td>
			<td>
				<div style="display : flex;">
					<div>
						<select name="select_column" id="select_column">
							<option value="all">전체 조회</option>
							<option value="username">아이디</option>
							<option value="name">이름</option>
							<option value="e_mail">이메일</option>
							<option value="tel">연락처</option>
							<option value="birth">생년월일</option>
							<option value="S_type">성별</option>
							<option value="created">가입일자</option>
						</select>
					</div>	
					<div><input type="text" id="find_str"></div>
					<div><button onclick="saveparam(), sendparam(0,1)">조회</button></div>
				</div>
			</td>
		</tr>
	</table>
</section>

<section id="list">
	<div style="text-align: left;">
		<h3>회원 정보 리스트 </h3>
	</div>
	<!-- 전체 리스트가 출력되는 테이블 생성   -->
	<table id="user_list">
		<tr>
			<td><input type="checkbox" id="all_chk" onclick="allchk()"></td>
			<td style="width : 30px;"></td>
			<td><p align=center>아이디</p></td>
			<td><p align=center>이름</p></td>
			<td><p align=center>이메일</p></td>
			<td><p align=center>연락처</p></td>
			<td><p align=center>생년월일</p></td>
			<td><p align=center>성별</p></td>
			<td><p align=center>가입일자</p></td>
		</tr>
		<tr>
			<td style="background-color: wheat;"></td>
			<td style="background-color: wheat;"></td>
			<td colspan="7"></td>
		</tr>
	</table>
</section>



<div style="display: flex; margin : 10px;" >
	<div style="margin-right: 10px;"><button id="delete_user" onclick="delete_user()">선택 삭제</button></div>
	<div><button id="go_insert" onClick="location.href='./go_insert_user'">회원 추가</button></div>
</div>




<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="${pageContext.request.contextPath}/js/user_page.js"></script>

<script>

</script>

</body>
</html>