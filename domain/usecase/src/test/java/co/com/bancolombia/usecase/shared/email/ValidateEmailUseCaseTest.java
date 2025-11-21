package co.com.bancolombia.usecase.shared.email;

import co.com.bancolombia.model.shared.cqrs.Command;
import co.com.bancolombia.model.shared.exception.BusinessException;
import co.com.bancolombia.model.shared.exception.ConstantBusinessException;
import co.com.bancolombia.usecase.TestObjects;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class ValidateEmailUseCaseTest {

    @InjectMocks
    private ValidateEmailUseCase useCase;

    @Test
    void validateEmailErrorInvalid() {
        var command = new Command<>("cracardebancolombia.com", TestObjects.contextData());

        useCase.validateEmail(command)
                .as(StepVerifier::create)
                .expectErrorSatisfies(throwable -> {
                    Assertions.assertInstanceOf(BusinessException.class, throwable);
                    Assertions.assertEquals(throwable.getMessage(),
                            ConstantBusinessException.INVALID_EMAIL_FORMAT.getLogCode());
                })
                .verify();
    }
}
