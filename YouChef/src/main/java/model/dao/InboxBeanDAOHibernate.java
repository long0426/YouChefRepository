package model.dao;

import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import model.InboxBean;
import model.InboxDAO;
import model.MemberBean;

@Repository(value="inboxDAO")
@Transactional
public class InboxBeanDAOHibernate implements InboxDAO {
	@Autowired
	private SessionFactory sessionFactory;
	public InboxBeanDAOHibernate(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	@Override
	public List<InboxBean> showInbox(MemberBean memberBean) {
		List<InboxBean> result = null;
		Query query = this.getSession().createQuery("from InboxBean where receiver= :receiver and i_status= '0' or i_status= '1'");
		query.setParameter("receiver", memberBean);
		result = query.getResultList();
		return result;
	}

	@Override
	public List<InboxBean> showOutbox(MemberBean memberBean) {
		List<InboxBean> result = null;
		Query query = this.getSession().createQuery("from InboxBean where sender= :sender");
		query.setParameter("sender", memberBean);
		result = query.getResultList();
		return result;
	}
	
	

	@Override
	public List<InboxBean> showUnread(MemberBean memberBean) {
		List<InboxBean> result = null;
		Query query = this.getSession().createQuery("from InboxBean where receiver= :receiver and i_status= '0'");
		query.setParameter("receiver", memberBean);
		result = query.getResultList();
		return result;
	}
	@Override
	public InboxBean insert(InboxBean bean) {
		if(bean!=null){
			InboxBean insert = this.getSession().get(InboxBean.class, bean.getMail_id());
			if(insert==null){
				this.getSession().save(bean);
				return bean;
			}
		}
		return null;
	}

	@Override
	public InboxBean select(int mail_id) {
		return getSession().get(InboxBean.class, mail_id);
	}

	@Override
	public boolean update(int mail_id, String i_status) {
		InboxBean bean=null;
		bean = this.getSession().get(InboxBean.class, mail_id);
		if(bean!=null){
			bean.setI_status(i_status);
			return true;
		}
		return false;
	}

}
