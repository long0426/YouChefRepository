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

import model.ChefBean;
import model.ChefService;
import model.DishPhotoBean;
import model.DishesBean;
import model.TypeBean;
import model.TypeService;
@Controller

public class ChefInsertController {
	@Autowired
	ChefService chefService;
	@Autowired
	TypeService typeService;
	
	@RequestMapping(
			path={"/chefdisplay/chef.controller"},
			method={RequestMethod.GET, RequestMethod.POST}
	)
	public String service(@RequestParam(value = "file", required = false) MultipartFile file,
						  @RequestParam(name="t_id") String temp1,
			ChefBean chef, Model model,MultipartHttpServletRequest request) {
		int t_id=0;
		t_id=Integer.parseInt(temp1);
		Map<String, String> errors = new HashMap<String, String>();
		model.addAttribute("errors", errors);
		
		System.out.println(chef);
		System.out.println(file);
		

			if(null==chef.getLastName()|| "".equals(chef.getLastName())) {
				errors.put("LastName", "請輸入姓氏");
			}
			if(null==chef.getFirstName()|| "".equals(chef.getFirstName())) {
				errors.put("FirstName", "請輸入名字");
			}
			if(null ==chef.getPhone()|| "".equals(chef.getPhone())) {
				errors.put("phone", "請輸入電話號碼");
			}
			if(null==chef.getAddress()||"".equals(chef.getAddress())) {
				errors.put("address", "請輸入地址");
			}
			if(null==chef.getBackground()||"".equals(chef.getBackground())) {
				errors.put("background", "請輸入背景");
			}
			if(null==chef.getC_status()||"".equals(chef.getC_status())) {
				errors.put("status", "請輸入狀態");
			}
			if(null==chef.getSex()||"".equals(chef.getSex())) {
				errors.put("Sex", "請輸入性別");
			}
			if(null==chef.getTypeBean()||"".equals(chef.getTypeBean())) {
				errors.put("Type", "請輸入菜系");
			}


		System.out.println("123abc");

		TypeBean typeBean = typeService.select(t_id);

		ChefBean chefbean =new ChefBean();

		chefbean.setAddress(chef.getAddress());
		chefbean.setBackground(chef.getBackground());
		chefbean.setC_status(chef.getC_status());
		chefbean.setFirstName(chef.getFirstName());
		chefbean.setLastName(chef.getLastName());
		chefbean.setPhone(chef.getPhone());
		chefbean.setTypeBean(typeBean);
		chefbean.setSex(chef.getSex());
		chefbean.setYears(chef.getYears());
		try {
			chefbean.setPhoto(file.getBytes());
			System.out.println(chefbean.getPhoto());
			;
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		chefService.addin(chefbean);
		
		return " chefsuccess";
		}

	@RequestMapping(path={"/chefdisplay/chefupdate.controller"},
			method={RequestMethod.GET, RequestMethod.POST}
			)
	public String ChefUpdateService(@RequestParam(value = "file", required = false) MultipartFile file,
			@RequestParam(name="t_id2") String temp1,
	 ChefBean chef,Model model, MultipartHttpServletRequest request){

int id = chef.getC_id();
int t_id=0;
t_id=Integer.parseInt(temp1);
ChefBean cbu =chefService.select(id);
TypeBean typebean =typeService.select(t_id);
if(cbu !=null){
	try {
		chefService.update(cbu,chef.getFirstName(), chef.getLastName(),chef.getSex(),chef.getPhone(), chef.getAddress(), 
				typebean, chef.getC_status(), chef.getBackground(),chef.getYears(),file.getBytes());
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}	return "Chefupsuccess";
	}
}

	


	
