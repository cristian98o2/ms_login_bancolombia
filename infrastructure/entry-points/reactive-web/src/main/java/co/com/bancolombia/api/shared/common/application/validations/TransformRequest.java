package co.com.bancolombia.api.shared.common.application.validations;

import co.com.bancolombia.api.signinuser.domain.SigninRequest;
import co.com.bancolombia.api.signupuser.domain.SignupRequest;
import co.com.bancolombia.model.shared.cqrs.ContextData;
import co.com.bancolombia.model.shared.exception.BusinessException;
import co.com.bancolombia.model.shared.exception.ConstantBusinessException;
import co.com.bancolombia.model.user.signin.model.Signin;
import co.com.bancolombia.model.user.signup.model.Signup;
import lombok.experimental.UtilityClass;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Objects;

@UtilityClass
public class TransformRequest {

    public static Mono<Signup> fromSignupRequest(ServerRequest serverRequest, ContextData contextData){
        return serverRequest.bodyToMono(SignupRequest.class)
                .filter(request -> request.getData() != null)
                .switchIfEmpty(Mono.error(
                        new BusinessException(ConstantBusinessException.MALFORMED_REQUEST)))
                .map(signupRequest -> mapperToSignupModel(signupRequest, serverRequest))
                .onErrorMap(BusinessException.class, e ->
                        new BusinessException((ConstantBusinessException) e.getConstantBusinessException(),
                                e.getOptionalInfo(), contextData));
    }

    private static Signup mapperToSignupModel(SignupRequest signupRequest, ServerRequest serverRequest) {
        var data = signupRequest.getData();

        if (isNull(data.getName(), data.getEmail(), data.getPassword())) {
            throw new BusinessException(ConstantBusinessException.MALFORMED_REQUEST);
        }

        return new Signup(data.getEmail(), data.getPassword(), data.getName());
    }

    public static Mono<Signin> fromSigninRequest(ServerRequest serverRequest, ContextData contextData){
        return serverRequest.bodyToMono(SigninRequest.class)
                .filter(request -> request.getData() != null)
                .switchIfEmpty(Mono.error(
                        new BusinessException(ConstantBusinessException.MALFORMED_REQUEST)))
                .map(signinRequest -> mapperToSigninModel(signinRequest, serverRequest))
                .onErrorMap(BusinessException.class, e ->
                        new BusinessException((ConstantBusinessException) e.getConstantBusinessException(),
                                e.getOptionalInfo(), contextData));
    }

    private static Signin mapperToSigninModel(SigninRequest signinRequest, ServerRequest serverRequest) {
        var data = signinRequest.getData();

        if (isNull(data.getEmail(), data.getPassword())) {
            throw new BusinessException(ConstantBusinessException.MALFORMED_REQUEST);
        }

        return new Signin(data.getEmail(), data.getPassword());
    }

    private static boolean isNull(Object... values) {
        return Arrays.stream(values).anyMatch(Objects::isNull);
    }
}
