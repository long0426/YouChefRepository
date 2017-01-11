package model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public interface OrdersDAO {

	OrdersBean insert(OrdersBean ordersBean);

	OrdersBean updateAll(ChefBean chefBean, MchefBean mchefBean,Date orderDate,String session,Float totalPrice,Timestamp dineDate,String dinePlace,Integer people,Timestamp updateTime, Integer o_id);
	
	OrdersBean updatePlace(String dinePlace, Date orderDate, Timestamp updateTime, Integer o_id);
	
	OrdersBean cancel(Timestamp updateTime, Integer o_id);
	
	OrdersBean complete(Timestamp updateTime, Integer o_id);
	
	OrdersBean standUp(Timestamp updateTime, Integer o_id);
	
	OrdersBean findByPrimaryKey(int o_id);
	
	List<OrdersBean> listOrderHistory(int m_id);

	List<OrdersBean> getAll();
	//Lee添加
	OrdersBean poreview(String r_message,float r_stars,Integer o_id);

}