package controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import model.CalendarBean;
import model.CalendarService;

@Controller
@RequestMapping(path = { "/pages/checkchefalong.controller" })
public class CheckChefAlongController {
	@Autowired
	CalendarService calendarService;
	
	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST })
	public void process(Model model, HttpServletRequest request, HttpServletResponse response) {
		String c_id = request.getParameter("c_id");
		String date = request.getParameter("date");
		String day = request.getParameter("day");
		
		CalendarBean cb = calendarService.selectChef(Integer.parseInt(c_id), date);
		response.setContentType("text/html;chartset=UTF-8");
		try {
			if(null != cb && 4 != getDate(cb, day)){
				
				response.getWriter().write("No");
			}else{
				response.getWriter().write("Yes");
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private int getDate(CalendarBean cb, String day) {
		// TODO Auto-generated method stub
		int result = 0;
		if (null != cb) {
			switch (Integer.parseInt(day)) {
			case 1:
				result = cb.getDate1();
				break;
			case 2:
				result = cb.getDate2();
				break;
			case 3:
				result = cb.getDate3();
				break;
			case 4:
				result = cb.getDate4();
				break;
			case 5:
				result = cb.getDate5();
				break;
			case 6:
				result = cb.getDate6();
				break;
			case 7:
				result = cb.getDate7();
				break;
			case 8:
				result = cb.getDate8();
				break;
			case 9:
				result = cb.getDate9();
				break;
			case 10:
				result = cb.getDate10();
				break;
			case 11:
				result = cb.getDate11();
				break;
			case 12:
				result = cb.getDate12();
				break;
			case 13:
				result = cb.getDate13();
				break;
			case 14:
				result = cb.getDate14();
				break;
			case 15:
				result = cb.getDate15();
				break;
			case 16:
				result = cb.getDate16();
				break;
			case 17:
				result = cb.getDate17();
				break;
			case 18:
				result = cb.getDate18();
				break;
			case 19:
				result = cb.getDate19();
				break;
			case 20:
				result = cb.getDate20();
				break;
			case 21:
				result = cb.getDate21();
				break;
			case 22:
				result = cb.getDate22();
				break;
			case 23:
				result = cb.getDate23();
				break;
			case 24:
				result = cb.getDate24();
				break;
			case 25:
				result = cb.getDate25();
				break;
			case 26:
				result = cb.getDate26();
				break;
			case 27:
				result = cb.getDate27();
				break;
			case 28:
				result = cb.getDate28();
				break;
			case 29:
				result = cb.getDate29();
				break;
			case 30:
				result = cb.getDate30();
				break;
			case 31:
				result = cb.getDate31();
				break;
			}
		}else{
			System.out.println("cb is null");
		}
		return result;
	}
}
