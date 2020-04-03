<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
</head>
<body>

<header class="menubar">
	<div id="menu_div" ><a id="menu" href="./">Menu</a></div>
	<div id="member_div" ><a id="member" href="./go_user">회원관리</a></div>	
	<div id="staff_div"><a id="staff" href="#" onclick="change_content('m')">인사</a></div>
	<div id="dummy_div"><a id="dummy" href="./user/dummy" >더미 데이터</a></div>
</header>


<section>
	<div id="content"></div>
</section>


<script  src="http://code.jquery.com/jquery-latest.min.js"></script>

<script type="text/javascript">
	$( document ).ready(function() {
		if("${type}" === "go_user"){
			$("#content").load("${pageContext.request.contextPath}/user"); 
		}else if("${type}" === "user_insert"){
			$("#content").load("${pageContext.request.contextPath}/user/user_insert"); 
		}
	});
</script>
	
</body>
</html>