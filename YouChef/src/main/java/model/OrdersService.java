package model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import model.dao.OrdersDAOHibernate;

@Service(value="ordersService")
public class OrdersService {
	
	
	
	@Autowired
	private OrdersDAO ordersDAO;

	@Autowired
	private SimpleDateFormat sdfYM;
	@Autowired
	private SimpleDateFormat sdfD;
	@Autowired
	private SimpleDateFormat sdfM;
	@Autowired
	private CalendarService calendarService;
	
	@Autowired
	private CalendarDAO calendarDao;
	
	public static void main (String[] args){
	    	ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
			SessionFactory sessionFactory = (SessionFactory)context.getBean("sessionFactory");
			try {
				sessionFactory.getCurrentSession().beginTransaction();
				OrdersService os = (OrdersService) context.getBean("ordersService");
				OrdersBean orderbean = os.ordersDAO.findByPrimaryKey(12004);
				//CalendarBean c = (CalendarBean) context.getBean("calendar");
				//System.out.println(c);
				//System.out.println(orderbean);
				os.updateChefShift(orderbean);
				CalendarService cs = (CalendarService) context.getBean("calendarService");
				CalendarBean calendar = cs.selectChef(3002, "201711");
				System.out.println(os.reflectionGet(calendar, "25", 1));
				
				
				
				
			
				sessionFactory.getCurrentSession().getTransaction().commit();
			} finally {
				sessionFactory.close();
				 ((ConfigurableApplicationContext) context).close();
			}
			
	    
	    }
	
	
	
	//選擇廚師傳送c_id,接收後班表立即顯示 0沒上班 1中午滿  2 晚上滿 3額滿  4尚未預約 
	public String showChefDate(int c_id, String month, String date){//ok
		String result = "";
		CalendarBean bean = calendarDao.selectChef(c_id, month);
		if (bean != null) {
			String noon = this.reflectionGet(bean, date, 2);
			String evening = this.reflectionGet(bean, date, 1);
			String all = "noon="+noon+ "\r\n"+ "evening="+ evening;
			result = all;
		}
		return result;	

	}
	
	//選擇廚師傳送mc_id,接收後班表立即顯示 假設maxNum為30 沒上班 30 額滿30  取消一份為當日減1  
	public String showMChefDate(int mc_id, String date){//ok
		String result = "";
		CalendarBean bean = calendarDao.selectMchef(mc_id, date);
		if (bean != null) {
			int maxNum = bean.getMaxNum();
			String available = this.reflectionMCGet(bean, date, maxNum);
			result = available;
		}
		return result;	

	}
	
	
	
	
	

//		選擇好日期即依照大廚有空的時段決定早上或晚上時段的選取權限(若中午已被預約,則只能勾晚上)
//      0沒上班 1中午滿  2 晚上滿 3額滿  4尚未預約    
	public String filterSession(int c_id, String month, String date){
		String result = "";
		CalendarBean bean = calendarDao.selectChef(c_id, month);
		if (bean != null) {
			    Class aClass = bean.getClass();
				String methodName = "getDate" + date ; // fieldName String
				Method m = null;
				try {
					m = aClass.getMethod(methodName);
				} catch (NoSuchMethodException nsme) {
					nsme.printStackTrace();
				}
				try {
					Integer cook_status = (Integer)m.invoke(bean); // field value
					switch (cook_status){
						case 1 : result="2";break;
						case 2 : result="1";break;
						case 4 : result="0";break;
						
					}
				
					
				} catch (IllegalAccessException iae) {
					iae.printStackTrace();
				} catch (InvocationTargetException ite) {
					ite.printStackTrace();
				}

		}
		return result;	

		
	}


	
	public OrdersBean placeNewOrder(OrdersBean bean){
		OrdersBean result = null;
		if (bean != null) {
			result = ordersDAO.insert(bean);
		}
		return result;	
	}

	
	
	 public String reflectionMCGet(CalendarBean bean,String date, int maxNum){
		 String selectable = "";
		 if(bean!=null){
			 	Class aClass = bean.getClass();
				int begin = Integer.parseInt(date);
				while(begin<=31){
					String methodName = "getDate" + begin ; // fieldName String
					Method m = null;
					try {
						m = aClass.getMethod(methodName);
					} catch (NoSuchMethodException nsme) {
						nsme.printStackTrace();
					}
					
					try {
						Integer cook_status = (Integer)m.invoke(bean); // field value
						if(!cook_status.equals(maxNum)){
							selectable += begin + "," ;
						}
						begin++;
					} catch (IllegalAccessException iae) {
						iae.printStackTrace();
					} catch (InvocationTargetException ite) {
						ite.printStackTrace();
					}
				}
		 }
		return selectable;		
	 }
	
	
	
