<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/user.css">
<style type="text/css">

</style>
</head>
<body>

<section id="content" style="text-align: center;">
	<div style="text-align: left;"><h3>회원 정보 리스트 </h3></div>
		<!-- 전체 리스트가 출력되는 테이블 생성   -->
		<table id="user_list">
			<tr>
				<td><input type="checkbox" id="all_chk" onclick="allchk()"></td>
				<td></td>
				<td>
					<div style="display: inline-flex;" onclick="sort('username'),select_icon('username',0), sendparam(${pageNum},${startPage})" >
						<a>아이디</a>
						<ion-icon id="caret-down-outline" name="caret-down-outline" class="icon"></ion-icon>
						<ion-icon id="caret-up-outline" name="caret-up-outline" class="icon"></ion-icon>
					</div>
				</td>
				<td>
					<div style="display: inline-flex;" onclick="sort('name'),select_icon('name',2), sendparam(${pageNum},${startPage})">
						<a  >이름</a>
						<ion-icon id="caret-down-outline" name="caret-down-outline" class="icon"></ion-icon>
						<ion-icon id="caret-up-outline" name="caret-up-outline" class="icon"></ion-icon>
					</div>
				</td>
				<td>
					<div style="display: inline-flex;" onclick="sort('eMail'),select_icon('eMail',4), sendparam(${pageNum},${startPage})">
						<a>이메일</a>
						<ion-icon id="caret-down-outline" name="caret-down-outline" class="icon"></ion-icon>
						<ion-icon id="caret-up-outline" name="caret-up-outline" class="icon"></ion-icon>
					</div>
				</td>
				<td>
					<div style="display: inline-flex;" onclick="sort('tel'),select_icon('tel',6), sendparam(${pageNum},${startPage})" >
						<a>연락처</a>
						<ion-icon id="caret-down-outline" name="caret-down-outline" class="icon"></ion-icon>
						<ion-icon id="caret-up-outline" name="caret-up-outline" class="icon"></ion-icon>
					</div>
				</td>
				<td>
					<div style="display: inline-flex;" onclick="sort('birth'),select_icon('birth',8), sendparam(${pageNum},${startPage})" >
						<a>생년월일</a>
						<ion-icon id="caret-down-outline" name="caret-down-outline" class="icon"></ion-icon>
						<ion-icon id="caret-up-outline" name="caret-up-outline" class="icon"></ion-icon>
					</div>
				</td>
				<td>
					<div style="display: inline-flex;" onclick="sort('sType'),select_icon('sType',10), sendparam(${pageNum},${startPage})" >
						<a>성별</a>
						<ion-icon id="caret-down-outline" name="caret-down-outline" class="icon"></ion-icon>
						<ion-icon id="caret-up-outline" name="caret-up-outline" class="icon"></ion-icon>
					</div>
				</td>
				<td>
					<div style="display: inline-flex;"  onclick="sort('created'),select_icon('created',12), sendparam(${pageNum},${startPage})" >
						<a>가입일자</a>
						<ion-icon id="caret-down-outline" name="caret-down-outline" class="icon"></ion-icon>
						<ion-icon id="caret-up-outline" name="caret-up-outline" class="icon"></ion-icon>
					</div>
				</td>
			</tr>
			<c:choose>
				<c:when test="${pageCnt == 0}">
					<tr>
						<td style="background-color: wheat;"></td>
						<td style="background-color: wheat;"></td>
						<td colspan="7">검색된 데이터가 없습니다.</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach items="${list}" var="e" varStatus="status">
						<tr style="height : 50px;" class="list_td"> 
							<td style="background-color: wheat;"><input type="checkbox" class="chk_box"></td>
							<td style="background-color: wheat;"><p align=center class="index_num">${(status.index+1)+(pageNum*10)}</p></td>
							<td><p align=center class="username_list"><a href="./go_user_info/${e.username}">${e.username}</a></p></td>
							<td><p align=center>${e.name}</p></td>
							<td><p align=center>${e.eMail}</p></td>
							<td><p align=center>${e.tel}</p></td>
							<td><p align=center><fmt:formatDate value="${e.birth}" pattern="yyyy-MM-dd"/></p></td>
							<td><p align=center>${e.sType}</p></td>
							<td><p align=center><fmt:formatDate value="${e.created}" pattern="yyyy-MM-dd"/></p></td>
						</tr>	
					</c:forEach>
					<tr id="blank">
						<td style="background-color: wheat;"></td>
						<td style="background-color: wheat;"></td>
						<td colspan="7"></td>
					</tr>
				</c:otherwise>
			</c:choose>
		</table>
