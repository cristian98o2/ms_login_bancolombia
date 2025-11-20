package co.com.bancolombia.api.signinuser.application;

import co.com.bancolombia.api.shared.common.application.HandleResponse;
import co.com.bancolombia.api.shared.common.application.validations.HeadersValidation;
import co.com.bancolombia.api.shared.common.infra.TransformRequest;
import co.com.bancolombia.model.shared.common.value.Constants;
import co.com.bancolombia.model.shared.cqrs.ContextData;
import co.com.bancolombia.model.shared.cqrs.Query;
import co.com.bancolombia.model.shared.log.model.Log;
import co.com.bancolombia.model.user.model.Signin;
import co.com.bancolombia.model.user.model.response.Session;
import co.com.bancolombia.usecase.signin.SigninUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class SigninUserHandler {

    private  final SigninUserUseCase signinUserUseCase;

    public Mono<ServerResponse> postSigninUser(ServerRequest serverRequest) {

        return HeadersValidation.validateHeaders(serverRequest)
                .flatMap(contextData -> TransformRequest.fromSigninRequest(serverRequest, contextData)
                        .doOnNext(signin -> contextData.setLog(
                                buildLog(contextData)))
                        .flatMap(signin -> callUseCase(signin, contextData))
                        .flatMap(session -> buildResponse(session, contextData, serverRequest)));

    }

    private Mono<ServerResponse> buildResponse(Session session, ContextData contextData,
                                               ServerRequest serverRequest) {
        return HandleResponse.createSuccessResponse(session, contextData, HttpStatus.OK, serverRequest);
    }

    private Mono<Session> callUseCase(Signin signin, ContextData contextData) {
        var query = new Query<>(signin, contextData);
        return signinUserUseCase.signinUser(query);
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
