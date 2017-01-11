package model.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.ReviewBean;
import model.ReviewDAO;

@Repository(value = "reviewDao")
public class ReviewDAOHibernate implements ReviewDAO{
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	@Override
	public ReviewBean select(ReviewBean bean) {
		return this.getSession().get(ReviewBean.class, bean.getR_id());
	}
	
	@Override
	public int insert(ReviewBean bean) {
		return (int) this.getSession().save(bean);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<ReviewBean> selectAll() {
		Query query = this.getSession().createQuery("from ReviewBean");
		return (List<ReviewBean>) query.getResultList();
	}
	
	@Override
	public ReviewBean update(ReviewBean bean) {
		if (null != select(bean))  {
			getSession().clear();
			this.getSession().update(bean);
			return select(bean);
		}else{
			return null;
		}
	}
//	@Override
//	public ReviewBean selectMchefReview(Integer mc_id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	@Override
//	public ReviewBean selectChefReview(Integer c_id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	private final String SELECT_MCHEFSTARS = "select ROUND((stars/orderNum),1) from ReviewBean where mc_id=:mc_id";

	@Override
	public Double MchefStars(Integer mc_id) {
		Query query = this.getSession().createQuery(SELECT_MCHEFSTARS);
		System.out.println("mc_id = " + mc_id);
		query.setParameter("mc_id", mc_id);
		return  (double)query.getResultList().get(0);	
	}
	
	private final String SELECT_CHEFSTARS = "select ROUND((stars/orderNum),1) from ReviewBean where c_id=:c_id";

	@Override
	public Double ChefStars(Integer c_id) {
		Query query = this.getSession().createQuery(SELECT_CHEFSTARS);
		System.out.println("c_id = " + c_id);
		query.setParameter("c_id", c_id);
		return  (double)query.getResultList().get(0);	
	}
}
