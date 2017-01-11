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

import model.CsBean;
import model.CsService;
import model.InboxBean;
import model.InboxService;
import model.MemberBean;
import model.MemberService;

@Controller
public class LoginController {
	@Autowired
	@Resource
	private MemberService memberService;
	@Autowired
	private InboxService inboxService;
	@Autowired
	private CsService csService;
	
	@RequestMapping(
			path={"/member/login.controller"},
			method={RequestMethod.GET, RequestMethod.POST}
			)
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
			try {
				if(memberService.check(email)){
					MemberBean mBean = memberService.loginCheck(email);
					model.addAttribute("memberPic", mBean);
				}
			} catch (NullPointerException e) {
				System.out.println("無此使用者");
			}
			return "login.fail";
		}else{
		    model.addAttribute("user", bean);
		    session.setAttribute("user", bean);
		    List<InboxBean> unReadMail =(List<InboxBean>)inboxService.showUnRead(bean);
		    model.addAttribute("inbox",unReadMail.size());
		    session.setAttribute("inbox", unReadMail.size());
		    List<CsBean> csMsg = (List<CsBean>)csService.listAll(bean.getM_id());
		    session.setAttribute("messages", csMsg);
		    return "login.success";
		}
		
	}
	
	@RequestMapping(
			path={"/backend/login.controller"},
			method={RequestMethod.GET, RequestMethod.POST}
			)
	public String admin(
		@RequestParam(name="email") String email,
		@RequestParam(name="password")String password,
		Model model, HttpSession session){
	Map<String,String> errors = new HashMap<String,String>();
	model.addAttribute("errors",errors);
	if(email==null || email.length()==0 || password==null || password.length()==0){
		errors.put("login", "請輸入信箱及密碼");
		return "backend.fail";
	}
	MemberBean bean = memberService.login(email, password);
	if(bean==null){
		errors.put("password", "登入失敗, 請重試!");
		try {
			if(memberService.check(email)){
				MemberBean mBean = memberService.loginCheck(email);
				model.addAttribute("memberPic", mBean);
			}
		} catch (NullPointerException e) {
			System.out.println("無此使用者");
		}
		return "backend.fail";
	}else{
	    session.setAttribute("admin", bean);
	    List<InboxBean> unReadMail =(List<InboxBean>)inboxService.showUnRead(bean);
	    session.setAttribute("inbox", unReadMail.size());
	    List<MemberBean> memberAll = (List<MemberBean>) memberService.listAll();
	    model.addAttribute("memberAll",memberAll);
	    return "backend.success";
	}	
}
	
}
