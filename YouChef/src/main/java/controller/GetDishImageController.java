package controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import model.DishPhotoBean;
import model.DishPhotoDAO;
import model.DishPhotoService;
import model.DishesBean;
import model.DishesService;
import model.TypeBean;
import model.TypeService;




@Controller
@RequestMapping(path = { "/getdishImage.controller" })
public class GetDishImageController {

	@Autowired
	DishPhotoService dishPhotoService;
	@Autowired
	DishPhotoDAO dishPhotoDao;

	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST })
	public void process( Model model, HttpServletRequest request, 
			HttpServletResponse response) {
		Integer num = Integer.parseInt(request.getParameter("id")); //d_id
		System.out.println("num= "+num);
		List<DishPhotoBean> photo = dishPhotoDao.selectByDid(num);
			
						byte[] ba = photo.get(0).getD_photo();
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
	

	
		
	
	


