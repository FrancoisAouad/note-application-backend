package app.global.dto;

import java.util.List;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DeleteSelectedDto {
    private List<String> ids;
}
