package app.notes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import app.notes.dto.CreateNoteDto;
import java.util.Date;
import java.util.List;
import java.util.Random;

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
        NoteModel newNote = NoteModel.builder().id(generateRandomString((8))).title(noteDto.getTitle())
                .content(noteDto.getContent())
                .createdDate(new Date()).updatedDate(new Date()).published(false)
                .build();
        NoteModel savedNoteModel = noteRepository.save(newNote);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedNoteModel);
    }

    public static String generateRandomString(int length) {
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random rand = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(rand.nextInt(chars.length())));
        }
        return sb.toString();
    }

}
