package pl.poncyliusz.backend.service.cup;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.poncyliusz.backend.model.Cup;
import pl.poncyliusz.backend.repository.cup.CupRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(MockitoExtension.class)
public class DeleteCupUseCaseTest {

    static final Long CUP_ID = 1L;

    @Mock
    CupRepository cupRepository;

    @InjectMocks
    DeleteCupUseCase deleteCupUseCase;

    @Test
    void shouldSetActiveFalse() {
        Cup cup = new Cup();

        Mockito.when(cupRepository.findById(CUP_ID)).thenReturn(Optional.of(cup));

        deleteCupUseCase.delete(CUP_ID);

        Mockito.verify(cupRepository, Mockito.times(1)).save(cup);
        assertFalse(cup.isActive());
    }
}
