package model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value="inboxService")
@Transactional
public class InboxService {
	@Autowired
	private InboxDAO inboxDao;
	
	public boolean sendMail(InboxBean bean, MemberBean sender, MemberBean receiver){
		if (bean!=null){
			bean.setSender(sender);
			bean.setReceiver(receiver);
			inboxDao.insert(bean);
			return true;
		}
		return false;
	}
	
	public List<InboxBean> showInbox(MemberBean receiver){
		return inboxDao.showInbox(receiver);
	}
	
	public List<InboxBean> showOutbox(MemberBean sender){
		return inboxDao.showOutbox(sender);
	}
	
	public List<InboxBean> showUnRead(MemberBean receiver){
		return inboxDao.showUnread(receiver);
	}
	
	public void readMail(InboxBean bean){
		inboxDao.update(bean.getMail_id(), "1");
	}
	
	public void deleteMail(int mail_id) {
		inboxDao.update(mail_id, "2");
	}
}
