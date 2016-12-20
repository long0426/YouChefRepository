package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="dishes")
public class DishesBean implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer d_id;
	@OneToOne
	@JoinColumn(name = "m_id")
	private MemberBean memberBean;;
	private Double price; 
	private	String d_name;
	private String d_briefing;
	private	String menu;
	@ManyToOne
	@JoinColumn(name = "t_id")
	private TypeBean typeBean;
	private String d_status;
	
	@Override
	public String toString() {
		return "DishesBean [d_id=" + d_id + ", memberBean=" + memberBean + ", price=" + price + ", d_name=" + d_name
				+ ", d_briefing=" + d_briefing + ", menu=" + menu + ", typeBean=" + typeBean + ", d_status=" + d_status
				+ "]";
	}
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	
//	@OneToOne
//	@JoinColumn(name = "m_id")
	public MemberBean getMemberBean() {
		return memberBean;
	}

	public void setMemberBean(MemberBean memberBean) {
		this.memberBean = memberBean;
	}
	
//	@ManyToOne
//	@JoinColumn(name = "t_id")
	public TypeBean getT_id() {
		return typeBean;
	}

	public void setT_id(TypeBean t_id) {
		this.typeBean = t_id;
	}
	
}