</section>

<div id="pagination" class="pagination">
	<c:set var="totalPageCnt" scope="session" value="${pageCnt}"/>
	<c:set var="pageStart" value="${startPage}"/>
	<c:set var="perPage" scope="session"  value="5"/>
	
	<c:choose>
		<c:when test="${pageStart == 1}">
		</c:when>
		<c:otherwise>		
	    	<a href="#" onclick="sendparam(${pageStart - perPage-1},${pageStart - perPage}), save_chk()">&laquo;</a>
		</c:otherwise>
	</c:choose>
	
	<c:choose>
		<c:when test="${pageCnt-pageStart < perPage}">
			<c:forEach var="i" begin="${pageStart}" end="${pageStart + ((pageCnt-1)%perPage)}" varStatus="status">
				<a class="page" href="#" onclick="sendparam(${status.index}-1,${startPage}),save_chk()">${status.index}</a>
			</c:forEach>
		</c:when>
		<c:otherwise>		
	    	<c:forEach var="i" begin="${pageStart}" end="${pageStart + perPage -1}" varStatus="status">
				<a class="page" href="#" onclick="sendparam(${status.index}-1,${pageStart}),save_chk()">${status.index}</a>
			</c:forEach>
		</c:otherwise>
	</c:choose>
	
	<c:choose>
		<c:when test="${pageCnt-pageStart < perPage}">
		</c:when>
		<c:otherwise>		
	    	<a href="#" onclick="sendparam(${pageStart + perPage-1},${pageStart + perPage}),save_chk()">&raquo;</a> 
		</c:otherwise>
	</c:choose>
</div>

<script src="${pageContext.request.contextPath}/js/user_list.js"></script>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>

<script type="text/javascript">
//데이터가 없을 때 처리할 부분
$( document ).ready(function() {
	
	let blank = document.getElementById("blank");
	let list_td = document.getElementsByClassName("list_td");
	let chk_box = document.getElementsByClassName('chk_box');
	let index_num = document.getElementsByClassName('index_num');
	let pageNum = ${pageNum};
	let list_length = checked_list.length;

	//리스트 테이블에 데이터가 10개 모두 있으면 빈공간 채우는 테이블을 없앤다.
	if(list_td.length === 10){
		blank.style.display = 'none';
	}
	
	//리스트를 보고 체크하게 하는 기능
	for(let i = 0;i < checked_list.length;i++){
		for(let j=0;j < index_num.length;j++){
			if(checked_list[i] === index_num[j].innerHTML){
				chk_box[j].checked = true; 
			}
		}
	}

	//해당 페이지 리스트 초기화
	for(let i=0; i < list_length; i++){
		for(let j = (pageNum*10)+1; j < (pageNum*10)+11 ; j++ ){
			if(checked_list[i] === j.toString()){
				checked_list.splice(i,1);
				checked_username_list.splice(i,1);
			}
		}
	}

	//정렬 정보 담기
	sortType = '${sortType}';
	sortDirection = '${sortDirection}';

	//정렬 화살표 정보 출력
	let icon = document.getElementsByClassName('icon');
	
	if(sortType === icon_type){
		if(sortDirection === 'desc'){
			icon[icon_direction].style.display = 'block';
		}else if(sortDirection === 'asc'){
			icon[icon_direction+1].style.display = 'block';
		}
	}else if(icon_type === 'init'){
/* 		sortType = 'id';
		sortDirection = 'desc'; */
		for(let i=0;i < icon.length;i++){
			icon[i].style.display = 'none';
		}
	}
	
});

</script>

</body>
</html>