<!DOCTYPE html>
<html lang="en">
<head>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="<c:url value='resources/css/style.css'/>" rel="stylesheet">
<meta charset="UTF-8">
<title>Angular Sort and Filter</title>

<!-- CSS -->
<style type="text/css">
.hovereffect {
	width: 100%;
	height: 100%;
	float: left;
	overflow: hidden;
	position: relative;
	text-align: center;
	cursor: default;
}

.hovereffect .overlay {
	width: 100%;
	height: 100%;
	position: absolute;
	overflow: hidden;
	top: 0;
	left: 0;
}

.hovereffect img {
	display: block;
	position: relative;
	-webkit-transform: scale(1.1);
	-ms-transform: scale(1.1);
	transform: scale(1.1);
	-webkit-transition: all 0.35s;
	transition: all 0.35s;
}

.hovereffect:hover img {
	-webkit-transform: scale(1);
	-ms-transform: scale(1);
	transform: scale(1);
	filter:
		url('data:image/svg+xml;charset=utf-8,<svg xmlns="http://www.w3.org/2000/svg"><filter id="filter"><feComponentTransfer color-interpolation-filters="sRGB"><feFuncR type="linear" slope="0.7" /><feFuncG type="linear" slope="0.7" /><feFuncB type="linear" slope="0.7" /></feComponentTransfer></filter></svg>#filter');
	filter: brightness(0.7);
	-webkit-filter: brightness(0.7);
}

.hovereffect h2 {
	text-transform: uppercase;
	color: #fff;
	text-align: center;
	font-size: 17px;
	padding: 10px;
	width: 100%;
}

.hovereffect:hover h2 {
	opacity: 0;
	filter: alpha(opacity = 0);
	-webkit-transform: translate3d(-50%, -50%, 0) scale3d(0.8, 0.8, 1);
	transform: translate3d(-50%, -50%, 0) scale3d(0.8, 0.8, 1);
}

.hovereffect a.info {
	display: inline-block;
	text-decoration: none;
	padding: 7px 14px;
	text-transform: uppercase;
	color: #fff;
	border: 1px solid #fff;
	margin: 50px 0 0 0;
	background-color: transparent;
}

.hovereffect a.info:hover {
	box-shadow: 0 0 5px #fff;
}

.hovereffect .rotate {
	-webkit-transform: rotate(-45deg);
	-ms-transform: rotate(-45deg);
	transform: rotate(-45deg);
	width: 100%;
	height: 100%;
	position: absolute;
}

.hovereffect hr {
	width: 50%;
	opacity: 0;
	filter: alpha(opacity = 0);
}

.hovereffect  hr:nth-child(2) {
	-webkit-transform: translate3d(-50%, -50%, 0) rotate(0deg)
		scale3d(0, 0, 1);
	transform: translate3d(-50%, -50%, 0) rotate(0deg) scale3d(0, 0, 1);
}

.hovereffect  hr:nth-child(3) {
	-webkit-transform: translate3d(-50%, -50%, 0) rotate(90deg)
		scale3d(0, 0, 1);
	transform: translate3d(-50%, -50%, 0) rotate(90deg) scale3d(0, 0, 1);
}

.hovereffect h2, .hovereffect hr {
	position: absolute;
	top: 50%;
	left: 50%;
	-webkit-transition: opacity 0.35s, -webkit-transform 0.35s;
	transition: opacity 0.35s, transform 0.35s;
	-webkit-transform: translate3d(-50%, -50%, 0);
	transform: translate3d(-50%, -50%, 0);
	-webkit-transform-origin: 50%;
	-ms-transform-origin: 50%;
	transform-origin: 50%;
	background-color: transparent;
	margin: 0px;
}

.group1, .group2 {
	left: 50%;
	position: absolute;
	-webkit-transition: opacity 0.35s, -webkit-transform 0.35s;
	transition: opacity 0.35s, transform 0.35s;
	-webkit-transform: translate3d(-50%, -50%, 0);
	transform: translate3d(-50%, -50%, 0);
	-webkit-transform-origin: 50%;
	-ms-transform-origin: 50%;
	transform-origin: 50%;
	background-color: transparent;
	margin: 0px;
	padding: 0px;
}

.group1 {
	top: 40%;
}

.group2 {
	top: 60%;
}

.hovereffect p {
	width: 60%;
	text-transform: none;
	font-size: 15px;
	line-height: 2;
}

.hovereffect p a {
	color: #fff;
}

.hovereffect p a:hover, .hovereffect p a:focus {
	opacity: 0.6;
	filter: alpha(opacity = 60);
}

.hovereffect  a i {
	opacity: 0;
	filter: alpha(opacity = 0);
	-webkit-transition: opacity 0.35s, -webkit-transform 0.35s;
	transition: opacity 0.35s, transform 0.35s;
	padding: 10px;
	font-size: 20px;
}

