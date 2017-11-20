package ese4.model;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Transient;

/**
 * Resembles a user. Users have different responsibilities determined by their type.
 * 
 * @author ese4
 *
 */
@Entity
public class User {
        	
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private int id;
    
	@Column(name = "email")
	@Email(message = "*Please use a valid Email")
	@NotEmpty(message = "*Please Type your email")
	private String email;
	
	@Column(name = "password")
	@Length(min = 4, message = "*atleast 4 characters")
	@NotEmpty(message = "*Please Type your password")
	@Transient
	private String password;
	
	@Column(name = "name")
	@NotEmpty(message = "*Please Type your Name")
	private String name;
	
	@Column(name = "roleInput")
	@NotEmpty(message = "*Please choose the role")
	private String roleInput;
		
	@Column(name = "active")
	private boolean active;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;
	
    @OneToMany(mappedBy="driver")
    private List<Tour> tours;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getRoleInput() {
		return roleInput;
	}
	public void setRoleInput(String roleInput) {
		this.roleInput = roleInput;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
    
}
