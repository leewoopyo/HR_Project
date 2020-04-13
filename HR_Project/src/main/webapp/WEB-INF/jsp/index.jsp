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
	<div id="menu_div" ><a id="menu" href="${pageContext.request.contextPath}/">Menu</a></div>
	<div id="member_div" ><a id="member" href="${pageContext.request.contextPath}/go_user">회원관리</a></div>	
	<div id="staff_div"><a id="staff" href="${pageContext.request.contextPath}/go_hr">인사</a></div>
	<div id="dummy_div"><a id="dummy" href="${pageContext.request.contextPath}/user/dummy" >더미 데이터</a></div>
</header>

<section>
	<div id="content"></div>
</section>

<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="https://unpkg.com/ionicons@5.0.0/dist/ionicons.js"></script>

<script type="text/javascript">

//index페이지 로드 시 서버에서 받은 model값을 보고 해당 페이지를 div에 출력
	$( document ).ready(function() {
		if("${type}" === "go_user"){
			$("#content").load("${pageContext.request.contextPath}/user/list"); 
		}else if("${type}" === "user_insert"){
			$("#content").load("${pageContext.request.contextPath}/user/user_insert"); 
		}else if("${type}" === "user_info"){
			$("#content").load("${pageContext.request.contextPath}/user/user_info/${username}"); 
		}else if("${type}" === "go_hr"){
			$("#content").load("${pageContext.request.contextPath}/hr");
		}
	});

	//선택할 검색 조건
	var select_column;
	//검색할 검색어 
	var find_str;
	//selected 뽑는 변수(검색 조건)
	var val = 'all';
	//체크 리스트 저장하는 리스트
	var checked_list = [];	
	var checked_username_list = []; 
	//정렬 상태값 저장
	var sortType = 'id';
	var sortDirection = 'desc';
	
</script>
	
</body>
</html>