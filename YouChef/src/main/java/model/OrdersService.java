package model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import model.dao.OrdersDAOHibernate;

@Service(value="ordersService")
@Transactional(transactionManager="transactionManager")
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
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private SessionFactory sessionFactory;
    public Session getSession(){
    	return sessionFactory.getCurrentSession();
    }
	
	private static final String ACCOUNTSUSPENDED = "2";
	
	public static void main (String[] args){
	    	ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
			SessionFactory sessionFactory = (SessionFactory)context.getBean("sessionFactory");
			try {
				sessionFactory.getCurrentSession().beginTransaction();
				OrdersService os = (OrdersService) context.getBean("ordersService");
				//OrdersBean orderbean = (OrdersBean) context.getBean("orders");
				OrdersDAO od = (OrdersDAOHibernate) context.getBean("ordersDAO");
				OrdersBean orderbean = od.findByPrimaryKey(12008);
				System.out.println("bean= "+ orderbean);
				
				//CalendarBean c = (CalendarBean) context.getBean("calendar");
				//System.out.println(c);
				//System.out.println(orderbean); session = 2 
//				System.out.println(os.updateChefShift(orderbean));
//				CalendarService cs = (CalendarService) context.getBean("calendarService");
//				CalendarBean calendar = cs.selectChef(3002, "201711");
//				System.out.println(os.reflectionGet(calendar, "25", 1));
				System.out.println(os.updateMChefShift(orderbean));
				
				
				
				
			
				sessionFactory.getCurrentSession().getTransaction().commit();
			} finally {
				sessionFactory.close();
				 ((ConfigurableApplicationContext) context).close();
			}
			
	    
	    }
	
	

	
	
	//選擇廚師傳送c_id,接收後班表立即顯示 0沒上班 1中午滿  2 晚上滿 3額滿  4尚未預約 
	@Transactional(isolation = Isolation.SERIALIZABLE)
	public String showChefDate(int c_id, String month, String date){//ok
		String result = "";
		CalendarBean bean = calendarDao.selectChef(c_id, month);
		if (bean != null) {
			System.out.println(bean);
			ArrayList<String> noWork = this.reflectionGet(bean, date, 0);
			ArrayList<String> full = this.reflectionGet(bean, date, 3);
			ArrayList<String> both = this.reflectionGet(bean, date, 4);
			ArrayList<String> noon = this.reflectionGet(bean, date, 2);
			ArrayList<String> evening = this.reflectionGet(bean, date, 1);				
			Map <String,ArrayList<String>>objs = new HashMap();
			objs.put("noWork", noWork);
			objs.put("full", full);
			objs.put("both", both);
			objs.put("noon", noon);
			objs.put("evening", evening);
			String jsonString = JSONValue.toJSONString(objs);
			result = jsonString;
		}
		return result;	

	}
	
	//選擇廚師傳送mc_id,接收後班表立即顯示 假設maxNum為30 沒上班 30 額滿30  取消一份為當日減1  
	@Transactional(isolation = Isolation.SERIALIZABLE)
	public String showMChefDate(int mc_id, String month, String date){//ok
		String result = "";
		CalendarBean bean = calendarDao.selectMchef(mc_id, month);
		if (bean != null) {
			int maxNum = bean.getMaxNum();
			ArrayList<String> available = this.reflectionMCGet(bean, date, maxNum);
			Map <String,ArrayList<String>>objs = new HashMap();
			objs.put("available", available);
			String jsonString = JSONValue.toJSONString(objs);
			result = jsonString;
		}
		return result;	

	}
	

	
	
	

