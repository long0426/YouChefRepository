<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="zh-Hant-TW">
<head>
<link rel="shortcut icon" href="../images/favicon.png">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="../css/bootstrap.min.css" rel="stylesheet">
<link href="../css/bootstrap-theme.min.css" rel="stylesheet">
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="../css/default.css">
<link href="../css/fileinput.css" media="all" rel="stylesheet"
	type="text/css" />
<script src="../js/fileinput.js" type="text/javascript"></script>
<script src="../js/locales/zh-TW.js" type="text/javascript"></script>
<style type="text/css">
.full-screen {
	background-size: cover;
	background-position: center;
	background-repeat: no-repeat;
}

.item>a {
	display: block;
	height: 100%;
}
</style>
<title>餐點內容</title>
</head>
<body>
	<div class="container">
		<div id="mycarousel" class="carousel slide" data-ride="carousel">
			<ol class="carousel-indicators"></ol>
			<!-- 廣告輪播列表 -->
			<div class="carousel-inner" role="listbox">
				<c:forEach var="element" items="${list}" varStatus="status">
					<c:choose>
						<c:when test="${status.first}">
							<div class="item active">
						</c:when>
						<c:otherwise>
							<div class="item">
						</c:otherwise>
					</c:choose>
						<img src="<c:url value='${request.contextPath}/pages/getImage.controller?dp_id=${element.dp_id}' />" data-color="#eee">
							<div class="carousel-caption">
								<h3>${element.dishesBean.d_name}</h3>
							</div>
					</div>
				</c:forEach>
<!-- 				<div class="item"> -->
<%-- 					<img src="<c:url value='${request.contextPath}/pages/getImage.controller?dp_id=6013' />" data-color="#eee"> --%>
<!-- 					<div class="carousel-caption"> -->
<!-- 						<h3>廣告輪播2</h3> -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 				<div class="item"> -->
<%-- 					<img src="<c:url value='${request.contextPath}/pages/getImage.controller?dp_id=6014' />" data-color="#eee"> --%>
<!-- 					<div class="carousel-caption"> -->
<!-- 						<h3>廣告輪播3</h3> -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 				<div class="item"> -->
<%-- 					<img src="<c:url value='${request.contextPath}/pages/getImage.controller?dp_id=6015' />" data-color="#eee"> --%>
<!-- 					<div class="carousel-caption"> -->
<!-- 						<h3>廣告輪播4</h3> -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 				<div class="item"> -->
<%-- 					<img src="<c:url value='${request.contextPath}/pages/getImage.controller?dp_id=6016' />" data-color="#eee"> --%>
<!-- 					<div class="carousel-caption"> -->
<!-- 						<h3>廣告輪播5</h3> -->
<!-- 					</div> -->
<!-- 				</div> -->
			</div>

			<!-- Controls -->
			<a class="left carousel-control" href="#mycarousel" role="button"
				data-slide="prev"> <span
				class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
				<span class="sr-only">上一則</span>
			</a> <a class="right carousel-control" href="#mycarousel" role="button"
				data-slide="next"> <span
				class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
				<span class="sr-only">下一則</span>
			</a>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(function() {
		var $item = $('.carousel .item');
		var $wHeight = $(window).height();
		$item.height($wHeight);
		$item.addClass('full-screen');

		$('.carousel img').each(function() {
			var $src = $(this).attr('src');
			var $color = $(this).attr('data-color');
			$(this).parent().css({
				'background-image' : 'url(' + $src + ')',
				'background-color' : $color
			});
			$(this).remove();
		});

		//下方自動加入控制圓鈕
		var total = $('.carousel .carousel-inner div.item').length;
		append_li();
		function append_li() {
			var li = "";
			var get_ac = $(".carousel .active");
			var ac = $(".carousel .carousel-inner div").index(get_ac);

			for (var i = 0; i <= total - 1; i++) {
				if (i == (ac) / 2) {
					li += "<li data-target='#mycarousel' data-slide-to='"+i+"' class='active'></li>";
				} else {
					li += "<li data-target='#mycarousel' data-slide-to='"+i+"' class=''></li>";
				}
			}
			$(".carousel-indicators").append(li);
		}

		//單則隱藏控制鈕
		if ($('.carousel .carousel-inner div.item').length < 2) {
			$('.carousel-indicators, .carousel-control').hide();
		}

		//縮放視窗調整視窗高度
		$(window).on('resize', function() {
			$wHeight = $(window).height();
			$item.height($wHeight);
		});

		//輪播秒數與滑入停止
		$('.carousel').carousel({
			interval : 2000,
			pause : "hover"
		});
	});
</script>
</html>

