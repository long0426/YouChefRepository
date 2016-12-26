package model;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

@Service(value="venueService")
public class VenueService {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		try {
			sessionFactory.getCurrentSession().beginTransaction();

			VenueService service = (VenueService) context.getBean("venueService");

			// insertVenue
//			VenueBean bean = new VenueBean();
//			bean.setV_name("台北福華大飯店");
//			bean.setV_address("臺北市仁愛路三段160號");
//			bean.setV_status("0");
//			System.out.println(service.insertVenue(bean));
			
			//select(v_id)
//			System.out.println(service.select(16002));
			
			//selectAll
//			System.out.println(service.selectAll());

			sessionFactory.getCurrentSession().getTransaction().commit();
		} finally {
			sessionFactory.close();
			((ConfigurableApplicationContext) context).close();
		}
	}

	@Autowired
	private VenueDAO venueDAO;

	public VenueBean insertVenue(VenueBean bean) {
		VenueBean result = null;
		if (bean != null) {
			result = venueDAO.insert(bean);
		}
		return result;

	}
	
	public VenueBean select(Integer v_id){
		return venueDAO.findByPrimaryKey(v_id);
	}

	public List<VenueBean> selectAll(){
		return venueDAO.getAll();
	}
}
