package app.notes.dto;

import java.util.List;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DeleteSelectedNotesDto {
    private List<String> ids;
}
