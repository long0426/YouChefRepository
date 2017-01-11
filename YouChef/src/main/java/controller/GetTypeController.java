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

import model.TypeBean;
import model.TypeService;

@Controller
@RequestMapping(path = { "/pages/gettype.controller" })
public class GetTypeController {
	@Autowired
	TypeService typeService;
	
	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST })
	public void process(Model model, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		List<TypeBean> list = typeService.selectAll();
//		System.out.println(list);
		StringBuilder jsonInString = new StringBuilder();
		jsonInString.append("[");
		for(TypeBean tb : list){
			jsonInString.append("{\"t_id\":" + tb.getT_id() + ",\"t_name\":\"" + tb.getT_name() + "\"},");
		}
		String data = jsonInString.substring(0, jsonInString.length()-1) + "]";
//		System.out.println(data);
		response.setContentType("application/json;chartset=UTF-8");
		response.getWriter().write(data);
	}
}
