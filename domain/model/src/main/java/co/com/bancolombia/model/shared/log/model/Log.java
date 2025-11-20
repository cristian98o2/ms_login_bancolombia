package co.com.bancolombia.model.shared.log.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
public class Log {
    private final String classification;
    private final String action;
    private final String channel;
    private final String messageId;
    private String actorAid;
    private String actorDocument;
    private String actorDocumentType;
    private final String description;
    private String resultCode;
    private String resultDescription;
    @Builder.Default
    private String additionDetails = "";
    @Builder.Default
    private String beforeValue = "";
    @Builder.Default
    private String afterValue = "";
    private final LocalDateTime time;

    public static Log copyOf(Log log) {
        return Log.builder()
                .action(log.getAction())
                .actorDocument(log.getActorDocument())
                .actorDocumentType(log.getActorDocumentType())
                .messageId(log.getMessageId())
                .beforeValue(log.getBeforeValue())
                .afterValue(log.getAfterValue())
                .additionDetails(log.getAdditionDetails())
                .description(log.getDescription())
                .resultCode(log.getResultCode())
                .resultDescription(log.getResultDescription())
                .classification(log.getClassification())
                .channel(log.getChannel())
                .build();
    }
}
