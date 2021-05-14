package pl.poncyliusz.backend.service.cup;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.poncyliusz.backend.dto.cup.EditCupCommand;
import pl.poncyliusz.backend.exceptions.CategoryNotFoundException;
import pl.poncyliusz.backend.model.Category;
import pl.poncyliusz.backend.model.Cup;
import pl.poncyliusz.backend.repository.CategoryRepository;
import pl.poncyliusz.backend.repository.cup.CupRepository;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class EditCupUseCase {

    private final CupRepository cupRepository;
    private final CategoryRepository categoryRepository;

    public void editCup(Long id, EditCupCommand editCupCommand) {
        Cup cup = cupRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Nie znaleziono pucharka"));

        setCategory(editCupCommand, cup);
        cup.setName(editCupCommand.getName());
        cup.setDescription(editCupCommand.getDescription());
        cup.setModificationDate(LocalDateTime.now());
        cup.setSolution(editCupCommand.getSolution());

        cupRepository.save(cup);
    }

    private void setCategory(EditCupCommand editCupCommand, Cup cup) {
        Category category = categoryRepository.findById(editCupCommand.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException("Nie znaleziono kategorii o id: " + editCupCommand.getCategoryId()));
        cup.setCategory(category);
    }
}
