package co.com.bancolombia.model.shared.exception;

import co.com.bancolombia.ecs.model.management.ErrorManagement;

import static java.net.HttpURLConnection.*;


public enum ConstantBusinessException implements ErrorManagement {
    EMAIL_ALREADY_EXISTS(
            HTTP_CONFLICT,
            CodeMessage.EMAIL_ALREADY_EXISTS,
            BusinessCode.ERROR_EMAIL_ALREADY_EXISTS,
            InternalMessage.EMAIL_ALREADY_EXISTS,
            CodeLog.LB_01),
    INVALID_EMAIL_FORMAT(
            HTTP_BAD_REQUEST,
            CodeMessage.INVALID_EMAIL_FORMAT,
            BusinessCode.INVALID_EMAIL_FORMAT,
            InternalMessage.INVALID_EMAIL_FORMAT,
            CodeLog.LB_02),
    WEAK_PASSWORD(
            HTTP_BAD_REQUEST,
            CodeMessage.WEAK_PASSWORD,
            BusinessCode.WEAK_PASSWORD,
            InternalMessage.WEAK_PASSWORD,
            CodeLog.LB_03),
    MALFORMED_REQUEST(
            HTTP_BAD_REQUEST,
            CodeMessage.MALFORMED_REQUEST,
            BusinessCode.MALFORMED_REQUEST,
            InternalMessage.MALFORMED_REQUEST,
            CodeLog.LB_04),
    USER_NOT_FOUND(
            HTTP_NOT_FOUND,
            CodeMessage.USER_NOT_FOUND,
            BusinessCode.USER_NOT_FOUND,
            InternalMessage.USER_NOT_FOUND,
            CodeLog.LB_05),
    INVALID_CREDENTIALS(
            HTTP_UNAUTHORIZED,
            CodeMessage.INVALID_CREDENTIALS,
            BusinessCode.INVALID_CREDENTIALS,
            InternalMessage.INVALID_CREDENTIALS,
            CodeLog.LB_06),
    UNEXPECTED_ERROR(
            HTTP_INTERNAL_ERROR,
            CodeMessage.UNEXPECTED_ERROR_DETAIL,
            BusinessCode.UNEXPECTED_ERROR_CODE,
            InternalMessage.UNEXPECTED_ERROR_DETAIL,
            CodeLog.LB_07),;


    private final Integer status;
    private final String message;
    private final String errorCode;
    private final String internalMessage;
    private final String logCode;

    ConstantBusinessException(Integer status, String message, String errorCode, String internalMessage,
                              String logCode) {
        this.status = status;
        this.message = message;
        this.errorCode = errorCode;
        this.internalMessage = internalMessage;
        this.logCode = logCode;
    }

    public static class CodeMessage {
        public static final String EMAIL_ALREADY_EXISTS = "Email ya registrado";
        public static final String INVALID_EMAIL_FORMAT = "Email incorrecto";
        public static final String WEAK_PASSWORD = "Contraseña debil";
        public static final String MALFORMED_REQUEST = "Request incorrecto";
        public static final String USER_NOT_FOUND = "Usuario no encontrado";
        public static final String INVALID_CREDENTIALS = "Credenciales incorrectas";
        public static final String UNEXPECTED_ERROR_DETAIL = "Ha ocurrido un error interno en el servicio";
    }

    public static class BusinessCode {
        public static final String ERROR_EMAIL_ALREADY_EXISTS = "LB-01";
        public static final String INVALID_EMAIL_FORMAT = "LB-02";
        public static final String WEAK_PASSWORD = "LB-03";
        public static final String MALFORMED_REQUEST = "LB-04";
        public static final String USER_NOT_FOUND = "LB-05";
        public static final String INVALID_CREDENTIALS = "LB-06";
        public static final String UNEXPECTED_ERROR_CODE = "LB-07";
    }

    public static class InternalMessage {
        public static final String EMAIL_ALREADY_EXISTS = "El email ya se encuentra registrado";
        public static final String INVALID_EMAIL_FORMAT = "El email no cumple con las reglas";
        public static final String WEAK_PASSWORD = "La contraseña es debil en seguridad";
        public static final String MALFORMED_REQUEST = "El request no esta bien formulado";
        public static final String USER_NOT_FOUND = "No existe registro del usuario";
        public static final String INVALID_CREDENTIALS = "Las credenciales no coinciden con los registros";
        public static final String UNEXPECTED_ERROR_DETAIL = "Ha ocurrido un problema inesperado en el sistema.";
    }

    private static class CodeLog {
        public static final String LB_01 = "LB-01";
        public static final String LB_02 = "LB-02";
        public static final String LB_03 = "LB-03";
        public static final String LB_04 = "LB-04";
        public static final String LB_05 = "LB-05";
        public static final String LB_06 = "LB-06";
        public static final String LB_07 = "LB-07";
    }

    @Override
    public Integer getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getInternalMessage() {
        return internalMessage;
    }

    @Override
    public String getLogCode() {
        return logCode;
    }
}
