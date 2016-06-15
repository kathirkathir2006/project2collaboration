<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	Scan this to get the random qrcode and use it to activate

	<c:if test="${ver eq true}">

			<% Thread.currentThread().sleep(10000); %>
			
<c:redirect url="userLogin">
		</c:redirect>
	</c:if>
	<img src="<c:url value='/resources/images/qrcode/${qr}'/>" />

	<form action="qrverify" method="post">
		<input type="email" name="mailid" /> <input type="password" name="otp" />
		<input type="submit" value="submit">
	</form>

after pass code  is verified<a href="<c:url value='userLogin'/>"> click
			here </a> to go to login Page or else wait for 10 seconds
	<a href="<c:url value='index'/>">home</a>
</body>
</html>