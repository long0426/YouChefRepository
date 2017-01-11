package model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import model.MchefBean;


@Entity
@Table(name = "Orders")
public class OrdersBean implements Serializable{
	private static final long serialVersionUID = 1L;

	private int o_id;   
	private MemberBean memberBean;
	private MchefBean mchefBean;    
	private ChefBean chefBean;      
	private Date orderDate;  
	private String r_message;  
	private Float r_stars; 
	private String session;   
	private Float totalPrice; 
	private Timestamp dineDate; 
	private String dinePlace;  
	private String o_status;  
	private Timestamp updateTime;
	private Integer people;
	private Set<OrderDetailBean> orderDetails = new HashSet<OrderDetailBean>();



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)   
	public int getO_id() {
		return o_id;
	}
	public void setO_id(int o_id) {
		this.o_id = o_id;
	}
	@ManyToOne
	@JoinColumn(name = "m_id") 
	public MemberBean getMemberBean() {
		return memberBean;
	}
	public void setMemberBean(MemberBean memberBean) {
		this.memberBean = memberBean;
	}
	@ManyToOne
	@JoinColumn(name = "mc_id")
	public MchefBean getMchefBean() {
		return mchefBean;
	}
	public void setMchefBean(MchefBean mchefBean) {
		this.mchefBean = mchefBean;
	}
	@ManyToOne
	@JoinColumn(name = "c_id")
	public ChefBean getChefBean() {
		return chefBean;
	}
	public void setChefBean(ChefBean chefBean) {
		this.chefBean = chefBean;
	}
	
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
	public String getR_message() {
		return r_message;
	}
	public void setR_message(String r_message) {
		this.r_message = r_message;
	}

	public Float getR_stars() {
		return r_stars;
	}
	public void setR_stars(Float r_stars) {
		this.r_stars = r_stars;
	}
	
	public String getSession() {
		return session;
	}
	public void setSession(String session) {
		this.session = session;
	}

	public Float getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Float totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public Timestamp getDineDate() {
		return dineDate;
	}
	public void setDineDate(Timestamp dineDate) {
		this.dineDate = dineDate;
	}
	
	public String getDinePlace() {
		return dinePlace;
	}
	public void setDinePlace(String dinePlace) {
		this.dinePlace = dinePlace;
	}
	
	public String getO_status() {
		return o_status;
	}
	public void setO_status(String o_status) {
		this.o_status = o_status;
	}
	
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	
	public Integer getPeople() {
		return people;
	}
	public void setPeople(Integer people) {
		this.people = people;
	}
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="ordersBean")
	@OrderBy("od_id ASC")
	public Set<OrderDetailBean> getOrderDetails() {
		return orderDetails;
	}
	public void setOrderDetails(Set<OrderDetailBean> orderDetails) {
		this.orderDetails = orderDetails;
	}

	
	@Override
	public String toString() {
		return "OrdersBean [o_id=" + o_id + ", orderDate=" + orderDate + ", r_message=" + r_message + ", r_stars=" + r_stars
				+ ", session=" + session + ", totalPrice=" + totalPrice + ", dineDate=" + dineDate + ", dinePlace="
				+ dinePlace + ", o_status=" + o_status + ", updateTime=" + updateTime + ", people=" + people
				 ;
	}

	

	
	
}
