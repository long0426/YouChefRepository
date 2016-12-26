package model;

import java.io.Serializable;
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


@Entity
@Table(name = "OrderDetail")
public class OrderDetailBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int od_id; 
	private OrdersBean ordersBean;
	private Set<OrderDishesBean> orderDishes = new HashSet<OrderDishesBean>();

	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)   
	public int getOd_id() {
		return od_id;
	}
	public void setOd_id(int od_id) {
		this.od_id = od_id;
	}
	
	@ManyToOne 
	@JoinColumn(name = "o_id")
	public OrdersBean getOrdersBean() {
		return ordersBean;
	}
	public void setOrdersBean(OrdersBean ordersBean) {
		this.ordersBean = ordersBean;
	}
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="orderDetailBean")
	@OrderBy("ods_id ASC")
	public Set<OrderDishesBean> getOrderDishes() {
		return orderDishes;
	}
	public void setOrderDishes(Set<OrderDishesBean> orderDishes) {
		this.orderDishes = orderDishes;
	}
	




	

   
}
