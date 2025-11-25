package co.com.bancolombia.api.signupuser.application;

import co.com.bancolombia.api.shared.common.application.HandleResponse;
import co.com.bancolombia.api.shared.common.application.validations.HeadersValidation;
import co.com.bancolombia.api.shared.common.application.validations.TransformRequest;
import co.com.bancolombia.api.shared.common.application.validations.ValidateEmail;
import co.com.bancolombia.api.shared.common.application.validations.ValidatePassword;
import co.com.bancolombia.model.shared.cqrs.Command;
import co.com.bancolombia.model.shared.cqrs.ContextData;
import co.com.bancolombia.model.user.signup.model.Signup;
import co.com.bancolombia.usecase.signup.SignupUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class SignupUserHandler {

    private final SignupUserUseCase signupUserUseCase;

    public Mono<ServerResponse> postSignupUser(ServerRequest serverRequest) {
        return HeadersValidation.validateHeaders(serverRequest)
                .flatMap(contextData -> TransformRequest.fromSignupRequest(serverRequest, contextData)
                        .flatMap(signup -> validateEmail(signup, contextData))
                        .flatMap(signup -> validatePassword(signup, contextData))
                        .flatMap(signup -> callUseCase(signup, contextData))
                        .flatMap(signup -> buildResponse(contextData, serverRequest)));
    }

    private Mono<ServerResponse> buildResponse(ContextData contextData,
                                               ServerRequest serverRequest) {
        return HandleResponse.createSuccessResponse(null, contextData, HttpStatus.OK, serverRequest);
    }

    private Mono<Signup> callUseCase(Signup signup, ContextData contextData) {
        var command = new Command<>(signup, contextData);
        return signupUserUseCase.signupUser(command)
                .thenReturn(signup);
    }

    private Mono<Signup> validatePassword(Signup signup, ContextData contextData) {
        var validationCommand = new Command<>(signup.getPassword(), contextData);

        return ValidatePassword.validatePassword(validationCommand)
                .thenReturn(signup);
    }

    private Mono<Signup> validateEmail(Signup signup, ContextData contextData) {
        var validationCommand = new Command<>(signup.getEmail(), contextData);

        return ValidateEmail.validateEmail(validationCommand)
                .thenReturn(signup);
    }
}
