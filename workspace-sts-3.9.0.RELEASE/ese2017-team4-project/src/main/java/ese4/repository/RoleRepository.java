package ese4.repository;

import org.springframework.data.repository.CrudRepository;


import ese4.model.Role;

public interface RoleRepository extends CrudRepository<Role, Integer>{
	Role findByRole(String role);

}