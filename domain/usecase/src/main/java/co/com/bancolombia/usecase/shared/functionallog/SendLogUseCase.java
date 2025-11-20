package co.com.bancolombia.usecase.shared.functionallog;

import co.com.bancolombia.model.shared.cqrs.Command;
import co.com.bancolombia.model.shared.cqrs.ContextData;
import co.com.bancolombia.model.shared.log.gateway.SaveLogGateway;
import co.com.bancolombia.model.shared.log.model.Log;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class SendLogUseCase {
    private final SaveLogGateway saveLogGateway;

    public Mono<Void> sendLog(Command<Log, ContextData> command) {
        return saveLogGateway.sendFunctionalLog(command);
    }
}
