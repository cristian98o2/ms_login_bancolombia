package co.com.bancolombia.api.shared.common.domain.response;

import co.com.bancolombia.model.shared.cqrs.ContextData;
import lombok.Data;

@Data
public class SuccessApiResponse {
    private Object data;

    private SuccessApiResponse(Object data) {
        this.data = data;
    }

    public static SuccessApiResponse build(Object data) {
        return new SuccessApiResponse(data);
    }
}
