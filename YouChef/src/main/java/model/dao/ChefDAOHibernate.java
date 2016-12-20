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
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.context.ConfigurableApplicationContext;

import model.ChefBean;
import model.ChefDAO;
import model.TypeBean;




@Repository(value="chefDao")
public class ChefDAOHibernate implements ChefDAO {
	@Autowired
	private SessionFactory sessionFactory;
	public ChefDAOHibernate(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public Session getSession() {
		return sessionFactory.getCurrentSession();
    }
		public static void main(String[] args){
			ApplicationContext context = 
					new ClassPathXmlApplicationContext("beans.config.xml");
			SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
			try {
				sessionFactory.getCurrentSession().beginTransaction();
				File pic = new File("D:/image/001.jpg");
				byte ba[] = null;

				try {
					ChefDAO chefDao = new ChefDAOHibernate(sessionFactory);
					System.out.println(chefDao.select());
					
					FileInputStream fis = new FileInputStream(pic);
					ba = new byte[fis.available()];
					fis.read(ba);			
					ChefBean bean = new ChefBean();
					TypeBean bean1 = new TypeBean();
					bean1.setT_id(4001);
					
					bean.setLastName("牟");
					bean.setFirstName("恕海");
					bean.setSex("0");
					bean.setPhone("0933955478");
					bean.setAddress("台北市中山區台北大道一段");
					bean.setC_status("0");
					bean.setBackground("飯店多年掌廚資歷，擅長川味料理。");
					bean.setPhoto(ba);
					bean.setT_id(bean1);
					bean.setYears(5);
					fis.close();
					System.out.println(bean);
					chefDao.insert(bean);
					
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
								
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
	public boolean delete(int c_id) {
		ChefBean delete = this.getSession().get(ChefBean.class, c_id);
		if(delete!=null){
			this.getSession().delete(delete);
		return true;
	}
		return false;
	}
	@Override
	public ChefBean update(int c_id, String fisrtName, String lastName, String sex, String phone, String address,
			String c_status, String background, byte[] photo, Integer years) {
		ChefBean bean = this.getSession().get(ChefBean.class, c_id);
		if(bean!=null){
			bean.setLastName(lastName);
			bean.setFirstName(fisrtName);
			bean.setSex(sex);
			bean.setPhone(phone);
			bean.setAddress(address);
			bean.setC_status(c_status);
			bean.setBackground(background);
			bean.setPhoto(photo);
			bean.setYears(years);
		}
		return bean;
		}
}


	

