<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<!DOCTYPE SYSTEM "about:legacy-compat">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="<c:url value="/css/table.css"/>" type="text/css" rel="stylesheet">
<title>大廚餐點</title>
<link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet">

<link href='https://fonts.googleapis.com/css?family=PT+Sans+Narrow' rel='stylesheet' type='text/css'>
		<script
			src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<!-- GENERAL CSS FILES -->
	<link rel="stylesheet" href="css/minified.css">
	<!-- // GENERAL CSS FILES -->
	<script>window.jQuery || document.write('<script src="js/jquery.min.js"><\/script>');</script>
	<script src="js/modernizr.min.js"></script>	
	<!-- PARTICULAR PAGES CSS FILES -->
	<link rel="stylesheet" href="css/jquery.nouislider.css">
	<link rel="stylesheet" href="css/isotope.css">

 	<!-- // PARTICULAR PAGES CSS FILES --> 
<!-- 	<link rel="stylesheet" href="css/responsive.css"> -->

	<style>
		@media (min-width: 992px)
		.col-md-4 {
		    width: 33.33333333333333%;
		    height:400px;
		}
		
		
		.entry-main {
			height:200px;			
		}
		
		.col-md-6 img{
			border: 2px solid;
   			border-radius: 25px;
   			width:400px;
   			height:300px;
		}
		
	</style>

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
					<a class="navbar-brand" href="#">大廚餐點</a>
				</div>

			
			</div>
			<!-- /.container-fluid -->
		</nav>
	</div>
	<div class="container">
		<div class="row">
		    <div class="col-md-2">
		       <div class="list-group">
				  <a href="<c:url value="${request.contextPath}/showDishes.controller?id=3001" />" class="list-group-item">台式料理</a>
				  <a href="<c:url value="${request.contextPath}/showDishes.controller?id=3002" />" class="list-group-item">日本料理</a>
				  <a href="<c:url value="${request.contextPath}/showDishes.controller?id=3004" />"class="list-group-item">西式料理</a>
				  <a href="<c:url value="${request.contextPath}/showDishes.controller?id=3003"/>" class="list-group-item">川味料理</a>
				  <a href="<c:url value="${request.contextPath}/showDishes.controller?id=3005"/>" class="list-group-item">東南亞料理</a>
				<div class="list-group">
					<a href="../pages/getmchef.controller" class="list-group-item">會員大廚專區</a>
			   </div>				
			   </div>
			</div>



				<div class="col-md-9">
				
				<h3>${ob[0].typeBean.t_name}</h3>
				
			<!-- 每頁不同的內容從這裡開始 -->
				<form action="<c:url value="${request.contextPath}/dishdisplay/showDishes.controller" />"
			method="post">
					<c:forEach var="element" items="${ob}">
					
					
								<div class="col-md-6">
										<div >
											<img src="<c:url value="${request.contextPath}/getdishImage.controller?id=${element.d_id}" />" alt="" class="lazyLoad thumb" /><!--圖片 -->

										</div>
										<div class="entry-main">
											<h3 class="entry-title"><a href="#">${element.d_name}</a></h3>
											<div class="entry-description visible-list">
								
											</div>
											<div>
												<strong class="accent-color price">售價${element.price}元</strong>
<!-- 												<a href="#" class="btn btn-round btn-default add-to-cart visible-list">Add to Cart</a> -->
											</div>
											<div class="entry-price"><strong class="accent-color price">${element.d_briefing}</strong>
<!-- 												<a href="#" class="btn btn-round btn-default add-to-cart visible-list">Add to Cart</a> -->
											</div>
											<div >
												<input type="button" value="立即訂購" onclick="location.href='要前往的網頁連結'">
											</div>
										</div>
								</div>
										</c:forEach>								
					</form>		
			
			</div>				
			</div>					

</div>
	
								
			
	<!-- /container -->


	<script src="<c:url value="/js/bootstrap.min.js"/>"></script>
		<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
		<script src="<c:url value="/js/ie10-viewport-bug-workaround.js"/>"></script>
	<script>
// 	 $(function () {
//          $("#tabs").tabs({
//              event: "mouseover"
//          });

//      });
	</script>
</body>

</html>