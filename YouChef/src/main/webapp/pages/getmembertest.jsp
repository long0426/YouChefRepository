<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="shortcut icon" href="../images/favicon.png">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="../css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="../css/bootstrap-responsive.css" rel="stylesheet">
<title>會員列表測試</title>
</head>
<body bgcolor="white">
	<script src="http://code.jquery.com/jquery.js"></script>
	<script src="../js/bootstrap.min.js"></script>
	<form action="<c:url value="${request.contextPath}/pages/getmember.controller" />"
		method="post">
		<table class="table table-hover">
			<thead>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>帳號</td>
					<td>姓名</td>
					<td>暱稱</td>
					<td>性別</td>
					<td>電話</td>
					<td>地址</td>
					<td>自我介紹</td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
				</tr>
			</thead>
			<tbody>
				<c:if test='${not empty list}'>
					<c:forEach var="element" items="${list}">
						<tr class="warning">
							<td><img height='50' width='50' src='<c:url value="${request.contextPath}/pages/getImage.controller?m_id=${element.m_id}" />'></td>
							<td>${element.email}</td>
							<td>${element.firstName}${element.lastName}</td>
							<td>${element.nickname}</td>
							<td>${element.sex}</td>
							<td>${element.phone}</td>
							<td>${element.city}${element.district}${element.address}</td>
							<td>${element.briefing}</td>
							<td>
							<a href="<c:url value="${request.contextPath}/pages/mchefapply.jsp?m_id=${element.m_id}&email=${element.email}&name=${element.firstName}${element.lastName}" />">申請會員大廚</a>
							<a href="<c:url value='${request.contextPath}/pages/getmchef.controller?mc_id=${element.m_id}'/>" Target='_blank'>營業日設定</a>
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
		<p>
			<input type="submit" name="getchef" value="會員列表">
	</form>
	<br>
</body>
</html>