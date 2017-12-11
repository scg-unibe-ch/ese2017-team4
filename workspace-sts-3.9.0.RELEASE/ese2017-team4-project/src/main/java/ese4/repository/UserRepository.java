package ese4.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import ese4.model.User;

/**
 * 
 * @author ESE04
 * Repository of the User objects. Finds, saves and deletes them.
 *
 */
public interface UserRepository extends CrudRepository<User, Long> {

	User findById(int id);
	
	@Query( "select u from User u where u.roleInput = :role" )
	List<User> findbyRole(@Param("role") String role);
	
	User findByName(String name);
	
	//Auto implemented
}
