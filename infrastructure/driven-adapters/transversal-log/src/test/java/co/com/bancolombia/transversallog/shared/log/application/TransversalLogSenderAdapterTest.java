package co.com.bancolombia.transversallog.shared.log.application;

import co.com.bancolombia.functionallog.gateways.LogsTransversalRepository;
import co.com.bancolombia.functionallog.librerialogs.Functionallog;
import co.com.bancolombia.model.shared.cqrs.Command;
import co.com.bancolombia.transversallog.logs.application.TransversalLogSenderAdapter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
class TransversalLogSenderAdapterTest {

    @Mock
    private LogsTransversalRepository logsTransversalRepository;

    @InjectMocks
    private TransversalLogSenderAdapter transversalLogSenderAdapter;

    @Test
    void shouldEmitLogSuccessfullyAndLogAppropriateMessagesTest() {
        var logInfo = DataObjects.functionalLog();
        lenient().when(logsTransversalRepository.emit(any(Functionallog.class))).thenReturn(Mono.empty().then());

        var result = transversalLogSenderAdapter.sendFunctionalLog(new Command<>(logInfo, DataObjects.contextData()));

        StepVerifier.create(result)
                .expectSubscription()
                .verifyComplete();
    }



}
