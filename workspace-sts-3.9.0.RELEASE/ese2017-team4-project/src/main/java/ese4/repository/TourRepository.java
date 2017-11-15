package ese4.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ese4.model.Tour;

public interface TourRepository extends CrudRepository<Tour, Long> {
	
	List<Tour> findByIsFinished(boolean isFinished);
	
	List<Tour> findByIdIn(List<Integer> ids);
	
	Tour findById(int id);

	//Auto implemented
}
