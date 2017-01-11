<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>後台 - 餐點管理</title>	
	<!-- Bootstrap core CSS -->
	<link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet">
	
	<link href="<c:url value="/css/back.end.css"/>" rel="stylesheet">	
	
	
</head>
<body class="home">

    <div class="container-fluid display-table">
        <div class="row display-table-row">
            <div class="col-md-2 col-sm-1 hidden-xs display-table-cell v-align box" id="navigation">
                <div class="logo">
                    <a hef="home.html"><img src="<c:url value="/images/YouChef-logo-backend.png" />" alt="youchef_logo" class="hidden-xs hidden-sm" height="150" width="160">
                        <img src="<c:url value="/images/YouChef-logo-backend.png"/>" alt="youchef_logo" class="visible-xs visible-sm circle-logo">
                    </a>
                </div>
                <div class="navi">
                    <ul>
                        <li class="active"><a href="<c:url value="/backend/ListAllMember.controller"/>"><i class="glyphicon glyphicon-user" aria-hidden="true"></i><span class="hidden-xs hidden-sm">會員管理</span></a></li>
                        <li><a href="<c:url value="${request.contextPath}/chefdisplay/chefview2.controller"/>"><i class="glyphicon glyphicon-cutlery" aria-hidden="true"></i><span class="hidden-xs hidden-sm">大廚管理</span></a></li>
                        <li><a href="<c:url value="${request.contextPath}/showDishes2.controller?t_id=3001"/>"><i class="glyphicon glyphicon-edit" aria-hidden="true"></i><span class="hidden-xs hidden-sm">餐點管理</span></a></li>
                        <li><a href="<c:url value="/backEndOrder.controller"/>"><i class="glyphicon glyphicon-usd" aria-hidden="true"></i><span class="hidden-xs hidden-sm">訂單管理</span></a></li>
                        <li><a href="#"><i class="fa fa-cog" aria-hidden="true"></i><span class="hidden-xs hidden-sm"></span></a></li>
                        <li><a href="#"><i class="fa fa-cog" aria-hidden="true"></i><span class="hidden-xs hidden-sm"></span></a></li>
                        <li><a href="#"><i class="fa fa-cog" aria-hidden="true"></i><span class="hidden-xs hidden-sm"></span></a></li>
                    </ul>
                </div>
            </div>
            <div class="col-md-10 col-sm-11 display-table-cell v-align">
                <!--<button type="button" class="slide-toggle">Slide Toggle</button> -->
                <div class="row">
                    <header>
                        <div class="col-md-7">
                            <nav class="navbar-default pull-left">
                                <div class="navbar-header">
                                    <button type="button" class="navbar-toggle collapsed" data-toggle="offcanvas" data-target="#side-menu" aria-expanded="false">
                                        <span class="sr-only">Toggle navigation</span>
                                        <span class="icon-bar"></span>
                                        <span class="icon-bar"></span>
                                        <span class="icon-bar"></span>
                                    </button>
                                </div>
                            </nav>
<!--                             <div class="search hidden-xs hidden-sm"> -->
<!--                                 <input type="text" placeholder="Search" id="search"> -->
<!--                             </div> -->
                        </div>
                        <div class="col-md-5">
                            <div class="header-rightside">
                                <ul class="list-inline header-top pull-right">
                                    <li class="hidden-xs"><a href="#" class="add-project" data-toggle="modal" data-target="#add_project">增刪管理員</a></li>
