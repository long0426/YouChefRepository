<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>



<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>
<body>


<form action="chefOrder.controller" method="post">
    <lable><b>ShowChefWithFixedC_id</b></label>
    <input type="text" name="c_id" value="4017">
    <input type="text" name="t_id" value="3001">
    <button type="submit">Go</button>
</form>

<form action="typeChefOrder.controller" method="post">
    <lable><b>ShowChefWithFixedT_id</b></label>
    <input type="text" name="t_id" value="3002">
    <button type="submit">Go</button>
</form>

<form action="mchefOrder.controller" method="post">
    <lable><b>ShowMChefWithFixedMC_id</b></label>
    <input type="text" name="mc_id" value="1003">
    <button type="submit">Go</button>
</form>

</body>
</html>