package model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="orderDetailService")
public class OrderDetailService {

	@Autowired
	private OrderDetailDAO orderDetailDAO;
	

	
	public OrderDetailBean placeNewOrderDetail(OrderDetailBean bean){
		OrderDetailBean result = null;
		if (bean != null) {
			result = orderDetailDAO.insert(bean);	
			}
		return result;
		
	}



	
	
}

	
	

