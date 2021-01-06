package pl.poncyliusz.backend.repository.cup;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import pl.poncyliusz.backend.model.Cup;

import java.util.List;

public interface CupRepository extends JpaRepository<Cup, Long>, JpaSpecificationExecutor {
    List<Cup> findByCategoryId(Long categoryId);

    @Query(value = "select c from Cup c left join fetch c.category where c.isActive = :b order by c.id",
            countQuery = "select count(c) from Cup c where c.isActive = :b")
    Page<Cup> findByIsActive(boolean b, Pageable pageable);

    @Query("select c from Cup c where c.isActive = true order by random()")
    List<Cup> getRepeatCups(Pageable pageable);
}
