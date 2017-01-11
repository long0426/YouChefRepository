<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html lang="zh-Hant-TW">
<head>
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
	type="text/javascript"></script>
<style>
/* .item>a { */
/* 	display: block; */
/* 	height: 100%; */
/* } */
* {
	margin: 0;
	padding: 0;
}

img {
	text-decoration: none;
	color: #000;
}

 .modal-header .modal-body .modal-footer { 
 	font-size: 14px; 
 	font-family: Arial, '微软雅黑'; 
 	background: #2c2c2c; 
 } 
ul {
	list-style: none;
}

.box {
	width: 346px;
	height: 300px;
	margin: 30px auto 0;
	overflow: hidden;
	position: relative;
}

.box_img {
	width: 346px;
	height: 250px;
	overflow: hidden;
}

.box_img ul li {
	position: absolute;
	display: none;
}

.box_img ul li img {
	display: block;
	width: 346px;
	height: 250px;
	font-size: 200px;
	text-align: center;
	line-height: 250px;
	color: #fff;
}

.box_tab {
	position: absolute;
	bottom: 0;
	text-align: center;
	width: 346px
}

.box_tab img {
	display: inline-block;
	padding: 2px 10px;
	font-size: 10px;
	background: #fff;
	margin: 0 5px;
	color: #333;
}

.box_tab img.active {
	background: #09b;
	color: #fff;
}
</style>
<title>會員大廚專區</title>

</head>
<body>
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
					<div class="page-header">
						<h3>會員大廚專區</h3>
					</div>
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
										<div class="modal-footer" align="center">
											<div class="modal-footer">
												<button type="button" class="btn btn-default"
													data-dismiss="modal" onclick="closeIframe()">關閉</button>
												<button type="button" class="btn btn-primary" >訂購</button>
											</div>
										</div>
									</div>
								</div>
							</div>
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- /container -->
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
// 		            alert('The modal is about to be shown.');
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
// 				alert("hide.bs.modal");
				closeIframe();
			});
		});
	</script>
	<script type="text/javascript">
		function getdpid(d_id, mc_id, name, price, background) {
// 		$('#menu').click(function(d_id, mc_id, name, price){
			$('#myiframe').attr("src", "${pageContext.request.contextPath}" + "/pages/getpdid.controller?d_id=" + d_id);
			$('#header-img').attr("src","${pageContext.request.contextPath}" + "/pages/getImage.controller?mc_id=" + mc_id);
			$('#header-img').attr("title",background);
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
		}
	</script>
</body>
</html>