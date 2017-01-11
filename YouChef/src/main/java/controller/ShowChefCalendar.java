package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import model.OrdersService;


@Controller
public class ShowChefCalendar {
	@Autowired
	@Resource(name="ordersService")
	private OrdersService ordersService;
 
	@RequestMapping(value="/handleAjaxForChef.controller")
	@ResponseBody
	public String handleAjax(@RequestParam String c_id, @RequestParam String theMonth,
			@RequestParam String date){
	  
        
		System.out.println("id= "+c_id);
		System.out.println("month= "+ theMonth);
		System.out.println("date= "+date);
		String ok = ordersService.showChefDate(Integer.valueOf(c_id), theMonth, date);
		System.out.println("ok= "+ok);
		return ok;


	}




}
