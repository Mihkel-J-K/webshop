package ee.Karu.webshop.model.output;

import lombok.Data;

import java.util.Date;

@Data
public class AuthData {
    private String token;
    private Date expiration;
}
