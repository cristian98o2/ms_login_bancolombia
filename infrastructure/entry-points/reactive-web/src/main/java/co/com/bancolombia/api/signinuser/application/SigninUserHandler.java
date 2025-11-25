package co.com.bancolombia.api.signinuser.application;

import co.com.bancolombia.api.shared.common.application.HandleResponse;
import co.com.bancolombia.api.shared.common.application.validations.HeadersValidation;
import co.com.bancolombia.api.shared.common.application.validations.TransformRequest;
import co.com.bancolombia.model.shared.cqrs.ContextData;
import co.com.bancolombia.model.shared.cqrs.Query;
import co.com.bancolombia.model.user.signin.model.Signin;
import co.com.bancolombia.model.user.signin.model.response.Session;
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

}
