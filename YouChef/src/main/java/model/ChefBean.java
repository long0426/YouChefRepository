package model;


import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="chef")
public class ChefBean implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private int c_id;
	private	String lastName;
	private	String firstName;
	private	String sex;
	private	String phone;
	private	String address;
	private	String c_status;
	private	String background;
	private byte[] photo;
	private TypeBean typeBean;
	private int years;
	
	
	@Override
	public String toString() {
		return "ChefBean [c_id=" + c_id + ", lastName=" + lastName + ", firstName=" + firstName + ", sex=" + sex
				+ ", phone=" + phone + ", address=" + address + ", c_status=" + c_status + ", background=" + background
				+ ", photo=" + Arrays.toString(photo) + ", TypeBean=" + typeBean + ", years=" + years + "]";
	}
	
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
	public int getYears() {
		return years;
	}
	public void setYears(int years) {
		this.years = years;
	}
	
	@ManyToOne
	@JoinColumn(name = "t_id")
	public TypeBean getT_id() {
		return typeBean;
	}

	public void setT_id(TypeBean t_id) {
		this.typeBean = t_id;
	}
	
}
