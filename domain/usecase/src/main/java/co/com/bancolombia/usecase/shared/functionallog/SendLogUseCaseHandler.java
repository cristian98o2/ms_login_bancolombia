package co.com.bancolombia.usecase.shared.functionallog;

import co.com.bancolombia.model.shared.bus.command.CommandHandler;
import co.com.bancolombia.model.shared.bus.model.DomainService;
import co.com.bancolombia.model.shared.cqrs.Command;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@DomainService
@RequiredArgsConstructor
public class SendLogUseCaseHandler implements CommandHandler<SendLogUseCaseCommand> {
    private final SendLogUseCase sendLogUseCase;

    @Override
    public Mono<Void> handler(SendLogUseCaseCommand command) {
        var useCaseCommand = new Command<>(command.log(), command.contextData());
        return Mono.defer(() -> sendLogUseCase.sendLog(useCaseCommand))
                .subscribeOn(Schedulers.single());
    }
}
