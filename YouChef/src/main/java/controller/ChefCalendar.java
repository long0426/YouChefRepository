//package controller;
//
//import java.util.Set;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.propertyeditors.StringTrimmerEditor;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.WebDataBinder;
//import org.springframework.web.bind.annotation.InitBinder;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import model.CalendarBean;
//import model.ChefBean;
//import model.ChefService;
//import model.misc.LeaveBean;
//
//@Controller
//@RequestMapping(path = { "/pages/chefcalendar.controller" })
//public class ChefCalendar {
//	@Autowired
//	ChefService chefService;
//	
//	@InitBinder
//	private void initBinder(WebDataBinder binder) {
//		binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
//	}
//	
//	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST })
//	public String process(LeaveBean bean, BindingResult bindingResult, Model model, HttpServletRequest request) {
//		String id = request.getParameter("id");
//		ChefBean cb = chefService.select(Integer.parseInt(id));
//		Set<CalendarBean> cbs = cb.getCalendars();
//		HttpSession session = request.getSession();
//		session.setAttribute("bean", cb);
//		session.setAttribute("calendar", cbs);
//		return "chefcalendar";
//	}
//}
