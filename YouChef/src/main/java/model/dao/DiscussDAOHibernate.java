package model.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.DiscussBean;
import model.DiscussDAO;
import model.EssayBean;

@Repository(value = "discussDao")
public class DiscussDAOHibernate implements DiscussDAO{
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	@Override
	public DiscussBean select(DiscussBean bean) {
		return this.getSession().get(DiscussBean.class, bean.getDiscuss_id());
	}
	@Override
	public int insert(DiscussBean bean) {
		return (int) this.getSession().save(bean);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<DiscussBean> selectAll() {
		Query query = this.getSession().createQuery("from DiscussBean where e_status='0'");
		return (List<DiscussBean>) query.getResultList();
	}
	@Override
	public DiscussBean update(DiscussBean bean) {
		if (null != select(bean))  {
			getSession().clear();
			this.getSession().update(bean);
			return select(bean);
		}else{
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<DiscussBean> discussAll(Integer essay_id) {
		Query query = this.getSession().createQuery("from DiscussBean where d_status='0' and essay_id=:essay_id");
		query.setParameter("essay_id", essay_id);
		return (List<DiscussBean>) query.getResultList();
	}
	
	@Override
	public boolean delete(int discuss_id, String d_status) {
		DiscussBean bean=null;
		bean = this.getSession().get(DiscussBean.class, discuss_id);
		if(bean!=null){
			bean.setD_status(d_status);
			return true;
		}
		return false;
	}
}
