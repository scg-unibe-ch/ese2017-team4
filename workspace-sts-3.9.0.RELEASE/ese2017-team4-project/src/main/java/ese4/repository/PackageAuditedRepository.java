package ese4.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import ese4.model.PackageAudited;

public interface PackageAuditedRepository extends CrudRepository<PackageAudited, Long> {
	
	
	//we can make queries by making methods in here
	List<PackageAudited> findById(Integer id);
	
	List<PackageAudited> findByIdIn(List<Integer> ids);
	
	List<PackageAudited> findByIsStatus(String isStatus);
	
	List<PackageAudited> findByIsStatusOrIsStatusOrderByDeliveryCounterDesc(String isStatus1, String isStatus2); 
	
	//@Transactional
	//void deleteByIsDelivered(String isStatus);
	
	
	// This will be AUTO IMPLEMENTED by Spring into a Bean called PackageRepository
	// CRUD refers Create, Read, Update, Delete

}