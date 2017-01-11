package model.misc;

import java.util.List;

public class MchefApplyBean {
	// private Integer m_id;
	// private String background;
	// private Integer years;
	// private byte[] photo;
	// private String d_name;
	// private String d_briefing;
	// private String menu;
	// private Integer t_id;
	// private Double price;
	// private List<byte[]> d_photo;
	// private Integer quota;
	// private Integer v_id;
	// private String venue;
	private String email;
	private String name;
	private int m_id;
	private String background;
	private int years;
	private String d_name;
	private String d_briefing;
	private String menu;
	private int t_id;
	private double price;
	private int quota;
	private int v_id;
	private String venue;

	@Override
	public String toString() {
		return "MchefApplyBean[m_id = " + m_id + ", background = " + background + ", years = " + years + ", d_name = "
				+ d_name + ", d_briefing = " + d_briefing + ", menu = " + menu + ", t_id = " + t_id + ", price = "
				+ price + ", quota = " + quota + ", v_id = " + v_id + ", venue = " + venue;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getM_id() {
		return m_id;
	}

	public void setM_id(int m_id) {
		this.m_id = m_id;
	}

	public String getBackground() {
		return background;
	}

	public void setBackground(String background) {
		this.background = background;
	}

	public int getYears() {
		return years;
	}

	public void setYears(int years) {
		this.years = years;
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

	public int getT_id() {
		return t_id;
	}

	public void setT_id(int t_id) {
		this.t_id = t_id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuota() {
		return quota;
	}

	public void setQuota(int quota) {
		this.quota = quota;
	}

	public int getV_id() {
		return v_id;
	}

	public void setV_id(int v_id) {
		this.v_id = v_id;
	}

	public String getVenue() {
		return venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}


}
