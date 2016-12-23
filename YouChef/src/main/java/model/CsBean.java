package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="cs")
public class CsBean {
	
	private int message_id;
	private MemberBean memberBean;
	private String content;
	private java.util.Date time;
	private String cs_status;
	
	public CsBean(){
		
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getMessage_id() {
		return message_id;
	}

	public void setMessage_id(int message_id) {
		this.message_id = message_id;
	}
	
	@ManyToOne
	@JoinColumn(name="m_id")
	public MemberBean getMemberBean() {
		return memberBean;
	}

	public void setMemberBean(MemberBean memberBean) {
		this.memberBean = memberBean;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public java.util.Date getTime() {
		return time;
	}

	public void setTime(java.util.Date time) {
		this.time = time;
	}

	public String getCs_status() {
		return cs_status;
	}

	public void setCs_status(String cs_status) {
		this.cs_status = cs_status;
	}

	@Override
	public String toString() {
		return "CsBean [message_id=" + message_id + ", memberBean=" + memberBean + ", content=" + content + ", time="
				+ time + ", cs_status=" + cs_status + "]";
	}
	
	
}
