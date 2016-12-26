<!-- 測試用 -->

<%@ page language="java" contentType="image/jpeg"
    pageEncoding="UTF-8"%>
<%@ page import="java.awt.*,java.awt.image.*,java.util.*,javax.imageio.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%!

Color getRandColor(int fc, int bc){
	Random random = new Random();
	if(fc > 255){
		fc = 255;
	}
	if(bc > 255){
		bc = 255;
	}
	int r = fc + random.nextInt(bc - fc);
	int g = fc + random.nextInt(bc - fc);
	int b = fc + random.nextInt(bc - fc);
	return new Color(r, g, b);
}

%>
<%
int width = 100;
int height = 80;
int codeCount = 5;
int xx = 15;
int fontHeight = 18;
int codeY = 16;
char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',	'I',
						'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 
						'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', 
						'1', '2', '3', '4', '5', '6', '7', '8', '9' };

response.setHeader("Prama", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);
response.setContentType("image/jpeg");
//Cache 中創造圖片
BufferedImage image = new BufferedImage(width, fontHeight, BufferedImage.TYPE_INT_RGB);

Graphics g = image.getGraphics();
Random random = new Random();
g.setColor(getRandColor(200, 250));
g.fillRect(0, 0, width, fontHeight);
g.setFont(new Font("Times New Roman", Font.PLAIN, fontHeight));
g.setColor(new Color(20,20,20));
g.drawRect(0, 0, width-1, height-1);
g.setColor(getRandColor(160, 200));
for (int i =0; i < 155; i++) {
	int x = random.nextInt(width);
	int y = random.nextInt(height);
	int x1 = random.nextInt(12);
	int y1 = random.nextInt(12);
	g.drawLine(x, y, x+x1, y+y1);
}

StringBuffer randomCode = new StringBuffer();
int red = 0, green = 0, blue = 0;
for (int i=0; i < codeCount; i++) {
	String code = String.valueOf(codeSequence[random.nextInt(36)]);
	red = 20 + random.nextInt(110);
	green = 20 + random.nextInt(110);
	blue = 20 + random.nextInt(110);
	g.setColor(new Color(red, green, blue));
	g.drawString(code, (i+1)*xx, codeY);
	randomCode.append(code);
}

session = request.getSession();
g.dispose();
session.setAttribute("code", randomCode.toString());
ServletOutputStream sos = response.getOutputStream();
ImageIO.write(image, "jpeg", sos);
sos.close();

%>