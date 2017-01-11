package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import model.DiscussBean;
import model.DiscussService;
import model.EssayBean;
import model.EssayService;
import model.MemberBean;

@Controller
@RequestMapping(path = {"/essay"})
public class EssayController {
		@Autowired
		EssayService essayService;
		@Autowired
		DiscussService discussService;
		
		@InitBinder
		private void initBinder(WebDataBinder binder) {
			System.out.println("Start to initbinder");
			binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
		}
		
		@RequestMapping(path={"/getessay.controller"},
				method = {RequestMethod.GET, RequestMethod.POST})
		public String process(EssayBean bean,
				BindingResult bindingResult, 
				Model model, HttpServletRequest request) {
//			String instruction = request.getParameter("getessay");
//			MemberBean writer =(MemberBean) request.getAttribute("user");
			Map<String, String> errors = new HashMap<String, String>();
			model.addAttribute("errors", errors);
//			if("發文"==(instruction) || "user"==null){
//				errors.put("memberlogin", "請先登入會員");
//			}
//			List<DiscussBean> discusst = discussService.selectAll();
			List<EssayBean> elist = essayService.listAll();
//			System.out.println("elist="+elist);	
			HttpSession session = request.getSession();
			session.setAttribute("elist", elist);
			return "essaylist";
			
		}
	
		@RequestMapping(path={"/insertessay.controller"},
				method = {RequestMethod.GET, RequestMethod.POST},
				produces="text/html;charset=utf-8")
		public String insertprocess(
				@RequestParam(name="writer_id")String writer_id, 
				@RequestParam(name="title")String title,
				@RequestParam(name="content")String content,
				Model model, HttpSession session) throws InterruptedException {
		MemberBean writer =(MemberBean) session.getAttribute("user");
		Map<String,String> errors = new HashMap<String,String>();
		model.addAttribute("errors",errors);
		if(title==null || title.length()==0 || content==null || content.length()==0){
			errors.put("content", "請輸入標題與內容");
		}
		if(errors!=null && !errors.isEmpty()){
			return "essayinsert";
		}
		EssayBean bean = new EssayBean();
		bean.setWriter_id(writer);
		bean.setTitle(title);
		long time = new java.util.Date().getTime();
		bean.setTime(new java.sql.Date(time));
		bean.setContent(content);
		bean.setE_status("0");
		essayService.insert(bean);
		session.setAttribute("essaybean", bean);
//		Thread.sleep(1000);
		List<EssayBean> elist = essayService.listAll();
//		System.out.println("elist = " + elist);
		session.setAttribute("elist", elist);
//		essayService.select(bean);
//		session.setAttribute("essaybean", bean);
		return "essayinsertsuccess";
		}
		
		@RequestMapping(path={"/selectessay.controller"},
				method = {RequestMethod.GET, RequestMethod.POST},
				produces="text/html;charset=utf-8")
		public String selectprocess(
				@RequestParam(name="essay_id")String essayid,
				Model model,HttpSession session){
			MemberBean writer =(MemberBean) session.getAttribute("user");
			Map<String, String> errors = new HashMap<String, String>();
			model.addAttribute("errors", errors);
			EssayBean bean = new EssayBean();
			int essay_id = Integer.parseInt(essayid);
			bean.setEssay_id(essay_id);
			bean=essayService.select(bean);
			session.setAttribute("essayPage", bean);
			List<DiscussBean> temp1 = discussService.discussAll(essay_id);
//			System.out.println("前面"+temp1);
			session.setAttribute("discussList", temp1);
//			System.out.println("後面"+temp1);
//			System.out.println("essaybean = " + bean);
			return "essayselect";
		}
		
		@RequestMapping(path={"/updateessay.controller"},
				method = {RequestMethod.GET, RequestMethod.POST},
				produces="text/html;charset=utf-8")
		public String updateprocess(
				@RequestParam(name="essay_id")String essayid,
				@RequestParam(name="title")String title,
				@RequestParam(name="content")String content,
				Model model, HttpSession session) throws InterruptedException {
		MemberBean writer =(MemberBean) session.getAttribute("user");
		Map<String,String> errors = new HashMap<String,String>();
		model.addAttribute("errors",errors);
		if(title==null || title.length()==0 || content==null || content.length()==0){
			errors.put("editcontent", "請輸入標題與內容");
		}
		if(errors!=null && !errors.isEmpty()){
			return "essayedit";
		}
		int essay_id = Integer.parseInt(essayid);
		EssayBean bean = new EssayBean();
		bean.setEssay_id(essay_id);
		bean=essayService.select(bean);
		bean.setTitle(title);
		long time = new java.util.Date().getTime();
		bean.setTime(new java.sql.Date(time));
		bean.setContent(content);
		essayService.update(bean);
		session.setAttribute("essaybean", bean);
		List<EssayBean> elist = essayService.listAll();
		session.setAttribute("elist", elist);
		return "essayinsertsuccess";
		}
		
		
		@RequestMapping(path={"/deleteessay.controller"},
				method = {RequestMethod.GET, RequestMethod.POST},
				produces="text/html;charset=utf-8")
		public String deleteprocess(
				@RequestParam(name="essay_id")String essayid,
				Model model, HttpSession session) throws InterruptedException {
		MemberBean writer =(MemberBean) session.getAttribute("user");
		Map<String,String> errors = new HashMap<String,String>();
		model.addAttribute("errors",errors);
		
		int essay_id = Integer.parseInt(essayid);
		EssayBean bean = new EssayBean();
		bean.setEssay_id(essay_id);
		bean=essayService.select(bean);
		essayService.delete(essay_id, "1");
		session.setAttribute("essaybean", bean);
		List<EssayBean> elist = essayService.listAll();
		session.setAttribute("elist", elist);
		return "essayinsertsuccess";
		}
}
