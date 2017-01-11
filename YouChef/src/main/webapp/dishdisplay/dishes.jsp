<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Dishes</title>
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

<h3 align='center'>Dishes Table</h3>
            
<form action="<c:url value="/dishdisplay/dishupdate.controller"/>" action="upload.action" enctype="multipart/form-data" method="post"> 

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
                      		編輯菜單
                   
                   <br><Input Type="Button" Value="回管理頁面" onClick="location='dishesmaintain.jsp';">
                </FONT>
            <TR height='5' >
                <TD align='center' >&nbsp;</TD>
            </TR>

            
         </TABLE>
           
       </td>
  <c:forEach var="element" items="${taiDishesList}">
  <c:choose >
  <c:when test="${element.d_id == param.d_id}">
	<tr height='36'>
		<td >ID : </td>
		<td colspan="3"><input type="text" name="d_id" value="${element.d_id}"readonly/>
		<font color='red' size='-1'>${errors.d_id}</font>		
	</tr>
	<tr height='36'>
		<td >菜名 :</td>
		<td colspan="3"><input type="text" name="d_name" value="${element.d_name}"/>
		<font color='red' size='-1'>${errors.d_name}</font>		
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
			<font color='red' size='-1'>${element.typeBean.t_id}</font>
		</td>
			
	</tr>
	<tr height='36'>
		<td>售價 : </td>
		<td colspan="3"><input type="text" name="price" value="${element.price}">
		<font color='red' size='-1'>${errors.price}</font>
		</td>
	</tr>

	
	<tr height='36'>
		<td>狀態 : </td>
		<td colspan="3"><Select name="d_status" value="${element.d_status}">
				<option value="0">上架</option>
				<option value="1">下架</option>
			</Select>
		
		<font color='red' size='-1'>${errors.d_status}</font>
		</td>
	</tr>
	<tr height='36'>
		<td>菜品簡介 : </td>
		<td colspan="3"><textarea name="d_briefing" rows="8" cols="80">${element.d_briefing}</textarea>
		<font color='red' size='-1'>${errors.d_briefing}</font></td>
	</tr>

	
	<tr height='36'>
		<td>照片:</td>
		<td>
		<input  class='InputClass'  type="file" name="file" size="40" />
            <font  size='-1'>${ErrMsg.errPicture}</font></td>
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