package pl.poncyliusz.backend.service.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.poncyliusz.backend.dto.category.EditCategoryCommand;
import pl.poncyliusz.backend.model.Category;
import pl.poncyliusz.backend.repository.CategoryRepository;

@Service
@RequiredArgsConstructor
public class EditCategoryUseCase {

    private final CategoryRepository categoryRepository;

    public void editCategory(Long id, EditCategoryCommand editCategoryCommand) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Nie znaleziono kategorii."));

        category.setName(editCategoryCommand.getName());
        category.setDescription(editCategoryCommand.getDescription());

        setParent(editCategoryCommand, category);

        categoryRepository.save(category);
    }

    private void setParent(EditCategoryCommand editCategoryCommand, Category category) {
        Category parent = categoryRepository.findById(editCategoryCommand.getParent().getId()).orElseThrow(() -> new RuntimeException("Nie znaleziono kategorii (rodzica)."));
        category.setParent(parent);
    }

}
