<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="<c:url value="../favicon.ico"/>">

<title>優廚 - 首頁</title>

<!-- Bootstrap core CSS -->
<link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet">

<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<link href="<c:url value="/css/ie10-viewport-bug-workaround.css"/>"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="<c:url value="/css/jumbotron.css"/>" rel="stylesheet">

<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
<script src="<c:url value="/js/ie-emulation-modes-warning.js"/>"></script>

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
<style>

.navuser {
	color: #93B7DB;
	font-size: 16px
}

</style>

</head>

<body>

	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="<c:url value="/index.jsp"/>">優廚
					youChef</a>
			</div>
			<c:choose>
				<c:when test="${empty user}">
					<div id="navbar" class="navbar-collapse collapse">



						<form class="navbar-form navbar-right"
							action="<c:url value="/member/login.controller"/>" method="POST">
							<div class="form-group">
								<input type="text" placeholder="Email" name="email"
									class="form-control">
							</div>
							<div class="form-group">
								<input type="password" placeholder="Password" name="password"
									class="form-control">
							</div>
							<button type="submit" class="btn btn-success">Sign in</button>
						</form>
					</div>
					<!--/.navbar-collapse -->
				</c:when>
				<c:otherwise>
					<div class="col-md-3 col-md-push-9">
						<p class="navuser">${user.firstName}${user.lastName}</p>
						<p class="navuser">
						<a class="navuser" href="<c:url value="/mail/inbox.controller"/>">未讀:
							<c:choose>
								<c:when test="${empty inbox}">(0)</c:when>
								<c:otherwise>${inbox}</c:otherwise>
							</c:choose>
						</a>
						 登出</p>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</nav>

	<!-- Main jumbotron for a primary marketing message or call to action -->
	
	<div class="jumbotron">
		<div class="container">
			<img src="<c:url value='/image/buddha_jump.jpg' />" class="img-responsive center-block">
		</div>
	</div>
	
	<div class="container col-md-8 col-md-offset-2">

		<h2>${registerOK}${user.lastName}</h2>



		<hr>
		<footer>
			<p>&copy; 2016 Company, Inc.</p>
		</footer>
	</div>
	<!-- /container -->


	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script>
		window.jQuery
				|| document
						.write('<script src="../js/vendor/jquery.min.js"><\/script>')
	</script>
	<script src="<c:url value="/js/bootstrap.min.js"/>"></script>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="<c:url value="/js/ie10-viewport-bug-workaround.js"/>"></script>
</body>
</html>

