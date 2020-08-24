package pl.poncyliusz.backend.service.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.poncyliusz.backend.dto.category.AddCategoryCommand;
import pl.poncyliusz.backend.model.Category;
import pl.poncyliusz.backend.repository.CategoryRepository;
import pl.poncyliusz.backend.utils.ObjectMapperUtils;

@Service
@RequiredArgsConstructor
public class AddCategoryUseCase {

    private final CategoryRepository categoryRepository;

    public void addCategory(AddCategoryCommand addCategoryCommand) {
        Category category = new Category();
        category.setName(addCategoryCommand.getName());
        category.setDescription(addCategoryCommand.getDescription());
        category.setParent(ObjectMapperUtils.map(addCategoryCommand.getParent(), Category.class));
        category.setActive(true);

        categoryRepository.save(category);
    }

}
