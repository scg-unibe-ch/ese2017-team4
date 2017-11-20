package ese4.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import ese4.model.Role;
import ese4.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

	User findById(int id);
	
	List<User> findByRoles(Set<Role> roles);
	
	User findByName(String name);


	//Auto implemented
}
