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
		<link rel="icon" href="<c:url value="/images/favicon.ico"/>">
		
		<title>優廚 - 首頁</title>
		
		<!-- Bootstrap core CSS -->
		<link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet">
		
		<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
		<link href="<c:url value="/css/ie10-viewport-bug-workaround.css"/>"	rel="stylesheet">
		
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
			
			
			#webSocketBtn{
				position:fixed;
				bottom:0px;
				right:20px;
				z-index:9999;
			}
			
			#webSocket {
				position:fixed;
				bottom:0px;
				right:0px;
				hieght:500px;
				Width: 300px;
				z-index:9998;
				background:white;
			}
			
			.slide {
				font-family: 'Microsoft JhengHei', 'Gotham SSm B';
				font-weight: 700;
				font-style: normal;
				color: #ffffff;
				background: transparent url(<c:url value='/image/cooking-header.jpg' />) center no-repeat;
				background-size: cover;
				width: 100%;
				height: 620px;
			}
					
			.headercontent {
				padding-top: 200px;
				padding-bottom: 10px;
			}			
			.maintitle {
				text-align: center;
				margin-bottom: 20px;
			}
			
			#block1{
				background-color: rgba(0,0,0,0.5);
				margin-left: auto ;
  				margin-right: auto ;
				width:45%;
				border-radius: 15px;
			}
			
			.maintitle p{
				font-size: 50px;
				color: #ffffff;
			}
			
			.subtitle {
				font-size: 30px;
				font-weight:500px;
				text-align: center;
				margin-bottom: 20px;
			}
			
			#block2{
				background-color: rgba(0,0,0,0.5);
				margin-left: auto ;
  				margin-right: auto ;
				width:40%;
				border-radius: 15px;
			}
			
			.subtitle p{
				font-size: 30px;
				color: #ffffff;
			}
			
			.enterBtn{
				font-size: 22px;
				text-align: center;
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
						<span class="sr-only">Toggle navigation</span> 
						<span class="icon-bar"></span> 
						<span class="icon-bar"></span> 
						<span class="icon-bar"></span>
					</button>
					<span class="in-line"><a href="<c:url value="/index.jsp"/>"><img alt="logo" src="<c:url value="/images/YouChef-logo-bk.png"/>" width="64px" height="64px"></a></span>
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
										<img src="<c:url value="/image/unknow64.png" />" />
										<span class="navuser">${user.firstName}${user.lastName}</span> 		
									</c:when>
									<c:otherwise>
											<img src="<c:url value="/pages/getMemImage.controller" />" height="64" width="64" >
											<span class="navuser">${user.firstName}${user.lastName}</span> 		
									</c:otherwise>
								</c:choose>
							</li>
							<li>
										<a href="<c:url value="/mail/inbox.controller"/>"><span class="navuser">未讀:
												<c:choose>
													<c:when test="${empty inbox}">(0)</c:when>
													<c:otherwise>${inbox}</c:otherwise>
												</c:choose>
											</span>
										</a>
							</li>
							<li>
										<a href="<c:url value="/member/logout.jsp" />"><span class="navuser">登出</span></a>
							</li>
						</ul>
					</div>
					</c:otherwise>
				</c:choose>
			</div>
		</nav>
		
		<!-- Main jumbotron for a primary marketing message or call to action -->
	
		<div class="jumbotron">
			<header id="header" class="slide">
				<div class="headercontent">
					<div class="maintitle"><div id="block1"><p>你最安心的到府烹飪服務</p></div></div>
					<div class="subtitle"><div id="block2"><p>現在就發掘令人食指大動的美食吧</p></div></div>
					<div class="row">
						<div class="enterBtn"><a href="<c:url value="/pages/main.jsp"/>"><button type="button" class="btn btn-default"><span style="font-size:22px">進入</span></button></a></div>
					</div>
				</div>
			</header>
		</div>
	
		<div class="container col-md-8 col-md-offset-2">
	
			<h2>${registerOK}</h2>
			
			<hr>
			<footer>
				<p>&copy; 2016 Company, Inc.</p>
			</footer>
			
			
			<button id="webSocketBtn"><img id="csPic" src="<c:url value="/image/info.png"/>" width="50" height="50"></button>
			
			<div id="webSocket" style="display:none">
				<iframe src="<c:url value="/demo.jsp" /> " width="300" height="500"></iframe>
			</div>			
			
		</div>
		<!-- /container -->
	
	
		<!-- Bootstrap core JavaScript
	    ================================================== -->
		<!-- Placed at the end of the document so the pages load faster -->
		<script
			src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		<script>
			window.jQuery || document.write('<script src="../js/vendor/jquery.min.js"><\/script>')
		
			<!-- Ajax 每10秒偵測是否有新郵件 -->		
			
			<c:choose>
				<c:when test="${not empty user}">			
				function MailCountValue(){
					$.get('<c:url value='/member/mailCheck.controller' />' ,{'m_id':${user.m_id}},function(data){
							$('#mailCount').html(data);
						});
					}				
				MailCountValue();
				setInterval('MailCountValue()',50000);					
				</c:when>
			</c:choose>

			$(document).ready(function(){
				$("#webSocketBtn").click(function(){
					$("#webSocket").slideToggle();
				});
			});
		</script>
		
		<script src="<c:url value="/js/bootstrap.min.js"/>"></script>
		<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
		<script src="<c:url value="/js/ie10-viewport-bug-workaround.js"/>"></script>
		
		
		
	</body>
</html>

