package model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public interface OrdersDAO {

	OrdersBean insert(OrdersBean ordersBean);

	OrdersBean updateAll(ChefBean chefBean, MchefBean mchefBean,Date orderDate,String session,Float totalPrice,Timestamp dineDate,String dinePlace,Integer people,Timestamp updateTime, Integer o_id);
	
	OrdersBean updatePlace(String dinePlace, Date orderDate, Timestamp updateTime, Integer o_id);
	
	OrdersBean cancel(String o_status,Timestamp updateTime, Integer o_id);
	
	OrdersBean complete(String o_status,Timestamp updateTime, Integer o_id);
	
	OrdersBean findByPrimaryKey(int o_id);

	List<OrdersBean> getAll();

	List<OrdersBean> selectlistReviewMember(int c_id);

}