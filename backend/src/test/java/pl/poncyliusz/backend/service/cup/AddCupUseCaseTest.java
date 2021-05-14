package pl.poncyliusz.backend.service.cup;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.poncyliusz.backend.dto.cup.AddCupCommand;
import pl.poncyliusz.backend.model.Category;
import pl.poncyliusz.backend.model.Cup;
import pl.poncyliusz.backend.repository.CategoryRepository;
import pl.poncyliusz.backend.repository.cup.CupRepository;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddCupUseCaseTest {

    @Mock
    CupRepository cupRepository;

    @Mock
    CategoryRepository categoryRepository;

    @InjectMocks
    AddCupUseCase addCupUseCase;

    @Test
    void shouldEvaluateSave() {

        AddCupCommand addCupCommand = new AddCupCommand();
        addCupCommand.setCategoryId(1L);

        Category category = prepareCategory();

        Cup cup = new Cup();
        cup.setName(addCupCommand.getName());
        cup.setDescription(addCupCommand.getDescription());
        cup.setCreatedDate(LocalDateTime.now());
        cup.setActive(true);
        cup.setCategory(category);
        cup.setSolution(addCupCommand.getSolution());

        when(categoryRepository.findById(addCupCommand.getCategoryId())).thenReturn(Optional.of(category));

        when(cupRepository.save(Mockito.any(Cup.class))).thenReturn(cup);

        Cup savedCup = addCupUseCase.addCup(addCupCommand);

        verify(cupRepository, times(1)).save(Mockito.any(Cup.class));
        assertEquals(savedCup.getId(), cup.getId());
    }

    private Category prepareCategory() {
        Category category = new Category();
        category.setId(1L);

        return category;
    }

}