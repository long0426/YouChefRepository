<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="zh-Hant-TW">
<head>
<link rel="shortcut icon" href="<c:url value='${request.contextPath}/images/favicon.png' />">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="<c:url value='${request.contextPath}/css/bootstrap.min.css' />" rel="stylesheet">

<link href="<c:url value='${request.contextPath}/css/bootstrap-theme.min.css' />" rel="stylesheet">

<script src="<c:url value='${request.contextPath}/js/jquery.min.js' />"></script>

<script src="<c:url value='${request.contextPath}/js/bootstrap.min.js' />"></script>

<link rel="stylesheet" type="text/css" href="<c:url value='${request.contextPath}/css/default.css' />">

<link href="<c:url value='${request.contextPath}/css/fileinput.css' />" media="all" rel="stylesheet"
	type="text/css" />
	
<script src="<c:url value='${request.contextPath}/js/fileinput.js' />" type="text/javascript"></script>

<script src="<c:url value='${request.contextPath}/js/locales/zh-TW.js' />" type="text/javascript"></script>
<title>會員大廚申請</title>
</head>
<body>
	<div class="container">

		<div role="tabpanel">
			<div class="well-sm">
				<!-- Nav tabs -->
				<ul class="nav nav-pills nav-justified" role="tablist">
					<li role="presentation" class="active"><a href="#home"
						aria-controls="home" role="tab" data-toggle="tab">個人資料</a></li>
					<li role="presentation"><a href="#profile"
						aria-controls="profile" role="tab" data-toggle="tab">餐點介紹</a></li>
					<li role="presentation"><a href="#messages"
						aria-controls="messages" role="tab" data-toggle="tab">餐點圖片</a></li>
					<li role="presentation"><a href="#settings"
						aria-controls="settings" role="tab" data-toggle="tab">用餐說明</a></li>
				</ul>
			</div>
			<br>
			<div class="container">
				<!-- Tab panes -->
				<form class="form-horizontal" role="form" enctype="multipart/form-data" action="<c:url value='${request.contextPath}/pages/mchefapply.controller' />" method="post">
				<div class="tab-content">
				<!-- 							個人資料								 -->
					<div role="tabpanel" class="tab-pane active" id="home">
							<div class="form-group">
							    <label class="col-md-1 col-md-offset-4 control-label">帳號</label>
							    <div class="col-md-3">
							      <c:choose>
							      <c:when test="${empty param.email}">
							      	<input type="text" class="form-control" value="${email}" disabled="disabled" name="email">
							      </c:when>
							      <c:otherwise>
							      	<input type="text" class="form-control" value="${param.email}" disabled="disabled" name="email">
							      	<input type="hidden" id="email" value='${param.email}' name="email">
							      </c:otherwise>
							      </c:choose>
							      <c:choose>
							      <c:when test="${empty param.m_id}">
							      	<input type="hidden" id="m_id" value='${m_id}' name="m_id">
							      </c:when>
							      <c:otherwise>
							      	<input type="hidden" id="m_id" value='${param.m_id}' name="m_id">
							      </c:otherwise>
							      </c:choose>
							    </div>
							  </div>
							  <div class="form-group">
							    <label class="col-md-1 col-md-offset-4 control-label">姓名</label>
							    <div class="col-md-3">
							    <c:choose>
							      <c:when test="${empty param.name}">
							      	<input type="text" class="form-control" value='${name}' disabled="disabled" name="name">
							      </c:when>
							      <c:otherwise>
							      	<input type="text" class="form-control" value='${param.name}' disabled="disabled" name="name">
							      	<input type="hidden" id="name" value='${param.name}' name="name">
							      </c:otherwise>
							    </c:choose>
							    </div>
							  </div>
							  <div class="form-group">
							    <label class="col-md-1 col-md-offset-4 control-label">簡介<font color="red">*</font></label>
							    <div class="col-md-3">
							      <textarea class="form-control" rows="5" id="background" name="background" >${param.background}</textarea><font color="red">${errors.background}</font>
							    </div>
							  </div>
							  <div class="form-group">
							    <label class="col-md-1 col-md-offset-4 control-label">料理年資</label>
							    <div class="col-md-3">
							      <select class="form-control" id="years" name="years">
							      <c:forEach var ='i' begin='0' end='20' step='1'>
							      	<c:choose>
							      	<c:when test="${i == 0}">
							      		<option selected value="${i}">請選擇...</option>
							      	</c:when>
							      	<c:otherwise>
								      	<c:choose>
								      		<c:when test="${i == param.years}">
								      		<option selected value="${i}">${i}</option>
								      		</c:when>
								      		<c:otherwise>
								      		<option value="${i}">${i}</option>
								      		</c:otherwise>
								      		</c:choose>
								      	</c:otherwise>
								  	</c:choose>
								  </c:forEach>
									</select>
							    </div>
							  </div>
							  <div class="form-group">
							    <label class="col-md-1 col-md-offset-4 control-label">照片</label>
							    <div class="col-md-3">
							      <input id="photo" class="file" type="file" name="photo">
							    </div>
							  </div>
							<br>
					</div>
					<!-- 							餐點介紹								 -->
					<div role="tabpanel" class="tab-pane" id="profile">
							<div class="form-group">
							    <label class="col-md-2 col-md-offset-3 control-label">餐點名稱<font color="red">*</font></label>
							    <div class="col-md-3">
							      <input type="text" class="form-control" id="d_name" name="d_name" value="${param.d_name}"><font color="red">${errors.d_name}</font>
							    </div>
							  </div>
							  <div class="form-group">
							    <label class="col-md-2 col-md-offset-3 control-label">餐點簡介<font color="red">*</font></label>
							    <div class="col-md-3">
							      <input type="text" class="form-control" id="d_briefing" name="d_briefing" value="${param.d_briefing}"><font color="red">${errors.d_briefing}</font>
							    </div>
							  </div>
							  <div class="form-group">
							    <label class="col-md-2 col-md-offset-3 control-label">菜單<font color="red">*</font></label>
							    <div class="col-md-3">
							      <textarea class="form-control" rows="8" id="menu" wrap="soft" style="text-align: center;" name="menu">${param.menu}</textarea><font color="red">${errors.menu}</font>
							    </div>
							  </div>
							  <div class="form-group">
							    <label class="col-md-2 col-md-offset-3 control-label">菜系</label>
							    <div class="col-md-3">
							      <select class="form-control" id="t_id" name="t_id" >
									<!-- javaScript -->
								  </select>
							    </div>
							  </div>
							  <div class="form-group">
							    <label class="col-md-2 col-md-offset-3 control-label">價格<font color="red">*</font></label>
							    <div class="col-md-3">
							      <input type="number" class="form-control" id="price" min="0" name="price" value="${param.price}"><font color="red">${errors.price}</font>
							    </div>
							  </div>
							<br>
					</div>
					<!-- 							餐點圖片								 -->
					<div role="tabpanel" class="tab-pane" id="messages">
							  <div class="form-group">
							    
							    <div class="col-md-9 col-md-offset-1">
							    <label for="d_photo">選擇圖片<font color="red">*(最多10張)</font></label>
							      <input id="d_photo" class="file" type="file" multiple="multiple" name="d_photo">
							    </div>
							  </div>
					</div>
					<!-- 							用餐說明								 -->
					<div role="tabpanel" class="tab-pane" id="settings">
							<div class="form-group">
							    <label class="col-md-2 col-md-offset-3 control-label">人數上限<font color="red">*</font></label>
							    <div class="col-md-3">
							      <select class="form-control" id="quota" name="quota" >
							      <c:forEach var ='i' begin='0' end='10' step='1'>
							      	<c:choose>
							      	<c:when test="${i == 0}">
							      		<option selected value="${i}">請選擇...</option>
							      	</c:when>
							      	<c:otherwise>
								      	<c:choose>
								      		<c:when test="${i == param.quota}">
								      		<option selected value="${i}">${i}</option>
								      		</c:when>
								      		<c:otherwise>
								      		<option value="${i}">${i}</option>
								      		</c:otherwise>
								      		</c:choose>
								      	</c:otherwise>
								  	</c:choose>
								  </c:forEach>
								  </select><font color="red">${errors.quota}</font>
							    </div>
							  </div>
							  <div class="form-group">
							    <label class="col-md-2 col-md-offset-3 control-label">用餐地點</label>
							    <div class="col-md-3">
							      <select class="form-control" id="v_id" name="v_id">
								  </select>
							    </div>
							  </div>
							  <div class="form-group">
							    <label class="col-md-2 col-md-offset-3 control-label">用餐地址<font color="red">*</font></label>
							    <div class="col-md-3">
							      <input type="text" class="form-control" id="venue" name="venue" value="${param.venue}"><font color="red">${errors.venue}</font>
							    </div>
							  </div>
							  <div class="form-group">
							    <label class="col-md-2 col-md-offset-3 control-label">營業時間<font color="red">*</font></label>
							    <div class="col-md-3">
							      <c:choose>
							      <c:when test="${empty param.m_id}">
							      	<a href="<c:url value='${request.contextPath}/pages/getmchef.controller?m_id=${m_id}' />" Target="_blank" class="btn btn-primary btn-danger active" role="button">設定</a>
							      </c:when>
							      <c:otherwise>
							      	<a href="<c:url value='${request.contextPath}/pages/getmchef.controller?m_id=${param.m_id}' />" Target="_blank" class="btn btn-primary btn-danger active" role="button">設定</a>
							      </c:otherwise>
							      </c:choose>
							    </div>
							  </div>
							<br>
							<div class="form-group">
								<div class="col-md-1 col-md-offset-5">
									<button type="submit" class="btn btn-primary btn" role="button">送出</button>
								</div>
								<div class="col-md-3">
									<button type="reset" class="btn btn-default">重設</button>
								</div>
							</div>
					</div>
				</div>
				</form>
			</div>
		</div>

	</div>
