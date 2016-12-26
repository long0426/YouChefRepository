<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh">
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="<c:url value="../favicon.ico"/>">

    <title>優廚 - 站內信箱</title>

    <!-- Bootstrap core CSS -->
    <link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="<c:url value="/css/ie10-viewport-bug-workaround.css"/>"rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="<c:url value="/css/jumbotron.css"/>" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="<c:url value="/js/ie-emulation-modes-warning.js"/>"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style>
		#navuser{
			color:#93B7DB;
			font-size:18px
		}
		#unread{
			color:#93B7DB;
			font-size:18px
		}
	</style>
	
  </head>

  <body>

    <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="<c:url value="/index.jsp"/>">優廚 youChef</a>
        </div>
        <c:choose>
       	 	 <c:when test="${empty user}">
        <div id="navbar" class="navbar-collapse collapse">
        

		
          		<form class="navbar-form navbar-right" action="<c:url value="/member/login.controller"/>" method="POST">
            		<div class="form-group">
              			 <input type="text" placeholder="Email" name="email" class="form-control">
            		</div>
            		<div class="form-group">
             			 <input type="password" placeholder="Password" name="password" class="form-control">
            		</div>
            			<button type="submit" class="btn btn-success">Sign in</button>
				</form>			      
        </div><!--/.navbar-collapse -->
       </c:when>
           <c:otherwise>
        	<div class="col-md-3 col-md-push-9">
        		<p id="navuser">${user.firstName} ${user.lastName}</p>
        		<a id="unread" href="<c:url value="/mail/inbox.controller"/>">未讀:<c:choose> <c:when test="${empty inbox}">(0)</c:when><c:otherwise>${inbox}</c:otherwise></c:choose></a>
        	</div>
           </c:otherwise>
      </c:choose>  
      </div>
    </nav>

    <!-- Main jumbotron for a primary marketing message or call to action -->

    <div class="container col-md-8 col-md-offset-2">
	<h2>收件匣</h2>
	
            <a href="<c:url value="/email/mail.controller"/>" class="btn btn-info">新郵件</a>
	  <form>
		    <table class="table table-hover table-curved">
				<thead>
				<tr>
					<th></th>
					<th></th>
					<th>寄件者</th>
					<th>主題</th>
					<th>內文</th>
					<th>時間</th>
					<th></th>
				</tr>
				</thead>
				<tbody>
				<c:forEach var="element" items="${mails}" varStatus="loopCounter">
		
					<c:url value="/pages/product.jsp" var="path">
						<c:param name="reciver1" value="${element.receiver.lastName}" />
						<c:param name="reciver2" value="${element.receiver.firstName}" />
						<c:param name="sender" value="${element.sender.email}" />
						<c:param name="content" value="${element.content}" />
					</c:url>
					<c:choose>
						<c:when test="${element.i_status == '0'}">
						   <tr class="info"> 
						   		<td><img alt="" src="<c:url value="/image/new.gif" />"></span></td>
								<td>${loopCounter.count}</td>
								<td>${element.sender.firstName}${element.sender.lastName}</td>
								<td>${element.subject}</td>
								<td><a href="path">${element.content}</a></td>
								<td>${element.mail_time}</td>
								<td><a href="<c:url value="/email/mail.controller?action=delete&mail_id=${element.mail_id }"/>"  
									   class="btn btn-danger"
									   onClick="return(confirm('你確定要刪除這封信件嗎?'))">刪除
									</a>
								</td>
						</c:when>
					<c:otherwise>
							<tr class="info">
								<td></td>
								<td>${loopCounter.count}</td>
								<td>${element.sender.firstName}${element.sender.lastName}</td>
								<td>${element.subject}</td>
								<td><a href="path">${element.content}</a></td>
								<td>${element.mail_time}</td>
			<%-- 					<td><a href="<c:url value="/email/mail.controller?action=reply&m_id=${element.sender.m_id }"/>" class="btn btn-success">回覆</a></td> --%>
								<td><a href="<c:url value="/email/mail.controller?action=delete&mail_id=${element.mail_id }"/>"  class="btn btn-danger">刪除</a></td>
			<!-- 					<td><input type="button" class="btn btn-success" name="emailAction" value="回覆"></td> -->
			<!-- 					<td><input type="button" class="btn btn-danger" name="emailAction" value="刪除"></td> -->
							</tr>
					</c:otherwise>
					</c:choose>
				</c:forEach>
				</tbody>
			</table>
	  </form>

      <hr>
      <footer>
        <p>&copy; 2016 Company, Inc.</p>
      </footer>
    </div> <!-- /container -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script>window.jQuery || document.write('<script src="../js/vendor/jquery.min.js"><\/script>')</script>
    <script src="<c:url value="/js/bootstrap.min.js"/>"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="<c:url value="/js/ie10-viewport-bug-workaround.js"/>"></script>
  </body>
</html>

