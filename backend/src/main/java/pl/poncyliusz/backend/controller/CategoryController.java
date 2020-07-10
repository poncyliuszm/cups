package pl.poncyliusz.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.poncyliusz.backend.dto.CategoryListDTO;
import pl.poncyliusz.backend.model.Category;
import pl.poncyliusz.backend.service.CategoryService;

import java.util.List;

@RequestMapping("/categories")
@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public List<CategoryListDTO> list() {
        return categoryService.findAll();
    }
}
