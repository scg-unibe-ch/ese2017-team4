package ese4.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ese4.model.Package;

/**
 * 
 * @author ESE04
 * Repository of the Package objects. Finds, saves and deletes them.
 *
 */
public interface PackageRepository extends CrudRepository<Package, Long> {

	Package findById(Integer id);
	
	List<Package> findByIdIn(List<Integer> ids);
	
	List<Package> findByStatusDisplay(String statusDisplay);
	
	List<Package> findByStatusDisplayOrStatusDisplayOrderByDeliveryCounterDesc(String statusDisplay1, String statusDisplay2); 

}