package co.com.bancolombia.usecase.signin;

import co.com.bancolombia.model.shared.cqrs.Query;
import co.com.bancolombia.model.shared.exception.BusinessException;
import co.com.bancolombia.model.shared.exception.ConstantBusinessException;
import co.com.bancolombia.model.user.signin.gateway.SigninGateway;
import co.com.bancolombia.usecase.TestObjects;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SigninUserUseCaseTest {

    @Mock
    private SigninGateway signinGateway;
    @InjectMocks
    private SigninUserUseCase useCase;

    @Test
    void testSigninUserSuccess() {
        var query = new Query<>(TestObjects.signin(), TestObjects.contextData());

        when(signinGateway.validateUser(query)).thenReturn(Mono.empty());
        when(signinGateway.saveSession(query)).thenReturn(Mono.just(TestObjects.session()));

        useCase.signinUser(query)
                .as(StepVerifier::create)
                .expectNextMatches(session -> TestObjects.session().equals(session))
                .verifyComplete();
    }

    @Test
    void testSigninUserSuccessSessionSaved() {
        var query = new Query<>(TestObjects.signin(), TestObjects.contextData());

        when(signinGateway.validateUser(query)).thenReturn(Mono.just(TestObjects.session()));
        when(signinGateway.saveSession(query)).thenReturn(Mono.just(TestObjects.session()));

        useCase.signinUser(query)
                .as(StepVerifier::create)
                .expectNextMatches(session -> TestObjects.session().equals(session))
                .verifyComplete();
    }

    @Test
    void testSigninUserErrorDontExistUser() {
        var query = new Query<>(TestObjects.signin(), TestObjects.contextData());

        when(signinGateway.validateUser(query))
                .thenReturn(Mono.error(new BusinessException(ConstantBusinessException.USER_NOT_FOUND, query.context())));
        when(signinGateway.saveSession(query)).thenReturn(Mono.just(TestObjects.session()));

        useCase.signinUser(query)
                .as(StepVerifier::create)
                .expectErrorSatisfies(throwable -> {
                    Assertions.assertInstanceOf(BusinessException.class, throwable);
                    Assertions.assertEquals(throwable.getMessage(),
                            ConstantBusinessException.USER_NOT_FOUND.getLogCode());
                })
                .verify();
    }

    @Test
    void testSigninUserErrorInvalidCredentials() {
        var query = new Query<>(TestObjects.signin(), TestObjects.contextData());

        when(signinGateway.validateUser(query))
                .thenReturn(Mono.error(new BusinessException(ConstantBusinessException.INVALID_CREDENTIALS, query.context())));
        when(signinGateway.saveSession(query)).thenReturn(Mono.just(TestObjects.session()));

        useCase.signinUser(query)
                .as(StepVerifier::create)
                .expectErrorSatisfies(throwable -> {
                    Assertions.assertInstanceOf(BusinessException.class, throwable);
                    Assertions.assertEquals(throwable.getMessage(),
                            ConstantBusinessException.INVALID_CREDENTIALS.getLogCode());
                })
                .verify();
    }
}
