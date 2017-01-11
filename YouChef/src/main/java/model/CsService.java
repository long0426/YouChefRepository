package model;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

@Service(value = "csService")
// @Transactional
public class CsService {
	@Autowired
	private CsDAO csDao;
	@Autowired
	private MemberService memberService;

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			CsService service = (CsService) context.getBean("csService");

			// sendMessage
//			CsBean bean = new CsBean();
//			MemberBean mb = service.memberService.login("long0426_1@gmail.com", "sa123456");
//			bean.setMemberBean(mb);
//			bean.setContent("這是測試文章4");
//			bean.setTime(new java.util.Date());
//			bean.setCs_status("0");
//			System.out.println(service.sendMessage(bean));
			
			//listAll
//			System.out.println(service.listAll());
			
			//delete
//			CsBean bean = service.csDao.select(11001);
//			service.deleteMessage(bean);

			sessionFactory.getCurrentSession().getTransaction().commit();
		} finally {
			sessionFactory.close();
			((ConfigurableApplicationContext) context).close();
		}
	}

	public void sendMessage(CsBean bean, MemberBean memberBean) {
		if (bean != null) {
			bean.setMemberBean(memberBean);
			csDao.insert(bean);
		}
	}

	public CsBean sendMessage(CsBean bean) {
		if(null != bean.getMemberBean()){
			return csDao.insert(bean);
		}else{
			return null;
		}
	}

	public List<CsBean> listAll(int m_id) {
		return csDao.selectAll(m_id);
	}

	public void deleteMessage(CsBean bean) {
		CsBean delete = csDao.select(bean.getMessage_id());
		csDao.update(delete.getMessage_id(), "2");
	}
}
