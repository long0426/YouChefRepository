package model.dao;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.DishPhotoBean;
import model.DishPhotoDAO;

@Repository(value = "dishPhotoDao")
public class DishPhotoDAOHibernate implements DishPhotoDAO {
	@Autowired
	private SessionFactory sessionFactory;
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public DishPhotoBean insert(DishPhotoBean bean) {
		int i = (int) this.getSession().save(bean);
		if(i>0)
			System.out.println("dp_id = " + bean.getDp_id()); 
		return bean;
	}
	
	private final String SELECT_BY_MCID = "from DishPhotoBean where mc_id = ?";
	@Override
	public List<DishPhotoBean> selectByMcid(Integer mc_id) {
		List<DishPhotoBean> list = new LinkedList<DishPhotoBean>();
		Query query = this.getSession().createQuery(SELECT_BY_MCID);
		query.setParameter(0, mc_id);
		list = query.getResultList();
		return list;
	}

	private final String SELECT_BY_DID = "from DishPhotoBean where d_id = ?";
	@Override
	public List<DishPhotoBean> selectByDid(Integer d_id) {
		List<DishPhotoBean> list = new LinkedList<DishPhotoBean>();
		Query query = this.getSession().createQuery(SELECT_BY_DID);
		query.setParameter(0, d_id);
		list = query.getResultList();
		return list;
	}

	private final String SELECT_BY_MCID_AND_DID = "from DishPhotoBean where mc_id = ? and d_id = ?";
	@Override
	public List<DishPhotoBean> selectByMcidAndDid(Integer mc_id, Integer d_id) {
		List<DishPhotoBean> list = new LinkedList<DishPhotoBean>();
		Query query = this.getSession().createQuery(SELECT_BY_MCID_AND_DID);
		query.setParameter(0, mc_id);
		query.setParameter(1, d_id);
		list = query.getResultList();
		return list;
	}

}
