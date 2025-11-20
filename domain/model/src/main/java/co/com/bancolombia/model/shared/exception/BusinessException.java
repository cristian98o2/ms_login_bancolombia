package co.com.bancolombia.model.shared.exception;

import co.com.bancolombia.ecs.model.management.BusinessExceptionECS;
import co.com.bancolombia.model.shared.cqrs.ContextData;
import co.com.bancolombia.model.shared.log.model.Log;
import lombok.Getter;

import java.util.Map;

@Getter
public class BusinessException extends BusinessExceptionECS {

    private final transient Log log;

    public BusinessException(String message) {
        super(message);
        this.log = null;
    }

    public BusinessException(ConstantBusinessException message) {
        super(message);
        this.log = null;
    }

    public BusinessException(ConstantBusinessException message, String optionalInfo) {
        super(message, optionalInfo);
        this.log = null;
    }

    public BusinessException(ConstantBusinessException message, MetaInfo metaInfo) {
        super(message, metaInfo);
        this.log = null;
    }

    public BusinessException(ConstantBusinessException message, Map<String, String> optionalInfo) {
        super(message, optionalInfo);
        this.log = null;
    }

    public BusinessException(ConstantBusinessException message, String optionalInfo, MetaInfo metaInfo) {
        super(message, optionalInfo, metaInfo);
        this.log = null;
    }

    public BusinessException(ConstantBusinessException message, Map<String, String> optionalInfo, MetaInfo metaInfo) {
        super(message, optionalInfo, metaInfo);
        this.log = null;
    }

    public BusinessException(ConstantBusinessException message, ContextData contextData) {
        super(message, MetaInfo.builder().messageId(contextData.getMessageId().getValue().toString()).build());
        this.log = contextData.getLog();
    }

    public BusinessException(ConstantBusinessException message, String optionalInfo, ContextData contextData) {
        super(message, optionalInfo, MetaInfo.builder().messageId(contextData.getMessageId().getValue().toString())
                .build());
        this.log = contextData.getLog();
    }

    public BusinessException(ConstantBusinessException message, Map<String, String> optionalInfo,
                             ContextData contextData) {
        super(message, optionalInfo, MetaInfo.builder().messageId(contextData.getMessageId().getValue().toString())
                .build());
        this.log = contextData.getLog();
    }
}
