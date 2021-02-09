package pl.poncyliusz.backend.service.cup;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.poncyliusz.backend.dto.cup.CupCriteria;
import pl.poncyliusz.backend.dto.cup.CupListDTO;
import pl.poncyliusz.backend.repository.cup.CupRepository;
import pl.poncyliusz.backend.repository.cup.CupSpecification;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SearchCupUseCase {

    private final CupRepository cupRepository;

    public Page<CupListDTO> search(Pageable pageable, CupCriteria cupCriteria) {
        CupSpecification cupSpecification = new CupSpecification(cupCriteria);

        return cupRepository.findCupList(cupSpecification, pageable);
    }
}
