package model;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

@Service(value = "essayService")
public class EssayService {
	@Autowired 
	private EssayDAO essayDao;
	
	@Autowired
	private MemberDAO memberDao;

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		try {
			sessionFactory.getCurrentSession().beginTransaction();                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    
			EssayService service = (EssayService) context.getBean("essayService");
//			MemberDAO memberDao = (MemberDAO) context.getBean("memberDAO");
//			EssayDAO Dao = (EssayDAO) context.getBean("essayDao");
			
			//test insert
//			EssayBean bean = new EssayBean();
//			MemberBean mb = new MemberBean();
//			mb=service.memberDao.select(1001);
//			bean.setWriter_id(mb);
//			bean.setTitle("吃不吃臭豆腐2");
//			long time  = new java.util.Date().getTime();
//			bean.setTime(new java.sql.Date(time));
//			bean.setContent("媽媽家裡種西瓜，媽媽家裡養泥鰍，西瓜大泥鰍肥，全家吃完吃光光");
//			bean.setE_status("0");
//			System.out.println("EssayBean = " + bean);
//			System.out.println(service.insert(bean));
			
//			//select
//			EssayBean bean = new EssayBean();
//			bean.setEssay_id(8005);
//			EssayBean eb = service.select(bean);
//			System.out.println("EssayBean = " + eb);
			
			//test update
//			MemberDAO memberDao = (MemberDAO) context.getBean("memberDAO");
//			EssayBean bean = new EssayBean();
//			bean.setEssay_id(8002);
//			MemberBean mb = new MemberBean();
//			mb=memberDao.select(1001);
//			bean.setWriter_id(mb);
//			bean.setTitle("你吃不吃臭豆腐");
//			long time  = new java.util.Date().getTime();
//			bean.setTime(new java.sql.Date(time));
//			bean.setContent("爸爸家裡種西瓜，媽媽家裡養泥鰍，西瓜大泥鰍肥，全家吃完吃光光");
//			bean.setE_status("0");
//			System.out.println("EssayBean = " + bean);
//			System.out.println(service.update(bean));
			
			//selectAll
			System.out.println(service.selectAll());
			
			sessionFactory.getCurrentSession().getTransaction().commit();
		} finally {
			sessionFactory.close();
			((ConfigurableApplicationContext) context).close();
		}
	}
	
	private int insert(EssayBean bean) {
		return essayDao.insert(bean);
	}
	public List<EssayBean> selectAll() {
		List<EssayBean> list = essayDao.selectAll();
		return list;
	}
	private EssayBean select(EssayBean bean) {
		return essayDao.select(bean);
	}
	private EssayBean update(EssayBean bean){
		return essayDao.update(bean);
	}
}
