package controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.CalendarBean;
import model.CalendarDAO;
import model.Cart;
import model.ChefDAO;
import model.DishesBean;
import model.DishesDAO;
import model.MchefDAO;
import model.MemberDAO;
import model.OrderDetailBean;
import model.OrderDetailService;
import model.OrderDishesBean;
import model.OrderDishesDAO;
import model.OrderDishesService;
import model.OrdersBean;
import model.OrdersService;
import model.RickyService;

@Controller
public class AddToMCartController {
	@Autowired
	private OrdersService ordersService;
	@Autowired
	private OrderDetailService orderDetailService;
	@Autowired
	private OrderDishesService orderDishesService;
	@Autowired
	private DishesDAO dishesDao;
	@Autowired
	private MchefDAO mchefDao;
	@Autowired
	private MemberDAO memberDAO;
	@Autowired
	private OrderDishesDAO orderDishesDAO;
	@Autowired
	private RickyService rickyService;
	@Autowired
	private CalendarDAO calendarDao;
	
	
		@RequestMapping(value="/AddToMCart.controller")
		@ResponseBody
		public String confirmOrderAjax( 
				@RequestBody List<Cart> wrapper,
				Model model, 
				HttpSession session){
//mc_id + "," + orderDate + "," + selectedSession + "," + DateAndTime + "," + dinePlace +"," + m_id + "," maxNum;
			//3003,201701,中午,01/03/2017/12:00pm,大安區
			String [] property = wrapper.get(0).getSummary();
			int mc_id = Integer.valueOf(property[0]);
			Date now = new Date(System.currentTimeMillis());
			Date orderDate = now;   //orderDate由後端處理
			String sess = property[2];
			System.out.println("sess= "+sess);
			if (sess.equalsIgnoreCase("中午")){
				sess = "1";
			}else{
				sess = "2";
			};
			Timestamp dineDate = null;
			String convert ="";
			if(property[3].substring(3, 4).equalsIgnoreCase("0")){
				convert = property[3].substring(0, 3) + property[3].substring(4);
			 //01/9/2017/12:00 pm
			}
			else{
				convert = property[3].substring(0, 17);
			}

			try{
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy/HH:mm");
			Date parsedDate = dateFormat.parse(convert);
			dineDate = new java.sql.Timestamp(parsedDate.getTime());
			}catch(Exception e){
			 e.printStackTrace();
			}		
			String dinePlace = property[4];
			int m_id = Integer.valueOf(property[5]);
			float totalPrice = 0;
			List<OrderDishesBean> holder = new ArrayList();
			int count = 0;
			Integer people = 0;
			for (Cart cart: wrapper){
				count++;
				if(count>1){
				int d_id = cart.getId();
				int price = cart.getPrice();
				int qty = cart.getQuantity();
				totalPrice += price*qty;		
				DishesBean dish = dishesDao.selectDishById(d_id);
				OrderDishesBean odb = new OrderDishesBean();
				odb.setDishesBean(dish);
				odb.setDishNum(qty);
				people = qty;
				holder.add(odb);
				}
			}
			
			String o_status ="0";
			Timestamp updateTime = new java.sql.Timestamp(now.getTime());
			
			//先確認有無重複下訂單的情況
			//01/03/2017/12:00pm
			String checkYear = property[3].substring(6, 10);//2017
			String checkMonth = property[3].substring(0, 2);//01
			String checkDay ="";
			if(property[3].substring(3, 4).equalsIgnoreCase("0")){
				checkDay = property[3].substring(4, 5);
			 //  01/09/2017/12:00 pm
			}
			else{
				checkDay = property[3].substring(3, 5);
			//  01/19/2017/12:00 pm
			}
			
			String yearMonth = checkYear + checkMonth;
			CalendarBean checkingCal = calendarDao.selectMchef(mc_id, yearMonth);
			int checking = ordersService.simpleGet(checkingCal, checkDay);
			System.out.println("check Mchef dishNum:"+ checking);
			int maxNum = Integer.valueOf(property[6]);
			if(checking!=maxNum){
				Map <String, String> error = new HashMap();
				error.put("duplicate", "duplicate");
				String jsonString = JSONValue.toJSONString(error);
				return jsonString;
			}
			
			
			OrdersBean ob = new OrdersBean();
			ob.setMchefBean(mchefDao.findByPrimaryKey(mc_id));
			ob.setDineDate(dineDate);
			ob.setDinePlace(dinePlace);
			ob.setMemberBean(memberDAO.select(m_id));
			ob.setO_status(o_status);
			java.sql.Date sqlDate = new java.sql.Date(orderDate.getTime());
			ob.setOrderDate(sqlDate);
			ob.setPeople(people);
			ob.setTotalPrice(totalPrice);
			ob.setUpdateTime(updateTime);
			ob.setR_stars(0F);
			ob.setR_message("N");
			ob.setSession(sess);
//(將m_id,mc_id,orderDate,session,totalPrice,dineDate,dinePlace,o_status,people,updateTime 封裝給orderBean)
	
//			OrdersBean order = ordersService.placeNewOrder(ob);
//			OrderDetailBean detail = orderDetailService.insertNewOrderDetail(order);
//			OrderDishesBean odb = new OrderDishesBean();
//			orderDishesService.insertNewOrderDetailDishes(detail, holder);
//			ordersService.updateMChefShift(order);
			
			rickyService.placeNewMChefOrder(ob, holder);
			Map <String, String> success = new HashMap();
			success.put("success", "ok");
			String jsonString = JSONValue.toJSONString(success);
			return jsonString;


	
	
	
}
}


