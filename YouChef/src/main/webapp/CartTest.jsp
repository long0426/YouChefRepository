<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
 <style>
  .badge-notify{
    background:red;
    position:relative;
    top: -20px;
    right: 10px;
  }
  .my-cart-icon-affix {
    position: fixed;
    z-index: 999;
  }

  
  body {
    background-color: #ed7f3f;
  }

  .style{
	font-size: 20px !important;
	color: black;
  }
  input {
	text-align: center;
	height: 25px !important;
	font-size: 20px !important;

  }
  .modal-body{
  	font-size: 20px !important;
	color: black;
  }
  #loading{
	background-color: #ed7f3f;
	height: 100%;
	width: 100%;
	position: fixed;
	z-index: 1;
	margin-top: 0px;
	top: 0px;
	left: 0px;
}
	#loading-center{
	width: 100%;
	height: 100%;
	position: relative;
}
	#loading-center-absolute {
	position: absolute;
	left: 50%;
	top: 50%;
	height: 200px;
	width: 200px;
	margin-top: -100px;
	margin-left: -100x;
}
	#object{
	width: 80px;
	height: 80px;
	background-color: #FFF;
	-webkit-animation: animate 1s infinite ease-in-out;
	animation: animate 1s infinite ease-in-out;
	margin-right: 430px;
	margin-left: -40px;
	margin-top: 60px;
	
}
	@-webkit-keyframes animate {
  0% { -webkit-transform: perspective(160px); }
  50% { -webkit-transform: perspective(160px) rotateY(-180deg); }
  100% { -webkit-transform: perspective(160px) rotateY(-180deg) rotateX(-180deg); }
}

	@keyframes animate {
  0% { 
    transform: perspective(160px) rotateX(0deg) rotateY(0deg);
    -webkit-transform: perspective(160px) rotateX(0deg) rotateY(0deg); 
  } 50% { 
    transform: perspective(160px) rotateX(-180deg) rotateY(0deg);
    -webkit-transform: perspective(160px) rotateX(-180deg) rotateY(0deg) ;
  } 100% { 
    transform: perspective(160px) rotateX(-180deg) rotateY(-180deg);
    -webkit-transform: perspective(160px) rotateX(-180deg) rotateY(-180deg);
  }
}

  
  </style>
  <link href="http://code.jquery.com/ui/1.11.3/themes/smoothness/jquery-ui.css" rel="stylesheet">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
  <script src="http://code.jquery.com/ui/1.11.3/jquery-ui.min.js"></script>
 <script type='text/javascript' src="js/bootstrap.min.js"></script>
 <script type='text/javascript' src="js/jquery.mycart.js"></script>
 
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.css">
<script src="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.js"></script>
   
    
    <script>
  
   

    
    function daysInMonth(month,year) {
        return new Date(year, month, 0).getDate();
    }
    var noon= [];
	var evening= [];
	var both= [];
	var full= [];
	var noWork= [];
    var disabledDays = [];
    var temp =[];
    var m_id = ${user.m_id};
    var t_id = ${data.t_id};
	var c_id = ${data.c_id};
	var yr = ${data.yr};
	var month = ${data.month}; 
	var theMonth = yr +"0"+ month;
	var date =  ${data.date};
	var orderDate = theMonth + "" + date;
	var dineTime;
	var dineDate;
	var DateAndTime;
	var dinePlace;
	var selectedSession;
	var maxNum = 10 ;
	var count = 0;
	var misc; 
    window.onload = function (){
    	
    	
    	 $("#loading").fadeOut(500);
    	
    	
    	$(".my-product-quantity").each(function(index) {
   		  alert($(this));
    	});
     
        	$.get('/YouChef/handleAjaxForChef.controller',{'c_id':c_id,'theMonth':theMonth,'date':date},function(data){
                    	var chef = JSON.parse(data);
                    	noon = chef.noon;
                        evening = chef.evening;
                        both = chef.both;
                        noWork = chef.noWork;
                        full = chef.full;
                        
                		var max = daysInMonth(month,yr); 
                   	 for (i = date;i <= max;i++){
                   		   for (j = 0;j < noWork.length;j++){
                             	 if (noWork[j] == i){
                             		temp.push(noWork[j]);
                             		//alert(noWork[j]);
                             	 }  	
                              }
                   		   for (j = 0;j < full.length;j++){
                               	 if (full[j] == i){
                               		temp.push(full[j]);
                               		//alert(full[j]);
                               	 }  	
                              }		   
                        }
                   	 for(i = 0 ; i<temp.length;i++){
                   		 var element = month + "-" + temp[i] + "-" +yr;
                   		 disabledDays.push(element);
                   		 //alert(element);
                   	 }
            
                    });
  
        
           	var typeOptions =
           	{'3001':'台式料理',
           	 '3002':'日本料理',
           	 '3003':'川味料理',
             '3004':'西式料理',
           	 '3005':'東南亞料理'
           	}
    
           	
           	var t = $('#type');      	
            $('<option />', {value: t_id, text: typeOptions[t_id]}).appendTo(t);  		
            t.insertAfter($('#index'));	
            
            
            
            
        	
            var chefOptions =
           	{'3003':'阿基師',
           	 '4002':'日本料理',
           	 '4003':'西式料理',
             '4004':'東南亞料理',
           	 '4005':'川味料理'
           	}
        	var c = $('#chef');      	
            $('<option />', {value: c_id, text: chefOptions[c_id]}).appendTo(c);  		
            c.insertAfter($('#type'));
            
            selectedSession = $("#session option:selected").text();
            //alert("first="+selectedSession);

            
            $('#session').change(function () {
                var optionSelected = $(this).find("option:selected");
                var valueSelected  = optionSelected.val();
                var textSelected   = optionSelected.text();
                selectedSession = textSelected;
    
                if($('#time3').is(':visible')){
                	document.getElementById("time3").setAttribute("type", "hidden");
                	document.getElementById("time4").className = "timepicker2";
                	document.getElementById("time4").setAttribute("type", "text");
                   	$('.timepicker2').timepicker({
                      	timeFormat:'h:mm p',
                        interval: 30,
                        minTime:'5:00pm',
                        maxTime:'8:00pm',
                        defaultTime: '5:00pm',
                        startTime: '5:00pm',
                        dynamic: false,
                        dropdown: true,
                        scrollbar: true,
                        change: function(time) {
                        	var a = formatDate(time);
                        	//alert(a);
                        	dineTime = a;
                           
                        }
                });          	
                }
                else if($('#time4').is(':visible')){
                	document.getElementById("time4").setAttribute("type", "hidden");  
                	document.getElementById("time3").setAttribute("type", "text"); 
                	        	
                }
         
        	
                //alert("change="+textSelected);
                //alert(selectedSession);
            });
        
            $('#address').on('change', function() { 
            	dinePlace = $(this).val() // get the current value of the input field.
            });
            
            
    
    }
			          



    var holydays = ['12/28/2016', '12/13/2016', '01/11/2017'];

    function highlightDays(date) {
        for (var i = 0; i < holydays.length; i++) {
            if (new Date(holydays[i]).toString() == date.toString()) {
                return [true, 'highlight'];
            }
        }
        return [true, ''];

    }


    
    
    function disableAllTheseDays(date) {
        var m = date.getMonth(), d = date.getDate(), y = date.getFullYear();
        for (i = 0; i < disabledDays.length; i++) {
            if($.inArray((m+1) + '-' + d + '-' + y,disabledDays) != -1) {
                return [false];
            }
        }
        return [true];
    }
    $(function() {
        $( "#availability" ).datepicker({
           // minDate: 0,
            dateFormat: 'mm/dd/yy', 
            inline: true,
            numberOfMonths: 1,
            dayNamesMin: ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'],
            beforeShowDay: $.datepicker.noWeekends,
            stepMonths: 0 
        });
    });
    
    $(function () {
        $("#dp").datepicker({
            minDate: 0,
            dateFormat: 'mm/dd/yy',
            inline: true,
            numberOfMonths: 1,
            dayNamesMin: ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'],
            beforeShowDay: setCustomDate, // <-----change function
            onSelect: getValue,
            stepMonths: 0
        });
    });
    
    
    function setCustomDate(date) {
        var clazz = "";
        var arr1 = highlightDays(date);
        if (arr1[1] != "") clazz = arr1[1];

        var arr2 = disableAllTheseDays(date);
        return [(!arr2[0] ) ? false : true, clazz];
    }
    
    
    function getValue(dateText, inst) {
    	$('.my-cart-badge').text("0");//歸零右上角購物車數量
    	localStorage.products = [];//歸零購物車紀錄
 
    	dineDate = $("#dp").val();
       	var sessionFilterOne = dateText.substring(3,5);//雙位數 
       	var sessionFilterTwo = dateText.substring(4,5);//單位數
       	var sessionNoon ={'noon':'中午'}
       	var sessionEvening ={'evening':'晚上'}
       	var sessionBoth ={'noon':'中午','evening':'晚上'}
       	for (i=0;i<noon.length;i++){
       		if(noon[i]==sessionFilterOne || noon[i]==sessionFilterTwo){
       			var s = $('#session');
       			s.empty();
         		for(var val in sessionNoon) {
         		    $('<option />', {value: val, text: sessionNoon[val]}).appendTo(s);
         		    s.val(val);
         		}   		
         		selectedSession = $("#session option:selected").text();
         		s.insertAfter('#dp');
         		document.getElementById("time4").setAttribute("type", "hidden");
         		document.getElementById("time2").setAttribute("type", "hidden");
         		document.getElementById("time3").setAttribute("type", "hidden");
         		document.getElementById("time").setAttribute("type", "text");
         		document.getElementById("time").className = "noonpicker";
         		//alert(document.getElementById("time").className);
            	$('.noonpicker').timepicker({
            	timeFormat: 'h:mm p',
                interval: 30,
                minTime: '12:00pm',
                maxTime: '3:00pm',
                defaultTime: '12:00pm',
                startTime: '12:00pm',
                dynamic: false,
                dropdown: true,
                scrollbar: true,
                change: function(time) {
                	var a = formatDate(time);
                	//alert(a);
                	dineTime = a;
                   
                }
            });
         		
         		
       		}
       	}
		for (i=0;i<evening.length;i++){
			if(evening[i]==sessionFilterOne || evening[i]==sessionFilterTwo){
       			var s = $('#session');
       			s.empty();
         		for(var val in sessionEvening) {
         		    $('<option />', {value: val, text: sessionEvening[val]}).appendTo(s);
         		   	s.val(val);
         		}   	
         		selectedSession = $("#session option:selected").text();
         		s.insertAfter('#dp');
         		document.getElementById("time4").setAttribute("type", "hidden");
         		document.getElementById("time").setAttribute("type", "hidden");
         		document.getElementById("time3").setAttribute("type", "hidden");
         		document.getElementById("time2").setAttribute("type", "text");
	        	document.getElementById("time2").className = "eveningpicker";
	        	//alert(document.getElementById("time2").className);
            	$('.eveningpicker').timepicker({
            	timeFormat: 'h:mm p',
                interval: 30,
                minTime: '17:00pm',
                maxTime: '20:00pm',
                defaultTime: '17:00pm',
                startTime: '17:00pm',
                dynamic: false,
                dropdown: true,
                scrollbar: true,
                change: function(time) {
                	var a = formatDate(time);
                	//alert(a);
                	dineTime = a;
                   
                }
            	
            });
       		}
			
       	}
		for (i=0;i<both.length;i++){
			if(both[i]==sessionFilterOne || both[i]==sessionFilterTwo){
       			var s = $('#session');
       			s.empty();
         		for(var val in sessionBoth) {
         		    $('<option />', {value: val, text: sessionBoth[val]}).appendTo(s);
         		   	if(val =='noon') s.val(val);
         		}   	
         		selectedSession = $("#session option:selected").text();
//          		alert("both" + selectedSession);
         		s.insertAfter('#dp');
         		document.getElementById("time").setAttribute("type", "hidden");
         		document.getElementById("time2").setAttribute("type", "hidden");
         		document.getElementById("time3").setAttribute("type", "text");
	        	document.getElementById("time3").className = "timepicker1";
	        	//alert(document.getElementById("time3").className);
            	$('.timepicker1').timepicker({
                  	timeFormat:'h:mm p',
                    interval: 30,
                    minTime:'12:00pm',
                    maxTime:'3:00pm',
                    defaultTime: '12:00pm',
                    startTime: '12:00pm',
                    dynamic: false,
                    dropdown: true,
                    scrollbar: true,
                    change: function(time) {
                    	var a = formatDate(time);
                    	//alert(a);
                    	dineTime = a;
                       
                    }
            }); 
            	
            	
       		}
			
       	}
		
		count = 0;
        var cart_button = $('.my-cart-btn');
        var inputNum = $('input.my-product-quantity');
        cart_button.prop("disabled", false);
	
    };
    
 
    
    function formatDate(date) {
    	  var year = date.getFullYear() + 118;
    	  var hours = date.getHours();
    	  var minutes = date.getMinutes();
    	  var ampm = hours >= 12 ? 'pm' : 'am';
    	  hours = hours % 12;
    	  hours = hours ? hours : 12; // the hour '0' should be '12'
    	  minutes = minutes < 10 ? '0'+minutes : minutes;
    	  var strTime = hours + ':' + minutes + ' ' + ampm;
    	  return strTime;
    	}
   


