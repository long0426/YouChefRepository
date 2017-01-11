package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import model.MemberBean;
import model.MemberService;
import model.misc.LeaveBean;

@Controller
@RequestMapping(path = { "/pages/getmember.controller" })
public class GetMemberController {
	@Autowired
	MemberService memberService;
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		System.out.println("Start to initbinder");
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
	}
	
	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST })
	public String process(LeaveBean bean, BindingResult bindingResult, Model model, HttpServletRequest request) {
		Map<String, String> errors = new HashMap<String, String>();
		model.addAttribute("errors", errors);
		List<MemberBean> list = memberService.listAll();
		
//		errors.put("test", "Test OK");
		HttpSession session = request.getSession();
		session.setAttribute("list", list);
		return "member";
	}
}
