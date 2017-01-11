package model;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import model.dao.DishesDAOHibernate;
import model.dao.OrderDetailDAOHibernate;
import model.dao.OrderDishesDAOHibernate;
import model.dao.OrdersDAOHibernate;

@Service(value="orderDishesService")
public class OrderDishesService {
	private static final String ORDER_HISTORTY_STMT =
	 "SELECT os.o_id,os.c_id,os.mc_id,os.dineDate,os.dinePlace,os.session,os.totalPrice,os.o_status "
	    +"FROM orders as os "
	 +"LEFT　JOIN mchef as mc "
	    +"ON os.mc_id = mc.mc_id "
     +"LEFT　JOIN chef as c "
	    +"ON os.c_id = c.c_id "
	 +"INNER　JOIN type as t "
	    +"ON c.t_id = t.t_id "
	 +"WHERE os.m_id = ? ";
	private static final String Single_ORDER_DETAIL_STMT =
	  "SELECT os.m_id,os.o_id,c.lastName,c.firstName,m.lastName,m.firstName,d.d_name,od.dishNum,d.price,d.menu,os.totalPrice,t.t_name "
         +"FROM   orders as os "
      +"LEFT JOIN mchef as mc "
         +"ON os.mc_id = mc.mc_id "
		+"LEFT JOIN member as m "
	     +"ON mc.mc_id = m.m_id "
	   +"LEFT JOIN chef as c "
	     +"ON os.c_id = c.c_id "
       +"INNER JOIN orderDetail as ode "
	     +"ON os.o_id = ode.o_id "
       +"INNER JOIN orderDishes as od "
         +"ON ode.od_id = od.od_id "
        +"LEFT JOIN dishes as d "
                  +"LEFT JOIN type as t "
                  +"ON d.t_id = t.t_id "
         +"ON od.d_id = d.d_id "
		 +"WHERE  os.o_id = ? ";
	
	@Autowired
	private OrderDishesDAO orderDishesDAO;
	
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
		
			OrdersDAOHibernate order = (OrdersDAOHibernate) context.getBean("ordersDAO");
			OrdersBean a = order.findByPrimaryKey(12000);
			OrderDetailDAOHibernate detail = (OrderDetailDAOHibernate) context.getBean("orderDetailDAO");
			OrderDetailBean b = detail.findByOrderId(12000);
			System.out.println("b= "+b);
			OrderDishesDAO orderDishes = (OrderDishesDAOHibernate) context.getBean("orderDishesDAO");
			DishesDAO dishes = (DishesDAOHibernate) context.getBean("dishesDao");
//			DishesBean one = dishes.select(5001);
//			DishesBean two = dishes.select(5002);
//			DishesBean three = dishes.select(5003);
//			OrderDishesBean aaa = new OrderDishesBean();
//			OrderDishesBean bbb = new OrderDishesBean();
//			OrderDishesBean ccc = new OrderDishesBean();
//			aaa.setDishesBean(one);
//			aaa.setDishNum(5);
//			aaa.setOrderDetailBean(b);
//			//System.out.println("aaa="+ orderDishes.insert(aaa));
//			
//			bbb.setDishesBean(two);
//			bbb.setDishNum(10);
//			bbb.setOrderDetailBean(b);
//			ccc.setDishesBean(three);
//			ccc.setDishNum(15);
//			ccc.setOrderDetailBean(b);
			OrderDishesService service = (OrderDishesService) context.getBean("orderDishesService");
//			System.out.println("Insert NewOrderDetailDishes= "+service.insertNewOrderDetailDishes(b, aaa,bbb,ccc)); //測試新增大廚訂單菜色明細
//			OrderDetailBean existingOrder = detail.findByOrderId(12000);
//			List <OrderDishesBean> check = orderDishes.listByOrderDetailId(existingOrder.getOd_id());
//			OrderDishesBean first = check.get(0);
//			OrderDishesBean second = check.get(1);
//			OrderDishesBean third = check.get(2);
//			first.setDishesBean(dishes.selectDishById(5003));
//			first.setDishNum(10);
//			second.setDishesBean(dishes.selectDishById(5002));
//			second.setDishNum(99);
//			third.setDishesBean(dishes.selectDishById(5002));
//			third.setDishNum(15);
//			System.out.println("Update OrderDetailDishes= "+service.updateOrderDetailDishesForChef(existingOrder, first,second,third));//測試修改大廚訂單菜色明細
			System.out.println("A member's order history = "+ service.listOrderHostory(1002));
			System.out.println("A member's single order detail = "+ service.listSingleOrderDetail(12003));

			
			
			
	


