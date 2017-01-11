package controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import model.Cart;
import model.CartWrapper;
import model.ChefDAO;
import model.DishesBean;
import model.DishesDAO;
import model.MchefBean;
import model.MchefDAO;
import model.MemberBean;
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
public class AddToCartController {
	@Autowired
	private OrdersService ordersService;
	@Autowired
	private OrderDetailService orderDetailService;
	@Autowired
	private OrderDishesService orderDishesService;
		
	@Autowired
	private RickyService rickyService;
	
	@Autowired
	private DishesDAO dishesDao;
	@Autowired
	private MchefDAO mchefDao;
	@Autowired
	private ChefDAO chefDao;
	@Autowired
	private MemberDAO memberDAO;
	@Autowired
	private OrderDishesDAO orderDishesDAO;
	
		@RequestMapping(value="/AddToCart.controller")
		@ResponseBody
		public String confirmOrderAjax( 
				@RequestBody List<Cart> wrapper,
				Model model, 
				HttpSession session){
			//c_id + "," + orderDate + "," + selectedSession + "," + DateAndTime + "," + dinePlace +"," + m_id;
			//3003,201701,中午,01/03/2017/12:00pm,大安區
			String [] property = wrapper.get(0).getSummary();
			int c_id = Integer.valueOf(property[0]);
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
			System.out.print(dateFormat.format(parsedDate));
			dineDate = new java.sql.Timestamp(parsedDate.getTime());
			}catch(Exception e){
			 e.printStackTrace();
			}		
			String dinePlace = property[4];
			int m_id = Integer.valueOf(property[5]);
			float totalPrice = 0;
			List<OrderDishesBean> holder = new ArrayList();
			int count = 0;
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
				holder.add(odb);
				}
			}
			
			String o_status ="0";
			Timestamp updateTime = new java.sql.Timestamp(now.getTime());
			Integer people = 1;
			//先確認有無重複下訂單的情況
			//01/03/2017/12:00pm
			String checkYear = property[3].substring(6, 10);
			String checkMonth = property[3].substring(0, 2);
			String checkDay = property[3].substring(3, 5);
			List <OrdersBean> checking = ordersService.checkOrdersForChef(c_id,sess,checkYear,checkMonth, checkDay);
			System.out.println("check order size:"+ checking.size());
			if(!checking.isEmpty()){
				Map <String, String> error = new HashMap();
				error.put("duplicate", "duplicate");
				String jsonString = JSONValue.toJSONString(error);
				return jsonString;
			}

			OrdersBean ob = new OrdersBean();			
			ob.setChefBean(chefDao.select(c_id));
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
//(將m_id,c_id,orderDate,session,totalPrice,dineDate,dinePlace,o_status,people,updateTime 封裝給orderBean)
	
//			OrdersBean order = ordersService.placeNewOrder(ob);
//			OrderDetailBean detail = orderDetailService.insertNewOrderDetail(order);
//			orderDishesService.insertNewOrderDetailDishes(detail, holder);
//			ordersService.updateChefShift(order);
			rickyService.placeNewChefOrder(ob, holder);
			
			System.out.println("ob= "+ob);
			
			Map <String, String> success = new HashMap();
			success.put("success", "ok");
			String jsonString = JSONValue.toJSONString(success);
			return jsonString;

			
			


	
	
	
}
}
