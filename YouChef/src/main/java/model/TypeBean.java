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
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name="type")
public class TypeBean implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private int t_id;
	private String t_name;
	private Set<ChefBean> chefBean = new HashSet<ChefBean>();
	private Set<DishesBean> dishesBean = new HashSet<DishesBean>();


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getT_id() {
		return t_id;
	}
	public void setT_id(int t_id) {
		this.t_id = t_id;
	}
	public String getT_name() {
		return t_name;
	}
	public void setT_name(String t_name) {
		this.t_name = t_name;
	}	
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="typeBean")
	@OrderBy("c_id ASC")
	public Set<ChefBean> getChefBean(){
		return chefBean;
	}
	public void  setChefBean(Set<ChefBean> chefBean){
		this.chefBean = chefBean;
	}
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="typeBean")
	@OrderBy("d_id ASC")
	public Set<DishesBean> getDishesBean(){
		return dishesBean;
	}
	public void  setDishesBean(Set<DishesBean> dishesBean){
		this.dishesBean = dishesBean;
	}
	@Override
	public String toString() {
		return "TypeBean [t_id=" + t_id + ", t_name=" + t_name 
				;
	}
	
	
	
}
