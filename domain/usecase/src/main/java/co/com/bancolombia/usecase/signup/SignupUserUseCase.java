package co.com.bancolombia.usecase.signup;

import co.com.bancolombia.model.shared.cqrs.Command;
import co.com.bancolombia.model.shared.cqrs.ContextData;
import co.com.bancolombia.model.user.signup.gateway.SignupGateway;
import co.com.bancolombia.model.user.signup.model.Signup;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class SignupUserUseCase {

    private final SignupGateway signupGateway;

    public Mono<Void> signupUser(Command<Signup, ContextData> command) {
        return signupGateway.signupUser(command);
    }

}
