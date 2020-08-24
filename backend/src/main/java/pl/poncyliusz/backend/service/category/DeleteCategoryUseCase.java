package pl.poncyliusz.backend.service.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.poncyliusz.backend.model.Category;
import pl.poncyliusz.backend.repository.CategoryRepository;

@Service
@RequiredArgsConstructor
public class DeleteCategoryUseCase {

    private final CategoryRepository categoryRepository;

    public void delete(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Nie znaleziono kategorii o id: " + id));
        category.setActive(false);

        categoryRepository.save(category);
    }
}
