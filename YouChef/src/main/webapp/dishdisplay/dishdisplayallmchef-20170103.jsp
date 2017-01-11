<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

<script
	src="<c:url value='${request.contextPath}/js/bootstrap.js' />"></script>

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
.item>a {
	display: block;
	height: 100%;
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
										data-target=".bs-example-modal" value="${element.d_id}" onclick="getdpid(${element.d_id})"/>
									<div>${element.d_name}</div>
									<div>每份${element.price}元</div>
								</div>
							</c:forEach>
							<!-- Modal -->
							<div class="modal fade bs-example-modal" tabindex="-1"
								role="dialog" aria-labelledby="myLargeModalLabel"
								aria-hidden="true">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<div id="carousel-example-generic" class="carousel slide"
												data-ride="carousel">
												<!-- Indicators -->
												<ol class="carousel-indicators" id="indicator">
													<!-- javascript -->
												</ol>

												<!-- Wrapper for slides -->
												<div class="carousel-inner" role="listbox" id="listbox">
													<!-- javascript -->
												</div>

												<!-- Controls -->
												<a class="left carousel-control"
													href="#carousel-example-generic" role="button"
													data-slide="prev"> <span
													class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
													<span class="sr-only">Previous</span>
												</a> <a class="right carousel-control"
													href="#carousel-example-generic" role="button"
													data-slide="next"> <span
													class="glyphicon glyphicon-chevron-right"
													aria-hidden="true"></span> <span class="sr-only">Next</span>
												</a>
											</div>
										</div>
										<div class="modal-body">
											
										</div>
										<div class="modal-footer" align="center">
											<div class="modal-footer">
												<button type="button" class="btn btn-default"
													data-dismiss="modal">關閉</button>
												<button type="button" class="btn btn-primary">訂購</button>
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
		function getdpid(d_id) {
			var b = true;
			var count = 0;
			$.getJSON('getpdid.controller',{d_id : d_id},function(data) {
				$('#indicator').html('');
				$('#listbox').html('');
				$(data).each(function(index, i) {
					if(b){
						$('#indicator').append(
							'<li data-target="#carousel-example-generic" data-slide-to="' + count + '" class="active"></li>'		
						)
						count++;
						$('#listbox').append(
							'<div class="item active">'
							+ '<img src="getImage.controller?dp_id='+ i.dp_id + '" >'
							+ '<div class="carousel-caption"></div></div>'
						)
						b = false;
					}else{
						$('#indicator').append(
							'<li data-target="#carousel-example-generic" data-slide-to="' + count + '" ></li>'		
						)
						count++;
						$('#listbox').append(
								'<div class="item>'
								+ '<img src="getImage.controller?dp_id='+ i.dp_id + '" >'
								+ '<div class="carousel-caption"></div></div>'
							)
					}
				});
			});
		}
		$('.carousel').carousel({
			  interval: 2000
			})
	</script>
</body>
</html>