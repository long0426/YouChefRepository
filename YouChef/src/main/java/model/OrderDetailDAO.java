package model;

import java.sql.Timestamp;
import java.util.List;

public interface OrderDetailDAO {
	OrderDetailBean insert(OrderDetailBean orderDetailBean);
	
	OrderDetailBean update(OrdersBean ordersBean, int od_id);
	
	OrderDetailBean findByPrimaryKey(int od_id);

	List<OrderDetailBean> getAll();
}
