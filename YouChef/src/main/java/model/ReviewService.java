package model;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;


@Service(value = "reviewService")
public class ReviewService {
	@Autowired 
	private ReviewDAO reviewDao;
	@Autowired
	private MchefDAO mchefDao;
	@Autowired
	private ChefDAO chefDao;
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		try {
			sessionFactory.getCurrentSession().beginTransaction();                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    
			ReviewService service = (ReviewService) context.getBean("reviewService");
			
			//insert
			ReviewBean bean = new ReviewBean();
			bean.setMchefBean(service.mchefDao.select(1002));
			bean.setChefBean(service.chefDao.select(4010));
			bean.setStars(6d);
			bean.setOrderNum(2);
			System.out.println(service.insert(bean));
			
			//update
//			ReviewBean bean = new ReviewBean();
//			bean.setMchefBean(service.mchefDao.select(1002));
//			bean.setChefBean(service.chefDao.select(4010));
//			bean.setStars(7d);
//			bean.setOrderNum(2);
//			bean.setR_id(15001);
//			System.out.println(service.update(bean));
			
			//select
//			ReviewBean bean = new ReviewBean();
//			bean.setR_id(15001);
//			System.out.println(service.select(bean));
			
			//selectAll
			System.out.println(service.selectAll());
			
//			bean.setR_id(14000);
//			ReviewBean rb = service.select(bean);
//			System.out.println("ReviewBean = " + rb);
//			ReviewBean bean = new ReviewBean();
//			MchefBean mb = new MchefBean();
//			mb.setMc_id(1001);
//			mb = mchefDao.select(mb);
//			bean.setMchefBean(mb);
//			bean.setChefBean(null);
//			bean.setStars(5.0);
//			bean.setOrderNum(1);
//			System.out.println("ReviewBean = " + bean);
//			service.insert(bean);
			
			
			
			sessionFactory.getCurrentSession().getTransaction().commit();
		} finally {
			sessionFactory.close();
			((ConfigurableApplicationContext) context).close();
		}
	}
	
	private ReviewBean select(ReviewBean bean) {
		return reviewDao.select(bean);
	}
	
	private int insert(ReviewBean bean) {
		return reviewDao.insert(bean);
	}
	public List<ReviewBean> selectAll() {
		List<ReviewBean> list = reviewDao.selectAll();
		return list;
	}
	private ReviewBean update(ReviewBean bean){
		return reviewDao.update(bean);
	}
	
//	public ReviewBean reviewMchefInput(ReviewBean reviewBean){
//		Integer mc_id = reviewBean.getMchefBean().getMc_id();
//		//確認table裡有沒有這筆資料
//		ReviewBean rmb = reviewDao.selectMchefReview(mc_id);
//		if (null == rmb) {//insert
//			if (reviewDao.insert(reviewBean) > 0)
//				return reviewDao.selectMchefReview(mc_id);
//		}else{//update
//			reviewBean.setR_id(rmb.getR_id());
//			ReviewBean rmbup = reviewDao.update(reviewBean);
//			return rmbup;
//		}
//		return null;
//	}
//	
//	public ReviewBean reviewChefInput(ReviewBean reviewBean){
//		Integer c_id = reviewBean.getChefBean().getC_id();
//		//確認table裡有沒有這筆資料
//		ReviewBean rmb = reviewDao.selectChefReview(c_id);
//		if (null == rmb) {//insert
//			if (reviewDao.insert(reviewBean) > 0)
//				return reviewDao.selectChefReview(c_id);
//		}else{//update
//			reviewBean.setR_id(rmb.getR_id());
//			ReviewBean rmbup = reviewDao.update(reviewBean);
//			return rmbup;
//		}
//		return null;
//	}
}
