package co.com.bancolombia.api.signinuser.domain;

import co.com.bancolombia.api.signupuser.domain.SignupRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@lombok.Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SigninRequest {

    private SignupRequest.Data data;

    @lombok.Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Data {
        private String email;
        private String password;
    }

}
