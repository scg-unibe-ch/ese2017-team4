package ese4.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ese4.model.Package;


public interface PackageRepository extends CrudRepository<Package, Long> {

	List<Package> findById(Integer id);
	
	List<Package> findByIdIn(List<Integer> ids);
	
	List<Package> findByStatusDisplay(String statusDisplay);
	
	List<Package> findByStatusDisplayOrStatusDisplayOrderByDeliveryCounterDesc(String statusDisplay1, String statusDisplay2); 

}