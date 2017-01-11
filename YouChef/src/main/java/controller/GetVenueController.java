package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import model.VenueBean;
import model.VenueService;

@Controller
@RequestMapping(path = { "/pages/getvenue.controller" })
public class GetVenueController {
	@Autowired
	VenueService venueService;
	
	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST })
	public void process(Model model, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		List<VenueBean> listv = venueService.selectAll();
//		System.out.println(list);
		StringBuilder jsonInString = new StringBuilder();
		jsonInString.append("[");
		for(VenueBean vb : listv){
			jsonInString.append("{\"v_id\":" + vb.getV_id() + ",\"v_name\":\"" + vb.getV_name() + "\",\"v_address\":\"" + vb.getV_address() + "\"},");
		}
		String datav = jsonInString.substring(0, jsonInString.length()-1) + "]";
//		System.out.println(datav);
		response.setContentType("application/json;chartset=UTF-8");
		response.getWriter().write(datav);
	}
	
}
