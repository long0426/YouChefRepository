package controller;

import java.io.IOException;
import java.io.OutputStream;
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
@RequestMapping(path = { "/dishdisplay/dishesImage.controller" })
public class DishesImageController {
	@Autowired
	DishPhotoService dishPhotoService;
	
	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST })
	public void process( BindingResult bindingResult, Model model, HttpServletRequest request, HttpServletResponse response) {
		
		DishPhotoBean db = (DishPhotoBean) dishPhotoService.selectByDid(Integer.parseInt(request.getParameter("id")));
		
		byte[] ba = db.getD_photo();
		response.setContentType("image/jpeg");
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			os.write(ba);
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}