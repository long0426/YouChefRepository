package controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import model.DishesBean;
import model.DishesService;
import model.misc.LeaveBean;

@Controller
@RequestMapping(path = { "/pages/getMenu.controller" })
public class GetMenuByDidController {
	@Autowired
	DishesService dishesService;
	
	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST })
	public void process(LeaveBean bean, BindingResult bindingResult, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("Start to get menu");
		DishesBean db = dishesService.select(Integer.parseInt(request.getParameter("d_id")));
//		DishesBean db = dishesService.select(5006);
		System.out.println(db);
		try {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(db.getMenu());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
