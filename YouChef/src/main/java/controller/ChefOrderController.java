package controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import model.ChefBean;
import model.ChefDAO;
import model.DishesBean;
import model.DishesService;
import model.OrdersService;



@Controller
public class ChefOrderController {
	@Autowired
	@Resource(name="ordersService")
	private OrdersService ordersService;
	
	@Autowired
	private SimpleDateFormat sdfYM;
	@Autowired
	private SimpleDateFormat sdfD;
	@Autowired
	private SimpleDateFormat sdfM;
	@Autowired
	private SimpleDateFormat sdfY;
	@Autowired
	private ChefDAO chefDao;
	
	@Autowired
	DishesService dishesService;
	
	@RequestMapping(value="/chefOrder.controller",
			method={RequestMethod.GET, RequestMethod.POST})
	public String handleChefWithFixedC_id(@RequestParam String c_id, 
			@RequestParam String t_id, Model model, HttpServletRequest request, HttpServletResponse response){
		Date a = new Date();
		String date = sdfD.format(a);
		String month = sdfM.format(a);
		String year = sdfY.format(a);
		Map<String, String> info = new HashMap<String, String>();
		info.put("t_id", t_id);//int
		info.put("c_id", c_id);//int
		info.put("yr", year);
		info.put("month", month);
		info.put("date", date);
		model.addAttribute("data", info); 
		ChefBean chef = chefDao.select(Integer.valueOf(c_id));
		model.addAttribute("chef", chef);
		
		
		List<Object[]> ob = dishesService.selectType(Integer.parseInt(request.getParameter("t_id")));//t_id

		List <DishesBean> list = new ArrayList();
		for (Object[] row: ob){
			for(Object item :row){
				if(item instanceof DishesBean){
					list.add((DishesBean)item);
					System.out.println(((DishesBean) item).getD_id());
				}
			}

			}
		
		System.out.println("fff"+list);
		model.addAttribute("ob", list);

		
		
//		return "chef.show";
		return "ChefCart.show";
	
	
	
}
}
