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

import model.ChefBean;
import model.ChefDAO;
import model.DishesBean;
import model.DishesService;
import model.MchefBean;
import model.MchefDAO;
import model.MemberBean;
import model.MemberDAO;
import model.OrdersService;
@Controller
public class MChefOrderController {
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
	private MchefDAO mchefDao;
	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	DishesService dishesService;
	
	
	@RequestMapping(value="/mchefOrder.controller",
			method={RequestMethod.GET, RequestMethod.POST})
	public String handleAjax(@RequestParam String mc_id, Model model,
			HttpServletRequest request, HttpServletResponse response){
	
		Date a = new Date();
		String date = sdfD.format(a);
		String month = sdfM.format(a);
		String year = sdfY.format(a);
		Map<String, String> info = new HashMap<String, String>();
		info.put("mc_id", mc_id);
		info.put("yr", year);
		info.put("month", month);
		info.put("date", date);
		model.addAttribute("data", info); 
		MchefBean mchef = mchefDao.select(Integer.valueOf(mc_id));
		model.addAttribute("mchef", mchef); 
		MemberBean member = memberDAO.select(Integer.valueOf(mc_id));//int
		System.out.println("member= "+ member);
		model.addAttribute("member", member); 

		List<Object[]> ob = dishesService.selectMchef(Integer.parseInt(request.getParameter("mc_id")));
		List <DishesBean> list = new ArrayList();
		System.out.println(ob.size());
		Object [] single = ob.get(0);
		list.add((DishesBean)single[0]);

		System.out.println("fff"+list);
		model.addAttribute("ob", list);
		
		
		return "MchefCart.show";
		//return "mchef.show";
	
	
	
}
}


