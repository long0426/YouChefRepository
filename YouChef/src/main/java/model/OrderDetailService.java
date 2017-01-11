package model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import model.dao.ChefDAOHibernate;
import model.dao.OrderDishesDAOHibernate;
import model.dao.OrdersDAOHibernate;

@Service(value="orderDetailService")
public class OrderDetailService {

	@Autowired
	private OrderDetailDAO orderDetailDAO;
	
	@Autowired
	private OrdersDAO ordersDAO;

	
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
			OrdersDAO order = (OrdersDAOHibernate) context.getBean("ordersDAO");
			OrdersBean a = order.findByPrimaryKey(12012);
			OrderDetailService service = (OrderDetailService) context.getBean("orderDetailService");
			OrderDishesDAO orderDishes = (OrderDishesDAOHibernate) context.getBean("orderDishesDAO");
			System.out.println("Insert= "+service.insertNewOrderDetail(a));
			a.setDinePlace("台北市政府");
			ChefDAO chef = (ChefDAOHibernate) context.getBean("chefDao");
			a.setChefBean(chef.select(3002));
			System.out.println("Update= "+service.updateOrderDetail(a));


	

			sessionFactory.getCurrentSession().getTransaction().commit();
		} finally {
			sessionFactory.close();
			 ((ConfigurableApplicationContext) context).close();
		}
		
    
    }
	
	

	
	public OrderDetailBean insertNewOrderDetail(OrdersBean bean){
		OrderDetailBean result = null;
		OrderDetailBean ready = new OrderDetailBean();
		OrdersBean checkOrder = ordersDAO.findByPrimaryKey(bean.getO_id());
		System.out.println(checkOrder.getO_id());
		OrderDetailBean checkOrderDetail = orderDetailDAO.findByOrderId(checkOrder.getO_id());
		if (checkOrder != null && checkOrderDetail==null) {
			ready.setOrdersBean(checkOrder);
			result = orderDetailDAO.insert(ready);	
			}
		return result;
		
	}
	
	public OrderDetailBean updateOrderDetail(OrdersBean bean){
		OrderDetailBean result = null;
		OrderDetailBean ready = new OrderDetailBean();
		OrdersBean checkOrder = ordersDAO.findByPrimaryKey(bean.getO_id());
		OrderDetailBean checkOrderDetail = orderDetailDAO.findByOrderId(checkOrder.getO_id());
		if (checkOrder != null && checkOrderDetail!=null) {
			ready.setOrdersBean(bean);
			result = ready;
			}
		return result;
		
	}



	
	
}

	
	

