package model.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import model.ChefBean;
import model.ChefDAO;
import model.DishesBean;
import model.TypeBean;



@Repository(value="chefDao")
public class ChefDAOHibernate implements ChefDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getSession() {
		return sessionFactory.getCurrentSession();
    }
		public static void main(String[] args){
			ApplicationContext context = 
					new ClassPathXmlApplicationContext("beans.config.xml");
			SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
			try {
				sessionFactory.getCurrentSession().beginTransaction();
				Session session = sessionFactory.getCurrentSession();
//				//insert
//				File pic = new File("D:/image/047.jpg");
//				byte ba[] = null;
//
//				try {
//					
//					ChefBean insert = new ChefBean();
//					
//					FileInputStream fis = new FileInputStream(pic);
//					ba = new byte[fis.available()];
//					fis.read(ba);			
//					TypeBean bean1 = new TypeBean();
//					bean1.setT_id(3003);
//					
//					insert.setLastName("朱");
//					insert.setFirstName("忠平");
//					insert.setSex("0");
//					insert.setPhone("0933955478");
//					insert.setAddress("台北市中山區台北大道一段");
//					insert.setC_status("0");
//					insert.setBackground("知名飯店大廚，擅長川味料理。");
//					insert.setPhoto(ba);
//					insert.setTypeBean(bean1);
//					insert.setYears(8);
//					session.save(insert);
//					fis.close();
//					System.out.println(insert);
//				
//					
//				} catch (FileNotFoundException e) {
//					e.printStackTrace();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
				
				//select
				ChefDAO chefDao = (ChefDAO) context.getBean("chefDao");
				ChefBean select = chefDao.select(4002);
				System.out.println("select="+select);
				//getAllALL
//				ChefDAO chefDao = (ChefDAO) context.getBean("chefDao");
				List<ChefBean> getAll = chefDao.select();
				System.out.println("getAll="+getAll);
				//update
				//selectChefByType
				List<ChefBean> chefs = chefDao.selectChefByType(3003);
				System.out.println("selects="+chefs);
				
//				File pic = new File("D:/image/049.jpg");
//				byte ba[] = null;
//				ChefBean update = session.get(ChefBean.class, 4004);
//				try {
//								
//					FileInputStream fis = new FileInputStream(pic);
//					ba = new byte[fis.available()];
//					fis.read(ba);			
//					
//
//					
//					update.setLastName("郭");
//					update.setFirstName("子明");
//					update.setSex("0");					
//					update.setPhone("0933955478");
//					update.setAddress("中山區台北大道一段");
//					update.setC_status("0");
//					update.setBackground("擅長日式料理，刀法見長。");
//					update.setPhoto(ba);
//					update.setYears(12);
//					fis.close();
//					System.out.println(update);
//
//				
//				} catch (FileNotFoundException e) {
//					e.printStackTrace();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//				
 
				
				sessionFactory.getCurrentSession().getTransaction().commit();
			} finally {
				((ConfigurableApplicationContext) context).close();
			}
}
		
		
	
    @Override
	public ChefBean select(int c_id) {
    	return getSession().get(ChefBean.class, c_id);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<ChefBean> select() {
		Query query = this.getSession().createQuery("from ChefBean");
		return (List<ChefBean>)query.getResultList();
	}
	@Override
	public ChefBean insert(ChefBean bean) {
		if(bean!=null){
			ChefBean insert = this.getSession().get(ChefBean.class, bean.getC_id());
			if(insert==null){
				this.getSession().save(bean);
				return bean;
			}
		}
		return null;
	}
	
	@Override
	public ChefBean update(ChefBean bean) {
		if(bean!=null){
			ChefBean update = this.getSession().get(ChefBean.class, bean.getC_id());
			if(update==null){
				this.getSession().save(bean);
				return bean;
			}
		}
		return null;
	}
	@Override
	public List<ChefBean> selectChefByType(int t_id) {
		Query query = this.getSession().createQuery("from ChefBean where t_id=?");
		query.setParameter(0, t_id);
		return (List<ChefBean>) query.getResultList();
	}
}


	

