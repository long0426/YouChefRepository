<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
		
		<title>優廚</title>
		
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
		<style>
			body{
				background-color: #F7F7F6;
			}
		
			#navuser {
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
				background: transparent url(<c:url value='/image/Discussimg2.png' />) center no-repeat;
				background-size: cover;
				margin-top: -20px;
				width: 100%;
				height: 300px;
			}
					
			.headercontent {
				padding-top: 200px;
				padding-bottom: 10px;
			}			
			.maintitle {
				font-size: 50px;
				text-align: center;
				margin-bottom: 20px;
			}
			.subtitle {
				font-size: 22px;
				text-align: center;
				margin-bottom: 20px;
			}
			
			.enterBtn{
				font-size: 22px;
				text-align: center;
			}
			
			#writerid01{
			visibility:hidden;
			}
			
			#diswriterid01{
			visibility:hidden;
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
					<li class="tab mainpage active"><a id="new-nav-mainpage" href=""><span>首頁</span></a></li>
					<li class="tab blog "><a id="new-nav-blog" href=""><span>瀏覽大廚</span></a></li>
					<li class="tab more-channel"><a id="new-nav-more-tab" href="#"><span>瀏覽餐點</span></a></li>
					<li class="tab beauty-group "><a class="tab-link" id="new-nav-makeup-tab" href=""><span>會員大廚餐點區</span></a></li>
					<li class="tab tech3c "><a class="tab-link"	id="new-nav-tech3c-tab"	href="<c:url value="/essay/getessay.controller"/>"><span>討論區</span></a></li>
<!-- 					<li class="tab movie "><a class="tab-link" id="new-nav-movie-tab" href=""><span>ZZZ</span></a></li> -->
<!-- 					<li class="tab travel "><a class="tab-link"	id="new-nav-travel-tab"	href=""><span>GGG</span></a></li> -->
<!-- 					<li class="tab travel "><a class="tab-link"	id="new-nav-family-tab"	href=""><span>AAA</span></a></li> -->
<!-- 					<li class="tab food "><a class="tab-link" id="new-nav-food-tab"	href=""><span>BBB</span></a></li> -->
				</ul>
			</div>
		</div>
	
		<div class="container col-md-8 col-md-offset-2">

	<br>
	<br>
	<br>
	<br>	
	<c:choose>
		<c:when test="${empty user}">
			<p>
				<a href="<c:url value="${request.contextPath}/essay/getessay.controller"/>"><input type="button" name="getessay" value="回列表"></a>
			</p>
		</c:when>
		<c:when test="${user.m_id == essayPage.writer_id.m_id}">
				<p>
					<a href="<c:url value="${request.contextPath}/essay/getessay.controller"/>"><input type="button" name="getessay" value="回列表"></a>
					<a href="<c:url value="${request.contextPath}/essaypages/essayEdit.jsp"/>"><input type="button" name="getessay" value="編輯"></a>
					<a href="<c:url value="${request.contextPath}/essay/deleteessay.controller?essay_id=${essayPage.essay_id}"/>" onClick="return(confirm('你確定要刪除文章嗎?'))"><input type="button" name="getessay" value="刪除"></a>
					<a href="<c:url value="${request.contextPath}/essaypages/discussPage.jsp"/>"><input type="button" name="getessay" value="留言"></a>
				</p>
			</c:when>
		<c:otherwise>
			<p>
				<a href="<c:url value="${request.contextPath}/essay/getessay.controller"/>"><input type="button" name="getessay" value="回列表"></a>
				<a href="<c:url value="${request.contextPath}/essaypages/discussPage.jsp"/>"><input type="button" name="getessay" value="留言"></a>
			</p>			
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${not empty errors}">
			<script>
				alert('${errors.content}');			
			</script>
		</c:when>
	</c:choose>
	<fmt:formatDate value="${essayPage.time}" var="formattedDate" type="date" pattern="YYYY年MM月dd日HH:mm:ss"/>
	<div>
			<p><label class="title">發文者:</label>${essayPage.writer_id.firstName}${essayPage.writer_id.lastName}</p>
	</div>
	<div>
			<p><label class="title">標題:</label>${essayPage.title}</p>
	</div>
	<div>
			<label class="title">內容:</label><p>
			<p>${essayPage.content}</p>
	</div>
	<div>
		<p><label class="title">最後發表時間:</label>${formattedDate}</p>
	</div>
	<div>
		<c:forEach var="discuss" items="${discussList}">
			<fmt:formatDate value="${discuss.time}" var="fordiscussDate" type="date" pattern="YYYY年MM月dd日HH:mm:ss"/>
			<table>
				<tr>
				<td>回文者：：${discuss.memberBean.firstName}${discuss.memberBean.lastName}</td>
				</tr>

				<tr>
				<td>回覆內容：${discuss.content}</td>
				</tr>
				<tr>
				<td>最後發表時間：${fordiscussDate}</td>
				</tr>
				<c:choose>
				<c:when test="${user.m_id == discuss.memberBean.m_id}">
					<td>
<%-- 						<a href="<c:url value="${request.contextPath}/discuss/updatediscuss.controller?discuss_id=p/>"><input type="button" name="getessay" value="編輯"></a> --%>
						<a href="<c:url value="${request.contextPath}/essaypages/discussEdit.jsp?discuss_id=${discuss.discuss_id}"/>"><input type="button" name="getessay" value="編輯"></a>
						<a href="<c:url value="${request.contextPath}/discuss/deletedDiscuss.controller?discuss_id=${discuss.discuss_id}"/>" onClick="return(confirm('你確定要刪除回文?'))"><input type="button" name="getessay" value="刪除"></a>	
					</td>
				</c:when>
				</c:choose>			
			</table>
		</c:forEach>
	</div>
	<%-- 				<c:when test="${user.m_id == discuss[0].memberBean.m_id}"> --%>
<%-- 				</c:when> --%>
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
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
