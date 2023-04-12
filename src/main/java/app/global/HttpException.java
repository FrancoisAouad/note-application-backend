package app.global;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class HttpException extends Throwable {
    private int statusCode;
    private String message;

    public static ResponseEntity<?> handleResponse(int statusCodeValue, Object message) {

        switch (statusCodeValue) {
            case 200:
                return ResponseEntity.ok().body(message);
            case 201:
                return ResponseEntity.status(HttpStatus.CREATED).body(message);
            case 204:
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(message);
            case 400:
                return ResponseEntity.badRequest().body(message);
            case 401:
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
            case 403:
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(message);
            case 404:
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
}
