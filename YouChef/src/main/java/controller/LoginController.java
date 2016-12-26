package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import model.InboxBean;
import model.InboxService;
import model.MemberBean;
import model.MemberService;

@Controller
@RequestMapping(
		path={"/member/login.controller"},
		method={RequestMethod.GET, RequestMethod.POST}
		)
public class LoginController {
	@Autowired
	@Resource
	private MemberService memberService;
	@Autowired
	private InboxService inboxService;
	
	@RequestMapping
	public String service(
			@RequestParam(name="email") String email,
			@RequestParam(name="password")String password,
			Model model, HttpSession session){
		Map<String,String> errors = new HashMap<String,String>();
		model.addAttribute("errors",errors);
		if(email==null || email.length()==0 || password==null || password.length()==0){
			errors.put("login", "請輸入信箱及密碼");
			return "login.fail";
		}
		MemberBean bean = memberService.login(email, password);
		if(bean==null){
			errors.put("password", "登入失敗, 請重試!");
			return "login.fail";
		}else{
		    model.addAttribute("user", bean);
		    session.setAttribute("user", bean);
		    List<InboxBean> unReadMail =(List<InboxBean>)inboxService.showUnRead(bean);
		    model.addAttribute("inbox",unReadMail.size());
		    session.setAttribute("inbox", unReadMail.size());
		    return "login.success";
		}
		
	}
}
