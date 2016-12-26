package model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value="csService")
@Transactional
public class CsService {
	@Autowired
	private CsDAO csDao;
	
	public void sendMessage(CsBean bean, MemberBean memberBean){
		if(bean!=null){
			bean.setMemberBean(memberBean);
			csDao.insert(bean);
		}
	}
	
	public List<CsBean> listAll(){
		return csDao.select();
	}
	
	public void deleteMessage(CsBean bean){
		CsBean delete = csDao.select(bean.getMessage_id());
		csDao.update(delete.getMessage_id(), "2");
	}
}