</script>
 <script type="text/javascript">
  $(function () {

    var goToCartIcon = function($addTocartBtn){
      var $cartIcon = $(".my-cart-icon");
      var $image = $('<img width="30px" height="30px" src="' + $addTocartBtn.data("image") + '"/>').css({"position": "fixed", "z-index": "999"});
      $addTocartBtn.prepend($image);
      var position = $cartIcon.position();
      $image.animate({
        top: position.top,
        left: position.left
      }, 500 , "linear", function() {
        $image.remove();
      });
    }

    $('.my-cart-btn').myCart({
      classCartIcon: 'my-cart-icon',
      classCartBadge: 'my-cart-badge',
      classProductQuantity: 'my-product-quantity',
      classProductRemove: 'my-product-remove',
      classCheckoutCart: 'my-cart-checkout',
      affixCartIcon: true,
      showCheckoutModal: true,
      cartItems: [
//         {id: 1, name: 'product 1', summary: 'summary 1', price: 10, quantity: 1, image: 'images/img_1.png'},
//         {id: 2, name: 'product 2', summary: 'summary 2', price: 20, quantity: 2, image: 'images/img_2.png'},
//         {id: 3, name: 'product 3', summary: 'summary 3', price: 30, quantity: 1, image: 'images/img_3.png'}
      ],
      clickOnAddToCart: function($addTocart){
//     	  var btn = $(this);
//     	  alert($(this));
//     	  $(".my-product-quantity").each(function(index) {
//     		  alert($(this));
//     		  if($(this).prop("value") == maxNum){
//     			  $(this).prop("disabled", true);
//     			  alert($(this).parent().parent().attr("data-id"));
//         		  if($(this).parent().parent().attr("data-id") == btn.attr("data-id")){
//         			  btn.prop("disabled", true);
//         		  }
//     		  }

    		
//     		});
//     	  count ++;
//           var cart_button = $('.my-cart-btn');
//           var inputNum = $('input.my-product-quantity');
//           inputNum.prop("disabled", true);
//           if(count == maxNum) {
//               cart_button.prop("disabled", true);         
//           }

        goToCartIcon($addTocart);
      },
      clickOnCartIcon: function($cartIcon, products, totalPrice, totalQuantity) {
        console.log("cart icon clicked", $cartIcon, products, totalPrice, totalQuantity);
      },
      checkoutCart: function(products, totalPrice, totalQuantity) { 	  
        console.log("checking out", products, totalPrice, totalQuantity);
        
        
        $.each(products, function(index, value){
        	products[index].id ;
          });
        

        
       if(confirm("確定結帳?")){
    	   DateAndTime = dineDate + "/" + dineTime;
    	   //alert(dineTime);
    	   //alert(DateAndTime);
//     	   misc = 
//     		   "[{"+"misc:"+ "["+ "'" + c_id + "'" + "," + "'" + orderDate + "'" + "," + "'" + selectedSession + "'" + "," + "'" + DateAndTime + "'"+ "," + "'" + dinePlace + "'" + "]},";
    	   //[{id:1, name: 'xxx' ...}]
    	   //var finalObj = misc.concat(products);

		   misc = 
			'[{' + '"id":"0"' +','+
				   '"name":"info"' +','+
			       '"summary":' + '[' + '"' + c_id + '"' + ',' +
				   								  '"' + orderDate + '"' + ',' +
				   								  '"' + selectedSession + '"' + ',' +
				   								  '"' + DateAndTime + '"' + ',' +
				   								  '"' + dinePlace + '"' + ',' +
				   								  '"' + m_id + '"' + ']' + ',' +
				   '"price":"0"' +','+
				   '"quantity":"0"' +','+
				   '"image":"0"' +  								  
				'},';



    	   var str = JSON.stringify(products);
    	   var res = str.substring(1);
    	   var ob = misc.concat(res);
    	   
    	   //alert(ob);

			 $.ajax({
		   url: '/YouChef/AddToCart.controller',
		   type: 'POST',
		   contentType:'application/json',
		   //data:JSON.stringify(products),
		   data:ob,
		   dataType:'json',
		   success: function(data){
		     //On ajax success do this
		     //     	   window.location = host + newHost;
			   var result = data;
	        	if(result.duplicate){
	        		 alert("此時段已經被預訂");
// 	        		 window.location.replace('http://stackoverflow.com/questions/6643838/jquery-ajax-parsererror');
	        		 //document.location.href="http://stackoverflow.com/questions/6643838/jquery-ajax-parsererror";
	        	}else{
	        		 alert("預訂成功");
// 	        		 window.location.replace('https://www.youtube.com/');
	        		 //document.location.href="https://www.youtube.com/";
	        	}
		     //alert("success");
		      },
		   error: function(xhr, ajaxOptions, thrownError) {
		      //On error do this
		     
		        if (xhr.status == 200) {
// 		        	 alert("此時段已經被預訂");
// 	        		 window.location.replace('http://stackoverflow.com/questions/6643838/jquery-ajax-parsererror');
		            //alert(ajaxOptions);
		        }
		        else {
// 		        	 alert("此時段已經被預訂");
// 	        		 window.location.replace('http://stackoverflow.com/questions/6643838/jquery-ajax-parsererror');
		            //alert(xhr.status);
		            //alert(thrownError);
		        }
		    }
		 });
			 
	

       };
        
      },
      getDiscountPrice: function(products, totalPrice, totalQuantity) {
        console.log("calculating discount", products[0], totalPrice, totalQuantity);
        //console.log(products.length);
//         for (i=0;i<products.length;i++){
//         	 var a = products[i].quantity;
//         	 if(a>maxNum){
//         		 products[i].quantity = maxNum;
//         	 }
//         }
 
//         var parent = $("#my-cart-table");
//         for(var i=0;i<parent.length;i++){
//         	  var child = parent[i].getElementsByTagName('tr');
//         	for(var j=0;j<child.length;j++){
//         	      var grandChild = child[j].getElementsByClassName('my-product-quantity');
//         	      console.log(grandChild.outerHTML);
        	      
//         	    }
//         }
    
        var inputNum = $('input.my-product-quantity');
        inputNum.prop("max", "30");
        return totalPrice ;
      }
    });

  });
  
 
  
  </script>


