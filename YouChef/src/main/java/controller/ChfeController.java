package controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import model.ChefBean;
import model.ChefService;
@Controller
@RequestMapping(
		path={"/pages/chef.controller"},
		method={RequestMethod.GET, RequestMethod.POST}
)
public class ChfeController {
	
		@Autowired
		@Resource(name="chefService")
		private ChefService chefService;
		@RequestMapping
		public String service(String chef, ChefBean bean, BindingResult bindingResult, Model model) {
	//接收資料
	//驗證資料
			Map<String, String> errors = new HashMap<String, String>();
			model.addAttribute("errors", errors);
			int id = bean.getC_id();
			if("Insert".equals(chef) || "Update".equals(chef) || "Delete".equals(chef)) {
				if(id==0) {
					errors.put("id", "Please enter ID for "+chef);
				}
			}
			
	//轉換資料
			if(bindingResult!=null) {
				if(bindingResult.getFieldError("c_id")!=null) {
					errors.put("c_id", "請輸入ID");
				}
				if(bindingResult.getFieldError("LastName")!=null) {
					errors.put("LastName", "請輸入姓氏");
				}
				if(bindingResult.getFieldError("FirstName")!=null) {
					errors.put("FirstName", "請輸入名字");
				}
				if(bindingResult.getFieldError("phone")!=null) {
					errors.put("phone", "請輸入電話號碼");
				}
				if(bindingResult.getFieldError("address")!=null) {
					errors.put("address", "請輸入地址");
				}
				if(bindingResult.getFieldError("background")!=null) {
					errors.put("background", "請輸入背景");
				}
			}
			
			if(errors!=null && !errors.isEmpty()) {
				return "chef.error";
			}
			
	//呼叫Model
	//根據Model的執行結果，顯示View
			if("Select".equals(chef)) {
				List<ChefBean> result = chefService.getAll();
				model.addAttribute("getAll", result);
				return "chef.select";
			} else if("Insert".equals(chef)) {
				ChefBean result = chefService.addin(bean);
				if(result==null) {
					errors.put("action", "Insert fail");
				} else {
					model.addAttribute("insert", result);
				}
				return "product.error";
			} else if("Update".equals(chef)) {
				ChefBean result = chefService.update(bean);
				if(result==null) {
					errors.put("action", "Update fail");
				} else {
					model.addAttribute("update", result);
				}
				return "chef.error";
			
			} else  {
				errors.put("action", "Unknown Action:"+chef);
				return "product.error";
			}
		}
		
	
}

