package model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import model.misc.GlobalService;

@Service(value="memberService")
@Transactional
public class MemberService {
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
