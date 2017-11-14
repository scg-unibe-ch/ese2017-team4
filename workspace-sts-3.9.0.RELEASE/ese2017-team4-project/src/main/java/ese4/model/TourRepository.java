package ese4.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface TourRepository extends CrudRepository<Tour, Long> {
	
	List<Tour> findByIsFinished(boolean isFinished);
	
	List<Tour> findByIdIn(List<Integer> ids);
	
	Tour findById(int id);

	//Auto implemented
}
