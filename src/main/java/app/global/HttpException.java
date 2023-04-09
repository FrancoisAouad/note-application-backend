package app.global;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class HttpException {
    private int statusCode;
    private String message;
}
