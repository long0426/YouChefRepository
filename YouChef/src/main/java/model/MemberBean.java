package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "member")
public class MemberBean implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	@Id
//	@SequenceGenerator(name="v_id", allocationSize=1)
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer m_id;
	private String lastName;
	private String firstName;
	private String nickname;
	private String sex;
	private String email;
	private String phone;
	private String password;
	private String city;
	private String district;
	private String address;
	private String facebook;
	private String ac_status;
	private String briefing;
	private Byte[] photo;
	private Integer absent;
	
	@Id
	@SequenceGenerator(name="v_id", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getM_id() {
		return m_id;
	}
	public void setM_id(Integer m_id) {
		this.m_id = m_id;
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
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getFacebook() {
		return facebook;
	}
	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}
	public String getAc_status() {
		return ac_status;
	}
	public void setAc_status(String ac_status) {
		this.ac_status = ac_status;
	}
	public String getBriefing() {
		return briefing;
	}
	public void setBriefing(String briefing) {
		this.briefing = briefing;
	}
	public Byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(Byte[] photo) {
		this.photo = photo;
	}
	public Integer getAbsent() {
		return absent;
	}
	public void setAbsent(Integer absent) {
		this.absent = absent;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
