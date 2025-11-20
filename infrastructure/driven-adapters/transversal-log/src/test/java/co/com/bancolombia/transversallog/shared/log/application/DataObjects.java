package co.com.bancolombia.transversallog.shared.log.application;

import co.com.bancolombia.model.shared.cqrs.ContextData;
import co.com.bancolombia.model.shared.log.model.Log;
import lombok.experimental.UtilityClass;

@UtilityClass
class DataObjects {

    public static final String CHANNEL_VALUE = "NEG";
    public static final String MESSAGE_ID_VALUE = "messageId";
    public static final String AID_CREATOR_VALUE = "aid";
    public static final String ACTION = "action";
    public static final String RESULT_CODE = "200-Exitoso";
    public static final String RESULT_DESCRIPTION = "EL REGISTRO SE HA CREADO CORRECTAMENTE";
    public static final String TRANSACTION_DESCRIPTION = "description";
    public static final String FIS_MESSAGE = "additionalInfo";
    public static final String PREVIOUS_STATE = "beforeValue";
    public static final String STATUS_DESCRIPTION = "afterValue";
    public static final String DOCUMENT_TYPE = "documentType";
    public static final String DOCUMENT_NUMBER = "document";
    public static final String USER = "USER";

    public static Log functionalLog() {
        return Log.builder()
                .action(ACTION)
                .channel(CHANNEL_VALUE)
                .messageId(MESSAGE_ID_VALUE)
                .actorAid(AID_CREATOR_VALUE)
                .actorDocumentType(DOCUMENT_TYPE)
                .actorDocument(DOCUMENT_NUMBER)
                .description(TRANSACTION_DESCRIPTION)
                .resultCode(RESULT_CODE)
                .resultDescription(RESULT_DESCRIPTION)
                .additionDetails(FIS_MESSAGE)
                .beforeValue(PREVIOUS_STATE)
                .afterValue(STATUS_DESCRIPTION)
                .classification(USER)
                .build();
    }

    public static ContextData contextData() {
        return new ContextData("5fa4e536-c95a-49cc-a1c1-b46a1a33c740", "5fa4e536-c95a-49cc-a1c1-b46a1a33c740", "NEG");
    }

}
