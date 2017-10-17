package ese4.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


public interface PackageRepository extends CrudRepository<Package, Long> {
	
	
	//we can make queries by making methods in here
	List<Package> findById(int id);
	
	List<Package> findByContent(String content);
	
	List<Package> findFirst2ByOrderByIdAsc();
	
	List<Package> findFirst2ByIsDeliveredFalse();
	
	//List<Package> findFirst2ByOrderByIdAscAndIsDeliveredFalse();
	
	// This will be AUTO IMPLEMENTED by Spring into a Bean called PackageRepository
	// CRUD refers Create, Read, Update, Delete

}