package pl.poncyliusz.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.poncyliusz.backend.dto.category.AddCategoryCommand;
import pl.poncyliusz.backend.dto.category.CategoryDTO;
import pl.poncyliusz.backend.dto.category.CategoryListDTO;
import pl.poncyliusz.backend.dto.category.EditCategoryCommand;
import pl.poncyliusz.backend.service.category.AddCategoryUseCase;
import pl.poncyliusz.backend.service.category.CategoryService;
import pl.poncyliusz.backend.service.category.EditCategoryUseCase;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/categories")
@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final AddCategoryUseCase addCategoryUseCase;
    private final EditCategoryUseCase editCategoryUseCase;

    @GetMapping
    public List<CategoryListDTO> list() {
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public CategoryDTO getOne(@PathVariable("id") Long id) {
        return new CategoryDTO(categoryService.findOne(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody @Valid AddCategoryCommand addCategoryCommand) {
        addCategoryUseCase.addCategory(addCategoryCommand);
    }

    @PutMapping("/{id}")
    public void edit(@PathVariable Long id, @RequestBody @Valid EditCategoryCommand editCategoryCommand) {
        editCategoryUseCase.editCategory(id, editCategoryCommand);
    }
}
