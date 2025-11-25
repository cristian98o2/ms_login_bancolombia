package co.com.bancolombia.api.shared.common.application;

import co.com.bancolombia.api.shared.common.domain.HeaderConstant;
import co.com.bancolombia.api.shared.common.domain.response.SuccessApiResponse;
import co.com.bancolombia.model.shared.common.value.Constants;
import co.com.bancolombia.model.shared.cqrs.ContextData;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.Consumer;

@UtilityClass
public class HandleResponse {

    public static Mono<ServerResponse> createSuccessResponse(Object content, ContextData contextData,
                                                             HttpStatus status, ServerRequest serverRequest) {
        return ServerResponse
                .status(status)
                .headers(buildHeaders(serverRequest, contextData))
                .bodyValue(SuccessApiResponse.build(content));
    }

    private Consumer<HttpHeaders> buildHeaders(ServerRequest serverRequest, ContextData contextData) {
        return headers -> {
            HttpHeaders filteredHeaders = new HttpHeaders();
            filteredHeaders.addAll(serverRequest.headers().asHttpHeaders());
            headers.addAll(filteredHeaders);
            headers.set(HeaderConstant.X_REQUEST_ID.value(), contextData.getXRequestId().getValue().toString());
        };
    }
}
