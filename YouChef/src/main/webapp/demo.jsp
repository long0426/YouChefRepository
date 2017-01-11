<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <title>Hello WebSocket</title>
    
  		<!-- Bootstrap core CSS -->
	<link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet">
    
    <style>
    
    	.footer {
    		position:fixed;
			bottom:50px;
    	}
    	
		#right{
			right:0px;
		}

    	
    </style>
    
    <script src="<c:url value="/js/sockjs-0.3.4.js"/>"></script>
    <script src="<c:url value="/js/stomp.js"/>"></script>
    
    <script type="text/javascript">
        var stompClient = null;

        function connect() {
            var socket = new SockJS('/YouChef/app/hello');
            stompClient = Stomp.over(socket);
            stompClient.connect({}, function(frame) {
                console.log('Connected: ' + frame);
                stompClient.subscribe('/topic/chatroom', function(messageOutput){
                    showMessageOutput(JSON.parse(messageOutput.body));
                });
            });
        }

        function sendMessage() {
            var content = document.getElementById('content').value;
            var m_id = document.getElementById('m_id').getAttribute('value');
            var name = document.getElementById('name').getAttribute('value');
            stompClient.send("/app/hello", {}, JSON.stringify({ 'content': content, 'm_id': m_id, 'name': name }));
        }

        function showMessageOutput(messageOutput) {
            var response = document.getElementById('response');
            var p = document.createElement('p');
            p.style.wordWrap = 'break-word';
            p.appendChild(document.createTextNode(messageOutput.name + ":  " +messageOutput.content + "   (" + messageOutput.time +")"));
            response.appendChild(p);
        }

        
    </script>
</head>
<!-- <body onload="disconnect()"> -->
<body>
<noscript><h2 style="color: #ff0000">Seems your browser doesn't support Javascript! Websocket relies on Javascript being enabled. Please enable
    Javascript and reload this page!</h2></noscript>

			<div class="container col-md-12">
	            <div class="box user-block">
	                <div class="pic-container">
	                <div class="row" id="right">
	                	<div class="col-md-5">
		              <img src="<c:url value="/images/YouChef-logo.png"/>" width="90" height="90" alt="image">
		              	</div>
		               <div class="col-md-10">
		              </div>
	                </div>
	                <div class="padding">
	                    <h5 class="user-name center">客服</h5>
	                    <h5 class="user-name center">需要幫忙嗎?</h5>
	                    <hr class="full">
	                </div>
	            </div>
			</div>
		<div class="">
			<c:choose>
				<c:when test="${not empty messages}">
					<c:forEach var="element" items="${messages}">
						<fmt:formatDate value="${element.time}" var="formattedDate" type="date" pattern="dd日 hh:mm"/>
						<p>${element.memberBean.firstName}${element.memberBean.lastName}:  ${element.content}  (${formattedDate})</p>
					</c:forEach>
				</c:when>	
			</c:choose>
			<p id="response"></p>
		</div>
	 			<div id="m_id" value="${user.m_id}"></div>
	 			<div id="name" value="${user.firstName}${user.lastName}"></div>
	 		
		 		<div class="footer">
			        <input type="text" id="content">
			      	 <button id="sendMessage" type="button" class="btn btn-default" data-dismiss="modal" onclick="sendMessage();">Send</button>
			   </div>

	</div>
		
		<script type="text/javascript">
			window.onload = function(){
				connect();
			};
		</script>
</div>

	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script>
		window.jQuery
				|| document
						.write('<script src="../js/vendor/jquery.min.js"><\/script>')
	</script>
	<script src="<c:url value="/js/bootstrap.min.js"/>"></script>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="<c:url value="/js/ie10-viewport-bug-workaround.js"/>"></script>

</body>
</html>