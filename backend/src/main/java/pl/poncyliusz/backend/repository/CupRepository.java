package pl.poncyliusz.backend.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.poncyliusz.backend.model.Cup;

import java.util.List;

public interface CupRepository extends JpaRepository<Cup, Long> {
    List<Cup> findByCategoryId(Long categoryId);

    List<Cup> findByIsActive(boolean b);

    @Query("select c from Cup c order by random()")
    List<Cup> getRepeatCups(Pageable pageable);
}
