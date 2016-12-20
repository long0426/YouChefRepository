package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

@Service(value = "dishPhotoService")
public class DishPhotoService {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			DishPhotoService service = (DishPhotoService) context.getBean("dishPhotoService");
			
			//Test for upload
//			DishesBean dishesBean = new DishesBean();
//			dishesBean.setD_briefing("sdfsdfsdf");
//			dishesBean.setD_id(5002);
////			MemberBean mb = new MemberBean();
////			mb.setM_id(1004);
////			dishesBean.setMemberBean(mb);
//			dishesBean.setPrice(200);
//			dishesBean.setD_name("bbb");
//			dishesBean.setMenu("asdasdfsdfsdfsd");
//			TypeBean tb = new TypeBean();
//			tb.setT_id(4001);
//			dishesBean.setT_id(tb);
//			dishesBean.setD_status("0");
//			DishPhotoBean dpb = new DishPhotoBean();
//			MchefBean mcb = new MchefBean();
//			mcb.setMc_id(1005);
//			System.out.println(service.mchefDao.select(mcb));
//			dpb.setMchefBean(service.mchefDao.select(mcb));
//			File file = new File("C:/Users/long0/Pictures/Saved Pictures/hibernate.png");
//			FileInputStream fis = new FileInputStream(file);
//			byte[] ba = new byte[fis.available()];
//			fis.read(ba);
//			dpb.setD_photo(ba);
//			dpb.setDishesBean(dishesBean);
//			service.upload(dpb);
			
			//Test for selectByMcid
//			service.selectByMcid(1004);
			
			//Test for selectByDid
//			service.selectByDid(5001);
			
			//Test for selectByMcidAndDid
			service.selectByMcidAndDid(1005, 5002);
			
			sessionFactory.getCurrentSession().getTransaction().commit();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
		} finally {
			sessionFactory.close();
			((ConfigurableApplicationContext) context).close();
		}
	}
	
	@Autowired
	DishPhotoDAO dishPhotoDao;
	@Autowired
	MchefDAO mchefDao;
	
	public DishPhotoBean upload(DishPhotoBean bean){
		return dishPhotoDao.insert(bean);
	}
	
	public List<DishPhotoBean> selectByMcid(Integer mc_id){
		List<DishPhotoBean> list = null;
		list = dishPhotoDao.selectByMcid(mc_id);
		System.out.println(list);
		return list;
	}
	
	public List<DishPhotoBean> selectByDid(Integer d_id){
		List<DishPhotoBean> list = null;
		list = dishPhotoDao.selectByDid(d_id);
		System.out.println(list);
		return list;
	}
	
	public List<DishPhotoBean> selectByMcidAndDid(Integer mc_id, Integer d_id){
		List<DishPhotoBean> list = null;
		list = dishPhotoDao.selectByMcidAndDid(mc_id, d_id);
		System.out.println(list);
		return list;
	}
}
