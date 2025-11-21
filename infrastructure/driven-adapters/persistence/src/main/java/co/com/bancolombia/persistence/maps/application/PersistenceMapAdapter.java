package co.com.bancolombia.persistence.maps.application;

import co.com.bancolombia.jwt.GenerateJWTAdapter;
import co.com.bancolombia.model.shared.cqrs.Command;
import co.com.bancolombia.model.shared.cqrs.ContextData;
import co.com.bancolombia.model.shared.cqrs.Query;
import co.com.bancolombia.model.shared.exception.BusinessException;
import co.com.bancolombia.model.shared.exception.ConstantBusinessException;
import co.com.bancolombia.model.user.signin.gateway.SigninGateway;
import co.com.bancolombia.model.user.signin.model.Signin;
import co.com.bancolombia.model.user.signin.model.response.Session;
import co.com.bancolombia.model.user.signup.gateway.SignupGateway;
import co.com.bancolombia.model.user.signup.model.Signup;
import co.com.bancolombia.persistence.maps.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@AllArgsConstructor
public class PersistenceMapAdapter implements SigninGateway, SignupGateway {

    private final GenerateJWTAdapter generateJWTAdapter;
    private static final Map<String, User> USERS = new ConcurrentHashMap<>();

    @Override
    public Mono<Void> signupUser(Command<Signup, ContextData> command) {
        var payload = command.payload();
        var email = payload.getEmail();
        if (USERS.containsKey(email)){
            return Mono.error(new BusinessException(ConstantBusinessException.EMAIL_ALREADY_EXISTS, command.context()));
        }
        USERS.put(email, User.builder()
                .email(email)
                .name(payload.getName())
                .password(payload.getPassword())
                .build());
        return Mono.empty();
    }

    @Override
    public Mono<Session> validateUser(Query<Signin, ContextData> query) {
        if (!USERS.containsKey(query.payload().getEmail())){
            throw new BusinessException(ConstantBusinessException.USER_NOT_FOUND, query.context());
        }

        User user = USERS.get(query.payload().getEmail());

        if (!user.getPassword().equals(query.payload().getPassword())){
            throw new BusinessException(ConstantBusinessException.INVALID_CREDENTIALS, query.context());
        }

        try{
            String session = user.getSessionId();
            generateJWTAdapter.validateToken(session);
            return Mono.just(Session.builder().sessionId(session).build());
        } catch (RuntimeException e){
            return Mono.empty();
        }
    }

    @Override
    public Mono<Session> saveSession(Query<Signin, ContextData> query) {
        User user = USERS.get(query.payload().getEmail());
        String sessionId = generateJWTAdapter.generateToken(user.getEmail());
        user.setSessionId(sessionId);
        USERS.replace(user.getEmail(), user);
        return Mono.just(Session.builder().sessionId(sessionId).build());
    }
}
