<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ko-kr">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>커뮤니티 관리 - 목록</title>
<!-- Bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">  
	<br>
	<h4>보드 목록</h4>
	<br>
	<table class="table table-bordered">
      <thead class="thead-inverse">
		<tr>
		  <td>DriverId</td>
		  <td>BoardId</td>
		  <td>출발시간</td>
		  <td>도착시간</td>
		  <td>출발지</td>
		  <td>도착지</td>
		  <td>최종 탑승 인원</td>
		</tr>
      </thead>
      <tbody>  	 
		<c:forEach var="board" items="${boardList}">
			<tr>
			  <td><a href="<c:url value='/community/view'>
						      <c:param name='commId' value='${comm.id}'/>
						   </c:url>">
				  ${board.driverId}</a>
			  </td>
			  <td>${board.boardId}</td>
			  <td>${board.departureTime}</td>
			  <td>${board.arrivalTime}</td>
			  <td>${board.departure}</td>
			    <td>${board.arrival}</td>
			    <td>${board.headCount }</td>
			</tr>
		</c:forEach>
	  </tbody>
	</table>	  	 
	<br>   
	<a href="<c:url value='/driver/register/board/form' />" class="btn btn-primary">보드 등록</a> 
</div>
</body>
</html>