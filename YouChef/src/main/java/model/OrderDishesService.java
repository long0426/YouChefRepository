package model;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import model.dao.OrderDetailDAOHibernate;

@Service(value = "orderDishesService")
public class OrderDishesService {
	@Autowired
	OrderDishesDAO orderDishesDAO;
	@Autowired
	OrderDetailDAOHibernate orderDetailDAO;
	@Autowired
	DishesService dishesService;

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			OrderDishesService service = (OrderDishesService) context.getBean("orderDishesService");
			
			//insert
//			OrderDishesBean bean = new OrderDishesBean();
//			bean.setOrderDetailBean(service.orderDetailDAO.findByPrimaryKey(13001));
//			bean.setDishesBean(service.dishesService.select(5002));
//			bean.setDishNum(2);
//			System.out.println(service.insert(bean));
			
			//getAll
			System.out.println(service.getAll());
			
			sessionFactory.getCurrentSession().getTransaction().commit();
		} finally {
			sessionFactory.close();
			((ConfigurableApplicationContext) context).close();
		}
	}

	public OrderDishesBean insert(OrderDishesBean bean){
		return orderDishesDAO.insert(bean);
	}
	
	public OrderDishesBean update(OrderDetailBean orderDetailBean, DishesBean dishesBean, Integer dishNum, int ods_id){
		return orderDishesDAO.update(orderDetailBean, dishesBean, dishNum, ods_id);
	}
	
	public OrderDishesBean findByPrimaryKey(int ods_id) {
		return orderDishesDAO.findByPrimaryKey(ods_id);
	}
	
	public List<OrderDishesBean> getAll() {
		return orderDishesDAO.getAll();
	}
}
