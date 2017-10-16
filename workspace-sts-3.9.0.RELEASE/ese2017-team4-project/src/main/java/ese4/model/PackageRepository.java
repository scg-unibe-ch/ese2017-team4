package ese4.model;

import org.springframework.data.repository.CrudRepository;


public interface PackageRepository extends CrudRepository<Package, Long> {
	
	// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
	// CRUD refers Create, Read, Update, Delete

}