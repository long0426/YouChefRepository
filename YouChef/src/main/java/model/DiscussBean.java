package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="discuss")
public class DiscussBean implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer discuss_id;

	private EssayBean essayBean;

	private MemberBean reWriter_id;
	private String content;
	private java.util.Date time;
	private String d_status;
	
	@Override
	public String toString() {
		return "DiscussBean [discuss_id=" + discuss_id + ", essayBean=" + essayBean + ", memberBean=" + reWriter_id
				+ ", content=" + content +", time=" + time +", d_status=" + d_status +"]";
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getDiscuss_id(){
		return discuss_id;
	}
	public void setDiscuss_id(Integer discuss_id){
		this.discuss_id = discuss_id;
	}
	@ManyToOne
	@JoinColumn(name = "essay_id")
	public EssayBean getEssayBean() {
		return essayBean;
	}
	public void setEssayBean(EssayBean disEssay_id) {
		this.essayBean = disEssay_id;
	}
	@ManyToOne
    @JoinColumn(name="writer_id")
	public MemberBean getMemberBean() {
		return reWriter_id;
	}
	public void setMemberBean(MemberBean reWriter_id) {
		this.reWriter_id = reWriter_id;
	}
//	public int getEssay_id(){
//		return essay_id;
//	}
//	public void setEssay_id(Integer essay_id){
//		this.essay_id = essay_id;
//	}
//	public int getWriter_id(){
//		return writer_id;
//	}
//	public void setWriter_id(Integer writer_id){
//		this.writer_id = writer_id;
//	}
	public String getContent(){
		return content;
	}
	public void setContent(String content){
		this.content = content;
	}
	public java.util.Date getTime() {
		return time;
	}
	public void setTime(java.util.Date time) {
		this.time = time;
	}
	public String getD_status(){
		return d_status;
	}
	public void setD_status(String d_status){
		this.d_status = d_status;
	}
}
