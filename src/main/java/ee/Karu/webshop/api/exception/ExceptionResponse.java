package ee.Karu.webshop.api.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
public class ExceptionResponse {
    private int HttpStatusCode;
    private HttpStatus HttpStatus;
    private Date timestamp;
    private String message;
}
