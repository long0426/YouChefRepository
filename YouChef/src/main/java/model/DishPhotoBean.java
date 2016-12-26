package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "dishPhoto")
public class DishPhotoBean implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer dp_id;
	private MchefBean mchefBean;
	private byte[] d_photo;
	private DishesBean dishesBean;
	
	@Override
	public String toString() {
		return "DishPhotoBean[dp_id = " + dp_id + ", md_id = " + mchefBean + ", d_photo = " 
				+ d_photo + ", d_id = " + dishesBean + "]";
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getDp_id() {
		return dp_id;
	}
	public void setDp_id(Integer dp_id) {
		this.dp_id = dp_id;
	}
	@OneToOne
	@JoinColumn(name="mc_id")
	public MchefBean getMchefBean() {
		return mchefBean;
	}
	public void setMchefBean(MchefBean mchefBean) {
		this.mchefBean = mchefBean;
	}
	public byte[] getD_photo() {
		return d_photo;
	}
	public void setD_photo(byte[] d_photo) {
		this.d_photo = d_photo;
	}
	@OneToOne
	@JoinColumn(name="d_id")
	public DishesBean getDishesBean() {
		return dishesBean;
	}
	public void setDishesBean(DishesBean dishesBean) {
		this.dishesBean = dishesBean;
	}
	
}
