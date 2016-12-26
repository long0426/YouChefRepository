package model.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import model.TypeBean;
import model.TypeDAO;






@Repository(value="typeDao")

public class TypeDAOHibernate implements TypeDAO{
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
			
			TypeDAO typeDao = (TypeDAO) context.getBean("typeDao");
			TypeBean select = typeDao.select(4001);
			System.out.println("select="+select);
			
			List<TypeBean> selects = typeDao.select();
			System.out.println("selects="+selects);
			
			
			
			
			
			
			sessionFactory.getCurrentSession().getTransaction().commit();
		} finally {
			
			((ConfigurableApplicationContext) context).close();
		}
	    }	
	@Override
	public TypeBean select(int t_id) {
		return this.getSession().get(TypeBean.class, t_id);

	}
	@Override
	public TypeBean insert(TypeBean typeBean) {
		return getSession().get(TypeBean.class, typeBean);
		
	}
	
	@Override
	public List<TypeBean> select() {
		Query query = this.getSession().createQuery("from TypeBean");
		return (List<TypeBean>)query.getResultList();
	}


	}
	
