package pl.poncyliusz.backend.service.category;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.poncyliusz.backend.dto.category.CategoryListDTO;
import pl.poncyliusz.backend.model.Category;
import pl.poncyliusz.backend.repository.CategoryRepository;
import pl.poncyliusz.backend.utils.ObjectMapperUtils;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private GetCategoryUseCase getCategoryUseCase;


    @Test
    public void findOne() {
        Category category = new Category(1L, "Category name", "Category description", null, true);
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));

        Category returnedCategory = getCategoryUseCase.findOne(1L);

        assertTrue(Objects.nonNull(returnedCategory));
        assertSame(returnedCategory, category);
    }

    @Test
    @DisplayName("should throw EntityNotFoundException")
    void findOneShouldThrowsEntityNotFoundException() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> getCategoryUseCase.findOne(1L));
    }

    @Test
    public void findAllShouldReturnTwoCategories() {
        List<Category> categories = Arrays.asList(
                new Category(1L, "Category name", "Category description", null, true),
                new Category(2L, "Category name 2", "Category description 2", null, true));

        when(categoryRepository.findByIsActive(true)).thenReturn(categories);
        List<CategoryListDTO> exceptionCategories = ObjectMapperUtils.mapAll(categories, CategoryListDTO.class);
        List<CategoryListDTO> returnedCategories = getCategoryUseCase.findAll();

        assertEquals(exceptionCategories, returnedCategories);
    }

    @Test
    public void findAllShouldReturnZeroActiveCategories() {
        List<Category> categories = Arrays.asList(
                new Category(1L, "Category name", "Category description", null, false),
                new Category(2L, "Category name 2", "Category description 2", null, false));

        when(categoryRepository.findByIsActive(true)).thenReturn(categories);
        List<CategoryListDTO> exceptionCategories = ObjectMapperUtils.mapAll(categories, CategoryListDTO.class);
        List<CategoryListDTO> returnedCategories = getCategoryUseCase.findAll();

        assertEquals(exceptionCategories, returnedCategories);
    }

}