package model;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;



@Service(value = "chefService")
public class ChefService {
	@Autowired
	private TypeService typeService;
	
	public static void main(String[] args) throws IOException{
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		FileInputStream fis = null;
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			ChefService service = (ChefService) context.getBean("chefService");
		
		//insert
//			File pic = new File("C:/Users/long0/Downloads/大廚照片/048.jpg");
//			byte ba[] = null;
//
//			ChefBean insert = new ChefBean();
//			
//			fis = new FileInputStream(pic);
//			ba = new byte[fis.available()];
//			fis.read(ba);			
//			
//			insert.setLastName("朱");
//			insert.setFirstName("忠平2");
//			insert.setSex("0");
//			insert.setPhone("0933955478");
//			insert.setAddress("台北市中山區台北大道一段");
//			insert.setC_status("0");
//			insert.setBackground("知名飯店大廚，擅長川味料理。");
//			insert.setPhoto(ba);
//			insert.setTypeBean(service.typeService.select(3005));
//			insert.setYears(8);
//			
//			System.out.println(service.addin(insert));
			
			//select()
//			System.out.println(service.select());
			
			//select(int)
			System.out.println(service.select(4011));
		
			sessionFactory.getCurrentSession().getTransaction().commit();
		} finally {
			if(null != fis)
				fis.close();
			sessionFactory.close();
			((ConfigurableApplicationContext) context).close();
		}
}

	
	@Autowired
	private ChefDAO chefDao;
	
	public ChefBean addin(ChefBean bean){
		ChefBean result=null;
		System.out.println(bean);
		if(bean!=null){
			result = chefDao.insert(bean);
		}
		return result;
	}
	
	
	public List<ChefBean> select(ChefBean bean) {
		List<ChefBean> result = null;
		if(bean != null && bean.getC_id() !=0){
			ChefBean temp = chefDao.select(bean.getC_id());
			if(temp !=null){
				result = new ArrayList<ChefBean>();
				result.add(temp);
			}
			}else{
				result = chefDao.select();
			}
			return result;
		}
	public List<ChefBean> select() {
		List<ChefBean> list = chefDao.select();
		return list;
	}	
	public ChefBean select(int c_id) {
		return chefDao.select(c_id);
	}
	
	
}