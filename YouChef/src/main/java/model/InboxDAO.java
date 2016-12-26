package model;

import java.util.List;

public interface InboxDAO {
	List<InboxBean> showInbox(MemberBean receiver);
	
	List<InboxBean> showOutbox(MemberBean sender);
	
	List<InboxBean> showUnread(MemberBean receiver);
	
	InboxBean insert (InboxBean bean);
	
	InboxBean select (int mail_id);
	
	boolean update (int mail_id, String i_status);
}
