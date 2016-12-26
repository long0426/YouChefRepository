package model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

@Service(value = "dishesService")
public class DishesService {
	@Autowired
	TypeDAO typeDao;
	@Autowired
	MchefService mchefService;
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		try {
			sessionFactory.getCurrentSession().beginTransaction();

			DishesService service = (DishesService) context.getBean("dishesService");
			
			//addin
//			DishesBean db = new DishesBean();
//			db.setPrice(360);
//			db.setMchefBean(service.mchefService.select(1002));
//			db.setD_briefing("酸酸微甜微辣的泰式涼拌海鮮很開胃。");
//			db.setD_name("涼拌海鮮");
//			db.setMenu("涼拌酸辣海鮮");
//			db.setTypeBean(service.typeDao.select(3002));
//			db.setD_status("0");
//			System.out.println(service.addin(db));
			
			//selectAll()
//			System.out.println(service.selectAll());
			
			//select(DishesBean)
//			System.out.println(service.select(service.select(5001)));
			
			//selectType
//			System.out.println(service.selectType(3009));
			
			//update(dishesBean)
//			DishesBean bean = service.select(5001);
//			bean.setPrice(999);
//			System.out.println(service.update(bean));
			
			//selectMchef
//			System.out.println(service.selectMchef(1002));
			
			sessionFactory.getCurrentSession().getTransaction().commit();
		}finally {
			sessionFactory.close();
			((ConfigurableApplicationContext) context).close();
		}
		}
	@Autowired
	private DishesDAO dishesDao;
	public DishesBean addin(DishesBean bean){
		DishesBean result=null;
		if(bean!=null){
			result = dishesDao.insert(bean);
		}
		return result;
	}
	public List<DishesBean> select(DishesBean bean) {
		List<DishesBean> result = null;
		if(bean != null && bean.getD_id() !=0){
			DishesBean temp = dishesDao.select(bean.getD_id());
			if(temp !=null){
				result = new ArrayList<DishesBean>();
				result.add(temp);
			}
			}else{
				result = dishesDao.selectAll();
			}
			return result;
		}
	public List<DishesBean> selectAll() {
		List<DishesBean> list = dishesDao.selectAll();
		return list;
	}	
	
	
	public DishesBean  select(int d_id) {
		return dishesDao.select(d_id);
	}
	public List<DishesBean>  selectType(int t_id) {
		List<DishesBean> list =dishesDao.selectType(t_id);
		return list;
	}
	public DishesBean update(String d_name,String d_briefing,double price,String menu,TypeBean typeBean,
			String d_status) {
		return dishesDao.update(d_name,d_briefing,price,menu,typeBean,d_status);
	}
	public List<DishesBean>  selectMchef(int m_id) {
		List<DishesBean> list =dishesDao.selectMchef(m_id);
		return list;
	}
	
	public DishesBean update(DishesBean dishesBean){
		return dishesDao.update(dishesBean);
	}
}

