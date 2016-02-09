package devgo.security.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="sys_group")
public class Group {
	private Long groupId;
	private String groupName;
	private List<User> users;
	private List<Role> roles;
	private Group parent;
	private List<Group> children;
	@Id
	@GeneratedValue
	public Long getGroupId() {
		return groupId;
	}
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	@ManyToMany(mappedBy="groups")
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	@ManyToMany
	@JoinTable(name="sys_group_role",
		joinColumns={@JoinColumn(name="groupId")},
		inverseJoinColumns={@JoinColumn(name="roleId")})
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	@ManyToOne
	@JoinColumn(name="")
	public Group getParent() {
		return parent;
	}
	public void setParent(Group parent) {
		this.parent = parent;
	}
	public List<Group> getChildren() {
		return children;
	}
	public void setChildren(List<Group> children) {
		this.children = children;
	}
}
