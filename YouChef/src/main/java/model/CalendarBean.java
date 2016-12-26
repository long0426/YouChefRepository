package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "calendar")
public class CalendarBean implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private Integer cal_id;
	
	private ChefBean chefBean;
	
	private MchefBean mchefBean;
	private Integer date1;
	private Integer date2;
	private Integer date3;
	private Integer date4;
	private Integer date5;
	private Integer date6;
	private Integer date7;
	private Integer date8;
	private Integer date9;
	private Integer date10;
	private Integer date11;
	private Integer date12;
	private Integer date13;
	private Integer date14;
	private Integer date15;
	private Integer date16;
	private Integer date17;
	private Integer date18;
	private Integer date19;
	private Integer date20;
	private Integer date21;
	private Integer date22;
	private Integer date23;
	private Integer date24;
	private Integer date25;
	private Integer date26;
	private Integer date27;
	private Integer date28;
	private Integer date29;
	private Integer date30;
	private Integer date31;
	private String theMonth;
	private Integer maxNum;
	
	@Override
	public String toString() {
		return "CalendarBean[cal_id = " + cal_id + ",  mc_id = " + mchefBean
				+ ", date1 = " + date1
				+ ", date2 = " + date2
				+ ", date3 = " + date3
				+ ", date4 = " + date4
				+ ", date5 = " + date5
				+ ", date6 = " + date6
				+ ", date7 = " + date7
				+ ", date8 = " + date8
				+ ", date9 = " + date9
				+ ", date10 = " + date10
				+ ", date11 = " + date11
				+ ", date12 = " + date12
				+ ", date13 = " + date13
				+ ", date14 = " + date14
				+ ", date15 = " + date15
				+ ", date16 = " + date16
				+ ", date17 = " + date17
				+ ", date18 = " + date18
				+ ", date19 = " + date19
				+ ", date20 = " + date20
				+ ", date21 = " + date21
				+ ", date22 = " + date22
				+ ", date23 = " + date23
				+ ", date24 = " + date24
				+ ", date25 = " + date25
				+ ", date26 = " + date26
				+ ", date27 = " + date27
				+ ", date28 = " + date28
				+ ", date29 = " + date29
				+ ", date30 = " + date30
				+ ", date31 = " + date31
				+ ", theMonth = " + theMonth
				+ ", maxNum = " + maxNum
				+"]";
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getCal_id() {
		return cal_id;
	}
	public void setCal_id(Integer cal_id) {
		this.cal_id = cal_id;
	}
	public Integer getDate1() {
		return date1;
	}
	public void setDate1(Integer date1) {
		this.date1 = date1;
	}
	public Integer getDate2() {
		return date2;
	}
	public void setDate2(Integer date2) {
		this.date2 = date2;
	}
	public Integer getDate3() {
		return date3;
	}
	public void setDate3(Integer date3) {
		this.date3 = date3;
	}
	public Integer getDate4() {
		return date4;
	}
	public void setDate4(Integer date4) {
		this.date4 = date4;
	}
	public Integer getDate5() {
		return date5;
	}
	public void setDate5(Integer date5) {
		this.date5 = date5;
	}
	public Integer getDate6() {
		return date6;
	}
	public void setDate6(Integer date6) {
		this.date6 = date6;
	}
	public Integer getDate7() {
		return date7;
	}
	public void setDate7(Integer date7) {
		this.date7 = date7;
	}
	public Integer getDate8() {
		return date8;
	}
	public void setDate8(Integer date8) {
		this.date8 = date8;
	}
	public Integer getDate9() {
		return date9;
	}
	public void setDate9(Integer date9) {
		this.date9 = date9;
	}
	public Integer getDate10() {
		return date10;
	}
	public void setDate10(Integer date10) {
		this.date10 = date10;
	}
	public Integer getDate11() {
		return date11;
	}
	public void setDate11(Integer date11) {
		this.date11 = date11;
	}
	public Integer getDate12() {
		return date12;
	}
	public void setDate12(Integer date12) {
		this.date12 = date12;
	}
	public Integer getDate13() {
		return date13;
	}
	public void setDate13(Integer date13) {
		this.date13 = date13;
	}
	public Integer getDate14() {
		return date14;
	}
	public void setDate14(Integer date14) {
		this.date14 = date14;
	}
	public Integer getDate15() {
		return date15;
	}
	public void setDate15(Integer date15) {
		this.date15 = date15;
	}
	public Integer getDate16() {
		return date16;
	}
	public void setDate16(Integer date16) {
		this.date16 = date16;
	}
	public Integer getDate17() {
		return date17;
	}
	public void setDate17(Integer date17) {
		this.date17 = date17;
	}
	public Integer getDate18() {
		return date18;
	}
	public void setDate18(Integer date18) {
		this.date18 = date18;
	}
	public Integer getDate19() {
		return date19;
	}
	public void setDate19(Integer date19) {
		this.date19 = date19;
	}
	public Integer getDate20() {
		return date20;
	}
	public void setDate20(Integer date20) {
		this.date20 = date20;
	}
	public Integer getDate21() {
		return date21;
	}
	public void setDate21(Integer date21) {
		this.date21 = date21;
	}
	public Integer getDate22() {
		return date22;
	}
	public void setDate22(Integer date22) {
		this.date22 = date22;
	}
	public Integer getDate23() {
		return date23;
	}
	public void setDate23(Integer date23) {
		this.date23 = date23;
	}
	public Integer getDate24() {
		return date24;
	}
	public void setDate24(Integer date24) {
		this.date24 = date24;
	}
	public Integer getDate25() {
		return date25;
	}
	public void setDate25(Integer date25) {
		this.date25 = date25;
	}
	public Integer getDate26() {
		return date26;
	}
	public void setDate26(Integer date26) {
		this.date26 = date26;
	}
	public Integer getDate27() {
		return date27;
	}
	public void setDate27(Integer date27) {
		this.date27 = date27;
	}
	public Integer getDate28() {
		return date28;
	}
	public void setDate28(Integer date28) {
		this.date28 = date28;
	}
	public Integer getDate29() {
		return date29;
	}
	public void setDate29(Integer date29) {
		this.date29 = date29;
	}
	public Integer getDate30() {
		return date30;
	}
	public void setDate30(Integer date30) {
		this.date30 = date30;
	}
	public Integer getDate31() {
		return date31;
	}
	public void setDate31(Integer date31) {
		this.date31 = date31;
	}
	public String getTheMonth() {
		return theMonth;
	}
	public void setTheMonth(String theMonth) {
		this.theMonth = theMonth;
	}
	public Integer getMaxNum() {
		return maxNum;
	}
	public void setMaxNum(Integer maxNum) {
		this.maxNum = maxNum;
	}
	@OneToOne
	@JoinColumn(name = "c_id")
	public ChefBean getChefBean() {
		return chefBean;
	}

	public void setChefBean(ChefBean chefBean) {
		this.chefBean = chefBean;
	}
	@OneToOne
	@JoinColumn(name = "mc_id")
	public MchefBean getMchefBean() {
		return mchefBean;
	}

	public void setMchefBean(MchefBean mchefBean) {
		this.mchefBean = mchefBean;
	}
	

	
}
