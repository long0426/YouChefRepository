package controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import model.ChefBean;
import model.ChefService;
import model.DishPhotoBean;
import model.DishPhotoService;
import model.MchefBean;
import model.MchefService;
import model.MemberBean;
import model.MemberService;
import model.misc.LeaveBean;

@Controller
public class GetImageController {
	@Autowired
	ChefService chefService;
	@Autowired
	MchefService mchefService;
	@Autowired
	DishPhotoService dishPhotoService;
	@Autowired
	MemberService memberService;

	@RequestMapping(path = { "/pages/getImage.controller" },
					method = { RequestMethod.GET, RequestMethod.POST })
	public void process(LeaveBean bean, BindingResult bindingResult, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		byte[] ba = null;
		System.out.println("id = " + request.getParameter("id"));
		System.out.println("mc_id = " + request.getParameter("mc_id"));
		System.out.println("d_id = " + request.getParameter("d_id"));
		System.out.println("m_id = " + request.getParameter("m_id"));
		System.out.println("dp_id = " + request.getParameter("dp_id"));
		if (null != request.getParameter("id")) {
			ChefBean cb = chefService.select(Integer.parseInt(request.getParameter("id")));
			ba = cb.getPhoto();
		}else if(null != request.getParameter("mc_id")){
			MchefBean mb = mchefService.select(Integer.parseInt(request.getParameter("mc_id")));
			ba = mb.getMemberBean().getPhoto();
		}else if(null != request.getParameter("d_id")){
			List<DishPhotoBean> list = dishPhotoService.selectByDid(Integer.parseInt(request.getParameter("d_id")));
			ba = list.get(0).getD_photo();
		}else if(null != request.getParameter("m_id")){
			MemberBean mb = memberService.select(Integer.parseInt(request.getParameter("m_id")));
			ba = mb.getPhoto();
		}else if(null != request.getParameter("dp_id")){
			DishPhotoBean mb = dishPhotoService.selectByDpid(Integer.parseInt(request.getParameter("dp_id")));
			ba = mb.getD_photo();
		}
		
		response.setContentType("image/jpeg");
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			os.write(ba);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (java.lang.NullPointerException e) {
			e.printStackTrace();
		} finally {
			try {
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@RequestMapping(path = { "/pages/getMemImage.controller" },
			method = { RequestMethod.GET, RequestMethod.POST })
	public void memberPic(HttpSession session,HttpServletResponse response){
	MemberBean mBean = (MemberBean)session.getAttribute("user");
	byte[] ba = mBean.getPhoto();
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
	
	@RequestMapping(path = { "/pages/getMemLoginImage.controller" },
		method = { RequestMethod.GET, RequestMethod.POST })
	public void checkPic(@RequestParam(name="email") String email,HttpServletResponse response){
	MemberBean mBean = memberService.loginCheck(email);				
	byte[] ba = mBean.getPhoto();
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
	
	@RequestMapping(path = { "/pages/getAdminImage.controller" },
		method = { RequestMethod.GET, RequestMethod.POST })
	public void adminPic(HttpSession session,HttpServletResponse response){
	MemberBean admin = (MemberBean)session.getAttribute("admin");
	byte[] ba = admin.getPhoto();
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

