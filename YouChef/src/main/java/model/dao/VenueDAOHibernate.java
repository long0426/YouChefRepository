package model.dao;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;




import model.VenueBean;
import model.VenueDAO;


@Repository(value="venueDAO")
public class VenueDAOHibernate implements VenueDAO{
	
	private static final String GET_ALL_STMT = "from VenueBean order by v_id";
	
	@Autowired
	private SessionFactory sessionFactory;
    public Session getSession(){
    	return sessionFactory.getCurrentSession();
    }
    
    
    public static void main (String[] args){
    	ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
		SessionFactory sessionFactory = (SessionFactory)context.getBean("sessionFactory");
	
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			VenueDAO venues = (VenueDAOHibernate) context.getBean("venueDAO");
			List <VenueBean> all = (List <VenueBean>) venues.getAll();
			System.out.println("all= "+ all);
			System.out.println("----------------");
//			VenueBean vb = new VenueBean();
//			vb.setV_id(15006);
//			vb.setV_name("Pacific Business Hotel 太平洋商旅");
//			vb.setV_address("台北市信義區光復南路495號");
//			vb.setV_status("1");
//			System.out.println("insert= "+ venues.insert(vb));
			System.out.println("----------------");
//			System.out.println("update= "+ venues.update("101 總裁行館", "台北市信義區福德街169號", "1", 15000));
			System.out.println("----------------");
//			System.out.println("single= "+ venues.findByPrimaryKey(15002));
			
			
			
			
			sessionFactory.getCurrentSession().getTransaction().commit();
		} finally {
			sessionFactory.close();
			 ((ConfigurableApplicationContext) context).close();
		}
		
    
    }

	@Override
	public VenueBean insert(VenueBean venueBean) {
		if(venueBean!=null){
			VenueBean inserted = this.getSession().get(VenueBean.class, venueBean.getV_id());
			if(inserted==null){
				this.getSession().save(venueBean);
				return venueBean;
			}
		}
		return null;
	
	}

	@Override
	public VenueBean update(String v_name, String v_address, String v_status, Integer v_id) {
		VenueBean updated = this.getSession().get(VenueBean.class,v_id);
    	if(updated!=null){
    		updated.setV_name(v_name);
    		updated.setV_address(v_address);
    		updated.setV_status(v_status);

    	}
    	return updated;
	
	}

	@Override
	public VenueBean findByPrimaryKey(Integer v_id) {
		return this.getSession().get(VenueBean.class, v_id);
	
	}

	@Override
	public List<VenueBean> getAll() {
		javax.persistence.Query query = this.getSession().createQuery(GET_ALL_STMT);
    	return (List<VenueBean>) query.getResultList();
    	
    	
    	
	}

}