			sessionFactory.getCurrentSession().getTransaction().commit();
		} finally {
			sessionFactory.close();
			 ((ConfigurableApplicationContext) context).close();
		}
		
    
    }
    
	@SuppressWarnings("null")
	public List <OrderDishesBean> insertNewOrderDetailDishes(OrderDetailBean orderDetail, List<OrderDishesBean> orderDishes){
		List <OrderDishesBean> result = null;
		List <OrderDishesBean> ready = orderDishes;
		List <OrderDishesBean> check = orderDishesDAO.listByOrderDetailId(orderDetail.getOd_id());
		if(check!=null){
			for(int i = 0; i<ready.size(); i++){		
				OrderDishesBean single = new OrderDishesBean();
				single.setOrderDetailBean(orderDetail);
				single.setDishesBean(ready.get(i).getDishesBean());
				single.setDishNum(ready.get(i).getDishNum());
				System.out.println("Insert new orderdishes= "+orderDishesDAO.insert(single));
				check.add(single);
	
			}
			result = check;
		}
		return result;
	}
	
//	@SuppressWarnings("null")
//	public List <OrderDishesBean> insertNewOrderDetailDishes(OrderDetailBean orderDetail, OrderDishesBean... orderDishes){
//		List <OrderDishesBean> result = null;
//		OrderDishesBean [] ready = orderDishes;
//		System.out.println("ready= " + ready[0]);
//		List <OrderDishesBean> check = orderDishesDAO.listByOrderDetailId(orderDetail.getOd_id());
//		if(check!=null){
//			for(int i = 0; i<ready.length; i++){		
//				OrderDishesBean single = new OrderDishesBean();
//				single.setOrderDetailBean(orderDetail);
//				single.setDishesBean(ready[i].getDishesBean());
//				single.setDishNum(ready[i].getDishNum());
//				System.out.println("Insert new orderdishes= "+orderDishesDAO.insert(single));
//				check.add(single);
//	
//			}
//			result = check;
//		}
//		return result;
//	}
	
	
	public List <OrderDishesBean> updateOrderDetailDishesForChef(OrderDetailBean orderDetail, List<OrderDishesBean> orderDishes){
		List <OrderDishesBean> result = null;
		List <OrderDishesBean> ready = orderDishes;
		List <OrderDishesBean> check = orderDishesDAO.listByOrderDetailId(orderDetail.getOd_id());
		if(check!=null){
			for(int i = 0; i<ready.size(); i++){
					OrderDishesBean single = new OrderDishesBean();
					single.setOrderDetailBean(orderDetail);
					single.setDishesBean(ready.get(i).getDishesBean());
					single.setDishNum(ready.get(i).getDishNum());
					System.out.println("Update orderdishes= "+ orderDishesDAO.update(orderDetail, ready.get(i).getDishesBean(), ready.get(i).getDishNum(), ready.get(i).getOds_id()));
					check.set(i,single); 
				}
			}
			result = check;
		return result;
	}
	
//	public List <OrderDishesBean> updateOrderDetailDishesForChef(OrderDetailBean orderDetail, OrderDishesBean... orderDishes){
//		List <OrderDishesBean> result = null;
//		OrderDishesBean [] ready = orderDishes;
//		List <OrderDishesBean> check = orderDishesDAO.listByOrderDetailId(orderDetail.getOd_id());
//		if(check!=null){
//			for(int i = 0; i<ready.length; i++){
//					OrderDishesBean single = new OrderDishesBean();
//					single.setOrderDetailBean(orderDetail);
//					single.setDishesBean(ready[i].getDishesBean());
//					single.setDishNum(ready[i].getDishNum());
//					System.out.println("Update orderdishes= "+ orderDishesDAO.update(orderDetail, ready[i].getDishesBean(), ready[i].getDishNum(), ready[i].getOds_id()));
//					check.set(i,single); 
//				}
//			}
//			result = check;
//		return result;
//	}
	@SuppressWarnings("deprecation")
	public List<Object[]> listOrderHostory(int m_id){  
		Query query = this.getSession().	
		  createSQLQuery(ORDER_HISTORTY_STMT);
		query.setParameter(0, m_id);
	    List<Object[]> list = query.getResultList();
	    for (Object[] aArray:list){
	    	for (Object aColumn:aArray){
	    		System.out.print(aColumn + ",");
	    	}
	    }
	    
	    return (List<Object[]>) query.getResultList();
	    
	}
	
	@SuppressWarnings("deprecation")
	public List<Object[]> listSingleOrderDetail(int o_id){  
		Query query = this.getSession().	
		  createSQLQuery(Single_ORDER_DETAIL_STMT);
		query.setParameter(0, o_id);
	    List<Object[]> list = query.getResultList();
	    for (Object[] aArray:list){
	    	for (Object aColumn:aArray){
	    		System.out.print(aColumn + ",");
	    	}
	    }
	    
	    return (List<Object[]>) query.getResultList();
	    
	}
	
	

	
}
