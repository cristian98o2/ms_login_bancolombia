package co.com.bancolombia.model.user.signup.model;

import co.com.bancolombia.model.shared.exception.BusinessException;
import co.com.bancolombia.model.shared.exception.ConstantBusinessException;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

@Getter
public class Signup {

    private final String email;
    private final String password;
    private final String name;

    public Signup(String email, String password, String name) {
        nonNullCheck(email, password);
        this.email = email;
        this.password = password;
        this.name = name;
    }

    private void nonNullCheck(Object... values) {
        if (Arrays.stream(values).anyMatch(Objects::isNull)) {
            throw new BusinessException(ConstantBusinessException.MALFORMED_REQUEST);
        }
    }
}
