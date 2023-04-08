package app.notes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import app.notes.dto.CreateNoteDto;
import java.util.Date;
import app.utils.GlobalService;
import java.util.List;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    /**
     * @function getAllNotes - Method to return list of notes
     * @return
     */
    public ResponseEntity<List<NoteModel>> getAll() {
        List<NoteModel> noteList = noteRepository.findAll();
        if (noteList.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(noteList);
        }
    }

    /**
     * @function createNote - Method to create a single note
     * @param noteDto - Fields needed to create a note
     * @return
     */
    public ResponseEntity<NoteModel> create(CreateNoteDto noteDto) {
        NoteModel newNote = NoteModel.builder()
                .id(GlobalService.generateUUID())
                .title(noteDto.getTitle())
                .content(noteDto.getContent())
                .createdDate(new Date())
                .updatedDate(new Date())
                .build();
        NoteModel savedNoteModel = noteRepository.save(newNote);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedNoteModel);
    }

    /**
     * @function getNoteById - Method to get a single note by its id
     * @param id - uuid if the note
     * @return
     */
    public ResponseEntity<NoteModel> getById(String id) {
        NoteModel note = noteRepository.getNoteById(id);
        return ResponseEntity.ok(note);

    }

}
