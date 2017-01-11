<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/bootstrap.min.css">
<!-- <link rel="stylesheet" href="css/jquery-ui.min.css"> -->

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
					<a class="navbar-brand" href="#">${ChefByType[0].typeBean.t_name}</a>
				</div>

			
			</div>
			<!-- /.container-fluid -->
		</nav>
	</div>
	<div class="container">
		<div class="row">
		    <div class="col-md-3">
		       <div class="list-group">
  <a href="chefdisplayAll.jsp" class="list-group-item">全部大廚</a>
  <a href="<c:url value="${request.contextPath}/chefdisplay/selectChefByType.controller?id=3001" />"class="list-group-item">台式料理</a>
  <a href="<c:url value="${request.contextPath}/chefdisplay/selectChefByType.controller?id=3002" />" class="list-group-item">日本料理</a>
  <a href="<c:url value="${request.contextPath}/chefdisplay/selectChefByType.controller?id=3004" />" class="list-group-item">西式料理</a>
  <a href="<c:url value="${request.contextPath}/chefdisplay/selectChefByType.controller?id=3003" />" class="list-group-item">川味料理</a>
  <a href="<c:url value="${request.contextPath}/chefdisplay/selectChefByType.controller?id=3005" />" class="list-group-item">東南亞料理</a>

 
</div>
		    </div>
		    <div class="col-md-9">
		    <div class="jumbotron">
			<div class="page-header">
				<h3>${ChefByType[0].typeBean.t_name}</h3>
				<div >
												<input type="button" value="立即訂購" onclick="location.href='要前往的網頁連結'">
											</div>
			</div>
			<!-- 每頁不同的內容從這裡開始 -->
              <!-- 按照jQueryUI的範例製作網頁內容 -->
<form action="<c:url value="${request.contextPath}/chefdisplay/selectChefByType.controller?id=3002" />"
			method="post">
	
			<table  class="table table-striped">
				<thead>
					<tr>
						<td>照片</td>
						<td>大廚姓名</td>
						<td>背景</td>
						<td>菜系</td>
						<td>年資</td>
					</tr>
				</thead>
				<tbody>
<%-- 					<c:if test="${not empty ChefByType}"> --%>
						<c:forEach var="element" items="${ChefByType}">
							<tr> 
								<td><a href="<c:url value="${request.contextPath}/chefdisplay/selectChef.controller?c_id=${element.c_id}" />"><img height='100' width='100' src='<c:url value="${request.contextPath}/pages/getImage.controller?id=${element.c_id}" />'></a></td>
								<td>${element.lastName}${element.firstName}</td>
								<td>${element.background}</td>
								<td>${element.typeBean.t_name}</td>
								<td>${element.years}</td>
								
							</tr>
						</c:forEach>
<%-- 					</c:if> --%>
				</tbody>
			</table>
<!-- 		<input type="submit"> -->
	</form>
			<!-- 每頁不同的內容到這裡結束 -->
		</div>
		    </div>
		</div>
</div>
		
	<!-- /container -->


	<script src="js/jquery-1.12.3.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
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