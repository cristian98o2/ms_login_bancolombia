package co.com.bancolombia.usecase.signin;

import co.com.bancolombia.model.shared.bus.command.CommandBus;
import co.com.bancolombia.model.shared.common.value.Constants;
import co.com.bancolombia.model.shared.cqrs.ContextData;
import co.com.bancolombia.model.shared.cqrs.Query;
import co.com.bancolombia.model.shared.log.model.Log;
import co.com.bancolombia.model.user.gateway.ManageUserGateway;
import co.com.bancolombia.model.user.model.Signin;
import co.com.bancolombia.model.user.model.Signup;
import co.com.bancolombia.model.user.model.response.Session;
import co.com.bancolombia.usecase.shared.functionallog.SendLogUseCaseCommand;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RequiredArgsConstructor
public class SigninUserUseCase {

    private final ManageUserGateway manageUserGateway;
    private final CommandBus commandBus;

    public Mono<Session> signinUser(Query<Signin, ContextData> query) {
        return manageUserGateway.validateUser(query)
                .switchIfEmpty(manageUserGateway.saveSession(query))
                .doOnNext(session -> sendSuccessLog(session, query.payload(), query.context())
                        .subscribeOn(Schedulers.boundedElastic())
                        .subscribe());
    }

    private Mono<Void> sendSuccessLog(Session session, Signin payload, ContextData contextData) {
        var commandLog = new SendLogUseCaseCommand(buildSuccessLog(session, payload, contextData),
                contextData);
        return commandBus.dispatch(commandLog);
    }

    private Log buildSuccessLog(Session session, Signin payload, ContextData contextData) {
        contextData.getLog().setResultCode(Constants.STATUS_200_SUCCESS);
        contextData.getLog().setResultDescription(Constants.SUCCESS_TRANSACTION_TITLE);
        contextData.getLog().setAdditionDetails("email: " + payload.getEmail() + "/session: " +
                session.getSessionId() + "/");
        return contextData.getLog();
    }

}
