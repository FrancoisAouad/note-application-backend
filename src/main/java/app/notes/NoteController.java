package app.notes;

// Spring
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
// Utils
import java.util.List;
// Services
import app.global.dto.DeleteSelectedDto;
import app.notes.dto.CreateNoteDto;
import app.notes.dto.UpdateNoteDto;

@RestController
@RequestMapping("/notes")
@CrossOrigin(origins = "*")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @GetMapping()
    public ResponseEntity<List<NoteModel>> getAll() {
        return noteService.getAll();
    }

    @PostMapping()
    public ResponseEntity<NoteModel> create(@RequestBody CreateNoteDto note) {
        return noteService.create(note);
    }

    @DeleteMapping()
    public ResponseEntity<Void> deleteAllNotes(@RequestBody DeleteSelectedDto request) {
        List<String> ids = request.getIds();
        return noteService.deleteAll(ids);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoteModel> getById(@PathVariable("id") String id) {
        return noteService.getById(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<NoteModel> update(@PathVariable("id") String id, @RequestBody UpdateNoteDto note) {
        return noteService.update(id, note);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNoteById(@PathVariable("id") String id) {
        return noteService.delete(id);
    }

}
