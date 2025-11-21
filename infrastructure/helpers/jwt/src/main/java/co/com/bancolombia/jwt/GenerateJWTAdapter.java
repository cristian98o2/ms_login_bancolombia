package co.com.bancolombia.jwt;

import co.com.bancolombia.model.shared.common.value.Constants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
@AllArgsConstructor
public class GenerateJWTAdapter {

    private static final Key KEY = Keys.hmacShaKeyFor(Constants.SECRET_STRING.getBytes());

    public String generateToken(String userEmail) {

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + Constants.EXPIRATION_TIME);

        return Jwts.builder()
                .setSubject(userEmail)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(KEY)
                .compact();
    }

    public void validateToken(String token) throws JwtException {
        Jws<Claims> claims = Jwts.parserBuilder()
                .setSigningKey(KEY)
                .build()
                .parseClaimsJws(token);

        claims.getBody().getSubject();
    }
}
