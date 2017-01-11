package model;

import java.util.List;

public interface OrderDishesDAO {
	OrderDishesBean insert(OrderDishesBean orderDishesBean);
	
	OrderDishesBean update(OrderDetailBean orderDetailBean, DishesBean dishesBean, Integer dishNum, int ods_id);
	
	OrderDishesBean findByPrimaryKey(int ods_id);
	
	List<OrderDishesBean> listByOrderDetailId(int od_id);

	List<OrderDishesBean> getAll();
}
