package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import model.DishPhotoBean;
import model.DishPhotoService;
import model.DishesBean;
import model.DishesService;
import model.MchefBean;
import model.MchefService;
import model.MemberBean;
import model.MemberService;
import model.TypeService;
import model.VenueService;
import model.misc.MchefApplyBean;

@Controller
@RequestMapping(path = { "/pages/mchefapply.controller" })
public class MchefApplyController {
	@Autowired
	MchefService mchefService;
	@Autowired
	MemberService memberService;
	@Autowired
	VenueService venueService;
	@Autowired
	DishesService dishesService;
	@Autowired
	TypeService typeService;
	@Autowired
	DishPhotoService dishPhotoService;
	@Autowired
	MchefBean mchefBean;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
//		binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
		binder.registerCustomEditor(double.class, new CustomNumberEditor(Double.class, true));
		binder.registerCustomEditor(int.class, new CustomNumberEditor(Integer.class, true));
	}

	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST })
	public String process(@RequestParam(value = "photo", required = false) MultipartFile file,
			@RequestParam(value = "d_photo", required = false) MultipartFile[] files, MchefApplyBean mab,
			BindingResult bindingResult, Model model, MultipartHttpServletRequest request, HttpSession session) {
		Map<String, String> errors = new HashMap<String, String>();
		model.addAttribute("errors", errors);
		// System.out.println(formData);
		System.out.println("file = " + file);
		System.out.println("files = " + files);
		System.out.println("mab = " + mab);
		System.out.println("menu = " + mab.getMenu());
		System.out.println("m_id = " + mab.getM_id());
		System.out.println("email = " + mab.getEmail());
		
		model.addAttribute("email", mab.getEmail());
		model.addAttribute("name", mab.getName());
		model.addAttribute("m_id", mab.getM_id());
		
		if (null == mab.getBackground() || "".equals(mab.getBackground())) {
			errors.put("background", "請輸入簡介！");
		}
		if (null == mab.getD_name() || "".equals(mab.getD_name())) {
			errors.put("d_name", "請輸入餐點名稱！");
		}
		if (null == mab.getD_briefing() || "".equals(mab.getD_briefing())) {
			errors.put("d_briefing", "請輸入餐點簡介！");
		}
		if (null == mab.getMenu() || "".equals(mab.getMenu())) {
			errors.put("menu", "請輸入菜單！");
		}
		if (0 == mab.getPrice()) {
			errors.put("price", "請輸入價格！");
		}
		if (0 == mab.getQuota()) {
			errors.put("quota", "請選擇人數上限！");
		}
		if (null == mab.getVenue() || "".equals(mab.getVenue())) {
			errors.put("venue", "請輸入用餐地址！");
		}
		
		if(!errors.isEmpty()){
			System.out.println("quota = " + errors.get("quota"));
			return "mchefapply";
		}
		
		MemberBean mb = memberService.select(mab.getM_id());
		mb.setAc_status("1");
//		MchefBean mchefBean = new MchefBean();
		mchefBean.setMc_id(mab.getM_id());
		mchefBean.setMemberBean(mb);
		mchefBean.setYears(mab.getYears());
		mchefBean.setVenue(mab.getVenue());
		mchefBean.setQuota(mab.getQuota());
		mchefBean.setBackground(mab.getBackground());
		System.out.println(mchefBean);
		if (0 != mab.getV_id()) {
			mchefBean.setV_id(venueService.select(mab.getV_id()));
			mchefBean.setHasPlace("0");
		} else {
			mchefBean.setHasPlace("1");
		}

		if (null != mchefService.apply(mab.getM_id(), mchefBean) && !file.isEmpty()) { // 會員大廚新增成功
			try {
				mb.setAc_status("1");
				mb.setPhoto(file.getBytes());
				memberService.update(mb); // 會員照片更新成功
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("更新會員照片失敗");
			}
		} else {
			System.out.println("會員大廚新增失敗");
		}
		DishesBean dishesBean = new DishesBean();
		dishesBean.setMchefBean(mchefBean);
		dishesBean.setPrice(mab.getPrice());
		dishesBean.setD_name(mab.getD_name());
		dishesBean.setD_briefing(mab.getD_briefing());
		dishesBean.setMenu(mab.getMenu());
		dishesBean.setTypeBean(typeService.select(mab.getT_id()));
		dishesBean.setD_status("0");
		System.out.println("controller 140 :"+dishesBean);
		DishesBean db = dishesService.addin(dishesBean);
		if (null != db) { // 餐點新增成功
			
			try {
				for (MultipartFile mf : files) {
					DishPhotoBean dpb = new DishPhotoBean();
					dpb.setMchefBean(mchefBean);
					dpb.setD_photo(mf.getBytes());
					dpb.setDishesBean(db);
					dishPhotoService.upload(dpb);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("新增餐點照片失敗");
			}
		}else{
			System.out.println("新增餐點失敗");
		}
		model.addAttribute("applyOk","恭喜您申請成功");
		mb = memberService.select(mab.getM_id());
		session.setAttribute("user",mb );
		return "applysuccess";
	}
}
