<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-Hant-TW">
<head>
<link rel="shortcut icon" href="../images/favicon.png">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href='../css/fullcalendar.min.css' rel='stylesheet' />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="../css/bootstrap.min.css" rel="stylesheet">
<link href="../css/bootstrap-theme.min.css" rel="stylesheet">
<link href="../css/jquery-ui.theme.min.css" rel="stylesheet">
<link href="../css/jquery-ui.min.css" rel="stylesheet">
<link href="../css/jquery-confirm.min.css" rel="stylesheet">
<script src='../js/jquery.min.js'></script>
<script src='../js/jquery-ui.min.js'></script>
<script src="../js/bootstrap.min.js"></script>
<script src="../js/jquery-confirm.min.js"></script>
<link rel="stylesheet" type="text/css" href="../css/default.css">
<!-- <link href='../fullcalendar.print.min.css' rel='stylesheet' media='print' /> -->
<script src='../js/moment.min.js'></script>


<script src='../js/fullcalendar.min.js'></script>
<script src='../js/locale-all.js'></script>
<script src='../js/moment.min.js'></script>
<!-- <script src='../js/bootstrap-confirmation.js'></script> -->
<script type="text/javascript">
	$(document).ready(function() {
		$.ajax({
			  dataType: 'json' // using json, jquery will make parse for  you
			});

		/* initialize the external events
		-----------------------------------------------------------------*/

		$('#external-events .fc-event').each(function() {

			// store data so the calendar knows to render an event upon drop
			$(this).data('event', {
				title : $.trim($(this).text()), // use the element's text as the event title
				stick : true
				
			// maintain when user navigates (see docs on the renderEvent method)
			});
			
			// make the event draggable using jQuery UI
			$(this).draggable({
				zIndex : 999,
				revert : true, // will cause the event to go back to its
				revertDuration : 0
			//  original position after the drag
			});
		});

		/* initialize the calendar
		-----------------------------------------------------------------*/
		var c_id=$("#c_id").val();
		$('#calendar').fullCalendar({
			displayEventTime : false,
			minTime: '12:00:00',
			locale: 'zh-tw',
// 			weekNumbers: true,
			theme: true,
			buttonIcons: { prev: 'circle-triangle-w', next: 'circle-triangle-e' },
			header : {
				left : 'prev,next today',
				center : 'title',
				right : 'month,agendaWeek,agendaDay'
			},
			weekMode:'variable',
// 			buttonText:{
// 				prev: '上個月', // ‹
// 				next: '下個月', // ›
// 			},
			editable : true,
			droppable : true, // this allows things to be dropped onto the calendar
// 			dropAccept : '#fe',
			drop : function(title, start, end, event) {
				// is the "remove after drop" checkbox checked?
				if ($('#drop-remove').is(':checked')) {
					// if so, remove the element from the "Draggable Events" list
					$(this).remove();
				}
				
// 				eventData = {
// 						title: title,
// 						start: start,
// 						end: end
// 					};
// 				alert("drop");
// 				alert("title:" + eventData.title + "\r\nstart:" + eventData.start + "\r\nend:" + eventData.end);
			},
// 			selectable: true,
// 			selectHelper: true,
// 			select: function(start, end) {
// 				var title = prompt('Event Title:');
// 				var eventData;
// 				if (title) {
// 					eventData = {
// 						title: title,
// 						start: start,
// 						end: end
// 					};
// // 					alert(eventData.start);
// // 					alert(eventData.end);
// 					$('#calendar').fullCalendar('renderEvent', eventData, true); // stick? = true
// 				}
// 				$('#calendar').fullCalendar('unselect');
// 			},
			dragOpacity: {
	            agenda: .8
	        },
	        eventDrop: function (event, delta) {
	        	if(event.title == '請假'){
		        	var before = $.datepicker.formatDate('yymmdd', new Date(event.start - delta));
		        	var c_id = '${bean.c_id}';
		        	var date = event.start.format("YYYYMM");
		        	var day = event.start.format("DD");
		        	var year = event.start.format("YYYY");
		        	var month = event.start.format("M");
		        	var d = event.start.format("D");
		        	if(checkAlong(c_id, date, day)){
		        		storeDB(c_id, date, day, 0, before, year, month, d);
		        	}else{
		        		alongWarning();
		        	}
	        	}else{
	        		$.confirm({
	            		icon: 'fa fa-icon',
	            		title: '只能拖曳"請假"項目！',
	            	    content: '',
	            	    type: 'blue',
	            	    typeAnimated: true,
	            	    async: false,
	            	    buttons: {
	            	        	確定: function () {
	            	        		$('#calendar').fullCalendar('refetchEvents');
	            	        },
// 	            	        	取消: function () {
// //	             	        	$('#calendar').fullCalendar('updateEvent',event);
// //	             	            $('#calendar').fullCalendar('refetchEvents');
// 	            	            location.reload();
// 	            	        },
	            	    }
	            	});
	        		
	        	}
	        },
	        eventReceive: function(event, clientEvents){
	          	var title = event.title;
	          	var start = event.start.format();
	          	var array = $('#calendar').fullCalendar('clientEvents');
	          	var c_id = '${bean.c_id}';
	        	var date = event.start.format("YYYYMM");
	        	var day = event.start.format("DD");
	        	var year = event.start.format("YYYY");
	        	var month = event.start.format("M");
	        	var d = event.start.format("D");
	        	if(checkAlong(c_id, date, day)){
	        	$.confirm({
	        		icon: 'fa fa-icon',
	        		title: '確定' + year + '年' + month + '月' + d + '日要請假？',
	        	    content: '',
	        	    type: 'blue',
	        	    typeAnimated: true,
	        	    async: false,
	        	    buttons: {
	        	        	確定: function () {
	        	            $.ajax({
	        	            	url: 'updatecalendar.controller',
	        	            	data: 'c_id=' + c_id + '&date=' + date + '&day=' + day + '&value=' + 0,
	        	            	type: 'POST',
	        	            	dataType: 'html',
	        	            	success: function(response){
// 	        	            		alert(response);
	        	            		if(response == 'OK')
// 	        	              		$('#calendar').fullCalendar('updateEvent',event);
// 	        	            		$('#calendar').fullCalendar('refetchEvents');
	        	            			location.reload();
	        	            	},
	        	            	error: function(e){
	        	            		errorWarning();
	        	            	}
	        	           });
	        	        },
	        	        	取消: function () {
// 	         	        	$('#calendar').fullCalendar('updateEvent',event);
// 	         	            $('#calendar').fullCalendar('refetchEvents');
	        	            location.reload();
	        	        },
	        	    }
	        	});
	        	}else{
	        		$.confirm({
	            		icon: 'fa fa-icon',
	            		title: '這一天無法請假！',
	            	    content: '',
	            	    type: 'blue',
	            	    typeAnimated: true,
	            	    async: false,
	            	    buttons: {
	            	        	確定: function () {
// 	            	        		$('#calendar').fullCalendar('refetchEvents');
	            	        		location.reload();
	            	        },
//	             	        	取消: function () {
////	        	             	        	$('#calendar').fullCalendar('updateEvent',event);
////	        	             	            $('#calendar').fullCalendar('refetchEvents');
//	             	            location.reload();
//	             	        },
	            	    }
	            	});
	        		
	        	}
	          	
	        },
			eventLimit: true,
			events:"getcalendar.controller?c_id=" + c_id,
			eventClick: function(calEvent, jsEvent, view) {
		        var title = calEvent.title;
		        var day = calEvent.start;
		        var moment = new Date();
				if(title == '請假' || title == '請假(已過期)'){
					if(day < moment){
						expiredWarning();
					}else{
						$.confirm({
				    		icon: 'fa fa-icon',
				    		title: '要刪除' + day.format('YYYY') + '年' + day.format('MM') + '月' + day.format('DD') + '日的請假？',
				    	    content: '',
				    	    type: 'blue',
				    	    typeAnimated: true,
				    	    async: false,
				    	    buttons: {
				    	        	確定: function () {
				    	        		$('#calendar').fullCalendar('removeEvents', function (calEvent) {
											if(calEvent.start == day){
												$.ajax({
						        	            	url: 'updatecalendar.controller',
						        	            	data: 'c_id=' + c_id + '&date=' + day.format('YYYYMM') + '&day=' + day.format('DD') + '&value=' + 4,
						        	            	type: 'POST',
						        	            	dataType: 'html',
						        	            	success: function(response){
//					 	        	            		alert(response);
						        	            		if(response != 'OK')
						        	            			errorWarning();
//					 	        	              		$('#calendar').fullCalendar('updateEvent',event);
//					 	        	            		$('#calendar').fullCalendar('refetchEvents');
						        	            	},
						        	            	error: function(e){
						        	            		errorWarning();
						        	            	}
						        	           });
												return true;
											}
									    });
										$.confirm({
								    		icon: 'fa fa-icon',
								    		title: '刪除成功！',
								    	    content: '',
								    	    type: 'blue',
								    	    typeAnimated: true,
								    	    async: false,
								    	    buttons: {
								    	        	確定: function () {
								    	        		$('#calendar').fullCalendar('refetchEvents');
								    	        },
//								     	        	取消: function () {
////									             	        	$('#calendar').fullCalendar('updateEvent',event);
////									             	            $('#calendar').fullCalendar('refetchEvents');
//								     	            location.reload();
//								     	        },
								    	    }
								    	});
				    	        },
				    	        	取消: function () {
//				     	        	$('#calendar').fullCalendar('updateEvent',event);
				    	            $('#calendar').fullCalendar('refetchEvents');
//				     	            location.reload();
				    	        },
				    	    }
				    	});
						
// 						$('#calendar').fullCalendar('refetchEvents');
					}
				}else{
					itemWarning();
				}

		    }
// 		    dayClick: function(date, allDay, jsEvent, view) {

// 		        if (allDay) {
// 		            alert('Clicked on the entire day: ' + date);
// 		        }else{
// 		            alert('Clicked on the slot: ' + date);
// 		        }
// 		        var moment = $('#calendar').fullCalendar('getDate');
// 		        alert("The current date of the calendar is " + moment.format());
// // 		        alert('Coordinates: ' + jsEvent.pageX + ',' + jsEvent.pageY);

// 		        // change the day's background color just for fun
// 		        $(this).css('background-color', 'red');

// 		    }
		});
	});
	
	function itemWarning(){
		$.confirm({
    		icon: 'fa fa-warning',
    		title: '只能點選請假項目！',
    	    content: '',
    	    type: 'blue',
    	    typeAnimated: true,
    	    async: false,
    	    buttons: {
    	        	確定: function () {
    	        		$('#calendar').fullCalendar('refetchEvents');
    	        },
    	    }
    	});
	}
	
	function expiredWarning(){
		$.confirm({
    		icon: 'fa fa-warning',
    		title: '過期無法點選！',
    	    content: '',
    	    type: 'blue',
    	    typeAnimated: true,
    	    async: false,
    	    buttons: {
    	        	確定: function () {
    	        		$('#calendar').fullCalendar('refetchEvents');
    	        },
    	    }
    	});
	}
	
	function errorWarning(){
		$.confirm({
    		icon: 'fa fa-warning',
    		title: '系統程式錯誤，請重新執行！',
    	    content: '',
    	    type: 'blue',
    	    typeAnimated: true,
    	    async: false,
    	    buttons: {
    	        	確定: function () {
    	        		$('#calendar').fullCalendar('refetchEvents');
    	        },
    	    }
    	});
	}
	
	function alongWarning(){
		$.confirm({
    		icon: 'fa fa-icon',
    		title: '這一天無法請假！',
    	    content: '',
    	    type: 'blue',
    	    typeAnimated: true,
    	    async: false,
    	    buttons: {
    	        	確定: function () {
    	        		$('#calendar').fullCalendar('refetchEvents');
    	        },
//     	        	取消: function () {
////	             	        	$('#calendar').fullCalendar('updateEvent',event);
////	             	            $('#calendar').fullCalendar('refetchEvents');
//     	            location.reload();
//     	        },
    	    }
    	});
	}
	
	function checkAlong(c_id, date, day){
		var result;
		$.ajax({
        	url: 'checkchefalong.controller',
        	data: 'c_id=' + c_id + '&date=' + date + '&day=' + day,
        	type: 'POST',
        	dataType: 'html',
        	async: false,
        	success: function(response){
        		if(response == 'Yes'){
        			result = true;
        		}else{
        			result = false;
        		}
        	},
        	error: function(e){
          		console.log(e.responseText);
        	}
       });
		return result;
	}
	
	function storeDB(c_id, date, day, value, before, year, month, d){
		$.confirm({
    		icon: 'fa fa-icon',
    		title: '確定' + year + '年' + month + '月' + d + '日要請假？',
    	    content: '',
    	    type: 'blue',
    	    typeAnimated: true,
    	    async: false,
    	    buttons: {
    	        	確定: function () {
    	            $.ajax({
    	            	url: 'updatecalendar.controller',
    	            	data: 'c_id=' + c_id + '&date=' + date + '&day=' + day + '&value=' + value + '&before=' + before,
    	            	type: 'POST',
    	            	dataType: 'html',
    	            	success: function(response){
//     	            		alert(response);
    	            		if(response == 'OK')
//     	              		$('#calendar').fullCalendar('updateEvent',event);
    	            		$('#calendar').fullCalendar('refetchEvents');
    	            	},
    	            	error: function(e){
    	            		errorWarning();
    	            	}
    	           });
    	        },
    	        	取消: function () {
//     	        	$('#calendar').fullCalendar('updateEvent',event);
    	            $('#calendar').fullCalendar('refetchEvents');
//     	            location.reload();
    	        },
    	    }
    	});
	}
