package co.com.bancolombia.usecase.signup;

import co.com.bancolombia.model.shared.cqrs.Command;
import co.com.bancolombia.model.shared.exception.BusinessException;
import co.com.bancolombia.model.shared.exception.ConstantBusinessException;
import co.com.bancolombia.model.user.signup.gateway.SignupGateway;
import co.com.bancolombia.usecase.TestObjects;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class SignupUserUseCaseTest {

    @Mock
    private SignupGateway signupGateway;
    @InjectMocks
    private SignupUserUseCase useCase;

    @Test
    void testSignupUserSuccess(){
        var command = new Command<>(TestObjects.signup(), TestObjects.contextData());

        when(signupGateway.signupUser(command)).thenReturn(Mono.empty());

        useCase.signupUser(command)
                .as(StepVerifier::create)
                .verifyComplete();

        verify(signupGateway, times(1)).signupUser(any());
    }

    @Test
    void testSignupUserErrorDuplicateEmail(){
        var command = new Command<>(TestObjects.signup(), TestObjects.contextData());

        when(signupGateway.signupUser(command))
                .thenReturn(Mono.error(new BusinessException(ConstantBusinessException.EMAIL_ALREADY_EXISTS, command.context())));

        useCase.signupUser(command)
                .as(StepVerifier::create)
                .expectErrorSatisfies(throwable -> {
                    Assertions.assertInstanceOf(BusinessException.class, throwable);
                    Assertions.assertEquals(throwable.getMessage(),
                            ConstantBusinessException.EMAIL_ALREADY_EXISTS.getLogCode());
                })
                .verify();

        verify(signupGateway, times(1)).signupUser(any());
    }
}
