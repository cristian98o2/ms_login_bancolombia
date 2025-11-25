package co.com.bancolombia.api.shared.common.domain.response;

import co.com.bancolombia.ecs.model.management.ErrorManagement;
import co.com.bancolombia.model.shared.exception.BusinessException;
import lombok.Data;


@Data
public class ErrorApiResponse {
    private Error errors;

    private ErrorApiResponse(BusinessException exception) {
        this.errors = getError(exception.getConstantBusinessException(), exception.getMetaInfo().getMessageId(),
                exception.getXRequestId());
    }

    public static ErrorApiResponse build(BusinessException exception) {
        return new ErrorApiResponse(exception);
    }

    private Error getError(ErrorManagement exception, String messageId, String xRequestId) {
        return Error.builder()
                .code(exception.getErrorCode())
                .message(exception.getMessage())
                .detail(new Detail())
                .correlation(Correlation.builder()
                        .messageId(messageId)
                        .xRequestId(xRequestId)
                        .build())
                .build();
    }
}
