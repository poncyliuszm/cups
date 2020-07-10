package pl.poncyliusz.backend.service.cup;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.poncyliusz.backend.dto.AddCupCommand;
import pl.poncyliusz.backend.dto.CupDTO;
import pl.poncyliusz.backend.dto.EditCupCommand;
import pl.poncyliusz.backend.exceptions.CategoryNotFoundException;
import pl.poncyliusz.backend.model.Category;
import pl.poncyliusz.backend.model.Cup;
import pl.poncyliusz.backend.repository.CategoryRepository;
import pl.poncyliusz.backend.repository.CupRepository;
import pl.poncyliusz.backend.utils.ObjectMapperUtils;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EditCupUseCase {

    private final CupRepository cupRepository;
    private final CategoryRepository categoryRepository;

    public void editCup(Long id, EditCupCommand editCupCommand) {
        Cup cup = cupRepository.findById(id).orElseThrow(() -> new RuntimeException("Nie znaleziono pucharka"));

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
