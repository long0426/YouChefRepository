package model;

import java.io.IOException;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import model.misc.GlobalService;

@Service(value="memberService")
@Transactional
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
			System.out.println(bean);
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
	
	public MemberBean update(MemberBean bean, String nickname, String sex, String city, String district, String address, String briefing, byte[] photo){		
			bean.setNickname(nickname);
			bean.setSex(sex);
			bean.setCity(city);
			bean.setDistrict(district);
			bean.setAddress(address);
			bean.setBriefing(briefing);
			bean.setPhoto(photo);
			memberDao.update(bean.getM_id(), bean.getFirstName(), bean.getLastName(), bean.getNickname(), bean.getSex(), 
							 bean.getCity(), bean.getDistrict(), bean.getAddress(), bean.getBriefing(), bean.getPhoto());
			return bean;
	}
	
	public boolean update(MemberBean bean){
		return memberDao.update(bean);
	}
	
	public MemberBean loginCheck(String email){
		if(check(email)){
			return memberDao.select(email);
		}
		return null;
	}
	
	public List<MemberBean> listAll(){
		return memberDao.select();
	}
	
	public MemberBean select(int m_id) {
		return memberDao.select(m_id);
		
	}
	
	public boolean changeStatus(int m_id, String ac_status){
		return memberDao.updateStatus(m_id, ac_status);
	}
	
}
