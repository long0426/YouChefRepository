package controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import model.DiscussBean;
import model.MemberBean;
import model.OrdersBean;
import model.OrdersService;
import model.ReviewBean;
import model.ReviewService;

@Controller
@RequestMapping(path = {"/review"})
public class ReviewController {
	@Autowired
	ReviewService reviewService;
	@Autowired
	OrdersService ordersService;
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		System.out.println("Start to initbinder");
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
	}
	@RequestMapping(path={"/updateorderreview.controller"},
			method = {RequestMethod.GET, RequestMethod.POST},
			produces="text/html;charset=utf-8")
	public String insertprocess(
			@RequestParam(name="r_message")String r_message, 
			@RequestParam(name="r_stars")String rstars,
			@RequestParam(name="o_id")String oid,
			Model model, HttpSession session) throws InterruptedException {
	MemberBean writer =(MemberBean) session.getAttribute("user");
	Map<String,String> errors = new HashMap<String,String>();
	model.addAttribute("errors",errors);
	if(r_message==null || r_message.length()==0 || rstars==null || rstars.length()==0){
		errors.put("reviewcontent", "請輸入評價");
	}
	if(errors!=null && !errors.isEmpty()){
		return "reviewPage";
	}
	OrdersBean bean = new OrdersBean();
	bean.setR_message(r_message);
	float r_stars = Float.parseFloat("stars");
	bean.setR_stars(r_stars);
	int o_id = Integer.parseInt(oid);
	bean.setO_id(o_id);
	ordersService.poreviewin(bean);
	session.setAttribute("reviewbean", bean);
	return "login.success";
	}
}
