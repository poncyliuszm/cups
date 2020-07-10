package pl.poncyliusz.backend.service.cup;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.poncyliusz.backend.dto.AddCupCommand;
import pl.poncyliusz.backend.exceptions.CategoryNotFoundException;
import pl.poncyliusz.backend.model.Category;
import pl.poncyliusz.backend.model.Cup;
import pl.poncyliusz.backend.repository.CategoryRepository;
import pl.poncyliusz.backend.repository.CupRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class DeleteCupUseCase {

    private final CupRepository cupRepository;

    public void delete(Long id) {
        Cup cup = cupRepository.findById(id).orElseThrow(() -> new RuntimeException("Nie znaleziono pucharka o id: " + id));
        cup.setActive(false);
        cup.setModificationDate(LocalDateTime.now());

        cupRepository.save(cup);
    }
}