</script>
<style>
body {
	margin-top: 40px;
	text-align: center;
	font-size: 14px;
	font-family: "Lucida Grande", Helvetica, Arial, Verdana, sans-serif;
}

#wrap {
	width: 1100px;
	margin: 0 auto;
}

#external-events {
	float: left;
	width: 200px;
	padding: 0 5px;
	border: 1px solid #ccc;
	background: #eee;
	text-align: center;
}

#external-events h4 {
	font-size: 16px;
	margin-top: 0;
	padding-top: 1em;
}

#external-events .fc-event {
	margin: 10px 30px;
	cursor: pointer;
	width: 100px;
}

#external-events p {
	margin: 1.5em 0;
	font-size: 11px;
	color: #666;
}

#external-events p input {
	margin: 0;
	vertical-align: middle;
}

#calendar { 
	float: right; 
 	width: 850px;
 }

/* #calendar { */
/* 		max-width: 900px; */
/* 		margin: 0 auto; */
/* 	} */
</style>
<title>大廚行事曆</title>
</head>
<body>
	<div id='wrap'>
		<div id='external-events'>
		<table align="center" border="0px">
			<tr>
				<td><h4>${bean.firstName}${bean.lastName}</h4></td>
				<form action="#">
				<input type="hidden" id="c_id" value="${bean.c_id}">
				</form>
			</tr>
			<tr style="margin-bottom: 20px">
				<td><img height='150' width='100'
					src='<c:url value="${request.contextPath}/pages/getImage.controller?id=${bean.c_id}" />'></td>
			</tr>
			<tr>
				<td>
				<hr>
					<div>
<!-- 						<h5>請假時段</h5> -->
<!-- 						<div class='fc-event'>中午</div> -->
<!-- 						<div class='fc-event'>晚上</div> -->
						<div id="fe" class='fc-event'>請假</div>
					</div>
					<br>
				</td>
			</tr>
			<tr></tr><tr></tr><tr></tr>
<!-- 			<tr> -->
<!-- 				<td><a href="#" class="btn btn-primary btn-sm active" role="button">確定</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" class="btn btn-primary btn-sm" role="button">返回</a></td> -->
<!-- 			</tr> -->
			
		</table>
		</div>
		<div id='calendar'></div>

		<div style='clear: both'></div>

	</div>
	<!-- <hr> -->
</body>
</html>