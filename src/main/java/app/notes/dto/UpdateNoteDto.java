package app.notes.dto;

import javax.validation.constraints.Size;

// Lombok
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UpdateNoteDto {
    @Size(min = 2, max = 40, message = "Title must be between 2 and 40 characters")
    private String title;
    @Size(min = 10, max = 10000, message = "Content must be between 10 and 10000 characters")
    private String content;
    private String categoryId;

}
