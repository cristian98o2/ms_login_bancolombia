package co.com.bancolombia.api.signinuser.infra;

import co.com.bancolombia.api.shared.common.domain.Paths;
import co.com.bancolombia.api.signinuser.application.SigninUserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class SigninUserRouter {

    @Bean
    public RouterFunction<ServerResponse> routerFunction(SigninUserHandler signinUserHandler) {
        return route(POST(Paths.POST_SIGNIN_USER), signinUserHandler::postSigninUser);
    }

}
