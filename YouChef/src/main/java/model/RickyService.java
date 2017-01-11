package model;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value="rickyService")
@Transactional(transactionManager="transactionManager")
public class RickyService {
	
	@Autowired
	private OrdersDAO ordersDAO;

	
	@Autowired
	private OrderDetailService orderDetailService;
	
	@Autowired
	private OrderDishesService orderDishesService;
	
	@Autowired
	private OrdersService ordersService;
	
	@Transactional
	public OrdersBean placeNewChefOrder(OrdersBean bean, List<OrderDishesBean> orderDishes){
		OrdersBean result = null;
		if (bean != null) {
			result = ordersDAO.insert(bean);
			if(result!= null){
				OrderDetailBean detail = orderDetailService.insertNewOrderDetail(result);
				if(detail!=null){
					List <OrderDishesBean> db = orderDishesService.insertNewOrderDetailDishes(detail,orderDishes);
					if(db!=null){
						OrdersBean check = ordersService.updateChefShift(result);
						if(check!=null){
							result = check;
						}
					}
				}
			}
		}
		return result;	
	}


	@Transactional
	public OrdersBean placeNewMChefOrder(OrdersBean bean, List<OrderDishesBean> orderDishes){
		OrdersBean result = null;
		if (bean != null) {
			result = ordersDAO.insert(bean);
			if(result!=null){
				OrderDetailBean detail = orderDetailService.insertNewOrderDetail(result);
				if(detail!=null){
					List <OrderDishesBean> db = orderDishesService.insertNewOrderDetailDishes(detail,orderDishes);
					if(db!=null){
						OrdersBean check = ordersService.updateMChefShift(result);
						if(check!=null){
							result = check;
						}
					}
				}
			}
		}
		return result;	
	}
	}

