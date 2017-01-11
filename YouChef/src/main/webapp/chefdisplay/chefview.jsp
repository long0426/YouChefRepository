<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html lang="zh">
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
		<meta name="description" content="">
		<meta name="author" content="">
		<link rel="shortcut icon" href="<c:url value='${request.contextPath}/images/favicon.png' />">
		
		<title>優廚 - 瀏覽會員大廚餐點</title>
		
		<!-- Bootstrap core CSS -->
		<link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet">
		
		<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
		<link href="<c:url value="/css/ie10-viewport-bug-workaround.css"/>"	rel="stylesheet">
		
		<!-- Custom styles for this template -->
<%-- 		<link href="<c:url value="/css/jumbotron.css"/>" rel="stylesheet"> --%>
		
		<!-- 瀏覽bar -->	
		<link href="<c:url value="/css/mid.nav.css"/>"	rel="stylesheet">
		
		
		<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
		<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
		<script src="<c:url value="/js/ie-emulation-modes-warning.js"/>"></script>
		
		<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
		<!--[if lt IE 9]>
		      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
		      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		    <![endif]-->

		<link rel="shortcut icon"
			href="<c:url value='${request.contextPath}/images/favicon.png' />">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">

		<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
		<link
			href="<c:url value='${request.contextPath}/css/bootstrap.min.css' />"
			rel="stylesheet">
		
		<link
			href="<c:url value='${request.contextPath}/css/bootstrap-theme.min.css' />"
			rel="stylesheet">
		
		<%-- <script src="<c:url value='${request.contextPath}/js/jquery.min.js' />"></script> --%>
	
		
		<script src="<c:url value='${request.contextPath}/js/bootstrap.js' />"></script>
		
		<link rel="stylesheet" type="text/css"
			href="<c:url value='${request.contextPath}/css/default.css' />">
		
		<link href="<c:url value='${request.contextPath}/css/fileinput.css' />"
			media="all" rel="stylesheet" type="text/css" />
		
		<script src="<c:url value='${request.contextPath}/js/fileinput.js' />"
			type="text/javascript"></script>

