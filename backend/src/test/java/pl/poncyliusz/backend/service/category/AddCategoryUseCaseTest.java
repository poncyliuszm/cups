package pl.poncyliusz.backend.service.category;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.poncyliusz.backend.dto.category.AddCategoryCommand;
import pl.poncyliusz.backend.model.Category;
import pl.poncyliusz.backend.repository.CategoryRepository;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AddCategoryUseCaseTest {

    @Mock
    CategoryRepository categoryRepository;

    @InjectMocks
    AddCategoryUseCase addCategoryUseCase;

    @Test
    void addCategory() {
        AddCategoryCommand addCategoryCommand = new AddCategoryCommand();
        Category category = new Category();
        category.setName(addCategoryCommand.getName());
        category.setDescription(addCategoryCommand.getDescription());
        category.setActive(true);

        addCategoryUseCase.addCategory(addCategoryCommand);
        verify(categoryRepository, times(1)).save(category);
    }
}