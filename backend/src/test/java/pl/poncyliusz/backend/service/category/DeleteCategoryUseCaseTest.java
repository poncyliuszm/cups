package pl.poncyliusz.backend.service.category;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.poncyliusz.backend.model.Category;
import pl.poncyliusz.backend.repository.CategoryRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class DeleteCategoryUseCaseTest {

    static final Long CATEGORY_ID = 1L;

    @Mock
    CategoryRepository categoryRepository;

    @InjectMocks
    DeleteCategoryUseCase deleteCategoryUseCase;

    @Test
    void deleteShouldThrowsNotFoundException() {
        Mockito.when(categoryRepository.findById(CATEGORY_ID)).thenReturn(Optional.empty());
        Assertions.assertThrows(EntityNotFoundException.class, () -> deleteCategoryUseCase.delete(CATEGORY_ID));
    }

    @Test
    void shouldSetActiveFalse() {
        Category category = new Category();
        category.setId(CATEGORY_ID);
        Mockito.when(categoryRepository.findById(CATEGORY_ID)).thenReturn(Optional.of(category));
        deleteCategoryUseCase.delete(category.getId());
        Mockito.verify(categoryRepository, Mockito.times(1)).save(category);
    }
}