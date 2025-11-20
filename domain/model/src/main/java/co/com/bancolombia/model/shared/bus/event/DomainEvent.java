package co.com.bancolombia.model.shared.bus.event;

import co.com.bancolombia.model.shared.common.value.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class DomainEvent<T> {
    private static final String DATA_CONTENT_TYPE = "application/json";
    private final String id;
    private final String source;
    private final String type;
    private final String dataContentType;
    private final String dataSchema;
    private final String subject;
    private final LocalDateTime time;
    private final Extension extension;
    private final T data;

    protected DomainEvent(String id, String source, String dataSchema, String subject,
                          LocalDateTime time, T data) {
        this.id = id;
        this.source = source;
        this.type = Constants.EVENT;
        this.dataContentType = DATA_CONTENT_TYPE;
        this.dataSchema = dataSchema;
        this.subject = subject;
        this.time = time;
        this.extension = new Extension(Constants.APP_NAME);
        this.data = data;
    }

    @Data
    @AllArgsConstructor
    public static class Extension {
        private final String source;
    }
}
