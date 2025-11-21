package co.com.bancolombia.api.shared.common.application.validations;

import co.com.bancolombia.api.shared.common.domain.HeaderConstant;
import co.com.bancolombia.model.shared.cqrs.ContextData;
import co.com.bancolombia.model.shared.exception.BusinessException;
import co.com.bancolombia.model.shared.exception.ConstantBusinessException;
import lombok.experimental.UtilityClass;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@UtilityClass
public class HeadersValidation {

    public static Mono<ContextData> validateHeaders(ServerRequest request) {
        var messageId = request.headers().firstHeader(HeaderConstant.MESSAGE_ID.value());
        var xRequestId = request.headers().firstHeader(HeaderConstant.X_REQUEST_ID.value());

        if (isNullOrEmpty(messageId, xRequestId)) {
            return Mono.error(new BusinessException(ConstantBusinessException.MALFORMED_REQUEST));
        }

        return Mono.just(new ContextData(messageId, xRequestId));
    }

    private static boolean isNullOrEmpty(String... values) {
        return Arrays.stream(values).anyMatch(value -> value == null || value.isEmpty());
    }

}
