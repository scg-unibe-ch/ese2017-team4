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

import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Transient;

/**
 * A User has an Id assigned which differs him clearly from any other user
 * Name and password together create a login for each user, with which he's able to log on the page
 * Every User has a role assigned and based on the role, has more or less authority.
 * Has an active status and knows if he has any tours assigned to him.
 * @author ese4
 *
 */
@Entity
@Audited
public class User {
        	
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private int id;
    
	@Column(name = "password")
	@Length(min = 4, message = "*Passwort muss mindestens 4 Buchstaben haben!")
	@NotEmpty(message = "*Bitte geben Sie ein Passwort ein.")
	@Transient
	private String password;
	
	@Column(name = "name")
	@NotEmpty(message = "*Bitte geben Sie einen Benutzernamen ein.")
	private String name;
	
	@Column(name = "roleInput")
	@NotEmpty(message = "*Bitte wählen Sie eine Rolle.")
	private String roleInput;
		
	@Column(name = "active")
	private boolean active;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;
	
    @OneToMany(mappedBy="driver")
    private List<Tour> tours;
    
    /**
     * Returns its id
     * @return id
     */
	public int getId() {
		return id;
	}
	
	/**
	 * Sets its parameter input as id.
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Return its password
	 * @return password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Sets its parameter input as password
	 * Is not null
	 * @param password
	 */
	public void setPassword(String password) {
		assert(password.length() > 0);
		this.password = password;
	}
	
	/**
	 * Returns its name
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets its parameter input as name
	 * Is not null
	 * @param name
	 */
	public void setName(String name) {
		assert(name.length() > 0);
		this.name = name;
	}
	
	/**
	 * Returns its roleInput
	 * @return roleInput
	 */
	public String getRoleInput() {
		return roleInput;
	}
	
	/**
	 * Sets its parameter input as roleInput
	 * @param roleInput
	 */
	public void setRoleInput(String roleInput) {
		this.roleInput = roleInput;
	}
	
	/**
	 * Returns its active boolean parameter
	 * @return active
	 */
	public boolean isActive() {
		return active;
	}
	
	/**
	 * Sets its parameter boolean input as active
	 * @param active
	 */
	public void setActive(boolean active) {
		this.active = active;
	}
	
	/**
	 * Returns roles
	 * @return roles
	 */
	public Set<Role> getRoles() {
		return roles;
	}
	
	/**
	 * Sets its parameter input as roles
	 * @param roles
	 */
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	/**
	 * Returns a list of all tours the user is assigned to
	 * @return tours
	 */
	public List<Tour> getTours() {
		return this.tours;
	} 
}
