package pl.poncyliusz.backend.dto.cup;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditCupCommand {

    @NotBlank
    private String name;

    @NotNull
    private Long categoryId;

    @Size(max = 2000)
    private String description;

    @Size(max = 5000)
    private String solution;

}
