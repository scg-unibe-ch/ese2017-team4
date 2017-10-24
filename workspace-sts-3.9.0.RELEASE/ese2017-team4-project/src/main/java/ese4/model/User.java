package ese4.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Resembles a user. Users have different responsibilities determined by their type.
 * 
 * @author ese4
 *
 */
@Entity
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    
    @OneToMany(mappedBy="driver")
    private List<Tour> tours;
    
    private String name;
    private int type;		//0 = logistician, 1 = driver
    	
	
	/**
	 * Returns its id.
	 * @return id
	 */
	public Integer getId() {
		return this.id;
	}
	/**
	 * Sets its parameter input as id.
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * Returns its name.
	 * @return name
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * Sets its parameter input as name.
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Returns its type.
	 * @return type
	 */
	public int getType() {
		return this.type;
	}
	/**
	 * Sets its parameter input as type.
	 * @param type
	 */
	public void setType(int type) {
		this.type = type;
	}
}
