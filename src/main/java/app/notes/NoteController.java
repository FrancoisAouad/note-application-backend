package app.notes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import app.notes.NoteRepository;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    @Autowired
    private NoteRepository noteRepository;
    private NoteService noteService;

    @GetMapping()
    public ResponseEntity<List<NoteModel>> getAllNotes(@RequestParam(required = false) String title) {
        try {
            return noteService.getAllNotes(title);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoteModel> getNoteById(@PathVariable("id") long id) {
        Optional<NoteModel> noteData = noteRepository.findById(id);

        if (noteData.isPresent()) {
            return new ResponseEntity<>(noteData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public ResponseEntity<NoteModel> createNote(@RequestBody NoteModel note) {
        try {
            NoteModel createdNote = noteRepository.save(note);
            return new ResponseEntity<>(createdNote, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<NoteModel> updateNote(@PathVariable("id") long id, @RequestBody NoteModel note) {
        Optional<NoteModel> noteData = noteRepository.findById(id);

        if (noteData.isPresent()) {
            NoteModel existingNote = noteData.get();
            existingNote.setTitle(note.getTitle());
            existingNote.setContent(note.getContent());
            return new ResponseEntity<>(noteRepository.save(existingNote), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteNoteById(@PathVariable("id") long id) {
        try {
            noteRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping()
    public ResponseEntity<HttpStatus> deleteAllNotes() {
        try {
            noteRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/published")
    public ResponseEntity<List<NoteModel>> getAllPublishedNotes() {
        try {
            List<NoteModel> publishedNotes = noteRepository.findByPublished(true);

            if (publishedNotes.isEmpty()) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.ok(publishedNotes);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
