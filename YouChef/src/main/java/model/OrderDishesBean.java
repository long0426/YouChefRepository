package model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "OrderDishes")
public class OrderDishesBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int ods_id;
	private OrderDetailBean orderDetailBean;
	private DishesBean dishesBean;     
	private Integer dishNum; 
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)   
	public int getOds_id() {
		return ods_id;
	}
	public void setOds_id(int ods_id) {
		this.ods_id = ods_id;
	}
	
	
	@ManyToOne 
	@JoinColumn(name = "od_id")
	public OrderDetailBean getOrderDetailBean() {
		return orderDetailBean;
	}
	public void setOrderDetailBean(OrderDetailBean orderDetailBean) {
		this.orderDetailBean = orderDetailBean;
	}
	
	@ManyToOne 
	@JoinColumn(name = "d_id")
	public DishesBean getDishesBean() {
		return dishesBean;
	}
	public void setDishesBean(DishesBean dishesBean) {
		this.dishesBean = dishesBean;
	}
	

	
	public Integer getDishNum() {
		return dishNum;
	}
	public void setDishNum(Integer dishNum) {
		this.dishNum = dishNum;
	}

	
	

}
