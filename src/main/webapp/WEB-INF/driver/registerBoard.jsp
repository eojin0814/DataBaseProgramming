<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<form  method="POST" action="<c:url value='/driver/register/board' />">
<input type="text" name="driverId" placeholder="id">
<input type="text" name="arrival" placeholder="arrival">
<input type="text" name="departure" placeholder="departure">
<input type="text" name="departureTime" placeholder="departureTime">
<input type="text" name="arrivalTime" placeholder="arrivalTime">
<input type="text" name="carShareDate" placeholder="carShareDate">
<input type="text" name="headCount" placeholder="headCount">
<button type="submit">등록</button>
</form>
</body>
</html>