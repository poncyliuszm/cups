package pl.poncyliusz.backend.service.cup;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.poncyliusz.backend.dto.RepeatCupDTO;
import pl.poncyliusz.backend.model.Cup;
import pl.poncyliusz.backend.repository.CupRepository;
import pl.poncyliusz.backend.utils.ObjectMapperUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RepeatService {

    private final CupRepository cupRepository;

    public List<RepeatCupDTO> getRepeatCups() {
        List<Cup> repeatCups = cupRepository.getRepeatCups(PageRequest.of(0, 5));

        return ObjectMapperUtils.mapAll(repeatCups, RepeatCupDTO.class);
    }
}
