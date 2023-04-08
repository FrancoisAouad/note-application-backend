package app.notes;

// Spring
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
// Utils
import java.util.Date;
import java.util.List;
// Services
import app.notes.dto.CreateNoteDto;
import app.notes.dto.UpdateNoteDto;
import app.utils.GlobalService;

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
        if (note == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(note);

    }

    /**
     * @function update
     * @param id
     * @param noteDto
     * @return ResponseEntity<NoteModel>
     */
    public ResponseEntity<NoteModel> update(String id, UpdateNoteDto noteDto) {
        NoteModel note = noteRepository.getNoteById(id);
        if (note == null) {
            return ResponseEntity.notFound().build();
        }
        if (noteDto.getTitle() != null) {
            note.setTitle(noteDto.getTitle());
        }
        if (noteDto.getContent() != null) {
            note.setContent(noteDto.getContent());
        }
        note.setUpdatedDate(new Date());
        return ResponseEntity.ok(note);
    }

}
