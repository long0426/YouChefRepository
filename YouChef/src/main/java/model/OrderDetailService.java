package model;

import java.io.IOException;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import model.dao.OrdersDAOHibernate;

@Service(value="orderDetailService")
public class OrderDetailService {

	@Autowired
	private OrderDetailDAO orderDetailDAO;
	@Autowired
	OrdersDAOHibernate ordersDAO;
	
	public static void main(String[] args) throws IOException {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			OrderDetailService service = (OrderDetailService) context.getBean("orderDetailService");
			
			OrderDetailBean bean = new OrderDetailBean();
			bean.setOrdersBean(service.ordersDAO.findByPrimaryKey(12001));
			System.out.println(service.placeNewOrderDetail(bean));
			
			sessionFactory.getCurrentSession().getTransaction().commit();
		} finally {
			sessionFactory.close();
			((ConfigurableApplicationContext) context).close();
		}
	}
	public OrderDetailBean placeNewOrderDetail(OrderDetailBean bean){
		OrderDetailBean result = null;
		if (bean != null) {
			result = orderDetailDAO.insert(bean);	
			}
		return result;
		
	}
}

	
	

