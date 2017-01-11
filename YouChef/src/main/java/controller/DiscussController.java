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
@RequestMapping(path = {"/discuss"})
public class DiscussController {
	@Autowired
	DiscussService discussService;
	@Autowired
	EssayService essayService;
	@InitBinder
	private void initBinder(WebDataBinder binder) {
//		System.out.println("Start to initbinder");
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
	}
	@RequestMapping(path={"/insertdiscuss.controller"},
			method = {RequestMethod.GET, RequestMethod.POST},
			produces="text/html;charset=utf-8")
	public String insertprocess(
			@RequestParam(name="essay_id")String essay_id, 
			@RequestParam(name="content")String content,
			Model model, HttpSession session) throws InterruptedException {
	MemberBean writer =(MemberBean) session.getAttribute("user");
	EssayBean essaywriter =(EssayBean) session.getAttribute("essayPage");
	Map<String,String> errors = new HashMap<String,String>();
	model.addAttribute("errors",errors);
	if(content==null || content.length()==0){
		errors.put("discontent", "請輸入內容");
	}
	if(errors!=null && !errors.isEmpty()){
		return "discussPage";
	}
	DiscussBean bean = new DiscussBean();
	bean.setEssayBean(essaywriter);
	bean.setMemberBean(writer);
	long time = new java.util.Date().getTime();
	bean.setTime(new java.sql.Date(time));
	bean.setContent(content);
	bean.setD_status("0");
	discussService.insert(bean);
//	System.out.println("disbean="+bean);
	session.setAttribute("discussPage", bean);
//	System.out.println("disbean="+bean);
//	essayService.select(bean);
//	session.setAttribute("essaybean", bean);
	return "essaylist";
	}
	
	@RequestMapping(path={"/updatediscuss.controller"},
			method = {RequestMethod.GET, RequestMethod.POST},
			produces="text/html;charset=utf-8")
	public String updateprocess(
			@RequestParam(name="discuss_id")String discussid,
			@RequestParam(name="content")String content,
			Model model, HttpSession session) throws InterruptedException {
	MemberBean writer =(MemberBean) session.getAttribute("user");
//	DiscussBean dister = (DiscussBean) session.getAttribute("discussList");
	Map<String,String> errors = new HashMap<String,String>();
	model.addAttribute("errors",errors);
	if(content==null || content.length()==0){
		errors.put("editdiscontent", "請輸入內容");
	}
	if(errors!=null && !errors.isEmpty()){
		return "discussedit";
	}
	int discuss_id = Integer.parseInt(discussid);
	DiscussBean bean = new DiscussBean();
	bean.setDiscuss_id(discuss_id);
	bean=discussService.select(bean);
	long time = new java.util.Date().getTime();
	bean.setTime(new java.sql.Date(time));
	bean.setContent(content);
	discussService.update(bean);
	session.setAttribute("discusslist", bean);
	List<EssayBean> elist = essayService.listAll();
	session.setAttribute("elist", elist);
	return "essaylist";
	}
	
	@RequestMapping(path={"/deletedDiscuss.controller"},
			method = {RequestMethod.GET, RequestMethod.POST},
			produces="text/html;charset=utf-8")
	public String deleteprocess(
			@RequestParam(name="discuss_id")String discussid,
			Model model, HttpSession session) throws InterruptedException {
	MemberBean writer =(MemberBean) session.getAttribute("user");
	Map<String,String> errors = new HashMap<String,String>();
	model.addAttribute("errors",errors);
	
	int discuss_id = Integer.parseInt(discussid);
	DiscussBean bean = new DiscussBean();
	bean.setDiscuss_id(discuss_id);
	bean=discussService.select(bean);
	discussService.delete(discuss_id, "1");
	session.setAttribute("discusslist", bean);
	List<EssayBean> elist = essayService.listAll();
	session.setAttribute("elist", elist);
	return "essaylist";
	}
}