//		選擇好日期即依照大廚有空的時段決定早上或晚上時段的選取權限(若中午已被預約,則只能勾晚上)
//      0沒上班 1中午滿  2 晚上滿 3額滿  4尚未預約    
	@Transactional
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


	@Transactional
	public OrdersBean placeNewOrder(OrdersBean bean){
		OrdersBean result = null;
		if (bean != null) {
			result = ordersDAO.insert(bean);
		}
		return result;	
	}

	
	
	public ArrayList<String> reflectionMCGet(CalendarBean bean,String date, int maxNum){
		 //String selectable = "";
		 ArrayList<String> selectable = new ArrayList<String>();
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
					
					try {//沒上班 -1 賣完 0 還沒賣出一個 maxNum
						Integer cook_status = (Integer)m.invoke(bean); // field value
						if(cook_status <= maxNum && cook_status > 0){
							//!cook_status.equals(maxNum) && cook_status!=0
							//selectable += begin + "," ;
							selectable.add(String.valueOf(begin));
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
	
	
	 
	 public ArrayList<String> reflectionGet(CalendarBean bean,String date, int status){//ok
		 //String selectable = "";
		 ArrayList<String> selectable = new ArrayList<String>();
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
							//selectable += begin + "," ;
							selectable.add(String.valueOf(begin));
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
	 

	 
	 

	 
	 
	
	 public boolean reflectionMCSet(CalendarBean bean,String date, int people, OrdersBean order){
		 boolean result = false;
		 if(bean!=null){
			    int o_status = Integer.valueOf(order.getO_status());
			 	int maxNum = bean.getMaxNum();
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
					//o_status  預設0  取消1  已結帳2 
					if(o_status==1){
						  int num = (Integer) get.invoke(bean); 
						  int updatedNum = num + people;
						  if(updatedNum<=maxNum){
							  set.invoke(bean,updatedNum);
							  System.out.println("This can only be seen once.");
							  return true;
						  }else{
					        	System.out.println("You enter here more than once.");
					        	return result;
					      }
					}
					
					if(o_status==0){ 
				        int num = (Integer) get.invoke(bean); 
				        int updatedNum = num - people;
				        if(updatedNum>=0){
				        	set.invoke(bean,updatedNum);
				        	return true;
				        }else{
				        	System.out.println("Dishes cannot meet demands.");
				        	return result;
				    	}
			        }
	
				} catch (IllegalAccessException iae) {
			    iae.printStackTrace();
				} catch (InvocationTargetException ite) {
			    ite.printStackTrace();
				}
			
		 }
		 System.out.println("Invalid input.");
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
	 
	 public int simpleGet(CalendarBean bean,String date){
		 int result = -1;
		 if(bean!=null){
			 	Class aClass = bean.getClass();
				Class[] paramTypes = new Class[1];
				paramTypes[0] = Integer.class; // get the actual param type
				String methodName = "getDate" + date ; // fieldName String
				System.out.println("methodName= "+ methodName);
				Method m = null;
				try {
					m = aClass.getMethod(methodName);
				} catch (NoSuchMethodException nsme) {
					nsme.printStackTrace();
				}

				try {
			         int num = (Integer)m.invoke(bean); // field value
			         result = num;

				} catch (IllegalAccessException iae) {
			    iae.printStackTrace();
				} catch (InvocationTargetException ite) {
			    ite.printStackTrace();
				}
			
		 }
		 
		 return result;
	 }
	 
	 
	 

    @Transactional(isolation = Isolation.SERIALIZABLE)
	public OrdersBean updateChefShift(OrdersBean bean){ //ok
		OrdersBean result = null;
		CalendarBean calendar = null;
		if (bean != null && bean.getO_id()!=0) {  //確認資料不是空的
			int c_id = bean.getChefBean().getC_id();  //客戶選取的廚師資料
			String ym = sdfYM.format(bean.getDineDate()); //將客戶選取的用餐日期的年月輸入 注意資料庫是YYYYMM
			String date = sdfD.format(bean.getDineDate());
			String month = sdfM.format(bean.getDineDate());
			System.out.println(ym);
			System.out.println(date);
			System.out.println(month);
			System.out.println("c_id="+c_id);
			if(date.substring(0, 1).equalsIgnoreCase("0")){
				date = date.substring(1);
			}
			
			if(calendarService.selectChef(c_id, ym)!=null){     //依年月找出該廚師班表
					System.out.println("enter");
					calendar = calendarService.selectChef(c_id, ym);
					int status = this.simpleGet(calendar, date);
					int session = Integer.valueOf(bean.getSession());   //確認午晚 session 1 約中午 2 約晚上 
					switch (session){
					case 1:
						switch (status){
							case 1:this.reflectionSet(calendar, date, 4);break;//取消中午
							case 3:this.reflectionSet(calendar, date, 2);break;//取消中午
							case 2:this.reflectionSet(calendar, date, 3);break;//預約中午
							case 4:this.reflectionSet(calendar, date, 1);break;//預約中午
						};break;
					case 2:
						switch (status){
							case 2:this.reflectionSet(calendar, date, 4);break;//取消晚上		
							case 3:this.reflectionSet(calendar, date, 1);break;//取消晚上
							case 1:this.reflectionSet(calendar, date, 3);break;//預約晚上		
							case 4:this.reflectionSet(calendar, date, 2);break;//預約晚上
				
					};break;
					default:System.out.println("No such a chef can be found!");break;
					}				
						//修改完廚師班表後將calendarBean送回資料庫
						calendarService.chefInput(calendar);
						System.out.println("Calendar is updated successfully!");
						result = bean;
			}
		}
		return result;
	
	}
	@Transactional(isolation = Isolation.SERIALIZABLE)
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
			if(date.substring(0, 1).equalsIgnoreCase("0")){
				date = date.substring(1);
			}
			if(calendarService.selectMchef(mc_id, ym)!=null){     //依年月找出該廚師班表
					System.out.println("enter");
					calendar = calendarService.selectMchef(mc_id, ym);
					//int maxNum = calendar.getMaxNum();
					if(reflectionMCSet(calendar,date,people,bean)){
						//修改完廚師班表後將calendarBean送回資料庫
						calendarService.mchefInput(calendar);
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
	
	
	@Transactional
	public OrdersBean changePlace(OrdersBean bean) {
		OrdersBean result = null;
		if (bean != null) {
			result = ordersDAO.updatePlace(bean.getDinePlace(), bean.getOrderDate(), bean.getUpdateTime() , bean.getO_id());
		}
		return result;
	}
	@Transactional
	public OrdersBean changeAll(OrdersBean bean) {
		OrdersBean result = null;
		if (bean != null) {
			result = ordersDAO.updateAll(bean.getChefBean(),bean.getMchefBean(), bean.getOrderDate(),
					bean.getSession(),bean.getTotalPrice(), bean.getDineDate(), bean.getDinePlace(),
					bean.getPeople(), bean.getUpdateTime(), bean.getO_id());
		}
		return result;
	}
	@Transactional
	public OrdersBean cancelOrder(OrdersBean bean) {
		OrdersBean result = null;
		if (bean != null) {
			result = ordersDAO.cancel(bean.getUpdateTime(), bean.getO_id());
		}
		return result;
	}
	public OrdersBean completeOrder(OrdersBean bean) {
		OrdersBean result = null;
		if (bean != null) {
			result = ordersDAO.complete(bean.getUpdateTime(), bean.getO_id());
		}
		return result;
	}
	@Transactional
	public OrdersBean standUpOrder(OrdersBean bean) {
		OrdersBean result = null;
		if (bean != null) {
			result = ordersDAO.standUp(bean.getUpdateTime(), bean.getO_id());
			MemberBean member = memberDAO.select(bean.getMemberBean().getM_id());
			Integer absent = member.getAbsent();
			if(absent==null){
				member.setAbsent(1);
			}
			else{
				switch (absent){
				case 1:	member.setAbsent(2);break;
				case 2:	member.setAbsent(3);break;
				default: member.setAc_status(ACCOUNTSUSPENDED);break;
				}
			}
			
			
		}
		return result;
	}
	
	
	private static final String CHEF_CHECK = "SELECT * FROM orders WHERE c_id = ? and session = ? and (DATEPART(yy, dineDate) = ? AND DATEPART(mm, dineDate) = ? AND DATEPART(dd, dineDate) = ?)";
	@Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
	@SuppressWarnings("deprecation")
	public List<OrdersBean> checkOrdersForChef(int c_id, String session, String year, String month, String day) {

		Query query = this.getSession().createSQLQuery(CHEF_CHECK);
				query.setParameter(0, c_id);		
				query.setParameter(1, session);
				query.setParameter(2, year);
				query.setParameter(3, month);
				query.setParameter(4, day);
			    
				System.out.println("sql="+CHEF_CHECK);
				System.out.println("c_id="+c_id);
				System.out.println("session="+session);
				System.out.println("year="+year);
				System.out.println("month="+month);
				System.out.println("day="+day);
				
			    return (List<OrdersBean>) query.getResultList();
			    
	}
	
	//Lee添加
	public OrdersBean poreviewin(OrdersBean bean){
		OrdersBean result = null;
		if (bean != null) {
			result = ordersDAO.poreview(bean.getR_message(), bean.getR_stars(),bean.getO_id());
		}
		return result;
	}
	//Shian
	public List<OrdersBean> getMemberOrders (int m_id){
		return ordersDAO.listOrderHistory(m_id);
	}

}
