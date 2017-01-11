package model;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "chefService")
@Transactional(transactionManager="transactionManager")
public class ChefService {
	public static void main(String[] args){
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("beans.config.xml");
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		try {sessionFactory.getCurrentSession().beginTransaction();
			 ChefService service = (ChefService) context.getBean("chefService");
			
		
		//insert
			File pic = new File("D:/image/047.jpg");
			byte ba[] = null;

			Session session = sessionFactory.getCurrentSession();
			ChefBean insert = new ChefBean();
			
			FileInputStream fis = new FileInputStream(pic);
			ba = new byte[fis.available()];
			fis.read(ba);			
			TypeBean bean1 = new TypeBean();
			bean1.setT_id(3003);
			
			insert.setLastName("朱");
			insert.setFirstName("忠平");
			insert.setSex("0");
			insert.setPhone("0933955478");
			insert.setAddress("台北市中山區台北大道一段");
			insert.setC_status("0");
			insert.setBackground("知名飯店大廚，擅長川味料理。");
			insert.setPhoto(ba);
			insert.setTypeBean(bean1);
			insert.setYears(8);
			session.save(insert);
			fis.close();
			System.out.println(insert);
		
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
				
			sessionFactory.getCurrentSession().getTransaction().commit();
			
	} finally {
		sessionFactory.close();
		((ConfigurableApplicationContext) context).close();
	}
}

	
	@Autowired
	private ChefDAO chefDao;
	@Transactional
	public ChefBean addin(ChefBean bean){
		ChefBean result=null;
		if(bean!=null){
			result = chefDao.insert(bean);
		}
		return result;
	}
	
	@Transactional(readOnly=true)
	public List<ChefBean> select(ChefBean bean) {
		List<ChefBean> result = null;
		if(bean != null && bean.getC_id() !=0){
			ChefBean temp = chefDao.select (bean.getC_id());
			if(temp !=null){
				result = new ArrayList<ChefBean>();
				result.add(temp);
			}
			}else{
				result = chefDao.select();
			}
			return result;
		}
	@Transactional(readOnly=true)
	public List<ChefBean> select() {
		List<ChefBean> list = chefDao.select();
		return list;
	}	
	@Transactional(readOnly=true)
	public ChefBean select (int c_id) {
		return chefDao.select (c_id);
	}
	
//	@Autowired
//	private OrdersDAO ordersDao;
//	public List<OrdersBean> selectlistReviewMember(int c_id){
//		List<OrdersBean> list =ordersDao.selectlistReviewMember(c_id);
//		return list;
//	}
	@Transactional(readOnly=true)
	public List<Object[]>  selectType(int t_id) {
		List<Object[]> list =dishesDao.selectDishesByType(t_id);
		return list;
	}
	@Autowired
	private DishesDAO dishesDao;	
	public List<Object[]> selectDishesByType(Integer t_id){
		List<Object[]> list =dishesDao.selectDishesByType(t_id);
	return list;
	}
//	@Autowired
//	private ReviewDAO reviewDao;
//	public List<ReviewBean> selectReviewByStars(int c_id){
//		List<ReviewBean> list =reviewDao.selectReviewByStars(c_id);
//		return list;
//		
//	}

	public ChefBean update(ChefBean bean) {
		return chefDao.update(bean);
	}
	
	public ChefBean update(ChefBean bean,String fisrtName, String lastName, String sex, String phone, 
            String address,TypeBean typeBean,String c_status,
            String background,Integer years, byte[] photo) {
		bean.setFirstName(fisrtName);
		bean.setLastName(lastName);
		bean.setSex(sex);
		bean.setPhone(phone);
		bean.setAddress(address);
		bean.setTypeBean(typeBean);
		bean.setC_status(c_status);
		bean.setBackground(background);
		bean.setYears(years);
		bean.setPhoto(photo);
		chefDao.update(bean.getC_id(),bean.getFirstName(), bean.getLastName(),bean.getSex(),bean.getPhone(), bean.getAddress(), 
				bean.getTypeBean(), bean.getC_status(), bean.getBackground(),bean.getYears(), bean.getPhoto());
		
		return bean;
	}

	public List<ChefBean> selectChefByType(int t_id){
		List<ChefBean> list =chefDao.selectChefByType(t_id);
		return list;
	}	
	
}