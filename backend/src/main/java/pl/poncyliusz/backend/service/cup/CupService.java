package pl.poncyliusz.backend.service.cup;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.poncyliusz.backend.dto.cup.CupDTO;
import pl.poncyliusz.backend.model.Category;
import pl.poncyliusz.backend.model.Cup;
import pl.poncyliusz.backend.repository.CategoryRepository;
import pl.poncyliusz.backend.repository.CupRepository;
import pl.poncyliusz.backend.utils.ObjectMapperUtils;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CupService {

    private final CupRepository cupRepository;
    private final CategoryRepository categoryRepository;

    public List<CupDTO> findAll() {
        List<Cup> cups = cupRepository.findByIsActive(true);
        return ObjectMapperUtils.mapAll(cups, CupDTO.class);
    }

    public Cup get(Long id) {
        return cupRepository.findById(id).orElseThrow(() -> new RuntimeException("Nie znaleziono pucharka o id: " + id));
    }

    public String getLastNameOfCategory(Long categoryId) {
        String lastNameOfCategory;
        Long lastIdOfCup = 1L;

        Optional<Category> category = categoryRepository.findById(categoryId);
        lastNameOfCategory = category.orElseThrow(() -> new RuntimeException("Nie znaleziono kategorii.")).getName().substring(0, 1);



        Optional<Cup> maxCup = cupRepository.findByCategoryId(categoryId).stream().max(Comparator.comparing(Cup::getId));
        if (maxCup.isPresent()) {
            Integer maxCupId = Integer.valueOf(maxCup.get().getName().substring(1));
            lastIdOfCup = maxCupId + lastIdOfCup;
        }

        return lastNameOfCategory.concat(lastIdOfCup.toString());
    }

    public void delete(Long id) {
    }
}
