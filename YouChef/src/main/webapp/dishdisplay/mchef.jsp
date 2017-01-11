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
		<link
			href="<c:url value='${request.contextPath}/css/bootstrap.min.css' />"
			rel="stylesheet">
		
		<link
			href="<c:url value='${request.contextPath}/css/bootstrap-theme.min.css' />"
			rel="stylesheet">
		
		<%-- <script src="<c:url value='${request.contextPath}/js/jquery.min.js' />"></script> --%>
		<script
			src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
		
		<script src="<c:url value='${request.contextPath}/js/bootstrap.js' />"></script>
		
		<link rel="stylesheet" type="text/css"
			href="<c:url value='${request.contextPath}/css/default.css' />">
		
		<link href="<c:url value='${request.contextPath}/css/fileinput.css' />"
			media="all" rel="stylesheet" type="text/css" />
		
		<script src="<c:url value='${request.contextPath}/js/fileinput.js' />"
			type="text/javascript"></script>
		<script src="<c:url value='${request.contextPath}/js/jquery.js' />"
			type="text/javascript"></script>
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

			.nav-pills {
				    opacity:1;
			}
			
			.nav-pills a {
		    color: #000000;
			}
			
			.nav-pills a:hover {
			    color: #000000;
			    border-radius: 5px;
			}
			
			.nav-pills .active a:hover {
			    color: #000000;
			    border-radius: 5px;
			}
			
			a.list-group-item, button.list-group-item {
	    		color: #000000;
			}
	
			
			a.list-group-item:focus, 
			a.list-group-item:hover, 
			button.list-group-item:focus, 
			button.list-group-item:hover {
			    color: #555;
			    text-decoration: none;
			    background-color: #F7B16D;
			}
		
		</style>
	
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
					<li class="tab mainpage"><a id="new-nav-mainpage" href=""><span>首頁</span></a></li>
					<li class="tab blog "><a id="new-nav-blog" href=""><span>瀏覽大廚</span></a></li>
					<li class="tab more-channel"><a id="new-nav-more-tab" href="#"><span>瀏覽餐點</span></a></li>
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
					<a class="navbar-brand" href="#">會員大廚專區</a>
				</div>


			</div>
			<!-- /.container-fluid -->
		</nav>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-md-2">
				<div class="list-group">
					<a href="../dishdisplay/dishdisplayTaiwan.jsp"
						class="list-group-item">台式料理</a> <a
						href="../dishdisplay/dishdisplayJapan.jsp" class="list-group-item">日本料理</a>
					<a href="../dishdisplay/dishdisplayWestern.jsp"
						class="list-group-item">西式料理</a> <a
						href="../dishdisplay/dishdisplaySichuan.jsp"
						class="list-group-item">川味料理</a> <a
						href="../dishdisplay/dishdisplaySoutheastAsia.jsp"
						class="list-group-item">東南亞料理</a>

				</div>
				<div class="list-group">
					<a href="../pages/getmchef.controller" class="list-group-item">會員大廚專區</a>
				</div>
			</div>
			<div class="col-md-10">
				<div class="jumbotron">
						<h4>會員大廚專區</h4>
					<div class="row">
						<c:if test="${not empty list}">
							<%-- 							${list[0].dishPhotoBean} --%>
							<c:forEach var="element" items="${list}">
								<div class="col-md-4" style="margin-top: 30px">
									<input type="image" style="border-color: black" height='250'
										width='250'
										src='<c:url value="${request.contextPath}/pages/getImage.controller?d_id=${element.d_id}" />'
										class="btn btn-lg" data-toggle="modal"
										data-target=".bs-example-modal" value="${element.menu}" id="menu"
										data-id="${element.mchefBean.mc_id}"
										onclick="getdpid(${element.d_id},${element.mchefBean.mc_id},'${element.mchefBean.memberBean.nickname}',${element.price},'${element.mchefBean.background}')">
									<div>${element.d_name}</div>
									<div>每份<fmt:formatNumber value="${element.price}" pattern="#,###"/>元</div>
									
								</div>
							</c:forEach>
							<!-- Modal -->
							<div id="mymodal" class="modal fade bs-example-modal" tabindex="-1"
								role="dialog" aria-labelledby="myLargeModalLabel"
								aria-hidden="false">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header" align="center" id="header">
											<iframe id="myiframe" frameborder="0" width="550"
												height="315"
												src=""></iframe>
												<br>
											<img data-toggle="tooltip" data-placement="left" id="header-img" src="..." class="img-circle"
												width="150px" height="150px">
											<label for="header-img" id="mylabel"></label>
										</div>
										<div class="modal-body" style="color: yellow;background: #2c2c2c">
											<div class="menucontents">
												<div class="menurow menutitle subhead col-10-md col-16-sm col-center">
												<div align="center" ng-show="canbook" ng-bind="model.menuprice() | currencyRound" class="ng-binding" id="price">NT$</div>
													<br>
													<div align="center">菜單</div>
														<div style="margin: 5px" align="center">
															<textarea disabled="disabled" draggable="false" wrap="soft" readonly="readonly" class="form-control-sm" rows="8" id="myTextarea" style="padding:5px;text-align:center;border-color:#2c2c2c; bcolor: yellow;background: #2c2c2c"></textarea>
														</div>
													</div>
												</div>
										</div>
										<form action="mchefOrder.controller" method="post">
											<div class="modal-footer">
												<button type="button" class="btn btn-default"
													data-dismiss="modal" onclick="closeIframe()">關閉</button>
												<a id="submit" class="btn btn-primary">訂購</a>
											</div>
										</form>
									</div>
								</div>
							</div>
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</div>
<div>

			
			<button id="webSocketBtn"><img id="csPic" src="<c:url value="/image/info.png"/>" width="50" height="50"></button>
			
			<div id="webSocket" style="display:none">
				<iframe src="<c:url value="/demo.jsp" /> " width="300" height="500"></iframe>
			</div>			
			
		</div>
		<!-- /container -->
	
	
		<!-- Bootstrap core JavaScript
	    ================================================== -->
		<!-- Placed at the end of the document so the pages load faster -->

			<!-- Ajax 每10秒偵測是否有新郵件 -->		
			
			<c:choose>
				<c:when test="${not empty user}">		
				<script>
				function MailCountValue(){
					$.get('<c:url value='/member/mailCheck.controller' />' ,{'m_id':${user.m_id}},function(data){
							$('#mailCount').html(data);
						});
					}				
				MailCountValue();
				setInterval('MailCountValue()',10000);
				</script>
				</c:when>
			</c:choose>
			
			<script>
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
			
			<script type="text/javascript">
			$(function() {
				$('#header-img').tooltip({
				    animated: 'fade',
				    placement: 'left',
				    html: true
				});
	// 			$("#menu").click(function(){
			        $("#mymodal").modal("show");
	// 		    });
			        $("#mymodal").on('show.bs.modal', function () {
// 			            alert('The modal is about to be shown.');
			    });
				
	// 			$(".bs-example-modal").on("show.bs.modal", function(e){
	// 		        alert('before show');
	// 		    }).on("shown.bs.modal",function(e){
	// 		         alert('after show');
	// 		    }).on("hide.bs.modal",function(e){
	// 		        alert('before hide')
	// 		    }).on("hidden.bs.modal",function(e){
	// 		        alert('after hide')
	// 		    });
				$('.bs-example-modal').on('shown.bs.modal', function(){
				    var margin_vertical = parseInt( $(this).find('.modal-dialog').css('margin-top') ) + parseInt( $(this).find('.modal-dialog').css('margin-bottom') );
				    var height_header   = parseInt( $(this).find('.modal-header').css('height') );
				    var height_footer   = parseInt( $(this).find('.modal-footer').css('height') );
				    var height_body     = ( window.innerHeight - height_header - height_footer - margin_vertical - 10 ) + 'px';
				    $(this).find('.modal-body').css('max-height', height_body).css('overflow', 'auto');
				});
				$('.bs-example-modal').on('hide.bs.modal', function (e) {
// 					alert("hide.bs.modal");
					closeIframe();
				});
			});
		</script>
		<script type="text/javascript">
			function getdpid(d_id, mc_id, name, price, background) {
	// 		$('#menu').click(function(d_id, mc_id, name, price){
				console.log(mc_id);
				var orderUrl = '<c:url value="/mchefOrder.controller?mc_id="/>'+mc_id;
				$('#submit').attr('target','_blank');
				$('#submit').attr('href',orderUrl)
				$('#myiframe').attr("src", "${pageContext.request.contextPath}" + "/pages/getpdid.controller?d_id=" + d_id);
				$('#header-img').attr("src","${pageContext.request.contextPath}" + "/pages/getImage.controller?mc_id=" + mc_id);
				$('#header-img').attr("title",mc_id);
				$('#mylabel').text(name);
				$('#price').text("NT$ " + price);
				$('#myTextarea').text('');
				$.ajax({
			        url: '${pageContext.request.contextPath}/pages/getMenu.controller?d_id=' + d_id,
			        contentType: "text/html; charset=utf-8",
	// 		        data: {'status': status},
			        type: 'GET',
			        cache: false,
			        success: function (result) {
			        	$('#myTextarea').text(result);
			        }
			    });
			}
			
			function closeIframe(){
				$('#myiframe').removeAttr("src");
			};


		</script>
		
		<script src="<c:url value="/js/bootstrap.min.js"/>"></script>
		<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
		<script src="<c:url value="/js/ie10-viewport-bug-workaround.js"/>"></script>
	
	</body>
</html>

