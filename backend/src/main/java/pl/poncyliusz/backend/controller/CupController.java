package pl.poncyliusz.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.poncyliusz.backend.dto.cup.AddCupCommand;
import pl.poncyliusz.backend.dto.cup.CupDTO;
import pl.poncyliusz.backend.dto.cup.EditCupCommand;
import pl.poncyliusz.backend.dto.cup.RepeatCupDTO;
import pl.poncyliusz.backend.service.cup.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cups")
@RequiredArgsConstructor
public class CupController {

    private final CupService cupService;
    private final SearchCupUseCase searchCupUseCase;
    private final RepeatService repeatService;
    private final AddCupUseCase addCupUseCase;
    private final EditCupUseCase editCupUseCase;
    private final DeleteCupUseCase deleteCupUseCase;

    @GetMapping
    public Page<CupDTO> search(Pageable pageable) {
        return searchCupUseCase.search(pageable);
    }

    @GetMapping("/{id}")
    public CupDTO getOne(@PathVariable Long id) {
        return new CupDTO(cupService.get(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody @Valid AddCupCommand addCupCommand) {
        addCupUseCase.addCup(addCupCommand);
    }

    @PutMapping("/{id}")
    public void edit(@PathVariable Long id, @RequestBody @Valid EditCupCommand editCupCommand) {
        editCupUseCase.editCup(id, editCupCommand);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        deleteCupUseCase.delete(id);
    }

    @GetMapping("/lastNameOfCategory/{categoryId}")
    public String getLastNameOfCategory(@PathVariable Long categoryId) {
        return cupService.getLastNameOfCategory(categoryId);
    }

    @GetMapping("/repeat")
    public List<RepeatCupDTO> getRepeatCups() {
        return repeatService.getRepeatCups();
    }

}
