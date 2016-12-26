package controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import model.InboxBean;
import model.InboxDAO;
import model.InboxService;
import model.MemberBean;

@Controller
@RequestMapping(path={"/email/mail.controller"})
public class MailController {
	@Autowired
	@Resource
	private InboxDAO inboxDao;
	@Autowired
	@Resource
	private InboxService inboxService;
	
	@RequestMapping
	public String mailService(
			@RequestParam(name="mail_id") String temp1,
			@RequestParam(name="action") String action, 
			Model model, HttpSession session
			){
		int mail_id = 0;
		System.out.println(action);
		if(temp1!=null && temp1.length()!=0){
			mail_id= Integer.parseInt(temp1);
		}
		
			MemberBean receiver = (MemberBean) session.getAttribute("user");
			inboxService.deleteMail(mail_id);
			List<InboxBean> unReadMail= inboxService.showUnRead(receiver);
			List<InboxBean> emails = (List<InboxBean>) inboxService.showInbox(receiver);
			model.addAttribute("mails", emails);
			session.setAttribute("inbox", unReadMail.size());
			return "email.delete";
		
	}
	
}
