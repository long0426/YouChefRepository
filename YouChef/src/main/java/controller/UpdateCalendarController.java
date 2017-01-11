package controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import model.CalendarBean;
import model.CalendarService;
import model.misc.LeaveBean;

@Controller
@RequestMapping(path = { "/pages/updatecalendar.controller" })
public class UpdateCalendarController {
	@Autowired
	CalendarService calendarService;

	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST })
	public void process(LeaveBean bean, BindingResult bindingResult, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		String before = request.getParameter("before");
		String c_id = request.getParameter("c_id");
		String mc_id = request.getParameter("mc_id");
		String date = request.getParameter("date");
		String day = request.getParameter("day");
		String value = request.getParameter("value");
		// System.out.println("c_id = " + c_id);
		// System.out.println("date = " + date);
		// System.out.println("day = " + day);
		// System.out.println("value = " + value);

		// System.out.println("cb = " + cb.getChefBean().getC_id() + ", " +
		// cb.getTheMonth() + ", " + day + ", " + value + ", " + cb.getDate1());
		// System.out.println("date29 = " + cb.getDate29());
		CalendarBean c = null;
		if (null != c_id) {
			if (null == before || 0 == before.length()) {
				if (null == calendarService.selectChef(Integer.parseInt(c_id), date))
					calendarService.newChefCalendar(Integer.parseInt(c_id), date);
				c = calendarService.chefInput(setDate(c_id, date, day, value));
			} else if (null != calendarService.chefInput(setDate(c_id, date, day, value)))
				c = calendarService.chefInput(setDate(c_id, before.substring(0, 6), before.substring(6), "4"));
		} else if (null != mc_id) {
//			if (null == before || 0 == before.length()) {
//				if (null == calendarService.selectMchef(Integer.parseInt(mc_id), date))
//					calendarService.newMchefCalendar(Integer.parseInt(mc_id), date);
//				c = calendarService.mchefInput(setMchefDate(mc_id, date, day, value));
//			} else if (null != calendarService.mchefInput(setMchefDate(c_id, date, day, value)))
//				c = calendarService.mchefInput(setMchefDate(mc_id, before.substring(0, 6), before.substring(6), value));
			if (null == calendarService.selectMchef(Integer.parseInt(mc_id), date)){
				System.out.println("Insert a new record");
				calendarService.newMchefCalendar(Integer.parseInt(mc_id), date);
			}
			c = calendarService.mchefInput(setMchefDate(mc_id, date, day, value));
		}

		// System.out.println("c = " + c.getDate22());
		response.setContentType("text/html;chartset=UTF-8");
		try {
			if (null != c)
				response.getWriter().write("OK");
			else
				response.getWriter().write("NG");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private CalendarBean setMchefDate(String mc_id, String date, String day, String value) {
		CalendarBean cb = null;
		if (null != mc_id) { // 會員大廚請假
			cb = calendarService.selectMchef(Integer.parseInt(mc_id), date);
			System.out.println("mc_id = " + mc_id);
			System.out.println("date = " + date);
			System.out.println("day = " + day);
			System.out.println("value = " + value);
			System.out.println(
					"cb = " + cb.getMchefBean().getMc_id() + ", " + cb.getTheMonth() + ", " + day + ", " + value);
			if (null != cb) {
				switch (Integer.parseInt(day)) {
				case 1:
					cb.setDate1(Integer.parseInt(value));
					break;
				case 2:
					cb.setDate2(Integer.parseInt(value));
					break;
				case 3:
					cb.setDate3(Integer.parseInt(value));
					break;
				case 4:
					cb.setDate4(Integer.parseInt(value));
					break;
				case 5:
					cb.setDate5(Integer.parseInt(value));
					break;
				case 6:
					cb.setDate6(Integer.parseInt(value));
					break;
				case 7:
					cb.setDate7(Integer.parseInt(value));
					break;
				case 8:
					cb.setDate8(Integer.parseInt(value));
					break;
				case 9:
					cb.setDate9(Integer.parseInt(value));
					break;
				case 10:
					cb.setDate10(Integer.parseInt(value));
					break;
				case 11:
					cb.setDate11(Integer.parseInt(value));
					break;
				case 12:
					cb.setDate12(Integer.parseInt(value));
					break;
				case 13:
					cb.setDate13(Integer.parseInt(value));
					break;
				case 14:
					cb.setDate14(Integer.parseInt(value));
					break;
				case 15:
					cb.setDate15(Integer.parseInt(value));
					break;
				case 16:
					// System.out.println("setDate16");
					cb.setDate16(Integer.parseInt(value));
					break;
				case 17:
					cb.setDate17(Integer.parseInt(value));
					break;
				case 18:
					cb.setDate18(Integer.parseInt(value));
					break;
				case 19:
					cb.setDate19(Integer.parseInt(value));
					break;
				case 20:
					cb.setDate20(Integer.parseInt(value));
					break;
				case 21:
					cb.setDate21(Integer.parseInt(value));
					break;
				case 22:
					cb.setDate22(Integer.parseInt(value));
					break;
				case 23:
					cb.setDate23(Integer.parseInt(value));
					break;
				case 24:
					cb.setDate24(Integer.parseInt(value));
					break;
				case 25:
					cb.setDate25(Integer.parseInt(value));
					break;
				case 26:
					cb.setDate26(Integer.parseInt(value));
					break;
				case 27:
					cb.setDate27(Integer.parseInt(value));
					break;
				case 28:
					cb.setDate28(Integer.parseInt(value));
					break;
				case 29:
					cb.setDate29(Integer.parseInt(value));
					break;
				case 30:
					cb.setDate30(Integer.parseInt(value));
					break;
				case 31:
					cb.setDate31(Integer.parseInt(value));
					break;
				}
			} else {
				System.out.println("cb is null");
			}
		}
		return cb;
	}

	private CalendarBean setDate(String c_id, String date, String day, String value) {
		CalendarBean cb = null;
		if (null != c_id) { // 大廚請假
			cb = calendarService.selectChef(Integer.parseInt(c_id), date);
			System.out.println("c_id = " + c_id);
			System.out.println("date = " + date);
			System.out.println(
					"cb = " + cb.getChefBean().getC_id() + ", " + cb.getTheMonth() + ", " + day + ", " + value);
			if (null != cb) {
				switch (Integer.parseInt(day)) {
				case 1:
					cb.setDate1(Integer.parseInt(value));
					break;
				case 2:
					cb.setDate2(Integer.parseInt(value));
					break;
				case 3:
					cb.setDate3(Integer.parseInt(value));
					break;
				case 4:
					cb.setDate4(Integer.parseInt(value));
					break;
				case 5:
					cb.setDate5(Integer.parseInt(value));
					break;
				case 6:
					cb.setDate6(Integer.parseInt(value));
					break;
				case 7:
					cb.setDate7(Integer.parseInt(value));
					break;
				case 8:
					cb.setDate8(Integer.parseInt(value));
					break;
				case 9:
					cb.setDate9(Integer.parseInt(value));
					break;
				case 10:
					cb.setDate10(Integer.parseInt(value));
					break;
				case 11:
					cb.setDate11(Integer.parseInt(value));
					break;
				case 12:
					cb.setDate12(Integer.parseInt(value));
					break;
				case 13:
					cb.setDate13(Integer.parseInt(value));
					break;
				case 14:
					cb.setDate14(Integer.parseInt(value));
					break;
				case 15:
					cb.setDate15(Integer.parseInt(value));
					break;
				case 16:
					// System.out.println("setDate16");
					cb.setDate16(Integer.parseInt(value));
					break;
				case 17:
					cb.setDate17(Integer.parseInt(value));
					break;
				case 18:
					cb.setDate18(Integer.parseInt(value));
					break;
				case 19:
					cb.setDate19(Integer.parseInt(value));
					break;
				case 20:
					cb.setDate20(Integer.parseInt(value));
					break;
				case 21:
					cb.setDate21(Integer.parseInt(value));
					break;
				case 22:
					cb.setDate22(Integer.parseInt(value));
					break;
				case 23:
					cb.setDate23(Integer.parseInt(value));
					break;
				case 24:
					cb.setDate24(Integer.parseInt(value));
					break;
				case 25:
					cb.setDate25(Integer.parseInt(value));
					break;
				case 26:
					cb.setDate26(Integer.parseInt(value));
					break;
				case 27:
					cb.setDate27(Integer.parseInt(value));
					break;
				case 28:
					cb.setDate28(Integer.parseInt(value));
					break;
				case 29:
					cb.setDate29(Integer.parseInt(value));
					break;
				case 30:
					cb.setDate30(Integer.parseInt(value));
					break;
				case 31:
					cb.setDate31(Integer.parseInt(value));
					break;
				}
			} else {
				System.out.println("cb is null");
			}
		}
		return cb;
	}

}
