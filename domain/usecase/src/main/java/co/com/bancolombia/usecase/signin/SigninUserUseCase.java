package co.com.bancolombia.usecase.signin;

import co.com.bancolombia.model.shared.cqrs.ContextData;
import co.com.bancolombia.model.shared.cqrs.Query;
import co.com.bancolombia.model.user.signin.gateway.SigninGateway;
import co.com.bancolombia.model.user.signin.model.Signin;
import co.com.bancolombia.model.user.signin.model.response.Session;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class SigninUserUseCase {

    private final SigninGateway signinGateway;

    public Mono<Session> signinUser(Query<Signin, ContextData> query) {
        return signinGateway.validateUser(query)
                .switchIfEmpty(signinGateway.saveSession(query));
    }

}
