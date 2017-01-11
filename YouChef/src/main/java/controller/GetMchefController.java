package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import model.DishesBean;
import model.MchefBean;
import model.MchefService;
import model.misc.LeaveBean;

@Controller
@RequestMapping(path = { "/pages/getmchef.controller" })
public class GetMchefController {
	@Autowired
	MchefService mchefService;

	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST })
	public String process(LeaveBean lb, BindingResult bindingResult, Model model, HttpServletRequest request) {
		Map<String, String> errors = new HashMap<String, String>();
		model.addAttribute("errors", errors);
		if (null != request.getParameter("mc_id")) {
			MchefBean bean = mchefService.select(Integer.parseInt(request.getParameter("mc_id")));
			HttpSession session = request.getSession();
			session.setAttribute("bean", bean);
			return "mchefcalendar";
		} else {
			List<MchefBean> li = mchefService.selectAll();
			List<DishesBean> list = new ArrayList<>();
			for(MchefBean mb : li){
				Set<DishesBean> set = mb.getDishesBean();
				Iterator<DishesBean> it = set.iterator();
				while(it.hasNext()){
					list.add((DishesBean)it.next());
				}
			}
			Collections.sort(list, new Comparator<DishesBean>() {
		        @Override public int compare(DishesBean b1, DishesBean b2) {
		            return b2.getD_id().compareTo(b1.getD_id());
		        }

		    });
			HttpSession session = request.getSession();
			session.setAttribute("list", list);
			return "mchefdishdisplay";
		}
	}
}
