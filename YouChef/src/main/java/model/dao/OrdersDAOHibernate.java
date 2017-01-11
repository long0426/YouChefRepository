package model.dao;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.SharedSessionContract;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import model.CalendarDAO;
import model.ChefBean;
import model.ChefDAO;
import model.MchefBean;
import model.MemberBean;
import model.OrdersBean;
import model.OrdersDAO;
@Repository(value="ordersDAO")
@Transactional(transactionManager="transactionManager")
public class OrdersDAOHibernate implements OrdersDAO {

	private static final String GET_ALL_STMT = "from OrdersBean order by o_id";
	private static final String LIST_ORDERHISTORY_STMT = "from OrdersBean where m_id =? order by orderDate DESC";
	private static final String CANCELCODE = "1";
	private static final String COMPLETELCODE = "2";
	private static final String STANDUPCODE = "3";

	
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
			OrdersDAO orders = (OrdersDAOHibernate) context.getBean("ordersDAO");
			System.out.println("History= " + orders.listOrderHistory(1003));
			List <OrdersBean> all = (List <OrdersBean>) orders.getAll();
			System.out.println("all= "+all);
			System.out.println("----------------");
			OrdersBean single = orders.findByPrimaryKey(12004);
			System.out.println("single= "+single);
			System.out.println("----------------");
			System.out.println("single's MemberBean= "+single.getMemberBean());
			System.out.println("----------------");
			System.out.println("single's MchefBean= "+ single.getMchefBean());
			System.out.println("----------------");
			System.out.println("single's ChefBean= "+ single.getChefBean());
			System.out.println("----------------");
			
			ChefDAO ch = (ChefDAOHibernate) context.getBean("chefDao");
			ChefBean ch1 = ch.select(3003);
			System.out.println("ch1: " + ch1);
			System.out.println("----------------");
			
			OrdersBean newOne = new OrdersBean();
			newOne.setChefBean(ch1);
			newOne.setOrderDate(Date.valueOf("2014-3-19"));
			newOne.setMchefBean(single.getMchefBean());
			newOne.setMemberBean(single.getMemberBean());
			newOne.setO_status(single.getO_status());
			newOne.setPeople(single.getPeople());
			newOne.setTotalPrice(new Float(3999.9));
			newOne.setUpdateTime(new Timestamp(System.currentTimeMillis()));
			newOne.setR_message("Good");
			newOne.setR_stars(new Float(3.9));
			newOne.setSession("2");
			newOne.setDineDate(new Timestamp(System.currentTimeMillis()));
			newOne.setDinePlace("資策會");
			
			OrdersBean insert = orders.insert(newOne);
			System.out.println("insert= "+ insert);
			
			System.out.println("----------------");
			OrdersBean cancel = orders.cancel(new Timestamp(System.currentTimeMillis()) , new Integer(12000));
			System.out.println("cancel= "+ cancel);
			System.out.println("----------------");
			OrdersBean complete = orders.complete(new Timestamp(System.currentTimeMillis()), new Integer(12000));
			System.out.println("complete= "+ complete);
			System.out.println("----------------");
			
			
			OrdersBean updatedPlace = orders.updatePlace("台北市龍山寺捷運站出口", Date.valueOf("2015-12-13"), new Timestamp(System.currentTimeMillis()), new Integer(13006));
			System.out.println("updatedPlace= "+ updatedPlace);
			
