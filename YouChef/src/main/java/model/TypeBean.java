package model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="type")
public class TypeBean implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private int t_id;
	private String t_name;
	private Set<ChefBean> chfs = new HashSet<ChefBean>();

	@Override
	public String toString() {
		return "ChefBean [t_id=" + t_id + ", t_name=" + t_name + "]";
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getT_id() {
		return t_id;
	}
	public void setT_id(int t_id) {
		this.t_id = t_id;
	}
	public String getT_name() {
		return t_name;
	}
	public void setT_name(String t_name) {
		this.t_name = t_name;
	}	
	
	@OneToMany
	@JoinColumn(name = "c_id")
	public Set<ChefBean> getChfs(){
		return chfs;
	}
	public void  setChfs(Set<ChefBean> chfs){
		this.chfs=chfs;
	}
	
}
