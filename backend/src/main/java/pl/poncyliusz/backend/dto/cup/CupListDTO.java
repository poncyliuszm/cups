package pl.poncyliusz.backend.dto.cup;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CupListDTO {
    private Long id;
    private String name;
    private String categoryName;
    private String description;

}
