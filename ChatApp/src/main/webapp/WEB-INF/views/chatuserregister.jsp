<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="en">

<head>

<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<meta charset="utf-8">
<title>Fullscreen Responsive Register Template</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<!-- CSS -->
<link rel='stylesheet'
	href='http://fonts.googleapis.com/css?family=PT+Sans:400,700' />
<link rel='stylesheet'
	href='http://fonts.googleapis.com/css?family=Oleo+Script:400,700' />
<link rel="stylesheet"
	href="resources/assets/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="resources/assets/css/style.css" />
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
</head>

<body>

	<div class="container" style="margin-top: 1%; margin-left: 90%;">
		<a href="<c:url value='index'/>" rel="tooltip"
			class="btn btn-info btn-lg"> <span
			class="glyphicon glyphicon-home">Home</span>
		</a>
	</div>
	<div class="well">${msg}</div>
	<div class="register-container container">
		<div class="row">
			<div class="iphone span5">
				<img src="resources/assets/img/iphone.png" alt="">
			</div>
			<div class="register span6">
				<form:form action="register" method="post" modelAttribute="cht"
					enctype="multipart/form-data">

					<h2>
						REGISTER TO <span class="red"><strong>Kites Chat</strong></span>
					</h2>
					<form:label for="firstname" path="username">First Name</form:label>
					<form:input type="text" path="username" id="firstname"
						name="firstname" placeholder="enter your first name..." />
					<form:errors path="username" cssErrorClass="error" element="div" />
					<form:label for="lastname" path="">mobnum</form:label>
					<form:input type="text" id="lastname" path="mobnum" name="lastname"
						placeholder="enter your first name..." />

					<form:label for="email" path="mailId">Mail Id</form:label>
					<form:input type="email" id="username" path="mailId"
						name="username" placeholder="choose a username..." />

					<form:label for="password" path="pass">Password</form:label>
					<form:input type="password" path="pass" id="password"
						name="password" placeholder="choose a password..." />

					<form:label for="password" path="cpass">cPassword</form:label>
					<form:input type="password" path="cpass" id="password"
						name="password" placeholder="choose a password..." />
                     image
                     	<form:input type="file" path="file1" />

					<form:button type="submit">REGISTER</form:button>
				</form:form>
			</div>
		</div>
	</div>

	<!-- Javascript -->
	<script src="resources/assets/js/jquery-1.8.2.min.js"></script>
	<script src="resources/assets/bootstrap/js/bootstrap.min.js"></script>
	<script src="resources/assets/js/jquery.backstretch.min.js"></script>
	<script src="resources/assets/js/scripts.js"></script>

</body>

</html>

