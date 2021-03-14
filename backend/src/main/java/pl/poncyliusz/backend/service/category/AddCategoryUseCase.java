package pl.poncyliusz.backend.service.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.poncyliusz.backend.dto.category.AddCategoryCommand;
import pl.poncyliusz.backend.model.Category;
import pl.poncyliusz.backend.repository.CategoryRepository;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AddCategoryUseCase {

    private final CategoryRepository categoryRepository;

    public void addCategory(AddCategoryCommand addCategoryCommand) {
        Category category = new Category();

        category.setName(addCategoryCommand.getName());
        category.setDescription(addCategoryCommand.getDescription());
        category.setActive(true);

        if (Objects.nonNull(addCategoryCommand.getParent()) && Objects.nonNull(addCategoryCommand.getParent().getId())) {
            setParent(addCategoryCommand, category);
        }
        categoryRepository.save(category);
    }

    private void setParent(AddCategoryCommand addCategoryCommand, Category category) {
        Category parent = categoryRepository.findById(addCategoryCommand.getParent().getId()).orElseThrow(() -> new RuntimeException("Nie znaleziono kategorii (rodzica)."));
        category.setParent(parent);
    }

}
