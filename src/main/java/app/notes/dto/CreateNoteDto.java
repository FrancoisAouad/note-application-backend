package app.notes.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
// import java.util.Date;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CreateNoteDto {

    // private String id;
    private String title;
    private String content;
    private boolean published;

}
