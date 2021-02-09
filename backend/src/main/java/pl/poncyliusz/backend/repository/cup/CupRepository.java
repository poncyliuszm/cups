package pl.poncyliusz.backend.repository.cup;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import pl.poncyliusz.backend.model.Cup;

import java.util.List;

public interface CupRepository extends JpaRepository<Cup, Long>, JpaSpecificationExecutor<Cup>, CupSearchRepository {

    List<Cup> findByCategoryId(Long categoryId);

    @Query("select c from Cup c where c.isActive = true order by random()")
    List<Cup> getRepeatCups(Pageable pageable);

}
