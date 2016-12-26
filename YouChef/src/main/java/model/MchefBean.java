package model;
import org.hibernate.annotations.Parameter;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "mChef")
public class MchefBean implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer mc_id;
	private Integer years;
	private String venue;
	private Integer quota;
	private String background;
	private VenueBean venueBean;
	private String hasPlace;
	private Set<OrdersBean> orders = new HashSet<OrdersBean>();
	private Set<CalendarBean> calendars = new HashSet<CalendarBean>();


	@Id  
    @GeneratedValue(generator="myGenerator")  
    @GenericGenerator(name="myGenerator", strategy="foreign", parameters= @Parameter(value="member", name = "property"))
	public Integer getMc_id(){
		return mc_id;
	}
	
	public void setMc_id(Integer mc_id) {
		this.mc_id = mc_id;
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
	public VenueBean getVenueBean() {
		return venueBean;
	}

	public void setVenueBean(VenueBean venueBean) {
		this.venueBean = venueBean;
	}

	public String getHasPlace() {
		return hasPlace;
	}

	public void setHasPlace(String hasPlace) {
		this.hasPlace = hasPlace;
	}
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="mchefBean")
	@OrderBy("o_id ASC")
	public Set<OrdersBean> getOrders() {
		return orders;
	}

	public void setOrders(Set<OrdersBean> orders) {
		this.orders = orders;
	}
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="mchefBean")
	@OrderBy("cal_id ASC")
	public Set<CalendarBean> getCalendars() {
		return calendars;
	}

	public void setCalendars(Set<CalendarBean> calendars) {
		this.calendars = calendars;
	}
	

	@Override
	public String toString() {
		return "MchefBean [mc_id=" + mc_id + ", years=" + years + ", venue=" + venue + ", quota=" + quota
				+ ", background=" + background + ", venueBean=" + venueBean + ", hasPlace=" + hasPlace + ", orders="
				+ orders + ", calendars=" + calendars;
	}


	
	
}
