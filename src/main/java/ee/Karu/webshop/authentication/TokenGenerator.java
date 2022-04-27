package ee.Karu.webshop.authentication;

import ee.Karu.webshop.model.output.AuthData;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenGenerator {

    public AuthData createAuthToken(String email) {
        AuthData authData = new AuthData();
        Date newDate = DateUtils.addHours(new Date(), 3);

        String token = Jwts.builder()
                .signWith(SignatureAlgorithm.ES512, "super-secret-key")
                .setIssuer("webshop")
                .setSubject(email)
                .setExpiration(newDate)
                .compact();

        authData.setToken(token);
        authData.setExpiration(newDate);
        return authData;
    }

}
