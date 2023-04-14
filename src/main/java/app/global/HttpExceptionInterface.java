package app.global;

import org.springframework.http.ResponseEntity;

public interface HttpExceptionInterface {
    int statusCode = 500;
    String message = "Internal Server Error";

    public int getStatusCode();

    public String getMessage();

    public static ResponseEntity<?> handleResponse(HttpExceptionInterface exception) {
        return ResponseEntity.status(exception.getStatusCode()).body(exception.getMessage());
    }
}
