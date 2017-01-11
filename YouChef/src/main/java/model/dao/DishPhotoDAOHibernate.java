package model.dao;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

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
	
	private final String SELECT_BY_MCID = "from DishPhotoBean where mc_id=:mc_id";
	@Override
	public List<DishPhotoBean> selectByMcid(Integer mc_id) {
		List<DishPhotoBean> list = new LinkedList<DishPhotoBean>();
		Query query = this.getSession().createQuery(SELECT_BY_MCID);
		query.setParameter("mc_id", mc_id);
		list = query.getResultList();
		return list;
	}

	private final String SELECT_BY_DID = "from DishPhotoBean where d_id=:d_id";
	@Override
	public List<DishPhotoBean> selectByDid(Integer d_id) {
		List<DishPhotoBean> list = new LinkedList<DishPhotoBean>();
		Query query = this.getSession().createQuery(SELECT_BY_DID);
		query.setParameter("d_id", d_id);
		list = query.getResultList();
		return list;
	}

	private final String SELECT_BY_MCID_AND_DID = "from DishPhotoBean where mc_id=:mc_id and d_id=:d_id";
	@Override
	public List<DishPhotoBean> selectByMcidAndDid(Integer mc_id, Integer d_id) {
		List<DishPhotoBean> list = new LinkedList<DishPhotoBean>();
		Query query = this.getSession().createQuery(SELECT_BY_MCID_AND_DID);
		query.setParameter("mc_id", mc_id);
		query.setParameter("d_id", d_id);
		list = query.getResultList();
		return list;
	}

	@Override
	public DishPhotoBean selectByDpid(Integer dp_id) {
		return this.getSession().get(DishPhotoBean.class, dp_id);
	}
	//書賢修改
	@Override
	public DishPhotoBean selectByPrimary(Integer d_id) {
		DishPhotoBean result = null;
		Query query = this.getSession().createQuery("from DishPhotoBean where d_id= :d_id");
		query.setParameter("d_id", d_id);
		try {
			result=(DishPhotoBean) query.getSingleResult();
		} catch (Exception e) {
			System.out.println("不是單筆資料");
			return null;
		}
		return result;
	}

	@Override
	public DishPhotoBean update(DishPhotoBean bean) {
		DishPhotoBean update= this.getSession().get(DishPhotoBean.class, bean.getDp_id());
		if(update!=null){
			update.setD_photo(bean.getD_photo());
		}
		return update;
	}
}
