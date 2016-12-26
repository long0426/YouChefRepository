package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import model.misc.GlobalService;

@Service(value="memberService")
//@Transactional
public class MemberService {
	public static void main(String[] args) throws IOException{
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			MemberService service = (MemberService) context.getBean("memberService");

			//register
//			MemberBean mb = new MemberBean();
//			mb.setLastName("英龍");
//			mb.setFirstName("楊");
//			mb.setNickname("龍哥");
//			mb.setSex("0");
//			mb.setEmail("long0426_1@gmail.com");
//			mb.setPhone("0939-008541");
//			mb.setPassword("sa123456");
//			mb.setCity("新北市");
//			mb.setDistrict("五股區");
//			mb.setAddress("御史路");
//			mb.setAc_status("0");
//			File file = new File("C:/Users/long0/Pictures/Saved Pictures/java.jpg");
//			FileInputStream fis = new FileInputStream(file);
//			byte[] ba = new byte[fis.available()];
//			fis.read(ba);
//			mb.setPhoto(ba);
//			System.out.println(service.register(mb));
			
			//check
//			System.out.println(service.check("long0426@gmail.com"));
			
			//login
//			System.out.println(service.login("long0426@gmail.com", "sa123456"));
			
			//changePassword
//			System.out.println(service.changePassword("long0426@gmail.com", "sa12345", "sa1234567"));
//			System.out.println(service.login("long0426_1@gmail.com", "sa123456"));
			
			sessionFactory.getCurrentSession().getTransaction().commit();
		} finally {
			sessionFactory.close();
			((ConfigurableApplicationContext) context).close();
		}
	}
	@Autowired
	private MemberDAO memberDao;
	
	public boolean check(String email){
		MemberBean bean = memberDao.select(email);
		if(bean!=null){
			return true;
		}
		return false;
	}	
	
	public boolean register(MemberBean bean){
		MemberBean register=null;
		if(bean!=null){
			String temp1 = bean.getEmail().toLowerCase();
			bean.setEmail(temp1);
			String temp2 = GlobalService.getMD5Encoding(GlobalService.encryptString(bean.getPassword()));
			bean.setPassword(temp2);
			register = memberDao.insert(bean);
			return true;
		}
		return false;
	}

	public MemberBean login(String email, String password) {
		MemberBean bean = memberDao.select(email);
		if(bean!=null){
			if(password !=null && password.length()!=0){
				String pass = bean.getPassword();
				String temp = GlobalService.getMD5Encoding(GlobalService.encryptString(password));
				if(pass.equals(temp)){
					return bean;
				}
			}
		}
		return null;
	}
	
	public boolean changePassword(String email, String oldPwd, String newPwd){
		MemberBean bean = this.login(email, oldPwd);
		if(bean!=null){
			if(newPwd != null && newPwd.length()!=0){
				String temp = GlobalService.getMD5Encoding(GlobalService.encryptString(newPwd));
				bean.setPassword(temp);
				memberDao.update(temp, bean.getM_id());
				return true;
			}
		}
		return false;
	}
}
