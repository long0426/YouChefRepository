package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="inbox")
public class InboxBean {

	private int mail_id;
	private String subject;
	private String content;
	private String i_status;
	private java.util.Date mail_time;
	private MemberBean sender;
	private MemberBean receiver;
	
	public InboxBean(){
		
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getMail_id() {
		return mail_id;
	}
	public void setMail_id(int mail_id) {
		this.mail_id = mail_id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getI_status() {
		return i_status;
	}
	public void setI_status(String i_status) {
		this.i_status = i_status;
	}
	public java.util.Date getMail_time() {
		return mail_time;
	}
	public void setMail_time(java.util.Date mail_time) {
		this.mail_time = mail_time;
	}


	@ManyToOne
	@JoinColumn(name="sender")
	public MemberBean getSender() {
		return sender;
	}
	
	public void setSender(MemberBean m_id) {
		this.sender = m_id;
	}

	@ManyToOne
	@JoinColumn(name="receiver")
	public MemberBean getReceiver() {
		return receiver;
	}

	public void setReceiver(MemberBean m_id) {
		this.receiver = m_id;
	}



	@Override
	public String toString() {
		return "InboxBean [mail_id=" + mail_id + ", subject=" + subject + ", content=" + content + ", i_status="
				+ i_status + ", mail_time=" + mail_time + ", sender=" + sender + ", receiver=" + receiver + "]";
	}
		
}
