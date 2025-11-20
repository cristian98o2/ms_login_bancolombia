package co.com.bancolombia.transversallog.shared.log.application;

import co.com.bancolombia.model.shared.common.value.Constants;
import co.com.bancolombia.transversallog.logs.application.FunctionalLogFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;

class FunctionalLogFactoryTest {
    private static final String CLASSIFICATION = "USER";
    private static final String ZONE_ID = "-05:00";

    @Test
    void buildTest() {
        var log = DataObjects.functionalLog();
        var functionallog = FunctionalLogFactory.build(log);

        Assertions.assertNotNull(functionallog);
        Assertions.assertEquals(CLASSIFICATION, functionallog.getType().getClassification());
        Assertions.assertEquals(DataObjects.ACTION, functionallog.getType().getAction());
        Assertions.assertEquals(DataObjects.CHANNEL_VALUE, functionallog.getType().getApp());
        Assertions.assertEquals(Constants.ENTITLEMENT, functionallog.getType().getProcess());
        Assertions.assertEquals(LocalDateTime.now(ZoneId.of(ZONE_ID)).getYear(), functionallog.getTime().getYear());
        Assertions.assertEquals(DataObjects.TRANSACTION_DESCRIPTION, functionallog.getDetails().getDescription());
        Assertions.assertEquals(DataObjects.PREVIOUS_STATE, functionallog.getDetails().getBeforeValue());
        Assertions.assertEquals(DataObjects.STATUS_DESCRIPTION, functionallog.getDetails().getAfterValue());
        Assertions.assertEquals(DataObjects.RESULT_CODE, functionallog.getDetails().getTransactionResultCode());
        Assertions.assertEquals(DataObjects.RESULT_DESCRIPTION, functionallog.getDetails()
                .getTransactionResultDescription());
        Assertions.assertEquals(DataObjects.DOCUMENT_TYPE, functionallog.getRegistry().getDocumentType());
        Assertions.assertEquals(DataObjects.DOCUMENT_NUMBER, functionallog.getRegistry().getDocument());
        Assertions.assertEquals(DataObjects.AID_CREATOR_VALUE, functionallog.getRegistry().getActor());
        Assertions.assertEquals(DataObjects.MESSAGE_ID_VALUE, functionallog.getRegistry().getTransactionalId());
        Assertions.assertEquals(DataObjects.FIS_MESSAGE, functionallog.getAdditionalInfo());
    }

    @Test
    void buildThrowsExceptionTest() {
        Assertions.assertThrows(NullPointerException.class, () -> FunctionalLogFactory.build(null));
    }


}
