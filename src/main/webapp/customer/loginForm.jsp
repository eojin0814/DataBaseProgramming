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
<form method="POST" action="<c:url value='/customer/login' />">
<input type="text" name="userId" placeholder="id">
<input type="text" name="password" placeholder="password">
<button type="submit">로그인</button>
<a href="<c:url value='/customer/register' />" >회원 가입 </a>  
</form>
</body>
</html>