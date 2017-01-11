package model.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.CalendarBean;
import model.CalendarDAO;

@Repository(value = "calendarDao")
public class CalendarDAOHibernate implements CalendarDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public int insert(CalendarBean bean) {
		return (int) this.getSession().save(bean);
	}

	private final String SELECT_MCHEF = "from CalendarBean where mc_id=:mc_id and theMonth=:date";

	@Override
	public CalendarBean selectMchef(int mc_id, String date) {
		Query query = this.getSession().createQuery(SELECT_MCHEF);
		System.out.println("mc_id = " + mc_id);
		query.setParameter("mc_id", mc_id);
		query.setParameter("date", date);
		System.out.println(query.getResultList().size());
		if (0 == query.getResultList().size())
			return null;
		return (CalendarBean) query.getResultList().get(0);
	}

	//with date
	private final String SELECT_CHEF = "from CalendarBean where c_id=:c_id and theMonth=:date";

	@Override
	public CalendarBean selectChef(int c_id, String date) {
		Query query = this.getSession().createQuery(SELECT_CHEF);
		System.out.println("Calendar DAO c_id = " + c_id);
		System.out.println("Calendar DAO date = " + date);
		query.setParameter("c_id", c_id);
		query.setParameter("date", date);
		System.out.println(query.getResultList().size());
		if (0 == query.getResultList().size())
			return null;
		return (CalendarBean) query.getResultList().get(0);
	}
	
	//without date
	private final String SELECT_CHEF_NO_DATE = "from CalendarBean where c_id=:c_id";

	@Override
	public List<CalendarBean> selectChef(int c_id) {
		Query query = this.getSession().createQuery(SELECT_CHEF_NO_DATE);
		query.setParameter("c_id", c_id);
		if (0 == query.getResultList().size())
			return null;
		else
			return (List<CalendarBean>) query.getResultList();
	}

	@Override
	public CalendarBean update(CalendarBean bean) {
		try {
			getSession().clear();
			this.getSession().update(bean);
			if(null == bean.getChefBean().getC_id()){
				System.out.println("return mchef");
//				return selectMchef(bean.getMchefBean().getMc_id(), bean.getTheMonth());
				return bean;
			}
			else{
				System.out.println("return chef");
				return selectChef(bean.getChefBean().getC_id(), bean.getTheMonth());
			}
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	private final String SELECT_MCHEF_NO_DATE = "from CalendarBean where mc_id=:mc_id";
	@Override
	public List<CalendarBean> selectMchef(int mc_id) {
		Query query = this.getSession().createQuery(SELECT_MCHEF_NO_DATE);
		query.setParameter("mc_id", mc_id);
		if (0 == query.getResultList().size())
			return null;
		else
			return (List<CalendarBean>) query.getResultList();
	}

}
