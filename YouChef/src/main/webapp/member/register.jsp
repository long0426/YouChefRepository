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
    <link rel="icon" href="../../favicon.ico">

    <title>優廚 - 加入會員</title>

    <!-- Bootstrap core CSS -->
    <link href="../css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="../css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="../css/jumbotron.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="../js/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    
    <script charset="UTF-8" src="../js/address.js"></script>
    
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
        <div id="navbar" class="navbar-collapse collapse">
          <form class="navbar-form navbar-right">
            <div class="form-group">
              <input type="text" placeholder="Email" class="form-control">
            </div>
            <div class="form-group">
              <input type="password" placeholder="Password" class="form-control">
            </div>
            <button type="submit" class="btn btn-success">Sign in</button>
          </form>
        </div><!--/.navbar-collapse -->
      </div>
    </nav>

    <!-- Main jumbotron for a primary marketing message or call to action -->

    <div class="container col-md-8 col-md-offset-2">
	<h2>加入會員-設定帳號</h2>
	<br/>
	<form class="form-group" action="<c:url value="/member/register.controller"/>" method="get">
		<div class="form-group" id="span">
<!-- 		<div class = "col-xs-4"> -->
			<label for="inputMail">電子信箱:
			<input type="text" name="email" id="inputMail" value="${param.email}">
			<span id="spanSign" class="glyphicon glyphicon-question-sign"></span>
			<span class="form-inline" id="message1">${errors.emailFromat} ${errors.email}</span>
			</label>
		</div>

		
		<div class="form-group">
			<label for="inputpassword1">輸入密碼:
			<input type="password" name="password" id="inpassword" size="8"> ${errors.password1}
			</label>
		</div>
		<div class="form-group">
			<label for="inputpassword2">確認密碼:
			<input type="password" name="password2" id="inpassword2" size="8"> ${errors.password2}
			</label>
		</div>		
		
		<div class="form-inline">
			<label for="firstName">姓:
				<input size="3" type="text" name ="firstName" id="firstName" value="${param.firstName}">
			</label>
			<label for="lastName">名:
				<input size="3" type="text" name ="lastName" id="lastName" value="${param.lastName}">
			</label> 
			<label for="nickname">暱稱:
				<input size="3" type="text" name ="nickname" id="nickname" value="${param.nickname}">
			${errors.name}
			</label>
		</div>
		<div class="radio-inline">
  			<label>
    			<input type="radio" name="sex" id="male" value="0" checked> 男
   			 </label>
        </div>
		<div class="radio-inline">
  			<label>
    			<input type="radio" name="sex" id="female" value="1">女
            </label>
		</div>
		<div class="form-group">
			<label for="phone">聯絡電話:
				<input type="text" name="phone" id="phone" value="${param.phone}"> ${errors.phone}
			</label>
		</div>
		
		<script type="text/javascript">
        	window.onload = function () {
            	AddressSeleclList.Initialize('city', 'district');
            //	選擇縣市, 地區的javascript
       		}
       		function show() {
            	alert(AddressSeleclList.ReturnSelectAddress('city', 'district', 'address'));
        	}
    	</script>
    	<div class="form-group">
    		<label for="address"> 地址: 
    	 	<select class="form-inline" id="city" name="city"></select>
         	<select class="form-inline" id="district" name="district"></select>
		 	<input type="text" name="address" id="address" name="address" value="${param.address}">
         	${errors.address}
			</label>
		</div>
		<div class="form-group">
			<label for="captcha">
			系統產生認證碼:<img src="captcha.controller" alt="New Image"/><br>
			請輸入上認證碼:<input type="text" name="captcha" size="3"/><span id="spanCaptcha" class="">  ${errors.captcha}</span>
			</label>
		</div>	
		<p>
		<button type="submit" class="btn btn-primary">
			確認送出
		</button>
		<button type="button" class="btn btn-danger" value="Clear" onclick="clearForm()">
			取消
		</button>
		</p>
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
    <script src="../js/bootstrap.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="../js/ie10-viewport-bug-workaround.js"></script>
    <script type="text/javascript" charset="UTF-8">
		function clearForm() {
			var inputs = document.getElementsByTagName("input");
			for(var i=0; i<inputs.length; i++) {
				if(inputs[i].type=="text") {
					inputs[i].value="";
				}
			}
		}
		
		$('#inputMail').blur(check);
		function check(){
			var email = $(this).val();
			$.get('accountCheck.controller',{'email':email},function(data){
				var spn =$('#spanSign');
						if(data=="帳號已存在"){
							spn.removeClass().addClass('glyphicon glyphicon-remove-sign');
							$('#message1').text(data);
						}else if (email===""){
							spn.removeClass().addClass('glyphicon glyphicon-remove-sign');
						}else {
							spn.removeClass().addClass('glyphicon glyphicon-ok-sign');
							$('#message1').text(data);
						}
			})				
		}
	</script>
  </body>
</html>

