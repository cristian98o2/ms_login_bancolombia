package co.com.bancolombia.usecase.shared.password;

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
class ValidatePasswordUseCaseTest {

    @InjectMocks
    private ValidatePasswordUseCase useCase;

    @Test
    void validateEmailErrorInvalid() {
        var command = new Command<>("bacolombia123", TestObjects.contextData());

        useCase.validatePassword(command)
                .as(StepVerifier::create)
                .expectErrorSatisfies(throwable -> {
                    Assertions.assertInstanceOf(BusinessException.class, throwable);
                    Assertions.assertEquals(throwable.getMessage(),
                            ConstantBusinessException.WEAK_PASSWORD.getLogCode());
                })
                .verify();
    }
}
