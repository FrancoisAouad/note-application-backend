package app.global;

import org.springframework.http.ResponseEntity;

// potentially make abstract class
public class ThrowableHttpException extends Throwable implements HttpExceptionInterface {
    private final int statusCode;
    private final String message;

    public ThrowableHttpException(int statusCode, String message) {
        super();
        this.statusCode = statusCode;
        this.message = message;
    }

    public static ResponseEntity<?> handleResponse(HttpExceptionInterface exception) {
        return ResponseEntity.status(exception.getStatusCode()).body(exception.getMessage());
    }

    @Override
    public int getStatusCode() {
        return statusCode;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
