package co.com.bancolombia.api.signupuser.application;

import co.com.bancolombia.api.shared.common.application.HandleResponse;
import co.com.bancolombia.api.shared.common.application.validations.HeadersValidation;
import co.com.bancolombia.api.shared.common.infra.TransformRequest;
import co.com.bancolombia.model.shared.common.value.Constants;
import co.com.bancolombia.model.shared.cqrs.Command;
import co.com.bancolombia.model.shared.cqrs.ContextData;
import co.com.bancolombia.model.shared.log.model.Log;
import co.com.bancolombia.model.user.model.Signup;
import co.com.bancolombia.usecase.shared.email.ValidateEmailUseCase;
import co.com.bancolombia.usecase.shared.password.ValidatePasswordUseCase;
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

    private final ValidateEmailUseCase validateEmailUseCase;
    private final ValidatePasswordUseCase validatePasswordUseCase;
    private final SignupUserUseCase signupUserUseCase;

    public Mono<ServerResponse> postSignupUser(ServerRequest serverRequest) {
        return HeadersValidation.validateHeaders(serverRequest)
                .flatMap(contextData -> TransformRequest.fromSignupRequest(serverRequest, contextData)
                        .doOnNext(signup -> contextData.setLog(
                                buildLog(contextData)))
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

        return validatePasswordUseCase.validatePassword(validationCommand)
                .thenReturn(signup);
    }

    private Mono<Signup> validateEmail(Signup signup, ContextData contextData) {
        var validationCommand = new Command<>(signup.getEmail(), contextData);

        return validateEmailUseCase.validateEmail(validationCommand)
                .thenReturn(signup);
    }

    private Log buildLog(ContextData contextData) {
        return Log.builder()
                .action(Constants.SIGNUP)
                .messageId(contextData.getMessageId().getValue().toString())
                .description(Constants.SIGNUP_DESCRIPTION)
                .classification(Constants.USER)
                .build();
    }
}
