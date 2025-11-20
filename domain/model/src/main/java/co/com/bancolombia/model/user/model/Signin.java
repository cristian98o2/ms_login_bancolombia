package co.com.bancolombia.model.user.model;

import co.com.bancolombia.model.shared.exception.BusinessException;
import co.com.bancolombia.model.shared.exception.ConstantBusinessException;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

@Getter
public class Signin {

    private final String email;
    private final String password;

    public Signin(String email, String password) {
        nonNullCheck(email, password);
        this.email = email;
        this.password = password;
    }

    private void nonNullCheck(Object... values) {
        if (Arrays.stream(values).anyMatch(Objects::isNull)) {
            throw new BusinessException(ConstantBusinessException.MALFORMED_REQUEST);
        }
    }
}
