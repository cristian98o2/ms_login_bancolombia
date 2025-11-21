package co.com.bancolombia.model.user.signin.gateway;

import co.com.bancolombia.model.shared.cqrs.ContextData;
import co.com.bancolombia.model.shared.cqrs.Query;
import co.com.bancolombia.model.user.signin.model.Signin;
import co.com.bancolombia.model.user.signin.model.response.Session;
import reactor.core.publisher.Mono;

public interface SigninGateway {

    Mono<Session> validateUser(Query<Signin, ContextData> query);

    Mono<Session> saveSession(Query<Signin, ContextData> query);

}
