package controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

@Controller
@RequestMapping("/member/captcha.controller")
public class CaptchaController extends AbstractController{
	private int width = 100;
	private int height = 50;
	private int codeCount = 5;
	private int xx = 15;
	private int fontHeight = 18;
	private int codeY = 16;
	char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',	'I',
							'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 
							'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', 
							'1', '2', '3', '4', '5', '6', '7', '8', '9' };
	
	private Color getRandColor(int fc, int bc){
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
	
	@RequestMapping(method={RequestMethod.GET})
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//設置頁面不緩存
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
		
		HttpSession session = request.getSession();
		g.dispose();
		session.setAttribute("code", randomCode.toString());
		ServletOutputStream sos = response.getOutputStream();
		ImageIO.write(image, "jpeg", sos);
		sos.close();
		return null;
	}
 }