</head>

<div id="loading">
	<div id="loading-center">
		<div id="loading-center-absolute">
			<div id="object"></div>
		</div>
	</div>
</div>

<body class="container" style="color:#eddcd1;">

  <div class="page-header">
    <h1 style="font-weight: bold;" >大廚點餐區
      <div style="float: right; cursor: pointer;">
        <span class="glyphicon glyphicon-shopping-cart my-cart-icon"><span class="badge badge-notify my-cart-badge"></span></span>
      </div>
    </h1>
  </div>
    <div class="style">
    		<span id="index"></span>
			<select id="type" disabled>
			</select>
			
			<select id="chef" disabled>
			<option value="${chef.c_id}">${chef.lastName}${chef.firstName}</option>
			</select>
			
			<span>日期: </span> <input type="text" id="dp">

			<select id="session">
			<option value="noon">中午</option>
			<option value="evening">晚上</option>
			</select>
			<span id="dineTime">用餐時間: </span> 
			<input id="time" type="hidden">
			<input id="time2" type="hidden">
			<input id="time3" type="hidden">
			<input id="time4" type="hidden">
			<br>
			<p>地址: <input type="text" id="address" size="47" ></p>

	
    
    </div>

  <div class="row">

	<c:forEach var="element" items="${ob}">
	<c:set var="a" value="${element.price}" />
	<fmt:parseNumber var="price" integerOnly="true" type="number" value="${a}" />
    <div class="col-md-3 text-center" style="margin-bottom:5px;">
      <img  width="250px" height="150px" src='<c:url value="${request.contextPath}/getdishImage.controller?id=${element.d_id}" />'>
      
      <br>
      <span style="font-size:120%; color:#2B333E; font-weight:600;">${element.d_name}</span>
      <br>
      <strong style="font-size:120%;">&#36;${price}</strong>
      <br>
      <button id="${element.d_id}" class="btn btn-danger my-cart-btn" data-id="${element.d_id}" data-name="${element.d_name}" data-summary="[&quot;a&quot;]" data-price="${price}" data-quantity="1"  data-image='<c:url value="${request.contextPath}/getdishImage.controller?id=${element.d_id}"/>'>加入購物車</button>
      
    </div>
	</c:forEach>
	


  </div>


</body>


</html>