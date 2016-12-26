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
@Table(name = "chef")
public class ChefBean implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private int c_id;
	private String lastName;
	private String firstName;
	private String sex;
	private String phone;
	private String address;
	private String c_status;
	private String background;
	private byte[] photo;
	private TypeBean typeBean;
	private Integer years;
	private Set<OrdersBean> orders = new HashSet<OrdersBean>();
	private Set<CalendarBean> calendars = new HashSet<CalendarBean>();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getC_id() {
		return c_id;
	}

	public void setC_id(int c_id) {
		this.c_id = c_id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getC_status() {
		return c_status;
	}

	public void setC_status(String c_status) {
		this.c_status = c_status;
	}

	public String getBackground() {
		return background;
	}

	public void setBackground(String background) {
		this.background = background;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public Integer getYears() {
		return years;
	}

	public void setYears(Integer years) {
		this.years = years;
	}

	@ManyToOne
	@JoinColumn(name = "t_id")
	public TypeBean getTypeBean() {
		return typeBean;
	}

	public void setTypeBean(TypeBean typeBean) {
		this.typeBean = typeBean;
	}

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy = "chefBean")
	@OrderBy("o_id ASC")
	public Set<OrdersBean> getOrders() {
		return orders;
	}

	public void setOrders(Set<OrdersBean> orders) {
		this.orders = orders;
	}

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy = "chefBean")
	@OrderBy("cal_id ASC")
	public Set<CalendarBean> getCalendars() {
		return calendars;
	}

	public void setCalendars(Set<CalendarBean> calendars) {
		this.calendars = calendars;
	}

	@Override
	public String toString() {
		return "ChefBean [c_id=" + c_id + ", lastName=" + lastName + ", firstName=" + firstName + ", sex=" + sex
				+ ", phone=" + phone + ", address=" + address + ", c_status=" + c_status + ", background=" + background
				+ ", typeBean=" + typeBean + ", years=" + years + ", orders = " + orders ;
	}

}
