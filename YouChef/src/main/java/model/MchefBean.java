package model;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "mChef")
public class MchefBean implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer mc_id;
	private MemberBean memberBean;
	private Integer years;
	private String venue;
	private Integer quota;
	private String background;
	private VenueBean v_id;
	private String hasPlace;

	@Override
	public String toString() {
		return "MchefBean[mc_id = " + mc_id + ", years = " + years + ", venue = " 
				+ venue + ", quota = " + quota + ", background = " + background 
				+ ", v_id = " + v_id + ", hasPlace = " + hasPlace + "]";
	}

	@Id  
	public Integer getMc_id(){
		return mc_id;
	}
	
	public void setMc_id(Integer mc_id) {
		this.mc_id = mc_id;
	}
	
	@OneToOne
	@JoinColumn(name = "mc_id")
	public MemberBean getMemberBean() {
		return memberBean;
	}

	public void setMemberBean(MemberBean memberBean) {
		this.memberBean = memberBean;
	}

	public Integer getYears() {
		return years;
	}

	public void setYears(Integer years) {
		this.years = years;
	}

	public String getVenue() {
		return venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}

	public Integer getQuota() {
		return quota;
	}

	public void setQuota(Integer quota) {
		this.quota = quota;
	}

	public String getBackground() {
		return background;
	}

	public void setBackground(String background) {
		this.background = background;
	}

	@ManyToOne 
	@JoinColumn(name = "v_id")
	public VenueBean getV_id() {
		return v_id;
	}

	public void setV_id(VenueBean v_id) {
		this.v_id = v_id;
	}

	public String getHasPlace() {
		return hasPlace;
	}

	public void setHasPlace(String hasPlace) {
		this.hasPlace = hasPlace;
	}
}
