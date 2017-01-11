package model.dao;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import model.ChefBean;
import model.ChefDAO;
import model.DishesBean;
import model.OrderDetailBean;
import model.OrderDetailDAO;
import model.OrderDishesBean;
import model.OrdersBean;
import model.OrdersDAO;

@Repository(value="orderDetailDAO")
public class OrderDetailDAOHibernate implements OrderDetailDAO {

	private static final String GET_ALL_STMT = "from OrderDetailBean order by od_id";
	private static final String FIND_By_ORDER_ID = "from OrderDetailBean where o_id = ?";
	
	
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
			OrderDetailDAO od = (OrderDetailDAOHibernate) context.getBean("orderDetailDAO");
			OrderDetailBean odbNew = new OrderDetailBean();
			odbNew.setOd_id(13007);

			DishesBean db = new DishesBean();
			db.setD_id(5001);
			db.setPrice(4443);
			db.setD_briefing("GOOOOOD");
			db.setD_name("Shit");
			db.setMenu("XXXXXXXX");
			db.setD_status("1");
	
			
			OrdersBean newOne = new OrdersBean();
			newOne.setO_id(12000);
			newOne.setOrderDate(Date.valueOf("2014-3-19"));
			newOne.setO_status("1");
			newOne.setPeople(new Integer(69));
			newOne.setTotalPrice(new Float(3999.9));
			newOne.setUpdateTime(new Timestamp(System.currentTimeMillis()));
			newOne.setR_message("Good");
			newOne.setR_stars(new Float(3.9));
			newOne.setSession("2");
			newOne.setDineDate(new Timestamp(System.currentTimeMillis()));
			newOne.setDinePlace("總統府");
			odbNew.setOrdersBean(newOne);
			
			OrderDetailBean insert = od.insert(odbNew);
			System.out.println("insert= "+ insert);
			
			System.out.println("----------------");

			
			OrderDetailBean updated = od.update(newOne, 13000);
			System.out.println("updated= "+ updated);
			
			List <OrderDetailBean> all = (List <OrderDetailBean>) od.getAll();
			System.out.println("all= "+all);
			System.out.println("----------------");
			OrderDetailBean single = od.findByPrimaryKey(13006);
			System.out.println("single= "+single);
			System.out.println("----------------");
			System.out.println("single's OrdersBean= "+single.getOrdersBean());
			System.out.println("----------------");


			
			
			
			
			sessionFactory.getCurrentSession().getTransaction().commit();
		} finally {
			sessionFactory.close();
			 ((ConfigurableApplicationContext) context).close();
		}
		
    
    }
    
    


    

	@Override
	public OrderDetailBean insert(OrderDetailBean orderDetailBean) {
			OrderDetailBean inserted = this.getSession().get(OrderDetailBean.class, orderDetailBean.getOd_id());
			if(inserted==null){
				this.getSession().save(orderDetailBean);
				return orderDetailBean;
			}
		return null;
	}
	




	@Override
	public OrderDetailBean findByPrimaryKey(int od_id) {
		return this.getSession().get(OrderDetailBean.class, od_id);
	
	}

	
	@Override
	public List<OrderDetailBean> getAll() {
    	javax.persistence.Query query = this.getSession().createQuery(GET_ALL_STMT);
    	return (List<OrderDetailBean>) query.getResultList();

	}

	
	@Override
	public OrderDetailBean update(OrdersBean ordersBean, int od_id) {
		OrderDetailBean updated = this.getSession().get(OrderDetailBean.class,od_id);
    	if(updated!=null){
    		updated.setOrdersBean(ordersBean);
    	}
    	return updated;
	}

	@Override
	public OrderDetailBean findByOrderId(int o_id) {
		javax.persistence.Query query = this.getSession().createQuery(FIND_By_ORDER_ID);
		query.setParameter(0, o_id);
    	List <OrderDetailBean> result = query.getResultList();
    	if (result.isEmpty()) {
    	    return null; 
    	} else {
    	    return result.get(0);
    	}
	}


	
	
	

}
