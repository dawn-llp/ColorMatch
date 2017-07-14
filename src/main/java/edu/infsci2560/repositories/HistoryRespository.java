package edu.infsci2560.repositories;

import edu.infsci2560.models.History;
import org.springframework.data.repository.PagingAndSortingRepository;
import java.util.List;


public interface HistoryRepository extends PagingAndSortingRepository<History, Long> {
  List<History> findById(Long id);
}
