package controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import model.InboxBean;
import model.InboxService;
import model.MemberBean;

@Controller
@RequestMapping(
			path={"/mail/inbox.controller"},
			method={RequestMethod.GET, RequestMethod.POST}
		)
public class InboxController {
	@Autowired
	private InboxService inboxService;
	
	@RequestMapping
	public String service(
				Model model,
				HttpSession session
			){
			MemberBean receiver = (MemberBean)session.getAttribute("user");
			List<InboxBean> emails = (List<InboxBean>) inboxService.showInbox(receiver);
			model.addAttribute("mails",emails);
		return "email";
	}
	
}
