package co.com.bancolombia.model.shared.common.value;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Constants {

    //Date formats
    public static final String OFFSET_ID = "America/Bogota";
    public static final String DATE_TIME_FORMATTER = "dd/MM/yyyy HH:mm:ss:SSSS";

    public static final String EMAIL_REGEX = "^[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z]{2,6}$";
    public static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&\\.])[A-Za-z\\d@$!%*?&\\.]{8,}$";

    public static final String SECRET_STRING = "12345678912345678912345678912345";
    public static final long EXPIRATION_TIME = 300000;
}
