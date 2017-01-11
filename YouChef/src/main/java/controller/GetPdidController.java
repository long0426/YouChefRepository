package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import model.DishPhotoBean;
import model.DishPhotoService;
import model.misc.LeaveBean;

@Controller
@RequestMapping(path = { "/pages/getpdid.controller" })
public class GetPdidController {
	@Autowired
	DishPhotoService dishPhotoService;
	
	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST })
	public String process(LeaveBean bean, BindingResult bindingResult, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		String d_id = request.getParameter("d_id");
		System.out.println("d_id = " + d_id);
		List<DishPhotoBean> list = dishPhotoService.selectByDid(Integer.parseInt(d_id));
		model.addAttribute("list", list);
		return "dish.detail";
	}
}
