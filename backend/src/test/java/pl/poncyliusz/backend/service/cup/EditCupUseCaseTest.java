package pl.poncyliusz.backend.service.cup;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.poncyliusz.backend.dto.cup.EditCupCommand;
import pl.poncyliusz.backend.model.Category;
import pl.poncyliusz.backend.model.Cup;
import pl.poncyliusz.backend.repository.CategoryRepository;
import pl.poncyliusz.backend.repository.cup.CupRepository;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class EditCupUseCaseTest {

    private static final Long CUP_ID = 1L;
    private static final Long CATEGORY_ID = 1L;

    @Mock
    CupRepository cupRepository;

    @Mock
    CategoryRepository categoryRepository;

    @InjectMocks
    EditCupUseCase editCupUseCase;

    @Test
    @DisplayName("should execute save method")
    void editCup() {
        EditCupCommand editCupCommand = new EditCupCommand();
        editCupCommand.setCategoryId(CATEGORY_ID);
        Cup cup = new Cup();
        cup.setModificationDate(LocalDateTime.now());
        Category category = new Category();

        Mockito.when(cupRepository.findById(CUP_ID)).thenReturn(Optional.of(cup));
        Mockito.when(categoryRepository.findById(editCupCommand.getCategoryId())).thenReturn(Optional.of(category));

        editCupUseCase.editCup(CUP_ID, editCupCommand);

        Mockito.verify(cupRepository, Mockito.times(1)).save(cup);
    }

    @Test
    void shouldThrowEntityNotFound() {
        EditCupCommand editCupCommand = new EditCupCommand();
        Mockito.when(cupRepository.findById(CUP_ID)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> editCupUseCase.editCup(CUP_ID, editCupCommand));
    }

}
