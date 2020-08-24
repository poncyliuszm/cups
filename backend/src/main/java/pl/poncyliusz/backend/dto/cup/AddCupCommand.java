package pl.poncyliusz.backend.dto.cup;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class AddCupCommand {

    @NotBlank
    private String name;
    @NotNull
    private Long categoryId;

    @Size(max = 2000)
    private String description;

    @Size(max = 2000)
    private String solution;
}
