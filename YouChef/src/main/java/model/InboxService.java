package model;

import java.io.IOException;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "inboxService")
@Transactional
public class InboxService {
	@Autowired
	private InboxDAO inboxDao;
	@Autowired
	private MemberService memberService;

	public static void main(String[] args) throws IOException {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			InboxService service = (InboxService) context.getBean("inboxService");

			// sendMail
			// MemberBean sender =
			// service.memberService.login("long0426@gmail.com", "sa12345678");
			// MemberBean receiver =
			// service.memberService.login("long0426_1@gmail.com", "sa123456");
			// InboxBean bean = new InboxBean();
			// bean.setContent("歡迎加入YouChef");
			// bean.setSubject("註冊成功");
			// bean.setI_status("0");
			// bean.setMail_time(new java.util.Date());
			// bean.setSender(sender);
			// bean.setReceiver(receiver);
			// System.out.println(service.sendMail(bean));

			// showInbox
			// MemberBean mb =
			// service.memberService.login("long0426_1@gmail.com", "sa123456");
			// System.out.println(service.showInbox(mb));

			// showOutbox
			// MemberBean mb = service.memberService.login("long0426@gmail.com",
			// "sa1234567");
			// System.out.println(service.showOutbox(mb));

			// readMail
			// InboxBean bean = new InboxBean();
			// bean.setMail_id(7002);
			// service.readMail(bean);

			// deleteMail
//			InboxBean bean = new InboxBean();
//			bean.setMail_id(7001);
//			service.deleteMail(bean);

			sessionFactory.getCurrentSession().getTransaction().commit();
		} finally {
			sessionFactory.close();
			((ConfigurableApplicationContext) context).close();
		}
	}

	public boolean sendMail(InboxBean bean, MemberBean sender, MemberBean receiver) {
		if (bean != null) {
			bean.setSender(sender);
			bean.setReceiver(receiver);
			inboxDao.insert(bean);
			return true;
		}
		return false;
	}

	public boolean sendMail(InboxBean bean) {
		if (null != bean.getSender() && null != bean.getReceiver()) {
			inboxDao.insert(bean);
			return true;
		} else {
			return false;
		}
	}

	public List<InboxBean> showInbox(MemberBean receiver) {
		return inboxDao.showInbox(receiver);
	}

	public List<InboxBean> showOutbox(MemberBean sender) {
		return inboxDao.showOutbox(sender);
	}

	public void readMail(InboxBean bean) {
		try {
			InboxBean read = inboxDao.select(bean.getMail_id());
			inboxDao.update(read.getMail_id(), "1");
		} catch (Exception e) {
			System.out.println("信件讀取錯誤");
		}
	}

	public void deleteMail(InboxBean bean) {
		try {
			InboxBean delete = inboxDao.select(bean.getMail_id());
			inboxDao.update(delete.getMail_id(), "2");
		} catch (Exception e) {
			System.out.println("信件刪除錯誤");
		}
	}
}
