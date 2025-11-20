package co.com.bancolombia.transversallog.logs.application;

import co.com.bancolombia.functionallog.gateways.LogsTransversalRepository;
import co.com.bancolombia.functionallog.librerialogs.Functionallog;
import co.com.bancolombia.model.shared.cqrs.Command;
import co.com.bancolombia.model.shared.cqrs.ContextData;
import co.com.bancolombia.model.shared.log.gateway.SaveLogGateway;
import co.com.bancolombia.model.shared.log.model.Log;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@AllArgsConstructor
@Log4j2
public class TransversalLogSenderAdapter implements SaveLogGateway {

    private final LogsTransversalRepository logsTransversalRepository;

    @Override
    public Mono<Void> sendFunctionalLog(Command<Log, ContextData> command) {
        return Mono.just(FunctionalLogFactory.build(command.payload()))
                .doOnNext(log -> Mono.defer(() -> emit(log))
                        .subscribeOn(Schedulers.boundedElastic())
                        .subscribe())
                .then();
    }

    private Mono<Void> emit(Functionallog functionallog) {
        return Mono.from(logsTransversalRepository.emit(functionallog));
    }
}
