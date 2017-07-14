package edu.infsci2560.repositories;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import edu.infsci2560.models.MatchPalettes;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MatchPalettesRepository extends PagingAndSortingRepository<MatchPalettes, Long> {
    List<MatchPalettes> findById(Long id);
    List<MatchPalettes> findByColors(String[] colors);

    //自定义方法
    public Long check(MatchPalettes m){
      @Autowired
      private MatchPalettesRepository repository;
      if(repository.findByColors(m.getColors()) == null){
        return m;
      }
      else {
        return(repository.findByColors(m.getColors()));
      }
    }
}
