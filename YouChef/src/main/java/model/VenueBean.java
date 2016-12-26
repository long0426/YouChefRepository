package model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;




@Entity
@Table(name = "Venue")
public class VenueBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int v_id;    
	private String v_name;    
	private String v_address; 
	private String v_status;
	private Set<MchefBean> mchef = new HashSet<MchefBean>();
	

	
	
	@Id
	@Column(name = "v_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)   
	public int getV_id() {
		return v_id;
	}
	public void setV_id(int v_id) {
		this.v_id = v_id;
	}
	@Column(name = "v_name")
	public String getV_name() {
		return v_name;
	}
	public void setV_name(String v_name) {
		this.v_name = v_name;
	}
	@Column(name = "v_address")
	public String getV_address() {
		return v_address;
	}
	public void setV_address(String v_address) {
		this.v_address = v_address;
	}
	
	@Column(name = "v_status")
	public String getV_status() {
		return v_status;
	}
	public void setV_status(String v_status) {
		this.v_status = v_status;
	}
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="mc_id")
	@OrderBy("mc_id ASC")
	public Set<MchefBean> getMchef() {
		return mchef;
	}
	public void setMchef(Set<MchefBean> mchef) {
		this.mchef = mchef;
	}
	@Override
	public String toString() {
		return "VenueBean [v_id=" + v_id + ", v_name=" + v_name + ", v_address=" + v_address + ", v_status=" + v_status
				+ ", mchef=" + mchef ;
	}

	
	
}
