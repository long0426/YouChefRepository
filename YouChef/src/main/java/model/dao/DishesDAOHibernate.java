package model.dao;

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

import model.DishesBean;
import model.DishesDAO;
import model.TypeBean;

@Repository(value = "dishesDao")
public class DishesDAOHibernate implements DishesDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		try {
			Session session = sessionFactory.getCurrentSession();
			sessionFactory.getCurrentSession().beginTransaction();
			DishesDAO dishesDao = (DishesDAO) context.getBean("dishesDao");
			DishesBean insert = new DishesBean();
			TypeBean type1 = new TypeBean();
			type1.setT_id(3002);

			// insert.setMenu();
			insert.setPrice(400);
			insert.setD_name("綜合生魚片");
			insert.setD_briefing("主要是使用當季新鮮海魚、海貝製作而成。");
			insert.setTypeBean(type1);
			insert.setD_status("0");
			session.save(insert);
			System.out.println(insert);
			//
			// //select
			//
			// List<DishesBean> selects = dishesDao.selectAll();
			// DishesBean select = dishesDao.select(5001);
			// System.out.println("select="+select);
			// System.out.println("selects="+selects);
			// List<DishesBean> selectType = dishesDao.selectType(4003);
			// System.out.println("selectType="+selectType);
			// List<DishesBean> selectMchef = dishesDao.selectMchef(4003);
			// System.out.println("selectMchef="+selectMchef);
			// //update
			// DishesBean update = session.get(DishesBean.class, 5002);
			// TypeBean bean2 = new TypeBean();
			// bean2.setT_id(4004);
			// update.setD_name("希臘沙拉");
			// update.setD_briefing("除了色彩鮮艷的蘿蔓、番茄、黑橄欖等，還有一定少不了菲達乳酪。");
			// update.setPrice(280);
			// update.setTypeBean(bean2);
			// update.setD_status("0");
			// session.save(update);
			// System.out.println(update);
			sessionFactory.getCurrentSession().getTransaction().commit();
		} finally {
			((ConfigurableApplicationContext) context).close();
		}
	}

	@Override
	public DishesBean insert(DishesBean dishesBean) {
		if ((int) this.getSession().save(dishesBean) > 0)
			return dishesBean;
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DishesBean> selectAll() {
		Query query = this.getSession().createQuery("from DishesBean");
		return (List<DishesBean>) query.getResultList();
	}

	@Override
	public DishesBean select(int d_id) {
		return getSession().get(DishesBean.class, d_id);
	}

	@Override
	public DishesBean update(String d_name, String d_briefing, double price, String menu, TypeBean typeBean,
			String d_status) {
		DishesBean update = this.getSession().get(DishesBean.class, d_name);
		if (update != null) {
			update.setD_name(d_name);
			update.setD_briefing(d_briefing);
			update.setPrice(price);
			update.setMenu(menu);
			update.setTypeBean(typeBean);
			update.setD_status(d_status);
		}
		return update;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DishesBean> selectType(int t_id) {
		Query query = this.getSession().createQuery("from DishesBean where t_id=?");
		query.setParameter(0, t_id);
		return (List<DishesBean>) query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DishesBean> selectMchef(int mc_id) {
		Query query = this.getSession().createQuery("from DishesBean where mc_id=?");
		query.setParameter(0, mc_id);
		return (List<DishesBean>) query.getResultList();
	}

	@Override
	public DishesBean update(DishesBean dishesBean) {
		try {
			this.getSession().update(dishesBean);
			return dishesBean;
		} catch (RuntimeException e) {
			return null;
		}
	}

}