	 public String reflectionGet(CalendarBean bean,String date, int status){//ok
		 String selectable = "";
		 if(bean!=null){
			 	Class aClass = bean.getClass();
				int begin = Integer.parseInt(date);
				while(begin<=31){
					String methodName = "getDate" + begin ; // fieldName String
					Method m = null;
					try {
						m = aClass.getMethod(methodName);
					} catch (NoSuchMethodException nsme) {
						nsme.printStackTrace();
					}
					
					try {
						Integer cook_status = (Integer)m.invoke(bean); // field value
						if(cook_status.equals(status)){
							selectable += begin + "," ;
						}
						begin++;
					} catch (IllegalAccessException iae) {
						iae.printStackTrace();
					} catch (InvocationTargetException ite) {
						ite.printStackTrace();
					}
				}
		 }
		return selectable;		
	 }
	 

	 
	 

	 
	 
	 
	 public boolean reflectionMCSet(CalendarBean bean,String date, int people){
		 boolean result = false;
		 if(bean!=null){
			 	Class aClass = bean.getClass();
				Class[] paramTypes = new Class[1];
				paramTypes[0] = Integer.class; 
				String getMethodName = "getDate" + date ; 
				String setMethodName = "setDate" + date ; 
				Method get = null;
				Method set = null;
				try {
					get = aClass.getMethod(getMethodName);
					set = aClass.getMethod(setMethodName, paramTypes);
				} catch (NoSuchMethodException nsme) {
					nsme.printStackTrace();
				}

				try {
			        int num = (Integer) get.invoke(bean); 
			        int updatedNum = num - people;
			        if(updatedNum>=0){
			        set.invoke(bean,updatedNum);
			        }
			        else{
			        	System.out.println("Dishes cannot meet demands.");
			        	return false;
			        }
				} catch (IllegalAccessException iae) {
			    iae.printStackTrace();
				} catch (InvocationTargetException ite) {
			    ite.printStackTrace();
				}
				result = true;
			
		 }
		 
		 return result;
	 }
	 
	 
	 public boolean reflectionSet(CalendarBean bean,String date, int status){//ok
		 boolean result = false;
		 if(bean!=null){
			 	Class aClass = bean.getClass();
				Class[] paramTypes = new Class[1];
				paramTypes[0] = Integer.class; // get the actual param type
				String methodName = "setDate" + date ; // fieldName String
				System.out.println("methodName= "+ methodName);
				Method m = null;
				try {
					m = aClass.getMethod(methodName, paramTypes);
				} catch (NoSuchMethodException nsme) {
					nsme.printStackTrace();
				}

				try {
			      m.invoke(bean,new Integer(status)); // field value

				} catch (IllegalAccessException iae) {
			    iae.printStackTrace();
				} catch (InvocationTargetException ite) {
			    ite.printStackTrace();
				}
				result = true;
			
		 }
		 
		 return result;
	 }
	 

	
	public OrdersBean updateChefShift(OrdersBean bean){ //ok
		OrdersBean result = null;
		CalendarBean calendar = null;
		if (bean != null && bean.getO_id()!=0) {  //確認資料不是空的
			int c_id = bean.getChefBean().getC_id();  //客戶選取的廚師資料
			String ym = sdfYM.format(bean.getDineDate()); //將客戶選取的用餐日期的年月輸入
			String date = sdfD.format(bean.getDineDate());
			String month = sdfM.format(bean.getDineDate());
//			System.out.println(ym);
//			System.out.println(date);
//			System.out.println(month);
			if(calendarService.selectChef(c_id, ym)!=null){     //依年月找出該廚師班表
					System.out.println("enter");
					calendar = calendarService.selectChef(c_id, ym);
					int session = Integer.valueOf(bean.getSession());   //確認午晚 session 1 約中午 2 約晚上 
					if(reflectionSet(calendar,date,session)){           // 0沒上班 1中午滿  2 晚上滿 3額滿  4尚未預約 
						//修改完廚師班表後將calendarBean送回資料庫
						calendarService.chefInput(calendar);
						System.out.println("Calendar is updated successfully!");
						result = bean;
	
					}
					else{
						System.out.println("No such a chef can be found!");
					}
			}
		}
		return result;
	
	}
	
	public OrdersBean updateMChefShift(OrdersBean bean){
		OrdersBean result = null;
		CalendarBean calendar = null;
		if (bean != null && bean.getO_id()!=0) {  //確認資料不是空的
			int mc_id = bean.getMchefBean().getMc_id();  //客戶選取的廚師資料
			String ym = sdfYM.format(bean.getDineDate()); //將客戶選取的用餐日期的年月輸入
			String date = sdfD.format(bean.getDineDate());
			String month = sdfM.format(bean.getDineDate());
			int people = bean.getPeople();//取得該訂單的人頭數		
//			System.out.println(ym);
//			System.out.println(date);
//			System.out.println(month);
			if(calendarService.selectMchef(mc_id, ym)!=null){     //依年月找出該廚師班表
					System.out.println("enter");
					calendar = calendarService.selectChef(mc_id, ym);
					//int maxNum = calendar.getMaxNum();
					if(reflectionMCSet(calendar,date,people)){
						//修改完廚師班表後將calendarBean送回資料庫
						calendarService.chefInput(calendar);
						System.out.println("Calendar is updated successfully!");
						result = bean;
	
					}
					else{
						System.out.println("No such a Mchef can be found!");
					}
			}
		}
		return result;
	
	}
	
	
	
	public OrdersBean changePlace(OrdersBean bean) {
		OrdersBean result = null;
		if (bean != null) {
			result = ordersDAO.updatePlace(bean.getDinePlace(), bean.getOrderDate(), bean.getUpdateTime() , bean.getO_id());
		}
		return result;
	}
	public OrdersBean changeAll(OrdersBean bean) {
		OrdersBean result = null;
		if (bean != null) {
			result = ordersDAO.updateAll(bean.getChefBean(),bean.getMchefBean(), bean.getOrderDate(),
					bean.getSession(),bean.getTotalPrice(), bean.getDineDate(), bean.getDinePlace(),
					bean.getPeople(), bean.getUpdateTime(), bean.getO_id());
		}
		return result;
	}
	public OrdersBean cancelOrder(OrdersBean bean) {
		OrdersBean result = null;
		if (bean != null) {
			result = ordersDAO.cancel(bean.getO_status(),bean.getUpdateTime(), bean.getO_id());
		}
		return result;
	}
	public OrdersBean completeOrder(OrdersBean bean) {
		OrdersBean result = null;
		if (bean != null) {
			result = ordersDAO.complete(bean.getO_status(), bean.getUpdateTime(), bean.getO_id());
		}
		return result;
	}
	

	
	
}
