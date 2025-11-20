package co.com.bancolombia.model.shared.common.value;

import lombok.experimental.UtilityClass;

import java.security.Key;

@UtilityClass
public class Constants {

    public static final String SIGNUP = "SIGNUP";
    public static final String SIGNUP_DESCRIPTION = "Registro de usuario";
    public static final String USER = "USER";
    public static final String EVENT = "EVENT";
    public static final String APP_NAME = "LoginBancolombiaMs";
    public static final String LOGIN = "LOGIN";

    //Date formats
    public static final String OFFSET_ID = "America/Bogota";
    public static final String DATE_TIME_FORMATTER = "dd/MM/yyyy HH:mm:ss:SSSS";

    public static final String STATUS_200_SUCCESS = "200-EXITOSO";
    public static final String SUCCESS_TRANSACTION_TITLE = "Operación exitosa";

    public static final String EMAIL_REGEX = "^[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z]{2,6}$";
    public static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

    public static final String SECRET_STRING = "12345678912345678912345678912345";
    public static final long EXPIRATION_TIME = 300000;
}
