package co.com.bancolombia.api.shared.common.application.validations;

import co.com.bancolombia.model.shared.common.value.Constants;
import co.com.bancolombia.model.shared.cqrs.Command;
import co.com.bancolombia.model.shared.cqrs.ContextData;
import co.com.bancolombia.model.shared.exception.BusinessException;
import co.com.bancolombia.model.shared.exception.ConstantBusinessException;
import lombok.experimental.UtilityClass;
import reactor.core.publisher.Mono;

import java.util.regex.Pattern;

@UtilityClass
public class ValidateEmail {

    private static final Pattern EMAIL_PATTERN = Pattern.compile(Constants.EMAIL_REGEX);

    public static Mono<Void> validateEmail(Command<String, ContextData> validationCommand) {
        var email = validationCommand.payload();

        if (!EMAIL_PATTERN.matcher(email).matches()) {
            return Mono.error(new BusinessException(ConstantBusinessException.INVALID_EMAIL_FORMAT, validationCommand.context()));
        }

        return Mono.empty();
    }
}
