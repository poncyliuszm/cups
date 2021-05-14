package pl.poncyliusz.backend.service.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.poncyliusz.backend.dto.category.CategoryListDTO;
import pl.poncyliusz.backend.model.Category;
import pl.poncyliusz.backend.repository.CategoryRepository;
import pl.poncyliusz.backend.utils.ObjectMapperUtils;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetCategoryUseCase {

    private final CategoryRepository categoryRepository;

    public List<CategoryListDTO> findAll() {
        List<Category> categories = categoryRepository.findByIsActive(true);
        return ObjectMapperUtils.mapAll(categories, CategoryListDTO.class);
    }

    public Category findOne(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Nie znaleziono kateogorii o id: " + id));
    }
}
