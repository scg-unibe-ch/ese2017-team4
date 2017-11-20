package ese4.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import ese4.model.Role;
import ese4.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

	User findById(int id);
	
	@Query( "select u from User u where u.roleInput = :role" )
	Iterable<User> findbyRole(@Param("role") String role);
	
	User findByName(String name);
	
	


	//Auto implemented
}
