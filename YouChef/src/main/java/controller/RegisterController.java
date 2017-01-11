package controller;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import model.MemberBean;
import model.MemberService;
import model.misc.GmailApp;

@Controller
@RequestMapping(path={"/member/register.controller"},
				method={RequestMethod.GET, RequestMethod.POST}
	
)
public class RegisterController {
	private static final long serialVersionUID = 1L;
	@Autowired
	private MemberService memberService;
	@Autowired
	private GmailApp gmailApp;	
	
	Pattern emailPattern = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$");
	Pattern pwdPattern = Pattern.compile("^(?!.*[^a-zA-Z0-9!@#$%^&*])(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&*]).{6,}$");
	Pattern namePattern = Pattern.compile("^[\u4e00-\u9fff]{2,}$");
	@RequestMapping
	public String registerService(@RequestParam(name="password2")String password2,
								  @RequestParam(name="captcha") String captcha, 
								  MemberBean bean, Model model, HttpSession session){
		Map<String, String> errors = new HashMap<String,String>();
		model.addAttribute("errors", errors);
		String code= (String) session.getAttribute("code");
		if(!code.equals(captcha)){
				errors.put("captcha", "驗證碼輸入錯誤, 請重填!");
		}

		if(bean.getEmail()==null||bean.getEmail()==""){
				errors.put("email","1.不可空白，2.格式不符合, 請重新輸入");
		}
		if(!emailPattern.matcher(bean.getEmail()).matches()){
			errors.put("email","1.不可空白，2.格式不符合, 請重新輸入");
		}
		if(!pwdPattern.matcher(bean.getPassword()).matches()){
			errors.put("password1", "1.不可空白，2.至少6個字且必須包含英文字母、數字、特殊字元[!@#$%^&*]");
		}
		if(!bean.getPassword().equals(password2)){
			errors.put("password2", "確認密碼錯誤, 請重新輸入");
		}
		if(bean.getLastName()==null || bean.getLastName() =="" || bean.getFirstName()==null || bean.getFirstName()==""){
			String name=bean.getFirstName() + bean.getLastName();
			if(!namePattern.matcher(name).matches()){
				errors.put("name", "1.姓名不可空白，2.至少兩個字以上，3.必須全部為中文字");
			}
		}
		if(bean.getPhone()==null || bean.getPhone()==""){
			errors.put("phone", "聯絡電話不可空白");
		}
		if(bean.getCity()=="" || bean.getDistrict()=="" ||bean.getAddress()==""){
			errors.put("address", "請選擇縣市，鄉鎮，地址不可空白");
		}
		if(errors!=null && !errors.isEmpty()) {
			return "register.error";
		}
		bean.setAc_status("0");
		memberService.register(bean);
		model.addAttribute("registerOK","註冊成功");
		
		String[] emaillist={"eeitgroup3@gmail.com",bean.getEmail()};
		
		try {
			gmailApp.postMail(emaillist, "註冊成功", "親愛的 會員您好， 感謝您於優廚申請註冊， 現在您可開始使用會員專屬功能囉！   <br/>"
    																+ "優廚 敬上   <br/>"
    																+ "＊此信為系統自動發送，請勿直接回覆，謝謝＊", "eeitgroup3@gmail.com", "優廚YouChef");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "register.success";
	}
	
		
}
