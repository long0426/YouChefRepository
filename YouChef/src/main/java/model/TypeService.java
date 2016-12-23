package model;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

@Service(value = "typeService")
public class TypeService {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		try {sessionFactory.getCurrentSession().beginTransaction();

		TypeService service = (TypeService) context.getBean("typeService");
		
		//insert
//		TypeBean typeBean = new TypeBean();
//		typeBean.setT_name("川味");
//		service.insert(typeBean);
//		typeBean.setT_name("日式");
//		service.insert(typeBean);
//		typeBean.setT_name("台式");
//		service.insert(typeBean);
//		typeBean.setT_name("西式");
//		service.insert(typeBean);
//		typeBean.setT_name("東南亞");
//		service.insert(typeBean);
		
		//select
//		System.out.println(service.select(3004).getT_name());
		
		//selectAll
//		System.out.println(service.selectAll());
		
		sessionFactory.getCurrentSession().getTransaction().commit();
	}finally {
		sessionFactory.close();
		((ConfigurableApplicationContext) context).close();
	}
}
	@Autowired
	private TypeDAO typeDao;
	public TypeBean select(int t_id) {
		return typeDao.select(t_id);
	}
	public TypeBean insert(TypeBean typeBean){
		TypeBean result = null;
		if(typeBean != null){
			result = typeDao.insert(typeBean);
		}
		return result;
	}
	public List<TypeBean> selectAll() {
		List<TypeBean> list = typeDao.select();
		return list;
	}
}

