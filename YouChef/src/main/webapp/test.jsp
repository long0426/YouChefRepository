
<%@page import="javax.sql.rowset.serial.SerialBlob"%>
<%@page import="org.hibernate.Session"%>
<%@page import="org.hibernate.SessionFactory"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ page import="model.*" %>
<%@ page import="model.dao.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%@ page import="java.sql.*, javax.sql.*" %>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%
WebApplicationContext context = (WebApplicationContext)
		WebApplicationContextUtils.getWebApplicationContext(application);

MemberDAO memberDao = (MemberDAO) context.getBean("memberDAO");
MemberService memberService= (MemberService) context.getBean("memberService");

System.out.print(memberService.listAll());

File file = new File("E:/001.jpg");
byte ba[] = null;
FileInputStream fis = new FileInputStream(file);

ba = new byte [fis.available()];
fis.read(ba);

MemberBean bean = memberDao.select(1002);
MemberBean update = memberService.update(bean, "小二", "0", "基隆市", "仁愛區", "仁愛路10號5樓", "您好啊!! 我小二啦!!", ba);
System.out.print(update);

// Blob bb= new SerialBlob(beans.get(0).getPhoto());
// if(bb!=null){
// 	InputStream in = bb.getBinaryStream();
// 	byte[] buffer = new byte[in.available()];
	
	
//	response.reset();

// 	response.setContentType("image/jpeg");
// 	ServletOutputStream sos = response.getOutputStream();
// 	sos.write(beans.get(0).getPhoto());
// 	sos.write(beans.get(1).getPhoto());
// 	sos.flush();
// 	sos.close();
// 	sos=null;
// 	response.flushBuffer();  
// 	out.clear();  
// 	out = pageContext.pushBody(); 
%>
</body>
</html>
