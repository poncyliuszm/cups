package pl.poncyliusz.backend.dto.cup;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.poncyliusz.backend.dto.category.CategoryDTO;
import pl.poncyliusz.backend.model.Cup;
import pl.poncyliusz.backend.utils.ObjectMapperUtils;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CupDTO {
    private Long id;
    private String name;
    private CategoryDTO category;
    private String description;
    private String solution;

    public CupDTO(Cup cup) {
        ObjectMapperUtils.map(cup, this);
    }
}
