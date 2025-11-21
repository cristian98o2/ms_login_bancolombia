package co.com.bancolombia.model.user.signup.gateway;

import co.com.bancolombia.model.shared.cqrs.Command;
import co.com.bancolombia.model.shared.cqrs.ContextData;
import co.com.bancolombia.model.user.signup.model.Signup;
import reactor.core.publisher.Mono;

public interface SignupGateway {
    
    Mono<Void> signupUser(Command<Signup, ContextData> command);

}
