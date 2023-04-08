package app.notes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import app.notes.dto.CreateNoteDto;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    public ResponseEntity<List<NoteModel>> getAllNotes() {
        List<NoteModel> noteModels = noteRepository.findAll();
        if (noteModels.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(noteModels);
        }
    }

    public ResponseEntity<NoteModel> createNote(CreateNoteDto noteDto) {
        NoteModel newNote = NoteModel.builder().id(generateUUID()).title(noteDto.getTitle())
                .content(noteDto.getContent())
                .createdDate(new Date()).updatedDate(new Date()).published(false)
                .build();
        NoteModel savedNoteModel = noteRepository.save(newNote);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedNoteModel);
    }

    public static String generateUUID() {
        UUID uuid = UUID.randomUUID();
        String uuidString = uuid.toString();
        String formattedUUID = uuidString.substring(0, 8) + "-" + uuidString.substring(8, 12) + "-"
                + uuidString.substring(12, 16) + "-" + uuidString.substring(16, 20) + "-" + uuidString.substring(20);
        return formattedUUID;
    }

    public ResponseEntity<NoteModel> getNoteById(String id) {
        NoteModel note = noteRepository.getNoteById(id);
        return ResponseEntity.ok().body(note);
    }

}
