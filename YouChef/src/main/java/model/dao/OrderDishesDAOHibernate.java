package model.dao;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import model.CalendarDAO;
import model.ChefBean;
import model.ChefDAO;
import model.DishesBean;
import model.DishesDAO;
import model.OrderDetailBean;
import model.OrderDetailDAO;
import model.OrderDishesBean;
import model.OrderDishesDAO;
import model.OrderDishesService;
import model.OrdersBean;
import model.OrdersDAO;

@Repository(value="orderDishesDAO")
public class OrderDishesDAOHibernate implements OrderDishesDAO {
	
	private static final String GET_ALL_STMT = "from OrderDishesBean order by ods_id";
	private static final String LIST_BY_OD_ID_STMT = "from OrderDishesBean where od_id=? order by d_id";
	

	
	@Autowired
	private SessionFactory sessionFactory;
    public Session getSession(){
    	return sessionFactory.getCurrentSession();
    }    
    
    public static void main (String[] args){
    	ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
		SessionFactory sessionFactory = (SessionFactory)context.getBean("sessionFactory");
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			OrderDishesDAO orderDishes = (OrderDishesDAOHibernate) context.getBean("orderDishesDAO");
			System.out.println("All= " + orderDishes.getAll());
			System.out.println("FindByPK = " + orderDishes.findByPrimaryKey(13001));
			System.out.println("ListByODID= " + orderDishes.listByOrderDetailId(13000));
			System.out.println("Insert= 請參考OrderDishesService的測試" );
			System.out.println("Update= 請參考OrderDishesService的測試" );


	

			sessionFactory.getCurrentSession().getTransaction().commit();
		} finally {
			sessionFactory.close();
			 ((ConfigurableApplicationContext) context).close();
		}
		
    
    }
    
    
    
    
	
	@Override
	public OrderDishesBean insert(OrderDishesBean orderDishesBean) {
		if(orderDishesBean!=null){
			OrderDishesBean inserted = this.getSession().get(OrderDishesBean.class, orderDishesBean.getOds_id());
			System.out.println("inserted = "+ inserted);
			if(inserted == null){
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

	@Override
	public List<OrderDishesBean> listByOrderDetailId(int od_id) {
    	javax.persistence.Query query = this.getSession().createQuery(LIST_BY_OD_ID_STMT);
    	query.setParameter(0, od_id);
    	return (List<OrderDishesBean>) query.getResultList();
	
	}

}
