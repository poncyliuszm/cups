package pl.poncyliusz.backend.dto.cup;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CupCriteria implements Serializable {
    private List<String> names;
    private List<String> descriptions;
}
