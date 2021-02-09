package pl.poncyliusz.backend.repository.cup;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import pl.poncyliusz.backend.dto.cup.CupListDTO;
import pl.poncyliusz.backend.model.Cup;

public interface CupSearchRepository {
    Page<CupListDTO> findCupList(Specification<Cup> specification, Pageable pageable);
}
