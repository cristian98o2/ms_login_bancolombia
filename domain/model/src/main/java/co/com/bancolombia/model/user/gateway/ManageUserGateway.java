package co.com.bancolombia.model.user.gateway;

import co.com.bancolombia.model.shared.cqrs.Command;
import co.com.bancolombia.model.shared.cqrs.ContextData;
import co.com.bancolombia.model.shared.cqrs.Query;
import co.com.bancolombia.model.user.model.Signin;
import co.com.bancolombia.model.user.model.Signup;
import co.com.bancolombia.model.user.model.response.Session;
import reactor.core.publisher.Mono;

public interface ManageUserGateway {
    
    Mono<Void> signupUser(Command<Signup, ContextData> command);

    Mono<Session> validateUser(Query<Signin, ContextData> query);

    Mono<Session> saveSession(Query<Signin, ContextData> query);
}
