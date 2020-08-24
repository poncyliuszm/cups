package pl.poncyliusz.backend.service.cup;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.poncyliusz.backend.dto.cup.AddCupCommand;
import pl.poncyliusz.backend.exceptions.CategoryNotFoundException;
import pl.poncyliusz.backend.model.Category;
import pl.poncyliusz.backend.model.Cup;
import pl.poncyliusz.backend.repository.CategoryRepository;
import pl.poncyliusz.backend.repository.CupRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AddCupUseCase {

    private final CupRepository cupRepository;
    private final CategoryRepository categoryRepository;

    public void addCup(AddCupCommand addCupCommand) {
        Cup cup = new Cup();
        cup.setName(addCupCommand.getName());
        cup.setDescription(addCupCommand.getDescription());
        cup.setCreatedDate(LocalDateTime.now());
        cup.setActive(true);
        setCategory(addCupCommand, cup);
        cup.setSolution(addCupCommand.getSolution());

        cupRepository.save(cup);
    }

    private void setCategory(AddCupCommand addCupCommand, Cup cup) {
        Category category = categoryRepository.findById(addCupCommand.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException("Nie znaleziono kategorii o id: " + addCupCommand.getCategoryId()));
        cup.setCategory(category);
    }

}
