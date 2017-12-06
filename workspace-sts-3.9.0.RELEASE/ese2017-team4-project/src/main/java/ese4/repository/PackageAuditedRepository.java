package ese4.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import ese4.model.PackageAudited;

public interface PackageAuditedRepository extends CrudRepository<PackageAudited, Long> {
	
	
	@Query("SELECT p FROM PackageAudited p join fetch p.info c WHERE p.id =:id")
	List<PackageAudited> findById(@Param("id") Integer id);

}