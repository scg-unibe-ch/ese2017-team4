package ese4.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import ese4.model.Tour;
import ese4.model.User;

public interface TourRepository extends CrudRepository<Tour, Long> {
	
	List<Tour> findByIsFinished(boolean isFinished);
	
	List<Tour> findByIdIn(List<Integer> ids);
	
	Tour findById(int id);

	Tour findTourByDriver(User currentUser);
	
	@Query( "select t from Tour t where t.driver = :currentUser and t.isFinished = false" )
	Tour findTourByDriverNotFinished(@Param("currentUser") User currentUser);
}
