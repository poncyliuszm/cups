package pl.poncyliusz.backend.dto.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.poncyliusz.backend.model.Category;
import pl.poncyliusz.backend.utils.ObjectMapperUtils;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    private Long id;
    private String name;
    private String description;
    private CategoryDTO parent;

    public CategoryDTO(Category category) {
        ObjectMapperUtils.map(category, this);
    }
}
