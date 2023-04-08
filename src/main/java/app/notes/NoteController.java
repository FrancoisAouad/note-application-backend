package app.notes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import app.notes.dto.CreateNoteDto;
import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @GetMapping()
    public ResponseEntity<List<NoteModel>> getAllNotes() {
        try {
            ResponseEntity<List<NoteModel>> result = noteService.getAllNotes();
            return result;
        } catch (Exception e) {
            System.out.println(e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

    }

    @PostMapping()
    public ResponseEntity<NoteModel> createNote(@RequestBody CreateNoteDto note) {
        try {
            ResponseEntity<NoteModel> result = noteService.createNote(note);
            return ResponseEntity.status(HttpStatus.CREATED).body(result.getBody());
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.notFound().build();
        }
    }
    // @GetMapping("/{id}")
    // public ResponseEntity<NoteModel> getNoteById(@PathVariable("id") long id) {
    // try {
    // NoteModel note = noteService.getNoteById(id);
    // if (note == null) {
    // return ResponseEntity.notFound().build();
    // }
    // return ResponseEntity.ok(note);

    // } catch (Exception e) {
    // System.out.println(e);
    // }
    // }

    // @PutMapping("/{id}")
    // public ResponseEntity<NoteModel> updateNote(@PathVariable("id") long id,
    // @RequestBody NoteModel note) {
    // try {
    // NoteModel updatedNote = noteService.updateNote(id, note);
    // if (updatedNote == null) {
    // return ResponseEntity.notFound().build();
    // }
    // return ResponseEntity.ok(updatedNote);

    // } catch (Exception e) {
    // System.out.println(e);
    // }
    // }

    // @DeleteMapping("/{id}")
    // public ResponseEntity<Void> deleteNoteById(@PathVariable("id") long id) {
    // try {
    // noteService.deleteNoteById(id);
    // return ResponseEntity.noContent().build();
    // } catch (Exception e) {
    // System.out.println(e);
    // }
    // }

    // @DeleteMapping()
    // public ResponseEntity<Void> deleteAllNotes() {
    // try {
    // noteService.deleteAllNotes();
    // return ResponseEntity.noContent().build();

    // } catch (Exception e) {
    // System.out.println(e);
    // }
    // }
}
