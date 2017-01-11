<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>	
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
                <div class="user-dashboard">
                    <h1>訂單列表</h1>
                    <div class="row">
                        <div class="col-md-12 col-sm-12 col-xs-12 gutter">
                            <div class="sales">
								<table class="table table-hover table-curved">
										<thead>
										<tr>
											<th>訂單編號</th>
											<th>會員姓名</th>
											<th>會員大廚姓名</th>
											<th>大廚姓名</th>
											<th>用餐日</th>
											<th>用餐時段</th>
											<th>總消費金額</th>
											<th>用餐日</th>
											<th>用餐地點</th>
											<th>訂單狀態</th>
											<th>訂單更新時間</th>
											<th>人數</th>
										</tr>
										</thead>
										<tbody>
										<c:forEach var="element" items="${ordersAll}">			
												   <tr class="warning"> 
												   		<td>${element.o_id}</td>
												   		<td>${element.memberBean.lastName}${element.memberBean.firstName}</td>
														<td>${element.mchefBean.memberBean.lastName}${element.mchefBean.memberBean.firstName}</td>
														<td>${element.chefBean.lastName}${element.chefBean.firstName}</td>
														<td>${element.orderDate}</td>
														<td>${element.session}</td>
														<td>${element.totalPrice}</td>
														<td>${element.dineDate}</td>
														<td>${element.dinePlace}</td>
													<c:choose>
														<c:when test="${element.o_status == 0 }">
															<td>處理中</td>
														</c:when>
														<c:when test="${element.o_status == 2 }">
															<td>已結帳</td>
														</c:when>
														<c:otherwise>
															<td>放鳥</td>
														</c:otherwise>
													</c:choose>
														<td>${element.updateTime}</td>
														<td>${element.people}</td>
														
													<c:choose>
														<c:when test="${element.o_status == 0 }">
															<td><a href="<c:url value="/maintain.controller?o_status=2&o_id=${element.o_id}"/>"  
																   class="btn btn-success"
																   onClick="return(confirm('確定修改訂單狀態嗎?'))">改為已結帳
																</a>
															</td>
																<td><a href="<c:url value="/maintain.controller?o_status=3&o_id=${element.o_id}"/>"  
																   class="btn btn-success"
																   onClick="return(confirm('確定修改訂單狀態嗎?'))">改為被放鳥
																</a>
															</td>
															
														</c:when>
												
														<c:otherwise>
															<td><a href="<c:url value="/maintain.controller?o_status=0&o_id=${element.o_id}"/>"  
																   class="btn btn-success"
																   onClick="return(confirm('確定修改訂單狀態嗎?'))">恢復原狀態
																</a>
															</td>
															<td><a href="<c:url value="/maintain.controller?o_status=0&o_id=${element.o_id}"/>"  
																   class="btn btn-success"
																   onClick="return(confirm('確定修改訂單狀態嗎?'))">恢復原狀態
																</a>
															</td>
														</c:otherwise>
													</c:choose>
										</c:forEach>
										</tbody>
									</table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
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
                    <h4 class="modal-title">增刪管理員</h4>
                </div>
                
                <!-- 新增大廚的表格 -->
                
                <form class="insert" action="<c:url value="/index.jsp" />" method="POST">
                	<div class="modal-body">
	                            <input type="text" placeholder="Project Title" name="name">
	                            <input type="text" placeholder="Post of Post" name="mail">
	                            <input type="text" placeholder="Author" name="passsword">
	                            <textarea placeholder="Desicrption"></textarea>
                	</div>
	                <div class="modal-footer">
	                    <button type="button" class="cancel" data-dismiss="modal">Close</button>
	                    <button type="submit" class="add-project" data-dismiss="modal" id="submit">Save</button>
	                </div>
                </form> 
            </div>

        </div>
    </div>
    
        <!-- Modal -->
<!--     <div id="add_test" class="modal fade" role="dialog"> -->
<!--         <div class="new-chef"> -->
		
<!--             Modal content -->
<!--             <div class="modal-content"> -->
<!--                 <div class="modal-header login-header"> -->
<!--                     <button type="button" class="close" data-dismiss="modal">×</button> -->
<!--                     <h4 class="modal-title">新增大廚</h4> -->
<!--                 </div> -->
                
<!--                 新增大廚的表格 -->
                
<%--                 <form class="insert" action="<c:url value="/index.jsp" />" method="POST"> --%>
<!--                 	<div class="modal-body"> -->
<!-- 	                            <input type="text" placeholder="Project Title" name="name"> -->
<!-- 	                            <input type="text" placeholder="Post of Post" name="mail"> -->
<!-- 	                            <input type="text" placeholder="Author" name="passsword"> -->
<!-- 	                            <textarea placeholder="Desicrption"></textarea> -->
<!--                 	</div> -->
<!-- 	                <div class="modal-footer"> -->
<!-- 	                    <button type="button" class="cancel" data-dismiss="modal">Close</button> -->
<!-- 	                    <button type="submit" class="add-project" data-dismiss="modal" id="submit">Save</button> -->
<!-- 	                </div> -->
<!--                 </form>  -->
<!--             </div> -->

<!--         </div> -->
<!--     </div> -->
    
    
    
    	<script
			src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js">
    	</script>

		<script>
			window.jQuery
					|| document
							.write('<script src="<c:url value="/js/jquery.min.js" />"><\/script>')
		</script>
		
		<!-- 用Ajax 連接 controller 去新增大廚 -->
    	
    	<script type="text/javascript">
    	  $(document).ready(function () {
    		  $("#submit").click(function(){
    		      $.ajax({
    		          type: "POST",
    		          url: "", // 連接到controller
    		          data: $('form.insert').serialize(),
    		          success: function(msg){
//     		        	  可以控制語法去改變html
//     		              $("#thanks").html(msg) //hide button and show thank you
//     		              $("#form-content").modal('hide'); //hide popup  
						  alert("success");
    		          },
    		          error: function(){
    		              alert("failure");
    		          }
    		      });
    		  });
    		  });
    	

		$(document).ready(function(){
		   $('[data-toggle="offcanvas"]').click(function(){
		       $("#navigation").toggleClass("hidden-xs");
		   });
		});
		
		
		<c:choose>
		<c:when test="${not empty admin}">			
			function MailCountValue(){
				$.get('<c:url value='/admin/mailCheck.controller' />' ,{'m_id':${admin.m_id}},function(data){
						$('#mailCount').html(data);
					});
				}				
			MailCountValue();
			setInterval('MailCountValue()',10000);					
		</c:when>
		</c:choose>


		</script>
		
		<script src="<c:url value="/js/bootstrap.min.js"/>"></script>
		<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
		<script src="<c:url value="/js/ie10-viewport-bug-workaround.js"/>"></script>
</body>
</html>