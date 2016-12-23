package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="review")
public class ReviewBean implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer r_id;
	@ManyToOne
	@JoinColumn(name = "mc_id")
	private MchefBean mchefBean;
	@ManyToOne
	@JoinColumn(name = "c_id")
	private ChefBean chefBean;
	private Double stars;
	private Integer orderNum;

	public Integer getR_id(){
		return r_id;
	}
	public void setR_id(Integer r_id){
		this.r_id = r_id;
	}
	public MchefBean getMchefBean() {
		return mchefBean;
	}
	public void setMchefBean(MchefBean mchefBean) {
		this.mchefBean = mchefBean;
	}
	public ChefBean getChefBean() {
		return chefBean;
	}
	public void setChefBean(ChefBean chefBean) {
		this.chefBean = chefBean;
	}
	
	public Double getStars(){
		return stars;
	}
	public void setStars(Double stars){
	 this.stars = stars;
	}
	public Integer getOrderNum(){
		return orderNum;
	}
	public void setOrderNum(Integer orderNum){
		this.orderNum = orderNum;
	}
	@Override
	public String toString() {
		return "ReviewBean [r_id=" + r_id +", mchefBean=" + mchefBean + ", chefBean=" + chefBean
				+", stars=" + stars +", orderNum=" + orderNum + ", toString()=" + super.toString() + "]";
	}
}
