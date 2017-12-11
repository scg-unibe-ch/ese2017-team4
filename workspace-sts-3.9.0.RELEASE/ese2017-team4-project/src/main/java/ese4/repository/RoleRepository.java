package ese4.repository;

import org.springframework.data.repository.CrudRepository;


import ese4.model.Role;

/**
 * 
 * @author ESE04
 * Repository of the Role objects. Finds, saves and deletes them.
 *
 */
public interface RoleRepository extends CrudRepository<Role, Integer>{
	Role findByRole(String role);

}