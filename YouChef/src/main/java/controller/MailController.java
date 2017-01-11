package controller;

import java.text.SimpleDateFormat;
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
import org.springframework.web.bind.annotation.ResponseBody;

import model.InboxBean;
import model.InboxDAO;
import model.InboxService;
import model.MemberBean;
import model.MemberDAO;

@Controller
public class MailController {
	@Autowired
	@Resource
	private InboxDAO inboxDao;
	@Autowired
	@Resource
	private InboxService inboxService;
	@Autowired
	@Resource
	private MemberDAO memberDao;
	@Autowired
	private SimpleDateFormat sdfYMH;
	
	@RequestMapping(path={"/email/mail.controller"})
	public String mailService(
			@RequestParam(name="mail_id") String temp1,
			@RequestParam(name="action") String action, 
			Model model, HttpSession session
			){
		int mail_id = 0;
		if(temp1!=null && temp1.length()!=0){
			mail_id= Integer.parseInt(temp1);
		}
		MemberBean receiver = (MemberBean) session.getAttribute("user");
			if("delete".equals(action)){
				inboxService.deleteMail(mail_id);
				List<InboxBean> unReadMail= inboxService.showUnRead(receiver);
				List<InboxBean> emails = (List<InboxBean>) inboxService.showInbox(receiver);
				session.setAttribute("mails", emails);
				session.setAttribute("inbox", unReadMail.size());
				return "email.delete";
			} else if("reply".equals(action)){
				InboxBean reciveMail = inboxDao.select(mail_id);
				session.setAttribute("receiveMail",reciveMail);
				inboxService.readMail(reciveMail);
				List<InboxBean> unReadMail= inboxService.showUnRead(receiver);
				session.setAttribute("inbox", unReadMail.size());
				return "email.reply";
			} else if("outbox".equals(action)){
				List<InboxBean> emails = (List<InboxBean>) inboxService.showOutbox(receiver);
				session.setAttribute("mails",emails);
				return "email.out";
			}
			return "email";
	}
	
	@RequestMapping(path={"/admin/mail.controller"})
	public String adminMailService(
			@RequestParam(name="mail_id") String temp1,
			@RequestParam(name="action") String action, 
			Model model, HttpSession session
			){
		int mail_id = 0;
		if(temp1!=null && temp1.length()!=0){
			mail_id= Integer.parseInt(temp1);
		}
		MemberBean receiver = (MemberBean) session.getAttribute("admin");
		if("delete".equals(action)){
			inboxService.deleteMail(mail_id);
			List<InboxBean> unReadMail= inboxService.showUnRead(receiver);
			List<InboxBean> emails = (List<InboxBean>) inboxService.showInbox(receiver);
			session.setAttribute("adminMails", emails);
			session.setAttribute("inbox", unReadMail.size());
			return "admin.email";
		} else if("reply".equals(action)){
			InboxBean reciveMail = inboxDao.select(mail_id);
			session.setAttribute("adminReplyMail",reciveMail);
			inboxService.readMail(reciveMail);
			List<InboxBean> unReadMail= inboxService.showUnRead(receiver);
			session.setAttribute("inbox", unReadMail.size());
			return "admin.email.reply";
		} else if("outbox".equals(action)){
			List<InboxBean> emails = (List<InboxBean>) inboxService.showOutbox(receiver);
			session.setAttribute("outMails",emails);
			return "admin.email.out";
		}
		return "admin.email";	
	}	
	
	@RequestMapping(			
			path={"/email/sendMail.controller"},
			method={RequestMethod.POST},
			produces="text/html;charset=utf-8")
	public String SendMail(
			@RequestParam(name="subject") String subject,
			@RequestParam(name="content") String content,
			@RequestParam(name="email") String email,
			@RequestParam(name="role") String role,
			Model model, HttpSession session
			){
		Map<String,String> errors = new HashMap<String,String>();
		model.addAttribute("errors",errors);
		if(email==null || email.length()==0){
			if("admin".equals(role)){
				errors.put("email", "信箱地址不能空白");
				return "admin.email.sendmail";
			}
			errors.put("email", "信箱地址不能空白");
			return "sendmail";
		}
		if(subject.equals("")){
			subject="(主旨空白)";
		}
		MemberBean receiver=null;
		if("admin".equals(role)){
			receiver = (MemberBean) session.getAttribute("admin");
		}else{
			receiver = (MemberBean) session.getAttribute("user");
		}
		MemberBean sender = memberDao.select(email);
		InboxBean bean = new InboxBean();
			bean.setSubject(subject);
			bean.setContent(content);
			bean.setI_status("0");
			long time= new java.util.Date().getTime();
			bean.setMail_time(new java.sql.Time(time));
			try {
				inboxService.sendMail(bean, receiver, sender);
			} catch (Exception e) {
				e.printStackTrace();
			}
		if("admin".equals(role)){
			List<InboxBean> emails = (List<InboxBean>) inboxService.showInbox(receiver);
			session.setAttribute("adminMails", emails);
			return "admin.email";
		}
			List<InboxBean> emails = (List<InboxBean>) inboxService.showInbox(receiver);
			session.setAttribute("mails", emails);
		return "email";
	}

	@RequestMapping(value="/member/mailCheck.controller",produces="text/plain;charset=utf-8",
	        method = RequestMethod.GET)
	@ResponseBody
	public String accountCheck(@RequestParam(name="m_id") String temp1, HttpSession session){
		int mail_id = 0;
		if(temp1!=null && temp1.length()!=0){
			mail_id= Integer.parseInt(temp1);
			MemberBean receiver = (MemberBean) session.getAttribute("user");
			List<InboxBean> unReadMail= inboxService.showUnRead(receiver);
			session.setAttribute("inbox", unReadMail.size());
			int mails = unReadMail.size();
			return Integer.toString(mails);  
		}
		return null;
	}
	
	@RequestMapping(value="/admin/mailCheck.controller",produces="text/plain;charset=utf-8",
	        method = RequestMethod.GET)
	@ResponseBody
	public String adminMailCheck(@RequestParam(name="m_id") String temp1, HttpSession session){
		int mail_id = 0;
		if(temp1!=null && temp1.length()!=0){
			mail_id= Integer.parseInt(temp1);
			MemberBean admin = (MemberBean) session.getAttribute("admin");
			List<InboxBean> unReadMail= inboxService.showUnRead(admin);
			session.setAttribute("inbox", unReadMail.size());
			int mails = unReadMail.size();
			return Integer.toString(mails);  
		}
		return null;
	}
}
