package co.com.bancolombia.transversallog.logs.application;

import co.com.bancolombia.functionallog.librerialogs.Functionallog;
import co.com.bancolombia.model.shared.common.value.Constants;
import co.com.bancolombia.model.shared.log.model.Log;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneId;

import static java.time.zone.ZoneOffsetTransitionRule.TimeDefinition.UTC;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class FunctionalLogFactory {
    public static final int MAX_CHARACTER_LENGTH = 1000;
    private static final String ZONE = "America/Bogota";

    public static Functionallog build(Log log) {
        var functionalLog = new Functionallog();
        setType(functionalLog, log);
        setTime(functionalLog);
        setRegistry(functionalLog, log);
        setDetails(functionalLog, log);
        setAdditionalInfo(functionalLog, log.getAdditionDetails());

        return functionalLog;
    }

    private static void setType(Functionallog functionalLog, Log log) {
        var action = log.getAction();
        var process = Constants.LOGIN;
        var app = log.getChannel();
        var classification = log.getClassification();
        functionalLog.withType(classification, action, app, process);
    }

    private static void setTime(Functionallog functionalLog) {
        var responseDate = LocalDateTime.now(ZoneId.of(ZONE));
        var year = responseDate.getYear();
        var month = responseDate.getMonthValue();
        var day = responseDate.getDayOfMonth();
        var hour = responseDate.getHour();
        var minute = responseDate.getMinute();
        var second = responseDate.getSecond();
        var milliseconds = (long) responseDate.getNano();
        var timeZone = (UTC + responseDate.atZone(ZoneId.of(ZONE)).getOffset().toString());

        functionalLog.withTime(year, month, day, hour, minute, second, milliseconds, timeZone);
    }


    private static void setRegistry(Functionallog functionalLog, Log log) {
        var sourceId = log.getMessageId();
        var documentType = log.getActorDocumentType() != null ?
                log.getActorDocumentType() : "";
        var document = log.getActorDocument() != null ?
                log.getActorDocument() : "";
        var actor = log.getActorAid() != null ? log.getActorAid() : "";
        var transactionalId = log.getMessageId();

        functionalLog.withRegistry(sourceId, actor, documentType, document, transactionalId);
    }

    private static void setDetails(Functionallog functionalLog, Log log) {
        var description = log.getDescription();
        var beforeValue = log.getBeforeValue();
        var afterValue = log.getAfterValue();
        var transactionResultCode = log.getResultCode();
        var transactionResultDescription = log.getResultDescription();

        functionalLog.withDetails(
                description,
                beforeValue,
                afterValue,
                transactionResultCode,
                transactionResultDescription);
    }

    private static void setAdditionalInfo(Functionallog functionallog, String additionalInformation) {
        var additionalInfo = additionalInformation.length() > MAX_CHARACTER_LENGTH ?
                additionalInformation.substring(0, MAX_CHARACTER_LENGTH) : additionalInformation;
        functionallog.withAdditionalInfo(additionalInfo);
    }
}