.group1 a:first-child i {
	-webkit-transform: translate3d(-60px, -60px, 0) rotate(45deg) scale(2);
	transform: translate3d(-60px, -60px, 0) rotate(45deg) scale(2);
}

.group1 a:nth-child(2) i {
	-webkit-transform: translate3d(60px, -60px, 0) rotate(45deg) scale(2);
	transform: translate3d(60px, -60px, 0) rotate(45deg) scale(2);
}

.group2 a:first-child i {
	-webkit-transform: translate3d(-60px, 60px, 0) rotate(45deg) scale(2);
	transform: translate3d(-60px, 60px, 0) rotate(45deg) scale(2);
}

.group2 a:nth-child(2) i {
	-webkit-transform: translate3d(60px, 60px, 0) rotate(45deg) scale(2);
	transform: translate3d(60px, 60px, 0) rotate(45deg) scale(2);
}

.hovereffect:hover hr:nth-child(2) {
	opacity: 1;
	filter: alpha(opacity = 100);
	-webkit-transform: translate3d(-50%, -50%, 0) rotate(0deg)
		scale3d(1, 1, 1);
	transform: translate3d(-50%, -50%, 0) rotate(0deg) scale3d(1, 1, 1);
}

.hovereffect:hover hr:nth-child(3) {
	opacity: 1;
	filter: alpha(opacity = 100);
	-webkit-transform: translate3d(-50%, -50%, 0) rotate(90deg)
		scale3d(1, 1, 1);
	transform: translate3d(-50%, -50%, 0) rotate(90deg) scale3d(1, 1, 1);
}

.hovereffect:hover .group1 i:empty, .hovereffect:hover .group2 i:empty {
	-webkit-transform: translate3d(0, 0, 0);
	transform: translate3d(0, 0, 0) rotate(45deg) scale(1);
	opacity: 1;
	filter: alpha(opacity = 100);
}
Close
</style>

<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootswatch/3.2.0/sandstone/bootstrap.min.css">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css">
<style>
body {
	padding-top: 50px;
}
</style>


<!-- JS -->

<script
	src="http://ajax.googleapis.com/ajax/libs/angularjs/1.2.23/angular.min.js"></script>

<script type="text/javascript">
angular.module('sortApp',[]).controller('mainController',function($scope,$http){
	//alert("hi");
	
		console.log("im in getProduct");
		$http({method:'GET',url:'getAlluser'}).success(function(data,status,headers,config)
				
		{
		//alert(data);	
			//$scope.categ=JSON.parse(JSON.stringify(data));
			$scope.categ=data;
			console.log("length"+categ.length);
			for(var c=0;c<$scope.categ.length;c++)
				{
					var cc=$scope.categ[c];
					console.log(cc);
					console.log($scope.categ);
					return $scope.categ;
				}
			
			
		});
	
});


</script>

</head>
<body>

<div class="container">
		<a href="<c:url value='index'/>" rel="tooltip"
			class="btn btn-primary"> &nbsp; <span
			class="glyphicon glyphicon-home">&nbsp; Home</span>
		</a>
	</div>
	<br/>
	<div class="container">
			<div class="row">
				<div class="section-title text-center">
					<h3>Friend list</h3>
				</div>
				<p align="center">click add button to add them to your group</p>
			</div>
		</div>
	<div class="container" ng-app="sortApp" ng-controller="mainController">
		<form>
			<div class="form-group">
				<div class="input-group">
					<div class="input-group-addon">
						<i class="fa fa-search"></i>
					</div>
					<input type="text" class="form-control" placeholder="search" 
						ng-model="username">
				</div>
			</div>
		</form>


		
		<div ng-repeat="c in categ | filter : username">
			<div class="col-lg-3 col-md-4 col-sm-6 col-xs-12">
				<div class="hovereffect">
					<img src="${pageContext.request.contextPath}{{c.fpath}}"
						class="img-responsive" alt="" height="240px" width="300px">
						<br/><br/>
					<div class="overlay">
						<h2 style="color: gold;font-weight: 300;font-size: x-large;
    font-weight: bold;">{{c.username}}</h2>
						<div class="rotate">
							<p class="group1">
								<a href="https://twitter.com/login" target="_blank"> <i class="fa fa-twitter"></i>
								</a> <a href="https://www.facebook.com/login/" target="_blank"> <i class="fa fa-facebook"></i>
								</a>
							</p>
							<hr>
							<hr>
							<p class="group2">
								<a href="https://www.instagram.com/accounts/login/?force_classic_login" target="_blank"> <i class="fa fa-instagram"></i>
								</a> <a href="https://dribbble.com/tags/login" target="_blank"> <i class="fa fa-dribbble"></i>
								</a>
							</p>
							<p class="btn-primary" style=" margin-left: 26%; margin-top: -3%;">
								<a href="{{c.userId}}" style="height: 30px;width:60px; font-weight:bold; text-decoration:none; ">add</a>
							</p>
						</div>
					</div>
				</div>
			
			</div>

		</div>

	</div>

</body>
</html>
