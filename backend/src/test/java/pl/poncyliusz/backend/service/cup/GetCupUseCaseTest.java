package pl.poncyliusz.backend.service.cup;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.poncyliusz.backend.model.Category;
import pl.poncyliusz.backend.model.Cup;
import pl.poncyliusz.backend.repository.cup.CupRepository;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetCupUseCaseTest {

    private final static Long CUP_ID = 1L;
    private final static Long CATEGORY_ID = 1L;

    @Mock
    CupRepository cupRepository;

    @InjectMocks
    GetCupUseCase getCupUseCase;

    @Test
    void getOne() {
        Cup cup = prepareCup();

        when(cupRepository.findById(CUP_ID)).thenReturn(Optional.of(cup));

        Cup returnedCup = getCupUseCase.getOne(CUP_ID);

        assertTrue(Objects.nonNull(returnedCup));
        assertSame(returnedCup, cup);

    }

    @Test
    void shouldThrowsNotFoundExcpetion() {
        Mockito.when(cupRepository.findById(CUP_ID)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> getCupUseCase.getOne(CUP_ID));
    }

    private Cup prepareCup() {
        Category category = prepareCategory();

        Cup cup = new Cup();
        cup.setId(CUP_ID);
        cup.setModificationDate(LocalDateTime.now());
        cup.setCategory(category);
        cup.setName("name");
        cup.setDescription("opis");
        cup.setSolution("sol");
        cup.setActive(true);
        cup.setCreatedDate(LocalDateTime.now());

        return cup;
    }

    private Category prepareCategory() {
        Category category = new Category();
        category.setId(CATEGORY_ID);

        return category;
    }

}
