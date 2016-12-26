package controller;

import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import model.MemberService;

@Controller
public class AccountCheckController {
	@Autowired
	@Resource(name="memberService")
	private MemberService memberService;
	@RequestMapping(value="/member/accountCheck.controller",produces="text/plain;charset=utf-8",
			        method = RequestMethod.GET)
	@ResponseBody
    public String accountCheck(@RequestParam(name="email") String email){
		
		String strMsg ="此信箱可註冊";
		if (memberService.check(email)){
			return "帳號已存在";
		}
		return strMsg;
	}
}
