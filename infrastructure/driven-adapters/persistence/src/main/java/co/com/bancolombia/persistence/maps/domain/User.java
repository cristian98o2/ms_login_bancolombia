package co.com.bancolombia.persistence.maps.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder(toBuilder = true)
public class User {

    private String email;
    private String password;
    private String name;
    private String sessionId;
}
