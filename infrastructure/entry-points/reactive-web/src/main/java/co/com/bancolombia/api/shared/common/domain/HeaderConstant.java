package co.com.bancolombia.api.shared.common.domain;

public enum HeaderConstant {
    MESSAGE_ID("message-Id"),
    X_REQUEST_ID("x-request-id");

    private final String headerName;

    HeaderConstant(String headerName) {
        this.headerName = headerName;
    }

    public String value() {
        return headerName;
    }
}
