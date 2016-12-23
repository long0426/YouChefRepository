package model.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import model.MemberBean;
import model.MemberDAO;
import model.misc.GlobalService;

@Repository(value="memberDAO")
@Transactional
public class MemberBeanDAOHibernate implements MemberDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	public MemberBeanDAOHibernate(){}
	public MemberBeanDAOHibernate(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public MemberBean select(int m_id) {
		return getSession().get(MemberBean.class, m_id);
	}

	@Override
	public MemberBean insert(MemberBean bean) {
		if(bean!=null){
			MemberBean insert = this.getSession().get(MemberBean.class, bean.getM_id());
			if(insert==null){
				this.getSession().save(bean);
				return bean;
			}
		}
		return null;
	}

	@Override
	public List<MemberBean> select() {
		Query query = this.getSession().createQuery("from MemberBean");
		return (List<MemberBean>) query.getResultList();
	}

	@Override
	public MemberBean update(int m_id, String fisrtName, String lastName, String nickname, String sex, String city,
			String district, String address, String briefing, byte[] photo) {
		MemberBean update = this.getSession().get(MemberBean.class, m_id );
		if(update!=null){
			update.setLastName(lastName);
			update.setFirstName(fisrtName);
			update.setNickname(nickname);
			update.setSex(sex);
			update.setCity(city);
			update.setDistrict(district);
			update.setAddress(address);
			update.setBriefing(briefing);
			update.setPhoto(photo);
		}
		return update;
	}
	@Override
	public MemberBean select(String email) {
		MemberBean result=null;
		Query query = this.getSession().createQuery("from MemberBean where email= :email");
		query.setParameter("email", email);
		try{
		result= (MemberBean) query.getSingleResult();
		}catch (NoResultException nre){
			return null;
		}
		return result;
	}
	@Override
	public boolean update(String password, int m_id) {
		MemberBean bean=null;
		bean = this.getSession().get(MemberBean.class, m_id);
		if(bean!=null){
			bean.setPassword(password);
			return true;
		}
		return false;
	}
	
}
