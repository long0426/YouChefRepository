<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Chef</title>
<link rel="stylesheet" type="text/css" href="" />
<script type="text/javascript">
function clearForm() {
	var inputs = document.getElementsByTagName("input");
	for(var i=0; i<inputs.length; i++) {
		if(inputs[i].type=="text") {
			inputs[i].value="";
		}
	}
}
</script>
</head>
<body>

<h3 align='center'>Welcome </h3>

<h3 align='center'>Chef Table</h3>
<form action="<c:url value="/chefdisplay/chefupdate.controller"/>" action="upload.action" enctype="multipart/form-data" method="post">
<table  class="table_color" width="680" border="2" align="center" cellpadding="2" cellspacing="2" bordercolorlight="#FFFFFF" bordercolordark="#330033">
	 <tr height='40'>
       <td colspan="4" align="center" valign="bottom"> 
           <TABLE width="680" BORDER='0' style="background:#ffE4C4">
            <TR height='5' >
                <TD align='center' >&nbsp;</TD>
            </TR>
            <TR height='20' >
                <TD  align='center' >
                   <FONT color='#8000FA' size='+2' style="font-weight:900;" >
                      		編輯大廚
                    <br><Input Type="Button" Value="回管理頁面" onClick="location='chefmaintain.jsp';">
               
                </FONT>
                
	<TR height='5' >
                <TD align='center' >&nbsp;</TD>
            </TR>
         </TABLE>
           
       </td>
<c:forEach var="element" items="${list}">
	<c:choose >
	<c:when test="${element.c_id == param.c_id}">
	<tr height='36'>
		<td>ID : </td>
		<td colspan="3"><input type="text" name="c_id" value="${element.c_id}"readonly>
		<font color='red' size='-1'>${errors.c_id}</font></td>
	</tr>
	<tr height='36'>
		<td>姓 : </td>
		<td colspan="3"><input type="text" name="lastName" value="${element.lastName}">
		<font color='red' size='-1'>${errors.lastName}</font></td>
	</tr>

	<tr height='36'>
		<td>名 : </td>
		<td colspan="3"><input type="text" name="FirstName" value="${element.firstName}">
		<font color='red' size='-1'>${errors.firstName}</font></td>
	</tr>
	<tr height='36'>
		<td>性別 : </td>
		<td colspan="3"><Select name="sex" value="${element.sex}">
				<option value="0">男</option>
				<option value="1">女</option>
			</Select>
			<font color='red' size='-1'>${errors.sex}</font></td>
		
	</tr>
	<tr height='36'>
		<td>電話 : </td>
		<td colspan="3"><input type="text" name="phone" value="${element.phone}">
		<font color='red' size='-1'>${errors.phone}</font></td>
	</tr>
	<tr height='36'>
		<td>地址:</td>
		<td colspan="3"><input type="text" name="address" value="${element.address}" size="30">
		<font color='red' size='-1'>${errors.address}</font></td>
	</tr>
	<tr height='36'>
		<td>菜系:</td>
		<td colspan="3">
			<Select name="t_id2" value="${element.typeBean.t_id}">
				<option value="3001">台式料理</option>
				<option value="3002">日本料理</option>
				<option value="3003">川味料理</option>
				<option value="3004">西式料理</option>
				<option value="3005">東南亞料理</option>
			</Select>
		<font color='red' size='-1'>${errors.typeBean.t_id}</font></td>
	</tr>
	<tr height='36'>
		<td>服務狀態:</td>
		<td colspan="3">
			<Select name="c_status" value="${element.c_status}">
				<option value="0">服務中</option>
				<option value="1">停止</option>
			</Select>
			<font color='red' size='-1'>${errors.c_status}</font></td>
		
	</tr>
	<tr height='36'>
		<td>背景:</td>
		<td colspan="3"><textarea name="background" rows="8" cols="80" >${element.background}</textarea>
		<font color='red' size='-1'>${errors.background}</font></td>
	</tr>
	<tr height='36'>
		<td>下廚資歷</td>
		<td colspan="3">
		<Select name="years" value="${element.years}">
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
		
		<font color='red' size='-1'>${errors.years}</font></td>
	</tr>
	<tr height='36'>
		<td>照片:</td>
		<td><input  class='InputClass'  type="file" 
            name="file" size="40" />
            <font  size='-1'>${errors.Picture}</font></td>		
	</tr>
	<tr>
	<td></td>
	<td align='center'>
			<input onclick="window.alert('更新')" type="submit" name="update"  value="Update">
			<input type="button" value="Clear" onclick="clearForm()">
		</td>
	</tr>
	</c:when>
	</c:choose>
	</c:forEach>
</table>

</form>

</body>
</html>