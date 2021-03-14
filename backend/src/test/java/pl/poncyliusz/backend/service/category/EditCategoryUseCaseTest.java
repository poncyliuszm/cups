package pl.poncyliusz.backend.service.category;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.poncyliusz.backend.dto.category.CategoryParentDTO;
import pl.poncyliusz.backend.dto.category.EditCategoryCommand;
import pl.poncyliusz.backend.model.Category;
import pl.poncyliusz.backend.repository.CategoryRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class EditCategoryUseCaseTest {

    static final Long CATEGORY_ID = 1L;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private EditCategoryUseCase editCategoryUseCase;

    @Test
    void editCategory() {
        EditCategoryCommand editCategoryCommand = new EditCategoryCommand();

        Category category = new Category();
        Mockito.when(categoryRepository.findById(CATEGORY_ID)).thenReturn(Optional.of(category));

        editCategoryUseCase.editCategory(CATEGORY_ID, editCategoryCommand);
        Mockito.verify(categoryRepository, Mockito.times(1)).save(category);
    }

    @Test
    void shouldThrowEntityNotFound() {
        EditCategoryCommand editCategoryCommand = new EditCategoryCommand();
        Mockito.when(categoryRepository.findById(CATEGORY_ID)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> editCategoryUseCase.editCategory(CATEGORY_ID, editCategoryCommand));
    }

    // nie znaleziono rodzica
    @Test
    void shouldThrowParentNotFound() {
        EditCategoryCommand editCategoryCommand = new EditCategoryCommand();
        Category category = new Category();
        CategoryParentDTO categoryParentDTO = new CategoryParentDTO(2L, null);
        editCategoryCommand.setParent(categoryParentDTO);
        Mockito.when(categoryRepository.findById(CATEGORY_ID)).thenReturn(Optional.of(category));
        Mockito.when(categoryRepository.findById(editCategoryCommand.getParent().getId())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> editCategoryUseCase.editCategory(CATEGORY_ID, editCategoryCommand));
    }
}