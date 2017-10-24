package ese4.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

	User findById(Integer id);
	
	List<User> findByType(int type);


	//Auto implemented
}
