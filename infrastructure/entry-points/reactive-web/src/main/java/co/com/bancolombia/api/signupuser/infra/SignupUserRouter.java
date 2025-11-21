package co.com.bancolombia.api.signupuser.infra;

import co.com.bancolombia.api.shared.common.domain.Paths;
import co.com.bancolombia.api.signupuser.application.SignupUserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class SignupUserRouter {

    @Bean
    public RouterFunction<ServerResponse> signupRrouterFunction(SignupUserHandler signupUserHandler) {
        return route(POST(Paths.POST_SIGNUP_USER), signupUserHandler::postSignupUser);
    }

}
