package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import model.CalendarBean;
import model.CalendarService;
import model.OrdersService;

@Controller
public class DishNumAjaxForMchefController {

	@Autowired
	@Resource(name="ordersService")
	private OrdersService ordersService;
	@Autowired
	private CalendarService calendarService;
	
	@RequestMapping(value="/dishesNumAjaxForMchef.controller")
	@ResponseBody
	public String handleAjax(@RequestParam String mc_id,
			@RequestParam String dateText, @RequestParam String theMonth){
		String selectedDate;	
		if(dateText.substring(3, 4).equalsIgnoreCase("0")){
			selectedDate = dateText.substring(4, 5); //  01/09/2017
			System.out.println("dddddd= "+ selectedDate);		
		}
		else{
			selectedDate = dateText.substring(3, 5); //  01/10/2017
		}
		CalendarBean calendar = calendarService.selectMchef(Integer.valueOf(mc_id), theMonth);
		int dishesMaxNum = ordersService.simpleGet(calendar, selectedDate);
		System.out.println("DishesMAxNum= "+ dishesMaxNum);		
		Map <String, Integer> obj = new HashMap();
		obj.put("dishesMaxNum", dishesMaxNum);
		String jsonString = JSONValue.toJSONString(obj);
		return jsonString;
		


	}
	
}
