package model;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

@Service(value = "calendarService")
public class CalendarService {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			CalendarService service = (CalendarService) context.getBean("calendarService");
			
			//Test for MchefInput
//			CalendarBean cb = new CalendarBean();
//			MchefBean mb = new MchefBean();
//			mb.setMc_id(1006);
//			cb.setMchefBean(service.mchefDao.select(mb));
//			cb.setDate1(2);
//			cb.setDate2(2);
//			cb.setTheMonth("201702");
//			cb.setMaxNum(5);
//			System.out.println(service.mchefInput(cb));
			
			//Test for ChefInput
//			CalendarBean cb = new CalendarBean();
//			cb.setChefBean(service.chefDao.select(3002));
//			cb.setDate1(2);
//			cb.setDate2(2);
//			cb.setDate3(3);
//			cb.setTheMonth("201702");
//			cb.setMaxNum(5);
//			System.out.println(service.chefInput(cb));
			
//			Test for selecMchef
			System.out.println(service.selectMchef(1005, "201701"));
			
			//Test for selectChef
//			System.out.println(service.selectChef(3002, "201702"));
			
			sessionFactory.getCurrentSession().getTransaction().commit();
		} finally {
			sessionFactory.close();
			((ConfigurableApplicationContext) context).close();
		}
	}
	
	@Autowired
	CalendarDAO calendarDao;
	@Autowired
	MchefDAO mchefDao;
	@Autowired
	ChefDAO chefDao;
	
	public CalendarBean selectMchef(Integer mc_id, String date){
		return calendarDao.selectMchef(mc_id, date);
	}
	
	public CalendarBean selectChef(Integer c_id, String date){
		return calendarDao.selectChef(c_id, date);
	}
	
	public CalendarBean mchefInput(CalendarBean calendarBean){
		Integer mc_id = calendarBean.getMchefBean().getMc_id();
		String date = calendarBean.getTheMonth();
		//先確認table裡有沒有這筆資料
		CalendarBean cab = calendarDao.selectMchef(mc_id, calendarBean.getTheMonth());
		if (null == cab) {//選擇insert
			if (calendarDao.insert(calendarBean) > 0)
				return calendarDao.selectMchef(mc_id, calendarBean.getTheMonth());
		}else{//選擇update
			calendarBean.setCal_id(cab.getCal_id());
			CalendarBean cb = calendarDao.update(calendarBean);
			return cb;
		}
		return null;
	}

	public CalendarBean chefInput(CalendarBean calendarBean){
		Integer c_id = calendarBean.getChefBean().getC_id();
		String date = calendarBean.getTheMonth();
		//先確認table裡有沒有這筆資料
		CalendarBean cab = calendarDao.selectChef(c_id, date);
		if (null == cab) {//選擇insert
			if (calendarDao.insert(calendarBean) > 0)
				return calendarDao.selectChef(c_id, date);
		}else{//選擇update
			calendarBean.setCal_id(cab.getCal_id());
			CalendarBean cb = calendarDao.update(calendarBean);
			return cb;
		}
		return null;
	}
}
