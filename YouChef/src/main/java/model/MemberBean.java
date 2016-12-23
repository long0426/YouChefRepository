package model;

import java.util.Arrays;
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
import javax.persistence.Table;

@Entity
@Table(name="member")
public class MemberBean {

	private int m_id;
	private	String lastName;
	private	String firstName;
	private String nickname;
	private	String sex;
	private String email;
	private String password;
	private	String phone;
	private String city;
	private String district;
	private String address;
	private String facebook;
	private	String ac_status;
	private	String briefing;
	private byte[] photo;
	private Integer absent;
	private Set<InboxBean> sender = new HashSet<InboxBean>();
	private Set<InboxBean> receiver = new HashSet<InboxBean>();
	private Set<CsBean> csBean = new HashSet<CsBean>();
	
	public MemberBean() {
		
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getM_id() {
		return m_id;
	}
	public void setM_id(int m_id) {
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	public Integer getAbsent() {
		return absent;
	}
	public void setAbsent(Integer absent) {
		this.absent = absent;
	}
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="sender")
	public Set<InboxBean> getSender() {
		return sender;
	}

	public void setSender(Set<InboxBean> sender) {
		this.sender = sender;
	}
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="receiver")
	public Set<InboxBean> getReceiver() {
		return receiver;
	}

	public void setReceiver(Set<InboxBean> receiver) {
		this.receiver = receiver;
	}
	
//	@OneToMany
//	@JoinColumn(name="m_id")
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="memberBean")
	public Set<CsBean> getCsBean() {
		return csBean;
	}

	public void setCsBean(Set<CsBean> csBean) {
		this.csBean = csBean;
	}

	@Override
	public String toString() {
		return "MemberBean [m_id=" + m_id + ", lastName=" + lastName + ", firstName=" + firstName + ", nickname="
				+ nickname + ", sex=" + sex + ", email=" + email + ", password=" + password + ", phone=" + phone
				+ ", city=" + city + ", district=" + district + ", address=" + address + ", facebook=" + facebook
				+ ", ac_status=" + ac_status + ", briefing=" + briefing + ", photo=" + Arrays.toString(photo)
				+ ", absent=" + absent + "]";
	}	
}