<!--                                    	<li class="hidden-xs"><a href="#" class="add-project" data-toggle="modal" data-target="#add_test">新增大廚</a></li> -->
                                    <li><a href="#"><i class="fa fa-envelope" aria-hidden="true"></i></a></li>
                                    <li>
                                        <a href="#" class="icon-info">
                                            <i class="fa fa-bell" aria-hidden="true"></i>
                                            <span class="label label-primary" id="mailCount">
                                            	<c:choose>
													<c:when test="${empty inbox}">(0)</c:when>
													<c:otherwise>${inbox}</c:otherwise>
												</c:choose>
                                            </span>
                                        </a>
                                    </li>
                                    <li class="dropdown">
                                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
											<c:choose>
												<c:when test="${empty admin.photo}">
													<img src="<c:url value="/image/unknow64.png" />"
															class="center-block">
												</c:when>
												<c:otherwise>
														<img src="<c:url value="/pages/getAdminImage.controller" />" height="64" width="64" class="center-block">
												</c:otherwise>
											</c:choose>
                                            <b class="caret"></b></a>
                                        <ul class="dropdown-menu">
                                            <li>
                                                <div class="navbar-content">
                                                    <span>管理員</span>
                                                    <p class="text-muted small">
                                                        ${admin.lastName}${admin.firstName}
                                                    </p>
                                                    <div class="divider">
                                                    </div>
                                                    <a href="<c:url value="/admin/mail/inbox.controller"/>" class="view btn-sm active">信箱</a>
                                                    <a href="<c:url value="/member/logout.jsp" />" class="view btn-sm active">登出</a>
                                                </div>
                                            </li>
                                        </ul>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </header>
                </div>
                 <!-- 餐點管理 -->
                <div class="user-dashboard">
                    <h3>${ob[0].typeBean.t_name}</h3>
                 
		
		<form method="GET" name="type" id="t_id" action="">
			<div class="col-sm-3">
				<Select id="typeDish" name="t_id" onchange='changeAction(this.value)' class="form-control inputstl">
					<option value="3001" selected>台式料理</option>
					<option value="3002">日本料理</option>
					<option value="3003">川味料理</option>
					<option value="3004">西式料理</option>
					<option value="3005">東南亞料理</option>
				</Select>
			</div>
		</form>
		
			<script type="text/javascript">

				function changeAction(val){
					document.getElementById('t_id').setAttribute('action','<c:url value="${request.contextPath}/showDishes2.controller?t_id="/>+val');
					type.submit();
				}
				
			</script>
		

		<form method="GET" name="typemenu" id="menu" action="">
		<div class="col-sm-3">
			<Select name="menu" value='changeAction(this.value)' class="form-control inputstl">
				<option value="3001">台式套餐</option>
				<option value="3002">日本套餐</option>
				<option value="3003">川味套餐</option>
				<option value="3004">西式套餐</option>
				<option value="3005">東南套餐</option>
			</Select>
		</div>
		</form>
	 		<script type="text/javascript"> 
		 		function changeAction(val){
		 		document.getElementById('menu').setAttribute('action','+val');
		 		type.submit();
		 		}
	 		
	 		</script> 
	
		
                    <form action="<c:url value="/showDishes.controller?id=3005" />" method="get">
		
				
                    <div class="row">
                        <div class="col-md-12 col-sm-12 col-xs-12 gutter">
                            <div class="sales">
								<table class="table table-hover table-curved">
				
										<thead>
										<tr>
											<td>照片</td>
											<td>品名</td>
											<td>價錢</td>
											<td>介紹</td>
											<td></td>
										</tr>
										</thead>
										<tbody>
										<c:forEach var="element" items="${ob}" varStatus="status">		
												   <tr class="warning">
												   		<td><img height='50' width='50' src='<c:url value="${request.contextPath}/getdishImage.controller?id=${element.d_id}" />'></td>	
														<td>${element.d_name}</td>
														<td>${element.price}</td>
														<td>${element.d_briefing}</td>
														<td>
															<a href="<c:url value="/dishdisplay/dishes.jsp?d_id=${element.d_id}"/>" data-toggle="modal" class="btn btn-success">編輯</a>
														</td>
										</c:forEach>
										</tbody>
									</table>
									 </div>
      </div>
       </div>
									</form>
                                </div>
                            </div>
                        </div>
                    </div>
    <!-- Modal -->
    <div id="add_project2" class="modal fade" role="dialog">
        <div class="new-chef">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header login-header">
                    <button type="button" class="close" data-dismiss="modal">×</button>
                    <h4 class="modal-title">新增餐點</h4>
                </div>
                
                <!-- 新增餐點的表格 -->              
                <form id="ds" class="insert"  enctype="multipart/form-data">  
                	<div class="modal-body">
                	
                				<input type="text" placeholder="菜名" name="d_name" value="${param.d_name}">
                				<span>${errors.d_name}</span>		
								<h6>菜系:</h6><Select name="t_id2" value="${param.t_id}"><option value="3001">台式料理</option>
																		  <option value="3002">日本料理</option>
																		  <option value="3003">川味料理</option>
																		  <option value="3004">西式料理</option>
																		  <option value="3005">東南亞料理</option>
																		  </Select>	
								<input type="text" placeholder="售價" name="price" value="${param.price}">
								<h6>狀態:</h6><Select name="d_status" value="${param.d_status}">
																		  <option value="0">上架</option>
																		  <option value="1">下架</option>
																		  </Select>
																		<tr height='36'>
								<textarea  placeholder="菜品簡介" name="d_briefing" rows="8" cols="80" value="${param.d_briefing}"></textarea>									  
								<h6>照片:</h6><input  class='InputClass'  type="file"  placeholder="照片" name="file" size="40" />
	                           
                	</div>
	                <div class="modal-footer">
	                    <button type="button" class="cancel" data-dismiss="modal">Close</button>
	                    <button type="submit" class="add-project" data-dismiss="modal" id="submit2">Save</button>
	                </div>
                </form> 
            </div>

        </div>
    </div>
    
        <!-- Modal -->
	    <div id="editDishes" class="modal fade" role="dialog">
	        <div class="edit-Dishes">
	            <div class="modal-content">
	                <div class="modal-header login-header">
	                    <button type="button" class="close" data-dismiss="modal">×</button>
	                    <h4 class="modal-title">編輯餐點</h4>
	                </div>
	                
	                <!-- 編輯餐點的表格 -->
	                <form id="ed" class="edit"  enctype="multipart/form-data">  
	                	<div class="modal-body">
		                				<input type="text" placeholder="菜名" name="6" value="${param.d_name}">
			                				<span>${param.d_name}</span>		
											<h6>菜系:</h6><Select name="t_id2" value="${param.t_id}"><option value="3001">台式料理</option>
																					  <option value="3002">日本料理</option>
																					  <option value="3003">川味料理</option>
																					  <option value="3004">西式料理</option>
																					  <option value="3005">東南亞料理</option>
																					  </Select>	
											<input type="text" placeholder="售價" name="price" value="${param.price}">
											<h6>狀態:</h6><Select name="d_status" value="${param.d_status}">
																					  <option value="0">上架</option>
																					  <option value="1">下架</option>
																					  </Select>	
											<textarea  placeholder="菜品簡介" name="d_briefing" rows="8" cols="80" value="${param.d_briefing}"></textarea>									  
											<h6>照片:</h6><input  class='InputClass'  type="file"  placeholder="照片" name="file" size="40" />   
	                	</div>
		                <div class="modal-footer">
		                    <button type="button" class="cancel" data-dismiss="modal">Close</button>
		                    <button type="submit3" class="add-project" data-dismiss="modal" id="submit2">Save</button>
		                </div>
	                </form> 
	            </div>
	
	        </div>
	    </div>


    <!-- Modal -->
    <div id="add_project" class="modal fade" role="dialog">
        <div class="new-chef">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header login-header">
                    <button type="button" class="close" data-dismiss="modal">×</button>
                    <h4 class="modal-title">新增大廚</h4>
                </div>
                
                <!-- 新增大廚的表格 -->
               
                <form id="tf" class="insert"  enctype="multipart/form-data">  
                	<div class="modal-body">
                	
                				<input type="text" placeholder="姓" name="lastName" value="${param.lastName}">
                				<span>${errors.LastName}</span>
                				<input type="text" placeholder="名" name="FirstName" value="${errors.LastName}">
                				<h4>性別:</h4><Select name="sex" placeholder="性別" value="${param.sex}"><option value="0">男</option>
																		<option value="1">女</option>
																		</Select>
								<input type="text" placeholder="電話" name="phone" value="${param.phone}">
								<input type="text" placeholder="地址" name="address" value="${param.address}" size="30">
								<h6>菜系:</h6><Select name="t_id" value="${param.t_id}"><option value="3001">台式料理</option>
																		  <option value="3002">日本料理</option>
																		  <option value="3003">川味料理</option>
																		  <option value="3004">西式料理</option>
																		  <option value="3005">東南亞料理</option>
																		  </Select>	
								<h6>服務狀態:</h6><Select name="c_status" value="${param.c_status}">
																		  <option value="0">服務中</option>
																		  <option value="1">停止</option>
																		  </Select>			
			<textarea  placeholder="背景" name="background" rows="8" cols="80" value="${param.background}"></textarea>						
	                            <h6>下廚資歷:</h6><Select name="years" value="${param.years}">
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
				<option value="6">6</option>
				<option value="7">7</option>
				<option value="8">8</option>
				<option value="9">9</option>
				<option value="10">10</option>
				<option value="11">11</option>
				<option value="12">12</option>
				<option value="13">13</option>
				<option value="14">14</option>
				<option value="15">15</option>
				<option value="16">16</option>
				<option value="17">17</option>
				<option value="18">18</option>
				<option value="19">19</option>
				<option value="20">20</option>
			</Select> 年
			<h6>照片:</h6><input  class='InputClass'  type="file"  placeholder="照片" name="file" size="40" />
	                           
                	</div>
	                <div class="modal-footer">
	                    <button type="button" class="cancel" data-dismiss="modal">Close</button>
	                    <button type="submit" class="add-project" data-dismiss="modal" id="submit">Save</button>
	                </div>
                </form> 
            </div>

        </div>
    </div>
    
    	<script
			src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js">
    	</script>

		<script>
			window.jQuery
					|| document
							.write('<script src="<c:url value="/js/jquery.min.js" />"><\/script>')
		</script>
		
		<!-- 用Ajax 連接 controller 去新增大廚 -->
