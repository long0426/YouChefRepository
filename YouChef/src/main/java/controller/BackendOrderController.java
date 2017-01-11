package controller;

import java.lang.reflect.Member;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import model.OrdersBean;
import model.OrdersDAO;
import model.OrdersService;


@Controller
public class BackendOrderController {
	@Autowired
	private OrdersDAO ordersDAO;
	
	@Autowired
	private OrdersService ordersService;
	
	@RequestMapping(value="/backEndOrder.controller")
	public String listAll( Model model) {
		List<OrdersBean> all = ordersDAO.getAll();
		model.addAttribute("ordersAll", all);
		return "orders.show";

		
		}
	//預設0  取消1 已結帳2 放鳥 3 
	@RequestMapping(value="/maintain.controller")
	public String update( String o_id, String o_status,Model model) {		
		OrdersBean single = ordersDAO.findByPrimaryKey(Integer.valueOf(o_id));	
		single.setO_status(o_status);
		if (o_status.equals("2")){
			ordersService.completeOrder(single);
		}
		else if (o_status.equals("3")){
			ordersService.standUpOrder(single);
		}
	
		List<OrdersBean> all = ordersDAO.getAll();
		model.addAttribute("ordersAll", all);
		return "orders.show";

		
		}
	
	@RequestMapping (value="/memberOrders.controller")
	public String OrderHistory (String m_id, Model model, HttpSession session){
		List<OrdersBean> result= ordersService.getMemberOrders(Integer.valueOf(m_id));
		session.setAttribute("memOrdersAll", result);
		return "memorders.show";
	}
	
}

