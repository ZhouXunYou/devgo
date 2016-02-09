package devgo.security.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="sys_user")
public class User {
	private String userName;
	private String pwd;
	private List<Group> groups;
	@Id
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	@ManyToMany
	@JoinTable(name="sys_user_group",
		joinColumns={@JoinColumn(name="userName")},
		inverseJoinColumns={@JoinColumn(name="groupId")})
	public List<Group> getGroups() {
		return groups;
	}
	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}
	
	
}
