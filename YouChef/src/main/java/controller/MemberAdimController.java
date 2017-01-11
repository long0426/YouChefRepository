package controller;

import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import model.MemberBean;
import model.MemberService;
import model.misc.GmailApp;

@Controller
public class MemberAdimController {
	@Autowired
	private MemberService memberService;
	@Autowired
	private GmailApp gmailApp;
	
	@RequestMapping(
				path={"/backend/ListAllMember.controller"},
				method={RequestMethod.GET, RequestMethod.POST}
			)
	public String listAllService(Model model){
			List<MemberBean> memberAll = memberService.listAll();
			model.addAttribute("memberAll",memberAll);
		return "backend.success";
	}
	
	@RequestMapping(
			path={"/backend/changeMemStatus.controller"},
			method={RequestMethod.GET, RequestMethod.POST}
		)
	public String changStatus(@RequestParam (name="action") String action,
							  @RequestParam (name="m_id") String temp1, 
							  HttpSession session, Model model){
		int m_id=0;
		if(temp1!=null && temp1.length()!=0){
			m_id= Integer.parseInt(temp1);
		}
		
		if("suspend".equals(action)){
			memberService.changeStatus(m_id, "3");
			MemberBean bean = memberService.select(m_id);
		    List<MemberBean> memberAll = (List<MemberBean>) memberService.listAll();
		    model.addAttribute("memberAll",memberAll);
			
				String[] emaillist={"eeitgroup3@gmail.com",bean.getEmail()};	
				try {
					gmailApp.postMail(emaillist, "你的優廚帳號被停權", "親愛的 會員您好， 由於您違反了優廚使用的條款，優廚已經您的帳號停權   <br/>"
												+ "詳請請洽管理者。　<br/> <br/>"
		    									+ "優廚 敬上   <br/>"
		    									+ "＊此信為系統自動發送，請勿直接回覆，謝謝＊", "eeitgroup3@gmail.com", "優廚YouChef");
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    
			return "backend.success";
		}
		if("resume".equals(action)){
			memberService.changeStatus(m_id, "0");
		    List<MemberBean> memberAll = (List<MemberBean>) memberService.listAll();
		    model.addAttribute("memberAll",memberAll);
			return "backend.success";
		}
	return "backend.success";
}
	
}
