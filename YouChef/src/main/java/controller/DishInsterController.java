package controller;



import java.io.IOException;
import java.util.HashMap;
import java.util.Map;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import model.DishPhotoBean;
import model.DishPhotoService;
import model.DishesBean;
import model.DishesService;
import model.TypeBean;
import model.TypeService;

@Controller
public class DishInsterController {
	@Autowired
	DishesService dishesService;
	@Autowired
	DishPhotoService dishPhotoService;
	@Autowired
	TypeService typeService;

	
	
	@RequestMapping(path={"/dishdisplay/dishinsert.controller"},
			method={RequestMethod.GET, RequestMethod.POST}
			)
	
	public String DishInsterService(@RequestParam(value = "file", required = false) MultipartFile file,
									@RequestParam(name="t_id2") String temp2,
									 DishesBean dishes, DishPhotoBean dishPhotoBean,
									Model model, MultipartHttpServletRequest request){
		System.out.println(dishes.getD_id());
		System.out.println(dishes);
		int t_id2=0;
		t_id2=Integer.parseInt(temp2);
		Map<String, String> errors = new HashMap<String, String>();
		model.addAttribute("errors", errors);
		int id=0;
		try {
			id = dishes.getD_id();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		if("Update".equals(dishes)) {
			if(id==0) {
				errors.put("id", "請輸入ID "+dishes);
			}
		}
			if(dishes.getD_name()==null||dishes.getD_name()==""){
				errors.put("菜名","1.不可空白，2.格式不符合, 請重新輸入");
			}
			if(dishes.getTypeBean()==null){
				errors.put("菜系","1.不可空白，2.格式不符合, 請重新輸入");
			}
			if(dishes.getPrice()==0){
				errors.put("售價","1.不可空白，2.格式不符合, 請重新輸入");
			}
			if(dishes.getD_status()==null){
				errors.put("狀態","請重新輸入");
			}
			if(dishes.getD_briefing()==null||dishes.getD_briefing()==""){
				errors.put("簡介","1.不可空白，2.格式不符合, 請重新輸入");
			}
//			model.addAttribute("DishInster","新增成功");
			TypeBean typeBean = typeService.select(t_id2);
			DishesBean dishesBean = new DishesBean();
			dishesBean.setD_name(dishes.getD_name());
			dishesBean.setTypeBean(typeBean);
			dishesBean.setPrice(dishes.getPrice());
			dishesBean.setD_status(dishes.getD_status());
			dishesBean.setMenu(dishes.getMenu());
			dishesBean.setD_briefing(dishes.getD_briefing());
			DishesBean db = dishesService.addin(dishesBean);
			System.out.println(db);
			if (null != db) { // 餐點新增成功
				
				try {
					
						DishPhotoBean dpb = new DishPhotoBean();
						dpb.setD_photo(file.getBytes());
						dpb.setDishesBean(db);
						dishPhotoService.upload(dpb);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("新增餐點照片失敗");
				}
			}else{
				System.out.println("新增餐點失敗");
			}
				
			
			
			System.out.println("dishesBean"+dishesBean);
			System.out.println("dishPhotoBean"+dishPhotoBean.getD_photo());
			
		
			
			return "Dishsuccess";
}
	
	@RequestMapping(path={"/dishdisplay/dishupdate.controller"},
			method={RequestMethod.GET, RequestMethod.POST}
			)
	public String DishUpdateService(@RequestParam(value = "file", required = false) MultipartFile file,
			@RequestParam(name="t_id2") String temp1,
			 DishesBean dishes, DishPhotoBean dishPhotoBean,
			Model model, MultipartHttpServletRequest request){
		
		int id = dishes.getD_id();
		int t_id=0;
		t_id=Integer.parseInt(temp1);
		System.out.println("dishes"+dishes);
		DishesBean dbu =dishesService.select(id);
		DishPhotoBean dpb = new DishPhotoBean();
		TypeBean typebean =typeService.select(t_id);
		DishesBean db = new DishesBean();
		if(dbu !=null){
			db = dishesService.updatedish(dbu, dishes.getD_name(), dishes.getD_briefing(), dishes.getPrice(), typebean, dishes.getD_status());
			System.out.println("db"+db);
		if (null != db) { 
			try {
	
					dpb = dishPhotoService.selectByPrimary(db.getD_id());
					dpb.setD_photo(file.getBytes());
					dpb.setDishesBean(db);
					dishPhotoService.update(dpb);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("更新餐點照片失敗");
			}
			}
		}else{
			System.out.println("更新餐點");
		}
		return "Dishupsuccess";
}
}