<%--     	<form class="form-horizontal" role="form" enctype="multipart/form-data" action="<c:url value='${request.contextPath}/pages/mchefapply.controller' />" method="post"> --%>
    	<script type="text/javascript">
    	  $(document).ready(function () {
    		  $("#submit").click(function(){
    		      var form = new FormData(document.getElementById("tf"));
    			  
    			  $.ajax({
//     		    	  contentType: 'multipart/form-data',
    		    	  type: "POST",
    		          url: "<c:url value="/chefdisplay/chef.controller"/>", // 連接到controller
    		          data: form,
    		          processData:false,
    		          contentType:false,
    		          success: function(msg){
//     		        	  可以控制語法去改變html
//     		              $("#thanks").html(msg) //hide button and show thank you
//     		              $("#form-content").modal('hide'); //hide popup  
						  alert("新增成功");
    		          },
    		          error: function(){
    		              alert("新增成功");
    		          }
    		      });
    		  });
    		  });
    	
		
		$(document).ready(function(){
			$("#typeDish option").removeAttr("selected");
			$(this).attr("selected");
			
		});
    	  
    	</script>
    	
    	<!-- 新增餐點 -->
    	<script type="text/javascript">
    	  $(document).ready(function () {
    		  $("#submit2").click(function(){
    		      var form = new FormData(document.getElementById("ds"));
    			  
    			  $.ajax({
    		    	  type: "POST",
    		          url: "<c:url value="/dishdisplay/dishinsert.controller"/>", // 連接到controller
    		          data: form,
    		          processData:false,
    		          contentType:false,
    		          success: function(msg){
//     		        	  可以控制語法去改變html
//     		              $("#thanks").html(msg) //hide button and show thank you
//     		              $("#form-content").modal('hide'); //hide popup  
						  alert("新增成功");
    		          },
    		          error: function(){
    		              alert("新增成功");
    		          }
    		      });
    		  });
    		  });
    			  </script>
    		    	<!-- 編輯餐點 -->
    		    	<script type="text/javascript">
    		    	  $(document).ready(function () {
    		    		  $("#submit3").click(function(){
    		    		      var form = new FormData(document.getElementById("ed"));
    		    			  
    		    			  $.ajax({
    		    		    	  type: "POST",
    		    		          url: "<c:url value="/dishdisplay/dishinsert.controller"/>", // 連接到controller
    		    		          data: form,
    		    		          processData:false,
    		    		          contentType:false,
    		    		          success: function(msg){
//    		     		        	  可以控制語法去改變html
//    		     		              $("#thanks").html(msg) //hide button and show thank you
//    		     		              $("#form-content").modal('hide'); //hide popup  
    								  alert("新增成功");
    		    		          },
    		    		          error: function(){
    		    		              alert("新增成功");
    		    		          }
    		    		      });
    		    		  });
    		    		  });
		
// 		$(document).ready(function(){
// 			$("#typeDish2 option").removeAttr("selected");
// 			$(this).attr("selected");
			
// 		});
    	  
    	</script>
			
		<script type="text/javascript">	
		$(document).ready(function(){
		   $('[data-toggle="offcanvas"]').click(function(){
		       $("#navigation").toggleClass("hidden-xs");
		   });
		});
		</script>
		
		<script src="<c:url value="/js/bootstrap.min.js"/>"></script>
		<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
		<script src="<c:url value="/js/ie10-viewport-bug-workaround.js"/>"></script>
</body>
</html>