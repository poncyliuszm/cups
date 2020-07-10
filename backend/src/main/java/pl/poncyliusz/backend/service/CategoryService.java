package pl.poncyliusz.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.poncyliusz.backend.dto.CategoryListDTO;
import pl.poncyliusz.backend.model.Category;
import pl.poncyliusz.backend.repository.CategoryRepository;
import pl.poncyliusz.backend.utils.ObjectMapperUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<CategoryListDTO> findAll() {
        List<Category> categories = categoryRepository.findAll();
        return ObjectMapperUtils.mapAll(categories, CategoryListDTO.class);
    }
}
