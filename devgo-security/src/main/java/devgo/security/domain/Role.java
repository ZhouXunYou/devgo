package devgo.security.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="sys_role")
public class Role {
	private Long roleId;
	private String roleName;
	private List<Group> groups;
	private List<Resource> resources;
	@Id
	@GeneratedValue
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	@ManyToMany(mappedBy="roles")
	public List<Group> getGroups() {
		return groups;
	}
	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}
	@ManyToMany
	@JoinTable(name="sys_role_resource",
		joinColumns={@JoinColumn(name="roleId")},
		inverseJoinColumns={@JoinColumn(name="resourceId")})
	public List<Resource> getResources() {
		return resources;
	}
	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}
	
}
