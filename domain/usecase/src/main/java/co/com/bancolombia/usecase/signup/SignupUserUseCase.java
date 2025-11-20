package co.com.bancolombia.usecase.signup;

import co.com.bancolombia.model.shared.bus.command.CommandBus;
import co.com.bancolombia.model.shared.common.value.Constants;
import co.com.bancolombia.model.shared.cqrs.Command;
import co.com.bancolombia.model.shared.cqrs.ContextData;
import co.com.bancolombia.model.shared.log.model.Log;
import co.com.bancolombia.model.user.gateway.ManageUserGateway;
import co.com.bancolombia.model.user.model.Signup;
import co.com.bancolombia.usecase.shared.functionallog.SendLogUseCaseCommand;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RequiredArgsConstructor
public class SignupUserUseCase {

    private final ManageUserGateway manageUserGateway;
    private final CommandBus commandBus;

    public Mono<Void> signupUser(Command<Signup, ContextData> command) {
        return manageUserGateway.signupUser(command)
                .doOnNext(log -> sendSuccessLog(command.payload(), command.context())
                        .subscribeOn(Schedulers.boundedElastic())
                        .subscribe());
    }

    private Mono<Void> sendSuccessLog(Signup payload, ContextData contextData) {
        var commandLog = new SendLogUseCaseCommand(buildSuccessLog(payload, contextData),
                contextData);
        return commandBus.dispatch(commandLog);
    }

    private Log buildSuccessLog(Signup payload, ContextData contextData) {
        contextData.getLog().setResultCode(Constants.STATUS_200_SUCCESS);
        contextData.getLog().setResultDescription(Constants.SUCCESS_TRANSACTION_TITLE);
        contextData.getLog().setAdditionDetails("email: " + payload.getEmail() + "/name: " +
                payload.getName() + "/");
        return contextData.getLog();
    }
}
