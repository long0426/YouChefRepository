package controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import model.DishesBean;
import model.DishesService;

@Controller
public class DishesViewController {
	@Autowired
	DishesService dishesService;
	@RequestMapping(
			path={"/dishdisplay/dishesview.controller"},
			method={RequestMethod.GET, RequestMethod.POST}
		)
	public String service(
			Model model,
			HttpSession session
		){
		List<DishesBean> selectAll = (List<DishesBean>) dishesService.selectAll();
		model.addAttribute("list",selectAll);
	return "dishesview.selectAll";
	}

	
}

	
