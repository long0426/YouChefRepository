package model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="essay")
public class EssayBean implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer essay_id;
	
	private MemberBean writer_id;	
	private String title;
	private java.util.Date time;
	private String content;
	private String e_status;
	
	private Set<DiscussBean> disEssay_id = new HashSet<DiscussBean>();


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getEssay_id() {
		return essay_id;
	}
	public void setEssay_id(Integer essay_id){
		this.essay_id = essay_id;
	}
	
	
	@ManyToOne
    @JoinColumn(name="writer_id")
	public MemberBean getWriter_id() {
		return writer_id;
	}

	public void setWriter_id(MemberBean writer_id) {
		this.writer_id = writer_id;
	}

	public String getTitle(){
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public java.util.Date getTime() {
		return time;
	}
	public void setTime(java.util.Date time) {
		this.time = time;
	}
	public String getContent(){
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getE_status(){
		return e_status;
	}
	public void setE_status(String e_status) {
		this.e_status = e_status;
	}
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="essayBean")
	public Set<DiscussBean> getDisEssay_id() {
		return disEssay_id;
	}
	public void setDisEssay_id(Set<DiscussBean> disEssay_id) {
		this.disEssay_id = disEssay_id;
	}
	@Override
	public String toString() {
		return "EssayBean [essay_id=" + essay_id + ", writer_id=" + writer_id + ", title=" + title + ", time=" + time
				+ ", content=" + content + ", e_status=" + e_status + "]";
	}
	
	
}
