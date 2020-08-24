package pl.poncyliusz.backend.dto.category;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class EditCategoryCommand {

    @NotBlank
    private String name;

    @Size(max = 2000)
    private String description;

    private CategoryDTO parent;
}