<%-- 		<script src="<c:url value='${request.contextPath}/js/jquery.js' />" --%>
<!-- 			type="text/javascript"></script> -->
		<script
			src="<c:url value='${request.contextPath}/js/locales/zh-TW.js' />"
			type="text/javascript" charset="UTF-8"></script>

		<style>
			body{
				  background-image:url(<c:url value="/image/background.gif"/>); 
  				  background-repeat: repeat; 
			}
			
			.jumbotron{
				opacity:1;
			}
			
			img{
				    border-radius: 100px;
			}
		
			#navuser {
				color: #ffffff;
				font-size: 16px
			}
			
			.icon-info .label {
			    border: 2px solid #ffffff;
			    font-weight: 500;
			    padding: 3px 5px;
			    text-align: center;
			}
			.label.label-primary {
			    border-radius: 50%;
			    font-size: 9px;
			    left: 8px;
			    position: absolute;
			    top: 45px;
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
				background: transparent url(<c:url value='/image/Cooking.jpg' />) center no-repeat;
				background-size: cover;
				margin-top: -20px;
				width: 100%;
				height: 300px;
			}
					
			
			.enterBtn{
				font-size: 22px;
				text-align: center;
			}
			
			.row{
				
			}
			
			 .modal-header .modal-body .modal-footer { 
			 	font-size: 14px; 
			 	font-family: Arial, '微软雅黑'; 
			 	background: #2c2c2c; 
			 } 
			
			.modal-open .modal {
			    overflow-x: hidden;
			    overflow-y: auto;
			    z-index: 9999;
			}
			
			*{margin: 0;padding: 0;}
			a{text-decoration: none;color: #000;}
	/* 		body{font-size: 14px;font-family: Arial,'微软雅黑';background: #2c2c2c;} */
			ul{list-style: none;}
			.box{width: 600px;height: 350px;margin: 20px auto 0;overflow: hidden;position: relative;}
			.box_img{width: 600px;height: 350px;overflow: hidden;}
			.box_img ul li{position: absolute;display: none;}
			.box_img ul li a{display: block;width: 600px;height: 350px;font-size: 100px;text-align: center;line-height: 250px;color: #fff;}
			
			.box_tab{position: absolute;bottom: 0;text-align: center;width: 346px}
			.box_tab a{display: inline-block;padding: 2px 10px;font-size: 10px;background: #fff;margin: 0 5px;color: #333;}
			.box_tab a.active{background: #09b;color: #fff;}	
		</style>
	
	<script type="text/javascript">
	
		$(document).ready(function(){
			var timejg=2000;
			var size = $('.box_img ul li').size();
			for(var i=1;i<=size;i++){
				$('.box_tab').append('<a href="javascript:(void)">'+i+'</a>')
			}

			$('.box_img ul li').eq(0).show();
			$('.box_tab a').eq(0).addClass('active')
			$('.box_tab a').mouseover(function(){
				$(this).addClass('active').siblings().removeClass('active');
				var index = $(this).index();
				i=index;
				$('.box_img ul li').eq(index).stop().fadeIn(300).siblings().stop().fadeOut(300);
			});

			var i = 0;
			var time = setInterval(move,timejg);

			function move(){
				i++;
				if(i==size){
					i=0;
				}

				$('.box_tab a').eq(i).addClass('active').siblings().removeClass('active');
				$('.box_img ul li').eq(i).fadeIn(300).siblings().fadeOut(300);
			}

			$('.box').hover(function(){
				clearInterval(time);
			},function(){
				time = setInterval(move,timejg);
			});
		});
	</script>
	
	</head>

	<body>
		<nav class="navbar navbar-inverse navbar-static-top">
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
                                <a class="icon-info">
                                    <i class="fa fa-bell" aria-hidden="true"></i>
                                    <span class="label label-primary" id="mailCount">
						                <c:choose>
											<c:when test="${empty inbox}">(0)</c:when>
											<c:otherwise>${inbox}</c:otherwise>
										</c:choose>
                                    </span>
                                </a>
                            </li>							
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
									<li><a href="<c:url value="/member/main.jsp"/>">會員專區</a></li>
									<li><a href="<c:url value="/member/main.jsp"/>#orders">訂單查詢</a></li>
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
										<c:choose>
											<c:when test="${user.ac_status==0}">
												<a href="<c:url value="/member/main.jsp"/>#applychef">申請會員大廚</a>
											</c:when>
											<c:otherwise>
													<a href="<c:url value="/member/main.jsp"/>#editchefmeal">修改餐點</a>
											</c:otherwise>
										</c:choose>
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
		
		<!-- Main jumbotron for a primary marketing message or call to action -->
	
			<header id="header" class="slide">
			</header>
			
		<div id="navigation-wapper">
			<div id="new-navigation">
				<ul id="nav-bar">
					<li class="tab mainpage active"><a id="new-nav-mainpage" href="<c:url value="/pages/main.jsp"/>"><span>首頁</span></a></li>
					<li class="tab blog "><a id="new-nav-blog" href="<c:url value="/chefdisplay/chefview.controller"/>"><span>瀏覽大廚</span></a></li>
					<li class="tab more-channel"><a id="new-nav-more-tab" href="<c:url value="/showDishes.controller?id=3001"/>"><span>瀏覽餐點</span></a></li>
					<li class="tab beauty-group "><a class="tab-link" id="new-nav-makeup-tab" href="<c:url value="/pages/getmchef.controller"/>"><span>會員大廚餐點區</span></a></li>
					<li class="tab tech3c "><a class="tab-link"	id="new-nav-tech3c-tab"	href="<c:url value="/essay/getessay.controller"/>"><span>討論區</span></a></li>
<!-- 					<li class="tab movie "><a class="tab-link" id="new-nav-movie-tab" href=""><span>ZZZ</span></a></li> -->
<!-- 					<li class="tab travel "><a class="tab-link"	id="new-nav-travel-tab"	href=""><span>GGG</span></a></li> -->
<!-- 					<li class="tab travel "><a class="tab-link"	id="new-nav-family-tab"	href=""><span>AAA</span></a></li> -->
<!-- 					<li class="tab food "><a class="tab-link" id="new-nav-food-tab"	href=""><span>BBB</span></a></li> -->
				</ul>
			</div>
		</div>
	
		<div class="container">
		<nav class="navbar navbar-default">
			<div class="container-fluid">
				<!-- Brand and toggle get grouped for better mobile display -->
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
						aria-expanded="false">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
				</div>


			</div>
			<!-- /.container-fluid -->
		</nav>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-md-2">
			       <div class="list-group">
				  <a href="<c:url value="${request.contextPath}/chefdisplay/chefview.controller"/>" class="list-group-item">全部大廚</a>
				  <a href="<c:url value="${request.contextPath}/chefdisplay/selectChefByType.controller?id=3001" />" class="list-group-item">台式料理</a>
				  <a href="<c:url value="${request.contextPath}/chefdisplay/selectChefByType.controller?id=3002" />" class="list-group-item">日本料理</a>
				  <a href="<c:url value="${request.contextPath}/chefdisplay/selectChefByType.controller?id=3004" />" class="list-group-item">西式料理</a>
				  <a href="<c:url value="${request.contextPath}/chefdisplay/selectChefByType.controller?id=3003" />" class="list-group-item">川味料理</a>
				  <a href="<c:url value="${request.contextPath}/chefdisplay/selectChefByType.controller?id=3005" />" class="list-group-item">東南亞料理</a>

				</div>
		    </div>
			
			<div class="col-md-9">
		    <div class="jumbotron">
				<h3>大廚介紹</h3>
			
			
			<div class="box">
		<div class="box_img">
		
			<ul>
				
				<li><img src=../images/product/2.JPG></li>
				<li><img src=../images/product/3.JPG></li>
				<li><img src=../images/product/4.JPG></li>
				<li><img src=../images/product/5.JPG></li>
				<li><img src=../images/product/1.JPG></li>
				<li><img src=../images/product/2.JPG></li>
				<li><img src=../images/product/3.JPG></li>
			</ul>
		</div>
		<div class="box_tab"></div>
	</div>
	
	
	
</div>

			<!-- 每頁不同的內容從這裡開始 -->
			<form action="<c:url value="${request.contextPath}/chefdisplay/chefview.controller" />"
			method="post">
<table  class="table table-striped">
<!-- 			<thead> -->
<!-- 				<tr> -->
<!-- 					<td>照片</td> -->
<!-- 					<td>大廚姓名</td> -->
<!-- 					<td>背景</td> -->
<!-- 					<td>菜系</td> -->
<!-- 					<td>年資</td> -->
<!-- 				</tr> -->
<!-- 			</thead> -->
			<tbody>
						<tr>
							<td><img height='200' width='200' src='<c:url value="${request.contextPath}/pages/getImage.controller?id=${chef.c_id}" />'></td>
							<td>${chef.lastName}${chef.firstName}</td>
							<td>${chef.background}</td>
							<td>${chef.typeBean.t_name}</td>
							<td>${chef.years}年</td>
				
						</tr>
			</tbody>
		</table>
					<div >
			<input type="button" value="立即訂購" onclick="location.href='<c:url value="/chefOrder.controller?c_id=${param.c_id}&t_id=${chef.typeBean.t_id}"/>'">
				</div>
		<p>
		
	</form>
		    </div>
				</div>
			</div>

			
			<button id="webSocketBtn"><img id="csPic" src="<c:url value="/image/info.png"/>" width="50" height="50"></button>
			
			<div id="webSocket" style="display:none">
				<iframe src="<c:url value="/demo.jsp" /> " width="300" height="500"></iframe>
			</div>			
			
		</div>
		<!-- /container -->
	
	
		<!-- Bootstrap core JavaScript
	    ================================================== -->
		<!-- Placed at the end of the document so the pages load faster -->

		<script>

		
			<!-- Ajax 每10秒偵測是否有新郵件 -->		
			
			<c:choose>
				<c:when test="${not empty user}">			
				function MailCountValue(){
					$.get('<c:url value='/member/mailCheck.controller' />' ,{'m_id':${user.m_id}},function(data){
							$('#mailCount').html(data);
						});
					}				
				MailCountValue();
				setInterval('MailCountValue()',10000);					
				</c:when>
			</c:choose>

			$(document).ready(function(){
				$("#webSocketBtn").click(function(){
					$("#webSocket").slideToggle();
				});
			});
			
			$(function(){
				　$(window).load(function(){
				　　$(window).bind('scroll resize', function(){
				　　var $this = $(this);
				　　var $this_Top=$this.scrollTop();
				
				　　//當高度小於100時，關閉區塊 
				　　if($this_Top < 50){
				　　　$('#navigation-wapper').stop().animate({top:"365px"});
				　　　}
				　　　　if($this_Top > 50){
				　　　　$('#navigation-wapper').stop().animate({top:"0px"});
			　　　	 }
				　　}).scroll();
				　});
				});
			
			</script>
			

		<script src="<c:url value="/js/bootstrap.min.js"/>"></script>
		<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
		<script src="<c:url value="/js/ie10-viewport-bug-workaround.js"/>"></script>
		
		
		
	</body>
</html>

