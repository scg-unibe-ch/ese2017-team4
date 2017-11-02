package ese4.model;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;


public interface PackageRepository extends CrudRepository<Package, Long> {
	
	
	//we can make queries by making methods in here
	List<Package> findById(Integer id);
	
	List<Package> findByIdIn(List<Integer> ids);
	
	List<Package> findByContent(String content);
	
	List<Package> findByIsDelivered(boolean isDelivered);
	
	@Transactional
	void deleteByIsDelivered(boolean isDelivered);
	
	
	// This will be AUTO IMPLEMENTED by Spring into a Bean called PackageRepository
	// CRUD refers Create, Read, Update, Delete

}