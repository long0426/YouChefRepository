package model.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import model.CsBean;
import model.CsDAO;

@Repository(value="csDAO")
@Transactional
public class CsBeanDAOHibernate implements CsDAO {
	@Autowired
	private SessionFactory sessionFactory;
	public CsBeanDAOHibernate(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public CsBean insert(CsBean bean) {
		if(bean!=null){
			CsBean insert = this.getSession().get(CsBean.class, bean.getMessage_id());
			if(insert==null){
				this.getSession().save(bean);
				return bean;
			}
		}
		return null;
	}

	@Override
	public CsBean select(int message_id) {
		return getSession().get(CsBean.class, message_id);
	}

	@Override
	public List<CsBean> select() {
		Query query = this.getSession().createQuery("from CsBean");
		return (List<CsBean>) query.getResultList();
	}

	@Override
	public boolean update(int message_id, String cs_status) {
		CsBean bean =null;
		bean = this.getSession().get(CsBean.class, message_id);
		if(bean!=null){
			bean.setCs_status(cs_status);
			return true;
		}
		return false;
	}

}
