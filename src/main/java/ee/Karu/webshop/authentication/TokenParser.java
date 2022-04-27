package ee.Karu.webshop.authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
public class TokenParser extends BasicAuthenticationFilter {

    public TokenParser(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            log.info(token);
            token = token.replace("Bearer ", "");

            Claims claims = Jwts.parser()
                    .setSigningKey("super-secret-key")
                    .parseClaimsJws(token)
                    .getBody();

            String email = claims.getSubject();
            String issuer = claims.getIssuer();

            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                    email,
                    null,
                    null
            );

            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        super.doFilterInternal(request, response, chain);
    }
}