package model.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.EssayBean;
import model.EssayDAO;

@Repository(value = "essayDao")
public class EssayDAOHibernate implements EssayDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	@Override
	public EssayBean select(EssayBean bean) {
		return this.getSession().get(EssayBean.class, bean.getEssay_id());
//		return this.getSession().get(EssayBean.class, 8002);
	}
	
	@Override
	public int insert(EssayBean bean) {
		return (int) this.getSession().save(bean);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<EssayBean> selectAll() {
		Query query = this.getSession().createQuery("from EssayBean");
		return (List<EssayBean>) query.getResultList();
	}
	
	@Override
	public EssayBean update(EssayBean bean) {
		if (null != select(bean))  {
			getSession().clear();
			this.getSession().update(bean);
			return select(bean);
		}else{
			return null;
		}
	}

}