</body>
<script type="text/javascript">
$(function() {
	var arr = {address:[]};
//	用餐地點下拉式選單
	if('${param.v_id}'.length == 0){
		$("#v_id").append($("<option></option>").attr("value", "0").attr("selected", "selected").text("請選擇..."));
		$.getJSON('getvenue.controller',{},function(data){
			$(data).each(function(index,i){
				$("#v_id").append($("<option></option>").attr("value", i.v_id).text(i.v_name));
				arr.address.push({v_id:i.v_id,v_address:i.v_address});
			});
		})
	}else{
		$("#v_id").append($("<option></option>").attr("value", "0").text("請選擇..."));
		$.getJSON('getvenue.controller',{},function(data){
			$(data).each(function(index,i){
				if('${param.v_id}' == i.v_id){
					$("#v_id").append($("<option></option>").attr("value", i.v_id).attr("selected", "selected").text(i.v_name));
				}else{
					$("#v_id").append($("<option></option>").attr("value", i.v_id).text(i.v_name));
				}
				arr.address.push({v_id: i.v_id,v_address: i.v_address});
			});
		})
	}
	
	//動態顯示用餐地址
	$('#v_id').on('change', function() {
		$.each(arr.address, function (index, value) {
		    if($('#v_id').val() == value.v_id){
		    	$('#venue').val(value.v_address);
		    }
		});
	});
	
	// 	菜系下拉式選單
	if('${param.t_id}'.length == 0){
		$("#t_id").append($("<option></option>").attr("value", "0").attr("selected", "selected").text("請選擇..."));
		$.getJSON('gettype.controller',{},function(data){
			$(data).each(function(index,i){
				$("#t_id").append($("<option></option>").attr("value", i.t_id).text(i.t_name));
			});
			
		})
	}else{
		$("#t_id").append($("<option></option>").attr("value", "0").text("請選擇..."));
		$.getJSON('gettype.controller',{},function(data){
			$(data).each(function(index,i){
				if('${param.t_id}' == i.t_id){
					$("#t_id").append($("<option></option>").attr("value", i.t_id).attr("selected", "selected").text(i.t_name));
				}else{
					$("#t_id").append($("<option></option>").attr("value", i.t_id).text(i.t_name));
				}
			});
		})
	}
	
	
});

$('#photo').fileinput({
	language : 'zh-TW',
	dropZoneEnabled : false,
	showUpload: false,
	autoReplace: true,
    overwriteInitial: true,
    initialPreview: [
        "<img height='150' width='150' src='../pages/getImage.controller?m_id=" + $('#m_id').val() + "'>"
    ],
	allowedFileExtensions : [ 'jpg', 'png', 'gif', 'jpeg' ],
});
$('#d_photo').fileinput({
	language : 'zh-TW',
	dropZoneEnabled : false,
	showUpload: false,
	maxFileCount: 10,
    overwriteInitial: true,
	allowedFileExtensions : [ 'jpg', 'png', 'gif', 'jpeg' ],
});
</script>
</html>

