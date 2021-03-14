package pl.poncyliusz.backend.dto.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryParentDTO {

    private Long id;
    private CategoryParentDTO parent;
}
