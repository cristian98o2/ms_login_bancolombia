package co.com.bancolombia.usecase.shared.password;

import co.com.bancolombia.model.shared.common.value.Constants;
import co.com.bancolombia.model.shared.cqrs.Command;
import co.com.bancolombia.model.shared.cqrs.ContextData;
import co.com.bancolombia.model.shared.exception.BusinessException;
import co.com.bancolombia.model.shared.exception.ConstantBusinessException;
import reactor.core.publisher.Mono;

import java.util.regex.Pattern;

public class ValidatePasswordUseCase {

    private static final Pattern PASSWORD_PATTERN = Pattern.compile(Constants.PASSWORD_REGEX);

    public Mono<Void> validatePassword(Command<String, ContextData> validationCommand) {
        var password = validationCommand.payload();

        if (!PASSWORD_PATTERN.matcher(password).matches()) {
            return Mono.error(new BusinessException(ConstantBusinessException.WEAK_PASSWORD, validationCommand.context()));
        }

        return Mono.empty();
    }
}