			CalendarDAO c = (CalendarDAOHibernate) context.getBean("calendarDao");
			System.out.println("c= " + c.selectMchef(1001, "12"));
			sessionFactory.getCurrentSession().getTransaction().commit();
		} finally {
			sessionFactory.close();
			 ((ConfigurableApplicationContext) context).close();
		}
		
    
    }
    
    
	@Override
	public OrdersBean insert(OrdersBean ordersBean){
		if(ordersBean!=null){
			OrdersBean inserted = this.getSession().get(OrdersBean.class, ordersBean.getO_id());
			if(inserted==null){
				this.getSession().save(ordersBean);
				return ordersBean;
			}
		}
		return null;
	}
    @Override
	public OrdersBean updateAll(ChefBean chefBean, MchefBean mchefBean, Date orderDate,String session,Float totalPrice,Timestamp dineDate,String dinePlace,Integer people,Timestamp updateTime, Integer o_id){
    	OrdersBean updated = this.getSession().get(OrdersBean.class,o_id);
    	if(updated!=null){
    		updated.setChefBean(chefBean);
    		updated.setMchefBean(mchefBean);;
    		updated.setOrderDate(orderDate);
    		updated.setSession(session);
    		updated.setTotalPrice(totalPrice);
    		updated.setDineDate(dineDate);
    		updated.setDinePlace(dinePlace);
    		updated.setPeople(people);
    		updated.setUpdateTime(updateTime);
    	
    	}
    	return updated;
    }
    @Transactional
    @Override
	public OrdersBean findByPrimaryKey(int o_id){
    	return this.getSession().get(OrdersBean.class, o_id);
     }
    
    
    @Override
    @Transactional
	public List<OrdersBean> getAll(){
    	javax.persistence.Query query = this.getSession().createQuery(GET_ALL_STMT);
    	return (List<OrdersBean>) query.getResultList();
    	}
	@Override
	public List<OrdersBean> listOrderHistory(int m_id) {
    	javax.persistence.Query query = this.getSession().createQuery(LIST_ORDERHISTORY_STMT);
    	query.setParameter(0,m_id);
    	return (List<OrdersBean>) query.getResultList();

	}



	@Override
	public OrdersBean updatePlace(String dinePlace, Date orderDate, Timestamp updateTime, Integer o_id) {
		OrdersBean updatedPlace = this.getSession().get(OrdersBean.class,o_id);
		if(updatedPlace!=null){
			updatedPlace.setDinePlace(dinePlace);
			updatedPlace.setOrderDate(orderDate);
			updatedPlace.setUpdateTime(updateTime);	
		}
		return updatedPlace;
	}


	


	@Override
	public OrdersBean cancel(Timestamp updateTime, Integer o_id) {
		OrdersBean cancellation = this.getSession().get(OrdersBean.class,o_id);
		if(cancellation!=null){
			cancellation.setO_status(CANCELCODE);
			cancellation.setUpdateTime(updateTime);	
		}
		return cancellation;
		
	}



	@Override
	public OrdersBean complete(Timestamp updateTime, Integer o_id) {
		OrdersBean completion = this.getSession().get(OrdersBean.class,o_id);
		if(completion!=null){
			completion.setO_status(COMPLETELCODE);
			completion.setUpdateTime(updateTime);	
		}
		return completion;
	}

	@Override
	public OrdersBean standUp(Timestamp updateTime, Integer o_id) {
		OrdersBean standMeUp = this.getSession().get(OrdersBean.class,o_id);
		if(standMeUp!=null){
			standMeUp.setO_status(STANDUPCODE);
			standMeUp.setUpdateTime(updateTime);
		}
		return standMeUp;
	}
	//Lee 更新評價
    @Override
	public OrdersBean poreview(String r_message,float r_stars,Integer o_id){
    	OrdersBean updatereview = this.getSession().get(OrdersBean.class,o_id);
    	if(updatereview!=null){
    		updatereview.setR_message(r_message);
    		updatereview.setR_stars(r_stars);
    	}
    	return updatereview;
    }
    
//    //shian 更新
//	private static final String GET_ALL_BY_MCHEF = "SELECT {ORDER.*}, {MEMBER.*} FROM ORDERS O, MEMBER M"
//			+ "WHERE O.MC_ID = MEMBER.M_ID";
//	public List<Object[]> getAllOrdersByMchef(){
////    	SQLQuery query = this.getSession().createSQLQuery(GET_ALL_BY_MCHEF)
////    			.addEntity("orders",OrdersBean.class)
////    			.addEntity("member",MemberBean.class);
//		Query query = this.getSession().createQuery("from OrdersBean as ods left join ods.M")
//    	
//    			
//    }
}

