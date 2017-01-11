<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh">
	<head>
		<meta http-equiv="content-type" content="text/html; charset=utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
		<meta name="description" content="">
		<meta name="author" content="">
		<link rel="icon" href="<c:url value="/images/favicon.ico"/>">
		
		<title>優廚 - 站內信箱</title>
		
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
		
		<script charset="UTF-8" src="<c:url value="/js/tinymce/tinymce.min.js"/>"></script>
		<script type="text/javascript">
			tinymce.init({ selector:'#message', language:'zh_TW'});
		</script> 
	    
		<style>		
  		body{
			background-color: #F7F7F6;
		}
    
		#navuser {
			color: #ffffff;
			font-size: 16px
		}

		.nav-pills a {
		    color: #000000
		}
		
		.nav-pills a:hover {
		    color: #000000;
		    border-radius: 5px;
		}
		
		.nav-pills .active a:hover {
		    color: #000000;
		    border-radius: 5px;
		}
		
		.nav>li>a:hover,
		.nav>li>a:focus {
		    text-decoration: none;
		    background-color: #F7B16D;
		}
		
		.nav-pills>li.active>a, 
		.nav-pills>li.active>a:focus, 
		.nav-pills>li.active>a:hover {
		   	color: #000000;
		    background-color: #F7B16D;
		}
				
		</style>

	</head>

	<body>
	<body>
		<nav class="navbar navbar-inverse navbar-fixed-top">
			<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#navbar" aria-expanded="false"
						aria-controls="navbar">
						<span class="sr-only">Toggle navigation</span> 
						<span class="icon-bar"></span> 
						<span class="icon-bar"></span> 
						<span class="icon-bar"></span>
					</button>
					<span class="in-line"><a href="<c:url value="/pages/main.jsp"/>"><img alt="logo" src="<c:url value="/images/YouChef-logo-bk.png"/>" width="64px" height="64px"></a></span>
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
								<button type="submit" class="btn btn-success">登入</button>
							</form>
						</div>
					</c:when>
				<c:otherwise>
					<div id="navbar" class="navbar-collapse collapse">
						<ul class="nav navbar-nav navbar-right">
							<li>	
								<c:choose>
									<c:when test="${empty user.photo}">
										<img src="<c:url value="/image/unknow64.png" />"/>
										<span class="dropdown-toggle" data-toggle="dropdown" id="navuser">${user.firstName}${user.lastName}</span> 		
									</c:when>
									<c:otherwise>
											<img src="<c:url value="/pages/getMemImage.controller" />" height="64" width="64" >
											<span class="dropdown-toggle" data-toggle="dropdown" id="navuser">${user.firstName}${user.lastName}</span> 		
									</c:otherwise>
								</c:choose>
								<ul class="dropdown-menu">
									<li><a href="#">會員專區</a></li>
									<li><a href="#">訂單查詢</a></li>
									<li>
										<a href="<c:url value="/mail/inbox.controller"/>">信箱 (未讀:
											<c:choose>
												<c:when test="${empty inbox}">(0)</c:when>
												<c:otherwise>${inbox}</c:otherwise>
											</c:choose>
											)
										</a>
									</li>
									<li>
										<a href="#" >申請會員大廚</a>
									</li>
									<li>
										<a href="<c:url value="/member/logout.jsp" />">登出</a>
									</li>
								</ul>
							</li>
						</ul>
					</div>
					</c:otherwise>
				</c:choose>
			</div>
		</nav>
		
		<div class="container">
		
			<div class="row">
				<br/>
				<br/>
				
			<div class="col-md-2">
				<nav >
					<ul class="nav nav-pills nav-stacked">
						<li role="presentation" style="font-size:18px">站內信</li>
						<li role="presentation" class="active"><a href="#">新郵件</a></li>
						<li role="presentation"><a href="<c:url value="/mail/inbox.controller"/>">收件匣</a></li>
						<li role="presentation"><a href="<c:url value="/email/mail.controller?action=outbox&mail_id=${element.mail_id}"/>">寄件匣</a></li>
					</ul>
				</nav>	
			</div>
						
			<div class="col-md-6 col-md-push-1">
				<br/>
				<br/>
						<form class="form-horizontal" role="form" action="<c:url value="/email/sendMail.controller?role=member"/>" method="post">
							<div class="form-group">
								<label for="to" class="col-sm-2">寄給:</label>
								<div class="col-sm-10">
									  <input type="email" class="form-control select2-offscreen select2-hidden-accessible" id="to" name="email"></input>
								<c:choose>
									<c:when test="${not empty errors.email}">
										<div class="alert alert-danger fade in">
											<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
											<p>${errors.email}</p>
										</div>
									</c:when>
								</c:choose>
								</div>
							</div>
							<div class="form-group">
								<label for="subject" class="col-sm-2">主旨:</label>
								<div class="col-sm-10">
									  <input type="text" class="form-control select2-offscreen select2-hidden-accessible" id="subjcet" name="subject"></input>
								</div>
							</div>
						
							<br>	
								
							<div class="form-group">
								<textarea class="form-control" id="message" name="content" rows="12" placeholder="在這裡輸入文字" maxlength="1024"></textarea>
							</div>
							<div class="form-group">	
								<button type="submit" class="btn btn-success">寄送</button>
								<button type="button" value="clear" class="btn btn-danger" onclick="javascript:clearForm();">清除</button>
							</div>
						</form>
				<hr>
				<footer>
					<p>&copy; 2017 YouChef Company, Inc.</p>
				</footer>
			</div>
			</div>
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
		
			<script type="text/javascript">
				$(".nav li").on("click", function(){
					$(".nav li").removeClass("active");
					$(this).addClass("active");
				});
				
				function clearForm() {
					var inputs = document.getElementsByTagName("input");
					for(var i=0; i<inputs.length; i++) {
						if(inputs[i].type=="text") {
							inputs[i].value="";
						}else if(inputs[i].type=="email"){
							inputs[i].value="";
						}
					}
					document.getElementById("message").value = "";
				}
			</script>
		
		<script src="<c:url value="/js/bootstrap.min.js"/>"></script>
		<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
		<script src="<c:url value="/js/ie10-viewport-bug-workaround.js"/>"></script>
	</body>
</html>
