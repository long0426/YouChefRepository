<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="zh-Hant-TW">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
		<meta name="description" content="">
		<meta name="author" content="">
		<link rel="shortcut icon" href="<c:url value='${request.contextPath}/images/favicon.png' />">
		
		<title>優廚</title>
		
		<!-- Bootstrap core CSS -->
		<link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet">
		<link href="<c:url value='${request.contextPath}/css/bootstrap-theme.min.css' />" rel="stylesheet">
		
		
		<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
		<link href="<c:url value="/css/ie10-viewport-bug-workaround.css"/>"	rel="stylesheet">

		
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
		
		<link href="<c:url value='${request.contextPath}/css/bootstrap.min.css' />" rel="stylesheet">
		
		<link href="<c:url value='${request.contextPath}/css/bootstrap-theme.min.css' />" rel="stylesheet">
		
		<script src="<c:url value='${request.contextPath}/js/jquery.min.js' />"></script>
		
		<script src="<c:url value='${request.contextPath}/js/bootstrap.min.js' />"></script>
		
		<link rel="stylesheet" type="text/css" href="<c:url value='${request.contextPath}/css/default.css' />">
		
		<link href="<c:url value='${request.contextPath}/css/fileinput.css' />" media="all" rel="stylesheet"
			type="text/css" />
			
		<script src="<c:url value='${request.contextPath}/js/fileinput.js' />" type="text/javascript"></script>
		
		<script src="<c:url value='${request.contextPath}/js/locales/zh-TW.js' />" type="text/javascript"></script>
				
		<style>
			body{
				background-color: #F7F7F6;
			}
						
			p {
				padding:2px;
			}
		
			#navuser {
				color: #93B7DB;
				font-size: 16px
			}
			
			.nav-justified {
			    width: 70%;
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
								
			.enterBtn{
				font-size: 22px;
				text-align: center;
			}
			
			.user-block .pic-container {
			    padding: 5px 5px 5px;
			    border-top-left-radius: 5px;
			    border-top-right-radius: 5px;
			}
			
			.user-block #user-stats > li .stat {
			    font-size: 22px;
			    font-weight: 600;
			    margin-bottom: 3px;
			}
			
			.box {
			    border: solid thin #efefef;
			    border-radius: 0;
			    background-color: white;
			}
			
			.box h4 {
			    font-family: 'Gotham SSm A', 'Gotham SSm B';
			    font-weight: 700;
			    font-style: normal;
			    font-size: 20px;
			    color: #464646;
			    white-space: nowrap;
			    overflow: hidden;
			    text-overflow: ellipsis;
			    width: 100%;
			    text-align: cneter;
			}
			
			.padding {
				padding: 1px 15px;
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
			<div class="container col-md-2">
	            <div class="box user-block">
	                <div class="pic-container">
	                   <c:choose>
		                   <c:when test="${not empty user.photo }">
		                   		<img src="<c:url value="/pages/getMemImage.controller"/>" width="150" height="150" alt="${user.firstName} image">
		                   </c:when>
		                   <c:otherwise>
		                   		<img src="<c:url value="/image/unknow128.png"/>" width="150" height="150" alt="${user.firstName} image">
		                   </c:otherwise>
	                   </c:choose>
	                </div>
	                <div class="padding">
	                    <h4 class="user-name center">${user.firstName}${user.lastName}</h4>
	                    <p class="center">
	                        <small class="muted"></small>
	                    </p>
						<p>
							所在城市: ${user.city}
						</p>		
	                   	<p class="center user-links">
	                   		<a href="/account/edit/" class="btn btn-small"><i class="glyphicon glyphicon-edit"></i>編輯個人資訊</a>
	                   	</p>
	                    
	
	                    <hr class="full">
	                    <ul id="user-stats">                      
	                        <li>
	                            <div class="stat"><i class="glyphicon glyphicon-cutlery"></i> 0</div>
	                            <div class="stat-label">處理中訂單</div>
	                        </li>
	                        <li>
	                            <div class="stat"><i class="glyphicon glyphicon-envelope"></i>
	                            	<c:choose>
										<c:when test="${empty inbox}"> 0</c:when>
										<c:otherwise> ${inbox}</c:otherwise>
									</c:choose>
								</div>
	                            <div class="stat-label">未讀信件</div>
	                        </li>
	                    </ul>
	                </div>
	            </div>
			</div>
			
			<div class="container col-md-9">
				<div role="tabpanel">
		
				  <!-- Nav tabs -->
					<ul class="nav nav-tabs" role="tablist" id="myTab">
					  <li role="presentation"><a href="#mainhome" aria-controls="mainhome" role="tab" data-toggle="tab">個人主頁</a></li>
					  <li role="presentation"><a href="#info" aria-controls="info" role="tab" data-toggle="tab">個人資訊</a></li>
					  <li role="presenWtation"><a href="#orders" aria-controls="orders" role="tab" data-toggle="tab">訂單查詢</a></li>
					  <c:choose>
					  	<c:when test="${user.ac_status == '1'}">
					  		<li role="presentation" class="active"><a href="#editchefmeal" aria-controls="editchefmeal" role="tab" data-toggle="tab">維護會員大廚</a></li>
					  		<li role="presentation"><a href="#" >營業時間</a>
					  	</c:when>
					  	<c:otherwise>
					  		<li role="presentation" class="active"><a href="#applychef" aria-controls="applychef" role="tab" data-toggle="tab">申請會員大廚</a></li>
					  	</c:otherwise>
					  </c:choose>
					</ul>
					
					<div class="tab-content">
					  <div role="tabpanel" class="tab-pane" id="mainhome">1234564</div>
					  <div role="tabpanel" class="tab-pane" id="info">555789</div>
					  <div role="tabpanel" class="tab-pane" id="orders">5798513</div>
					  <div role="tabpanel" class="tab-pane active" id="applychef">
					  	<div class="container">
							<div role="tabpanel">
								<div class="well-sm">
									<!-- Nav tabs -->
									<ul class="nav nav-pills nav-justified" role="tablist">
										<li role="presentation" class="active"><a href="#home"
											aria-controls="home" role="tab" data-toggle="tab">個人資料</a></li>
										<li role="presentation"><a href="#profile"
											aria-controls="profile" role="tab" data-toggle="tab">餐點介紹</a></li>
										<li role="presentation"><a href="#messages"
											aria-controls="messages" role="tab" data-toggle="tab">餐點圖片</a></li>
										<li role="presentation"><a href="#settings"
											aria-controls="settings" role="tab" data-toggle="tab">用餐說明</a></li>
									</ul>
								</div>
					
									<!-- Tab panes -->
									<form class="form-horizontal" role="form" enctype="multipart/form-data" action="<c:url value='${request.contextPath}/pages/mchefapply.controller' />" method="post">
									<div class="tab-content">
									<!-- 							個人資料								 -->
										<div role="tabpanel" class="tab-pane active" id="home">
												<div class="form-group">
												    <label class="col-md-1 col-md-offset-1 control-label">帳號</label>
												    <div class="col-md-3">
												      <c:choose>
												      <c:when test="${empty param.email}">
												      	<input type="text" class="form-control" value="${email}" disabled="disabled" name="email">
												      </c:when>
												      <c:otherwise>
												      	<input type="text" class="form-control" value="${param.email}" disabled="disabled" name="email">
												      	<input type="hidden" id="email" value='${param.email}' name="email">
												      </c:otherwise>
												      </c:choose>
												      <c:choose>
												      <c:when test="${empty param.m_id}">
												      	<input type="hidden" id="m_id" value='${m_id}' name="m_id">
												      </c:when>
												      <c:otherwise>
												      	<input type="hidden" id="m_id" value='${param.m_id}' name="m_id">
												      </c:otherwise>
												      </c:choose>
												    </div>
												  </div>
												  <div class="form-group">
												    <label class="col-md-1 col-md-offset-1 control-label">姓名</label>
												    <div class="col-md-3">
												    <c:choose>
												      <c:when test="${empty param.name}">
												      	<input type="text" class="form-control" value='${name}' disabled="disabled" name="name">
												      </c:when>
												      <c:otherwise>
												      	<input type="text" class="form-control" value='${param.name}' disabled="disabled" name="name">
												      	<input type="hidden" id="name" value='${param.name}' name="name">
												      </c:otherwise>
												    </c:choose>
												    </div>
												  </div>
												  <div class="form-group">
												    <label class="col-md-1 col-md-offset-1 control-label">簡介<font color="red">*</font></label>
												    <div class="col-md-3">
												      <textarea class="form-control" rows="5" id="background" name="background" >${param.background}</textarea><font color="red">${errors.background}</font>
												    </div>
												  </div>
												  <div class="form-group">
												    <label class="col-md-1 col-md-offset-1 control-label">料理年資</label>
												    <div class="col-md-3">
												      <select class="form-control" id="years" name="years">
												      <c:forEach var ='i' begin='0' end='20' step='1'>
												      	<c:choose>
												      	<c:when test="${i == 0}">
												      		<option selected value="${i}">請選擇...</option>
												      	</c:when>
												      	<c:otherwise>
													      	<c:choose>
													      		<c:when test="${i == param.years}">
													      		<option selected value="${i}">${i}</option>
													      		</c:when>
													      		<c:otherwise>
													      		<option value="${i}">${i}</option>
													      		</c:otherwise>
													      		</c:choose>
													      	</c:otherwise>
													  	</c:choose>
													  </c:forEach>
														</select>
												    </div>
												  </div>
												  <div class="form-group">
												    <label class="col-md-1 col-md-offset-1 control-label">照片</label>
												    <div class="col-md-3">
												      <input id="photo" class="file" type="file" name="photo">
												    </div>
												  </div>
												<br>
										</div>
										<!-- 							餐點介紹								 -->
										<div role="tabpanel" class="tab-pane" id="profile">
												<div class="form-group">
												    <label class="col-md-2  control-label">餐點名稱<font color="red">*</font></label>
												    <div class="col-md-3">
												      <input type="text" class="form-control" id="d_name" name="d_name" value="${param.d_name}"><font color="red">${errors.d_name}</font>
												    </div>
												  </div>
												  <div class="form-group">
												    <label class="col-md-2  control-label">餐點簡介<font color="red">*</font></label>
												    <div class="col-md-3">
												      <input type="text" class="form-control" id="d_briefing" name="d_briefing" value="${param.d_briefing}"><font color="red">${errors.d_briefing}</font>
												    </div>
												  </div>
												  <div class="form-group">
												    <label class="col-md-2 control-label">菜單<font color="red">*</font></label>
												    <div class="col-md-3">
												      <textarea class="form-control" rows="8" id="menu" wrap="soft" style="text-align: center;" name="menu">${param.menu}</textarea><font color="red">${errors.menu}</font>
												    </div>
												  </div>
												  <div class="form-group">
												    <label class="col-md-2 control-label">菜系</label>
												    <div class="col-md-3">
												      <select class="form-control" id="t_id" name="t_id" >
														<!-- javaScript -->
													  </select>
												    </div>
												  </div>
												  <div class="form-group">
												    <label class="col-md-2 control-label">價格<font color="red">*</font></label>
												    <div class="col-md-3">
												      <input type="number" class="form-control" id="price" min="0" name="price" value="${param.price}"><font color="red">${errors.price}</font>
												    </div>
												  </div>
												<br>
										</div>
										<!-- 							餐點圖片								 -->
										<div role="tabpanel" class="tab-pane" id="messages">
												  <div class="form-group">
												    
												    <div class="col-md-8 col-md-offset-1">
												    <label for="d_photo">選擇圖片<font color="red">*(最多10張)</font></label>
												      <input id="d_photo" class="file" type="file" multiple="multiple" name="d_photo">
												    </div>
												  </div>
										</div>
										<!-- 							用餐說明								 -->
										<div role="tabpanel" class="tab-pane" id="settings">
												<div class="form-group">
												    <label class="col-md-2  control-label">人數上限<font color="red">*</font></label>
												    <div class="col-md-3">
												      <select class="form-control" id="quota" name="quota" >
												      <c:forEach var ='i' begin='0' end='10' step='1'>
												      	<c:choose>
												      	<c:when test="${i == 0}">
												      		<option selected value="${i}">請選擇...</option>
												      	</c:when>
												      	<c:otherwise>
													      	<c:choose>
													      		<c:when test="${i == param.quota}">
													      		<option selected value="${i}">${i}</option>
													      		</c:when>
													      		<c:otherwise>
													      		<option value="${i}">${i}</option>
													      		</c:otherwise>
													      		</c:choose>
													      	</c:otherwise>
													  	</c:choose>
													  </c:forEach>
													  </select><font color="red">${errors.quota}</font>
												    </div>
												  </div>
												  <div class="form-group">
												    <label class="col-md-2 control-label">用餐地點</label>
												    <div class="col-md-3">
												      <select class="form-control" id="v_id" name="v_id">
													  </select>
												    </div>
												  </div>
												  <div class="form-group">
												    <label class="col-md-2 control-label">用餐地址<font color="red">*</font></label>
												    <div class="col-md-3">
												      <input type="text" class="form-control" id="venue" name="venue" value="${param.venue}"><font color="red">${errors.venue}</font>
												    </div>
												  </div>
					<!-- 							  <div class="form-group"> -->
					<!-- 							    <label class="col-md-2 control-label">營業時間<font color="red">*</font></label> -->
					<!-- 							    <div class="col-md-3"> -->
					<%-- 							      <a href="<c:url value="/pages/getmchef.controller?mc_id=1002"/>" Target="_blank" class="btn btn-primary btn-danger active" role="button">設定</a> --%>
					<!-- 							    </div> -->
					<!-- 							  </div> -->
												<br>
												<div class="form-group">
													<div class="col-md-1 col-md-offset-2">
														<button type="submit" class="btn btn-primary btn" role="button">送出</button>
													</div>
													<div class="col-md-3">
														<button type="reset" class="btn btn-default">重設</button>
													</div>
												</div>
										</div>
									</div>
									</form>
							</div>			
			    		</div>
			    	</div>
		    		
		    	  <div role="tabpanel" class="tab-pane" id="editchefmeal">
				  	<div class="container">
						<div role="tabpanel">
							<div class="well-sm">
								<!-- Nav tabs -->
								<ul class="nav nav-pills nav-justified" role="tablist">
									<li role="presentation" class="active"><a href="#home"
										aria-controls="home" role="tab" data-toggle="tab">個人資料</a></li>
									<li role="presentation"><a href="#profile"
										aria-controls="profile" role="tab" data-toggle="tab">餐點介紹</a></li>
									<li role="presentation"><a href="#messages"
										aria-controls="messages" role="tab" data-toggle="tab">餐點圖片</a></li>
									<li role="presentation"><a href="#settings"
										aria-controls="settings" role="tab" data-toggle="tab">用餐說明</a></li>
								</ul>
							</div>
				
								<!-- Tab panes -->
								<form class="form-horizontal" role="form" enctype="multipart/form-data" action="<c:url value='${request.contextPath}/pages/mchefapply.controller' />" method="post">
								<div class="tab-content">
								<!-- 							個人資料								 -->
									<div role="tabpanel" class="tab-pane active" id="home">
											<div class="form-group">
											    <label class="col-md-1 col-md-offset-1 control-label">帳號</label>
											    <div class="col-md-3">
											      <c:choose>
											      <c:when test="${empty param.email}">
											      	<input type="text" class="form-control" value="${email}" disabled="disabled" name="email">
											      </c:when>
											      <c:otherwise>
											      	<input type="text" class="form-control" value="${param.email}" disabled="disabled" name="email">
											      	<input type="hidden" id="email" value='${param.email}' name="email">
											      </c:otherwise>
											      </c:choose>
											      <c:choose>
											      <c:when test="${empty param.m_id}">
											      	<input type="hidden" id="m_id" value='${m_id}' name="m_id">
											      </c:when>
											      <c:otherwise>
											      	<input type="hidden" id="m_id" value='${param.m_id}' name="m_id">
											      </c:otherwise>
											      </c:choose>
											    </div>
											  </div>
											  <div class="form-group">
											    <label class="col-md-1 col-md-offset-1 control-label">姓名</label>
											    <div class="col-md-3">
											    <c:choose>
											      <c:when test="${empty param.name}">
											      	<input type="text" class="form-control" value='${name}' disabled="disabled" name="name">
											      </c:when>
											      <c:otherwise>
											      	<input type="text" class="form-control" value='${param.name}' disabled="disabled" name="name">
											      	<input type="hidden" id="name" value='${param.name}' name="name">
											      </c:otherwise>
											    </c:choose>
											    </div>
											  </div>
											  <div class="form-group">
											    <label class="col-md-1 col-md-offset-1 control-label">簡介<font color="red">*</font></label>
											    <div class="col-md-3">
											      <textarea class="form-control" rows="5" id="background" name="background" >${param.background}</textarea><font color="red">${errors.background}</font>
											    </div>
											  </div>
											  <div class="form-group">
											    <label class="col-md-1 col-md-offset-1 control-label">料理年資</label>
											    <div class="col-md-3">
											      <select class="form-control" id="years" name="years">
											      <c:forEach var ='i' begin='0' end='20' step='1'>
											      	<c:choose>
											      	<c:when test="${i == 0}">
											      		<option selected value="${i}">請選擇...</option>
											      	</c:when>
											      	<c:otherwise>
												      	<c:choose>
												      		<c:when test="${i == param.years}">
												      		<option selected value="${i}">${i}</option>
												      		</c:when>
												      		<c:otherwise>
												      		<option value="${i}">${i}</option>
												      		</c:otherwise>
												      		</c:choose>
												      	</c:otherwise>
												  	</c:choose>
												  </c:forEach>
													</select>
											    </div>
											  </div>
											  <div class="form-group">
											    <label class="col-md-1 col-md-offset-1 control-label">照片</label>
											    <div class="col-md-3">
											      <input id="photo" class="file" type="file" name="photo">
											    </div>
											  </div>
											<br>
									</div>
									<!-- 							餐點介紹								 -->
									<div role="tabpanel" class="tab-pane" id="profile">
											<div class="form-group">
											    <label class="col-md-2  control-label">餐點名稱<font color="red">*</font></label>
											    <div class="col-md-3">
											      <input type="text" class="form-control" id="d_name" name="d_name" value="${param.d_name}"><font color="red">${errors.d_name}</font>
											    </div>
											  </div>
											  <div class="form-group">
											    <label class="col-md-2  control-label">餐點簡介<font color="red">*</font></label>
											    <div class="col-md-3">
											      <input type="text" class="form-control" id="d_briefing" name="d_briefing" value="${param.d_briefing}"><font color="red">${errors.d_briefing}</font>
											    </div>
											  </div>
											  <div class="form-group">
											    <label class="col-md-2 control-label">菜單<font color="red">*</font></label>
											    <div class="col-md-3">
											      <textarea class="form-control" rows="8" id="menu" wrap="soft" style="text-align: center;" name="menu">${param.menu}</textarea><font color="red">${errors.menu}</font>
											    </div>
											  </div>
											  <div class="form-group">
											    <label class="col-md-2 control-label">菜系</label>
											    <div class="col-md-3">
											      <select class="form-control" id="t_id" name="t_id" >
													<!-- javaScript -->
												  </select>
											    </div>
											  </div>
											  <div class="form-group">
											    <label class="col-md-2 control-label">價格<font color="red">*</font></label>
											    <div class="col-md-3">
											      <input type="number" class="form-control" id="price" min="0" name="price" value="${param.price}"><font color="red">${errors.price}</font>
											    </div>
											  </div>
											<br>
									</div>
									<!-- 							餐點圖片								 -->
									<div role="tabpanel" class="tab-pane" id="messages">
											  <div class="form-group">
											    
											    <div class="col-md-8 col-md-offset-1">
											    <label for="d_photo">選擇圖片<font color="red">*(最多10張)</font></label>
											      <input id="d_photo" class="file" type="file" multiple="multiple" name="d_photo">
											    </div>
											  </div>
									</div>
									<!-- 							用餐說明								 -->
									<div role="tabpanel" class="tab-pane" id="settings">
											<div class="form-group">
											    <label class="col-md-2  control-label">人數上限<font color="red">*</font></label>
											    <div class="col-md-3">
											      <select class="form-control" id="quota" name="quota" >
											      <c:forEach var ='i' begin='0' end='10' step='1'>
											      	<c:choose>
											      	<c:when test="${i == 0}">
											      		<option selected value="${i}">請選擇...</option>
											      	</c:when>
											      	<c:otherwise>
												      	<c:choose>
												      		<c:when test="${i == param.quota}">
												      		<option selected value="${i}">${i}</option>
												      		</c:when>
												      		<c:otherwise>
												      		<option value="${i}">${i}</option>
												      		</c:otherwise>
												      		</c:choose>
												      	</c:otherwise>
												  	</c:choose>
												  </c:forEach>
												  </select><font color="red">${errors.quota}</font>
											    </div>
											  </div>
											  <div class="form-group">
											    <label class="col-md-2 control-label">用餐地點</label>
											    <div class="col-md-3">
											      <select class="form-control" id="v_id" name="v_id">
												  </select>
											    </div>
											  </div>
											  <div class="form-group">
											    <label class="col-md-2 control-label">用餐地址<font color="red">*</font></label>
											    <div class="col-md-3">
											      <input type="text" class="form-control" id="venue" name="venue" value="${param.venue}"><font color="red">${errors.venue}</font>
											    </div>
											  </div>
											<br>
											<div class="form-group">
												<div class="col-md-1 col-md-offset-2">
													<button type="submit" class="btn btn-primary btn" role="button">送出</button>
												</div>
												<div class="col-md-3">
													<button type="reset" class="btn btn-default">重設</button>
												</div>
											</div>
									</div>
								</div>
							</form>
						</div>
					</div>
				  </div>
				  </div>
				 </div>
		    	<footer id="footer">
		    		<hr>
		        	<p>&copy; 2017 YouChef Company, Inc.</p>
		    	</footer>
		    </div>
	    </div>
			
			
			<button id="webSocketBtn"><img id="csPic" src="<c:url value="/image/info.png"/>" width="50" height="50"></button>
			
			<div id="webSocket" style="display:none;">
				<iframe src="<c:url value="/demo.jsp" /> " width="300" height="500"></iframe>
			</div>			
			
		</div>
		<!-- /container -->
	
	
		<!-- Bootstrap core JavaScript
	    ================================================== -->
		<!-- Placed at the end of the document so the pages load faster -->
				
		<script src="<c:url value="/js/bootstrap.min.js"/>"></script>
		<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
		<script src="<c:url value="/js/ie10-viewport-bug-workaround.js"/>"></script>
		
		
		
		<script type="text/javascript">
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
			
			// Javascript to enable link to tab
			var url = document.location.toString();
			if (url.match('#')) {
			    $('.nav-tabs a[href="#' + url.split('#')[1] + '"]').tab('show');
			} 

			// Change hash for page-reload
			$('.nav-tabs a').on('shown.bs.tab', function (e) {
			    window.location.hash = e.target.hash;
			})
			
	</script>
	
	<script type="text/javascript">
$(function() {
	var arr = {address:[]};
//	用餐地點下拉式選單
	if('${param.v_id}'.length == 0){
		$("#v_id").append($("<option></option>").attr("value", "0").attr("selected", "selected").text("請選擇..."));
		$.getJSON('<c:url value='/pages/getvenue.controller'/>',{},function(data){
			$(data).each(function(index,i){
				$("#v_id").append($("<option></option>").attr("value", i.v_id).text(i.v_name));
				arr.address.push({v_id:i.v_id,v_address:i.v_address});
			});
		})
	}else{
		$("#v_id").append($("<option></option>").attr("value", "0").text("請選擇..."));
		$.getJSON('<c:url value='/pages/getvenue.controller'/>',{},function(data){
			$(data).each(function(index,i){
				if('${param.v_id}' == i.v_id){
					$("#v_id").append($("<option></option>").attr("value", i.v_id).attr("selected", "selected").text(i.v_name));
				}else{
					$("#v_id").append($("<option></option>").attr("value", i.v_id).text(i.v_name));
				}
				arr.address.push({v_id: i.v_id,v_address: i.v_address});
			});
		})
	}
	
	//動態顯示用餐地址
	$('#v_id').on('change', function() {
		$.each(arr.address, function (index, value) {
		    if($('#v_id').val() == value.v_id){
		    	$('#venue').val(value.v_address);
		    }
		});
	});
	
	// 	菜系下拉式選單
	if('${param.t_id}'.length == 0){
		$("#t_id").append($("<option></option>").attr("value", "0").attr("selected", "selected").text("請選擇..."));
		$.getJSON('<c:url value='/pages/gettype.controller'/>',{},function(data){
			$(data).each(function(index,i){
				$("#t_id").append($("<option></option>").attr("value", i.t_id).text(i.t_name));
			});
			
		})
	}else{
		$("#t_id").append($("<option></option>").attr("value", "0").text("請選擇..."));
		$.getJSON('gettype.controller',{},function(data){
			$(data).each(function(index,i){
				if('${param.t_id}' == i.t_id){
					$("#t_id").append($("<option></option>").attr("value", i.t_id).attr("selected", "selected").text(i.t_name));
				}else{
					$("#t_id").append($("<option></option>").attr("value", i.t_id).text(i.t_name));
				}
			});
		})
	}
	});

	$('#photo').fileinput({
		language : 'zh-TW',
		dropZoneEnabled : false,
		showUpload: false,
		autoReplace: true,
	    overwriteInitial: true,
	    initialPreview: [
	    	<c:choose>
	    		<c:when test='${not empty user.photo}'>
	        		"<img height='150' width='150' src='../pages/getMemImage.controller'>"
	        	</c:when>
	        	<c:otherwise>
	        		"<img height='150' width='150' src='<c:url value='/image/unknow128.png'/>'"
	        	</c:otherwise>
	        </c:choose>
	    ],
		allowedFileExtensions : [ 'jpg', 'png', 'gif', 'jpeg' ],
	});
	$('#d_photo').fileinput({
		language : 'zh-TW',
		dropZoneEnabled : false,
		showUpload: false,
		maxFileCount: 10,
	    overwriteInitial: true,
		allowedFileExtensions : [ 'jpg', 'png', 'gif', 'jpeg' ],
	});
	</script>

	</body>
</html>

