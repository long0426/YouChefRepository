package model.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.DishesBean;
import model.OrderDetailBean;
import model.OrderDishesBean;
import model.OrderDishesDAO;
import model.OrdersBean;

@Repository(value="orderDishesDAO")
public class OrderDishesDAOHibernate implements OrderDishesDAO {
	
	private static final String GET_ALL_STMT = "from OrderDishesBean order by ods_id";
	
	@Autowired
	private SessionFactory sessionFactory;
    public Session getSession(){
    	return sessionFactory.getCurrentSession();
    }
	
	@Override
	public OrderDishesBean insert(OrderDishesBean orderDishesBean) {
		if(orderDishesBean!=null){
			OrderDetailBean inserted = this.getSession().get(OrderDetailBean.class, orderDishesBean.getOds_id());
			if(inserted==null){
				this.getSession().save(orderDishesBean);
				return orderDishesBean;
			}
		}
		return null;

	}

	@Override
	public OrderDishesBean update(OrderDetailBean orderDetailBean, DishesBean dishesBean, Integer dishNum, int ods_id) {
		OrderDishesBean updated = this.getSession().get(OrderDishesBean.class,ods_id);
    	if(updated!=null){
    		updated.setOrderDetailBean(orderDetailBean);
    		updated.setDishesBean(dishesBean);
    		updated.setDishNum(dishNum);
    	}
    	return updated;
	}

	@Override
	public OrderDishesBean findByPrimaryKey(int ods_id) {
		return this.getSession().get(OrderDishesBean.class, ods_id);
	
	}

	@Override
	public List<OrderDishesBean> getAll() {
    	javax.persistence.Query query = this.getSession().createQuery(GET_ALL_STMT);
    	return (List<OrderDishesBean>) query.getResultList();
	}

}
