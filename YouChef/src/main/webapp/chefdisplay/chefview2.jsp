<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="../css/table.css" />
<title>大廚上菜</title>
<link rel="stylesheet" href="../css/bootstrap.min.css">
<!-- <link rel="stylesheet" href="css/jquery-ui.min.css"> -->
<script type="text/javascript" src="../js/jquery.js"></script>

	<style type="text/css">

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
					<a class="navbar-brand" href="#">大廚上菜</a>
				</div>

			
			</div>
			<!-- /.container-fluid -->
		</nav>
	</div>
	<div class="container">
		<div class="row">
		    <div class="col-md-3">
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
							<td><img height='80' width='80' src='<c:url value="${request.contextPath}/pages/getImage.controller?id=${chef.c_id}" />'></td>
							<td>${chef.lastName}${chef.firstName}</td>
							<td>${chef.background}</td>
							<td>${chef.typeBean.t_name}</td>
							<td>${chef.years}年</td>
				
						</tr>
			</tbody>
		</table>
					<div >
			<input type="button" value="立即訂購" onclick="location.href='要前往的網頁連結'">
				</div>
		<p>
		
	</form>
	
	<br>
              <!-- 按照jQueryUI的範例製作網頁內容 -->

			<!-- 每頁不同的內容到這裡結束 -->
		</div>
		    </div>
		</div>
</div>
		
	<!-- /container -->


	<script src="../js/jquery-1.12.3.min.js"></script>
	<script src="../js/bootstrap.min.js"></script>
<!-- 	<script src="js/jquery-ui.min.js"></script> -->
	<script>
// 	 $(function () {
//          $("#tabs").tabs({
//              event: "mouseover"
//          });

//      });
	</script>
</body>

</html>