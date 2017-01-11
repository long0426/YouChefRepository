package model;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "calendarService")
@Transactional
public class CalendarService {
	@Autowired
	CalendarBean calendarBean;
	@Autowired
	ChefService chefService;
	@Autowired
	MchefService mchefService;

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			CalendarService service = (CalendarService) context.getBean("calendarService");
			
			//Test for MchefInput
//			CalendarBean cb = new CalendarBean();
//			cb.setMchefBean(service.mchefDao.select(1001));
//			cb.setDate1(2);
//			cb.setDate2(2);
//			cb.setTheMonth("201701");
//			cb.setMaxNum(5);
//			System.out.println(service.mchefInput(cb));
			
			//Test for ChefInput
//			CalendarBean cb = new CalendarBean();
//			cb.setChefBean(service.chefDao.select(4010));
//			cb.setDate1(2);
//			cb.setDate2(2);
//			cb.setDate3(3);
//			cb.setTheMonth("201702");
//			cb.setMaxNum(10);
//			System.out.println(service.chefInput(cb));
			
//			Test for selecMchef
//			System.out.println(service.selectMchef(1001, "201701"));
			
			//Test for selectChef
//			System.out.println(service.selectChef(4011, "201702"));
			
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
	
	public List<CalendarBean> selectChef(Integer c_id){
		return calendarDao.selectChef(c_id);
	}
	
	public List<CalendarBean> selectMchef(Integer mc_id){
		return calendarDao.selectMchef(mc_id);
	}
	
	public CalendarBean mchefInput(CalendarBean calendarBean){
		System.out.println("calendarBean = " + calendarBean);
		Integer mc_id = calendarBean.getMchefBean().getMc_id();
		String date = calendarBean.getTheMonth();
		//先確認table裡有沒有這筆資料
		CalendarBean cab = calendarDao.selectMchef(mc_id, date);
		if (null == cab) {//選擇insert
			if (calendarDao.insert(calendarBean) > 0)
				return calendarDao.selectMchef(mc_id, date);
			else
				return null;
		}else{//選擇update
			System.out.println("Start to update calendar");
//			calendarBean.setCal_id(cab.getCal_id());
			CalendarBean cb = calendarDao.update(calendarBean);
			cb = calendarDao.selectMchef(mc_id, date);
			System.out.println("After update, cb = " + cb);
			return cb;
		}
	}

	public CalendarBean chefInput(CalendarBean calendarBean){
		int c_id = calendarBean.getChefBean().getC_id();
		String date = calendarBean.getTheMonth();
//		先確認table裡有沒有這筆資料
		CalendarBean cab = calendarDao.selectChef(c_id, date);
		if (null == cab) {//選擇insert
			if (calendarDao.insert(calendarBean) > 0)
				return calendarDao.selectChef(c_id, date);
		}else{//選擇update
//			System.out.println("Update calendarBean");
//			calendarBean.setCal_id(cab.getCal_id());
//			System.out.println("update bean = " + calendarBean);
			CalendarBean cb = calendarDao.update(calendarBean);
			return cb;
		}
		return null;
	}

	public void newChefCalendar(int c_id, String date) {
		// TODO Auto-generated method stub
		calendarBean.setChefBean(chefService.select(c_id));
		calendarBean.setDate1(4);
		calendarBean.setDate2(4);
		calendarBean.setDate3(4);
		calendarBean.setDate4(4);
		calendarBean.setDate5(4);
		calendarBean.setDate6(4);
		calendarBean.setDate7(4);
		calendarBean.setDate8(4);
		calendarBean.setDate9(4);
		calendarBean.setDate10(4);
		calendarBean.setDate11(4);
		calendarBean.setDate12(4);
		calendarBean.setDate13(4);
		calendarBean.setDate14(4);
		calendarBean.setDate15(4);
		calendarBean.setDate16(4);
		calendarBean.setDate17(4);
		calendarBean.setDate18(4);
		calendarBean.setDate19(4);
		calendarBean.setDate20(4);
		calendarBean.setDate21(4);
		calendarBean.setDate22(4);
		calendarBean.setDate23(4);
		calendarBean.setDate24(4);
		calendarBean.setDate25(4);
		calendarBean.setDate26(4);
		calendarBean.setDate27(4);
		calendarBean.setDate28(4);
		calendarBean.setDate29(4);
		calendarBean.setDate30(4);
		calendarBean.setDate31(4);
		calendarBean.setTheMonth(date);
		calendarBean.setMaxNum(4);
		calendarDao.insert(calendarBean);
	}
	public void newMchefCalendar(int mc_id, String date) {
		// TODO Auto-generated method stub
		MchefBean mb = mchefService.select(mc_id);
		calendarBean.setMchefBean(mb);
		calendarBean.setDate1(mb.getQuota());
		calendarBean.setDate2(mb.getQuota());
		calendarBean.setDate3(mb.getQuota());
		calendarBean.setDate4(mb.getQuota());
		calendarBean.setDate5(mb.getQuota());
		calendarBean.setDate6(mb.getQuota());
		calendarBean.setDate7(mb.getQuota());
		calendarBean.setDate8(mb.getQuota());
		calendarBean.setDate9(mb.getQuota());
		calendarBean.setDate10(mb.getQuota());
		calendarBean.setDate11(mb.getQuota());
		calendarBean.setDate12(mb.getQuota());
		calendarBean.setDate13(mb.getQuota());
		calendarBean.setDate14(mb.getQuota());
		calendarBean.setDate15(mb.getQuota());
		calendarBean.setDate16(mb.getQuota());
		calendarBean.setDate17(mb.getQuota());
		calendarBean.setDate18(mb.getQuota());
		calendarBean.setDate19(mb.getQuota());
		calendarBean.setDate20(mb.getQuota());
		calendarBean.setDate21(mb.getQuota());
		calendarBean.setDate22(mb.getQuota());
		calendarBean.setDate23(mb.getQuota());
		calendarBean.setDate24(mb.getQuota());
		calendarBean.setDate25(mb.getQuota());
		calendarBean.setDate26(mb.getQuota());
		calendarBean.setDate27(mb.getQuota());
		calendarBean.setDate28(mb.getQuota());
		calendarBean.setDate29(mb.getQuota());
		calendarBean.setDate30(mb.getQuota());
		calendarBean.setDate31(mb.getQuota());
		calendarBean.setTheMonth(date);
		calendarBean.setMaxNum(mb.getQuota());
		calendarDao.insert(calendarBean);
	}
}
