package co.com.bancolombia.model.shared.log.model.info;

import lombok.experimental.UtilityClass;

@UtilityClass
public class AdditionalInfo {
    public static final String RELATIONSHIP_DELETED_ADDITIONAL_INFO = "delegateDocumentNumber:%s/" +
            "delegateDocumentType:%s/relationshipId:%s/roleId:%s/channel:%s";
    public static final String ROLE_DELETED_ADDITIONAL_INFO = "roleId:%s/roleName:%s";
    public static final String SCHEMA_DELETED_ADDITIONAL_INFO =
            "relationshipIdList:%s/schema:%s/relationshipNumber:%s/transactionExpirationDays:%s";
    public static final String GROUP_DELETED_ADDITIONAL_INFO = "groupId:%s/groupName:%s";
    public static final String TRANSACTION_DELETED_ADDITIONAL_INFO = "transactionId:%s/statusDescription:%s/";
    public static final String AUTHORIZATION_FLOW_DELETED_DESCRIPTION_INFO = "idFlow:%s/nameStatus:%s/";
}
