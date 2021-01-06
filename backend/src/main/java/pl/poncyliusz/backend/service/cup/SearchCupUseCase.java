package pl.poncyliusz.backend.service.cup;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.poncyliusz.backend.dto.cup.CupCriteria;
import pl.poncyliusz.backend.dto.cup.CupDTO;
import pl.poncyliusz.backend.model.Cup;
import pl.poncyliusz.backend.repository.cup.CupRepository;
import pl.poncyliusz.backend.repository.cup.CupSpecification;
import pl.poncyliusz.backend.utils.ObjectMapperUtils;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SearchCupUseCase {

    private final CupRepository cupRepository;

    public Page<CupDTO> search(Pageable pageable, CupCriteria cupCriteria) {
        CupSpecification cupSpecification = new CupSpecification(cupCriteria);
        Page<Cup> cups = cupRepository.findAll(cupSpecification, pageable);

        return new PageImpl<>(ObjectMapperUtils.mapAll(cups.getContent(), CupDTO.class), pageable, cups.getTotalElements());
    }
}
