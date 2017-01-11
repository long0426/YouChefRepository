package controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import model.OrdersService;


@Controller
public class ShowMchefCalendar {
	@Autowired
	@Resource(name="ordersService")
	private OrdersService ordersService;
 
	@RequestMapping(value="/handleAjaxForMchef.controller")
	@ResponseBody
	public String handleAjax(@RequestParam String mc_id, @RequestParam String theMonth,
			@RequestParam String date){

		System.out.println("mc_id= "+mc_id);
		System.out.println("month= "+ theMonth);
		System.out.println("date= "+date);
		String ok = ordersService.showMChefDate(Integer.valueOf(mc_id),theMonth, date);
		System.out.println("ok= "+ok);
		return ok;


	}

}
