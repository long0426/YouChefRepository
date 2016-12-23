package model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name="dishes")
public class DishesBean implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private int d_id;
	private MchefBean mchefBean;
	private double price; 
	private	String d_name;
	private String d_briefing;
	private	String menu;
	private TypeBean typeBean;
	private String d_status;
	private Set<OrderDishesBean> orderDishes = new HashSet<OrderDishesBean>();
	@Override
	public String toString() {
		return "DishesBean [d_id=" + d_id + ", mchefBean=" + mchefBean + ", price=" + price + ", d_name=" + d_name
				+ ", d_briefing=" + d_briefing + ", menu=" + menu + ", typeBean=" + typeBean + ", d_status=" + d_status
				+ "]";
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getD_id() {
		return d_id;
	}
	public void setD_id(int d_id) {
		this.d_id = d_id;
	}
	public double getPrice(){
		return price;
	}
	public void setPrice(double price) {
		this.price = price;	
	}
	public String getD_name() {
		return d_name;
	}
	public void setD_name(String d_name) {
		this.d_name = d_name;
	}
	public String getD_briefing() {
		return d_briefing;
	}
	public void setD_briefing(String d_briefing) {
		this.d_briefing = d_briefing;
	}
	public String getMenu() {
		return menu;
	}
	public void setMenu(String menu) {
		this.menu = menu;
	}
	public String getD_status() {
		return d_status;
	}
	public void setD_status(String d_status) {
		this.d_status = d_status;
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
	@JoinColumn(name = "t_id")
	public TypeBean getTypeBean() {
		return typeBean;
	}
	public void setTypeBean(TypeBean typeBean) {
		this.typeBean = typeBean;
	}
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="dishesBean")
	@OrderBy("ods_id ASC")
	public Set<OrderDishesBean> getOrderDishes() {
		return orderDishes;
	}
	public void setOrderDishes(Set<OrderDishesBean> orderDishes) {
		this.orderDishes = orderDishes;
	}
}
