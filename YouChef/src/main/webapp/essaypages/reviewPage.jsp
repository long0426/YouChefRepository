<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>評價</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/starsbox.css"/>"/>
<script src="<c:url value="/js/instarsgrade.js"/>"></script>
<script src="<c:url value="/js/jquery-3.1.1.min.js"/>"></script>
<style>
#r_stars01{visibility:hidden}
</style>
</head>
<body>
<div>
	<H4>請給予評價</H4>
		<c:choose>
			<c:when test="${not empty errors}">
				<script>
					alert('${errors.reviewcontent}');			
				</script>
			</c:when>
		</c:choose>
<form action="<c:url value="${request.contextPath}/review/updateorderreview.controller"/>" method="POST"> 
    	<div><label class="title">滿意度:</label>
    	<img id="p1" src="<c:url value="/images/stars/starsoff.png"/>" />
    	<img id="p2" src="<c:url value="/images/stars/starsoff.png"/>" />
    	<img id="p3" src="<c:url value="/images/stars/starsoff.png"/>" />
    	<img id="p4" src="<c:url value="/images/stars/starsoff.png"/>" />
    	<img id="p5" src="<c:url value="/images/stars/starsoff.png"/>" /><span id="rate"></span>
    	<input type="text" value="" name=r_stars id="stars">
<%--     	<input id="r_stars01" name="r_stars" value="${rate _stars}" > --%>
		
    	</div>
    <div>
		<label class="title">評價內容:</label><p>
		<textarea cols="40" rows="20" name="r_message"></textarea>
	</div>
	<p>
	<input type="reset" value="清除內容">
	<input type="submit" name="inessay" value="發表評價">    
	</p>
</form>
</div>  

<script type="text/javascript">
var flag = false;
var starAry;
document.addEventListener("DOMContentLoaded",function () {
    var imgs = document.getElementsByTagName("img");
    starAry = new Array([imgs.length]);
    for (var i = 0; i < imgs.length; i++) {
        starAry[i] = imgs[i].id;
        imgs[i].addEventListener("mouseover",function () { mouseOver(this.id);});
        imgs[i].addEventListener("click",function () { Click(this.id);});
        
    }
});

function mouseOver(imgid) {
if (!flag) {
        var index = starAry.indexOf(imgid);
        var fox = (index+1);
        for (var i = 0; i <= index; i++) {
            document.getElementById(starAry[i]).src = "../images/stars/starson.png";
        }
        if (index < starAry.length - 1) {
            for (var i = index + 1; i < starAry.length; i++) {
                document.getElementById(starAry[i]).src = "../images/stars/starsoff.png";
            }
        }
        document.getElementById("rate").innerHTML = "評分中..." + fox;
    }
}

function Click(imgid) {
    if (!flag) {
        flag = true;
        var index = starAry.indexOf(imgid);
        var fox = (index+1);
        var getStar_element = document.getElementById("stars");
        getStar_element.value=fox;
        document.getElementById("rate").innerHTML = "選定了"+fox + "顆星";
    }     
}
</script>

 <div img class="star-s s5"> 5科</div><br>
 <div img class="starsmax m5">五顆星 </div><br>
 
</body>
</html>