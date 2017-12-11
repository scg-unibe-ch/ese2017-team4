package ese4.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import ese4.model.PackageAudited;

/**
 * 
 * @author ESE04
 * Repository of the PackageAudited objects. Finds, saves and deletes them.
 *
 */
public interface PackageAuditedRepository extends CrudRepository<PackageAudited, Long> {
	
	List<PackageAudited> findByIdOrderByRevDesc(@Param("id") Integer id);

}