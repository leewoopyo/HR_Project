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
</head>
<body>

<section id="content" style="text-align: center;">
	<div style="text-align: left;"><h3>회원 정보 리스트 </h3></div>
		<!-- 전체 리스트가 출력되는 테이블 생성   -->
		<table id="user_list">
			<tr>
				<td><input type="checkbox" id="all_chk" onclick="allchk()"></td>
				<td></td>
				<td><p align=center>아이디</p></td>
				<td><p align=center>이름</p></td>
				<td><p align=center>이메일</p></td>
				<td><p align=center>연락처</p></td>
				<td><p align=center>생년월일</p></td>
				<td><p align=center>성별</p></td>
				<td><p align=center>가입일자</p></td>
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
							<td style="background-color: wheat;"><p align=center>${status.index+1}</p></td>
							<td><p align=center class="username_list">${e.username}</p></td>
							<td><p align=center>${e.name}</p></td>
							<td><p align=center>${e.e_mail}</p></td>
							<td><p align=center>${e.tel}</p></td>
							<td><p align=center><fmt:formatDate value="${e.birth}" pattern="yyyy-MM-dd"/></p></td>
							<td><p align=center>${e.s_type}</p></td>
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

<%-- <div id="pagination" class="pagination">
	
	${pageCnt}
	
	<c:set var="totalPageCnt" scope="session" value="${pageCnt}"/>
	<c:set var="pageStart" value="${startPage}"/>
	<c:set var="perPage" scope="session"  value="5"/>
	<c:if test="${empty pageStart or pageStart < 0}">
    	<c:set var="pageStart" value="0"/>
	</c:if>
	
	<c:if test="${totalPageCnt < pageStart}">
       <c:set var="pageStart" value="${pageStart - 5}"/>
	</c:if>
	
	<a href="#" onclick="sendparam(${pageStart - 5})">&laquo;</a>
	
	    <c:forEach var="i" begin="${pageStart+1}" end="${pageStart + perPage}" varStatus="status">
			<a class="page" href="#" onclick="sendparam(${status.index}-1)">${status.index}</a>
		</c:forEach>
		
    <a href="#" onclick="sendparam(${pageStart + 5})">&raquo;</a>  
</div> --%>


<div id="pagination" class="pagination">
	
	<c:set var="totalPageCnt" scope="session" value="${pageCnt}"/>
	<c:set var="pageStart" value="${startPage}"/>
	<c:set var="perPage" scope="session"  value="5"/>
	
	<%-- 페이지네이션 시작 부분 --%>
	<c:choose>
		<c:when test="${pageStart == 1}">
			<c:forEach var="i" begin="${pageStart}" end="${pageStart + perPage-1}" varStatus="status">
				<a class="page" href="#" onclick="sendparam(${status.index}-1,${startPage})">${status.index}</a>
			</c:forEach>
	    	<a href="#" onclick="sendparam(${pageStart + perPage-1},${pageStart + perPage})">&raquo;</a> 
		</c:when>
		
		<%-- 페이지네이션 마지막 부분 --%>
		<c:when test="${pageCnt/pageStart < 2 and pageCnt % pageStart < perPage}">
			<a href="#" onclick="sendparam(${pageStart - perPage -1},${pageStart - perPage})">&laquo;</a>
		    <c:forEach var="i" begin="${pageStart}" end="${pageStart + (pageCnt % pageStart)}" varStatus="status">
				<a class="page" href="#" onclick="sendparam(${status.index}-1,${startPage})">${status.index}</a>
			</c:forEach>
		</c:when>
		
		<%-- 페이지네이션 중간 부분 --%>
		<c:otherwise>
			<a href="#" onclick="sendparam(${pageStart - perPage-1},${pageStart - perPage})">&laquo;</a>
		
		    <c:forEach var="i" begin="${pageStart}" end="${pageStart + perPage -1}" varStatus="status">
				<a class="page" href="#" onclick="sendparam(${status.index}-1,${startPage})">${status.index}</a>
			</c:forEach>
			
			<a href="#" onclick="sendparam(${pageStart + perPage-1},${pageStart + perPage})">&raquo;</a>
			
		</c:otherwise>
	</c:choose>
</div>


<script src="${pageContext.request.contextPath}/js/user_list.js"></script>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>

<script type="text/javascript">

$( document ).ready(function() {

	let blank = document.getElementById("blank");
	let list_td = document.getElementsByClassName("list_td");

	if(list_td.length === 10){
		blank.style.display = 'none';
	}
});


</script>

</body>
</html>