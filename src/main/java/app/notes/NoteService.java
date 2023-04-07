package app.notes;

// import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
// import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class NoteService {

    @PersistenceContext
    private EntityManager entityManager;
    // private final NoteRepository noteRepository;

    public void getAllNotes(String title) {
        // String query;
        // if (title == null) {
        // query = "SELECT id, title, content FROM notes";
        // } else {
        // query = "SELECT id, title, content FROM notes WHERE title LIKE '%" + title +
        // "%'";
        // }
        // List<Object[]> results =
        // entityManager.createNativeQuery(query).getResultList();
        // List<NoteModel> noteModels = new ArrayList<>();
        // for (Object[] result : results) {
        // NoteModel noteModel = new NoteModel();
        // noteModel.setId((String) result[0]);
        // noteModel.setTitle((String) result[1]);
        // noteModel.setContent((String) result[2]);
        // noteModels.add(noteModel);
        // }
        // if (noteModels.isEmpty()) {
        // return ResponseEntity.noContent().build();
        // } else {
        // return ResponseEntity.ok(noteModels);
        // }
    }

}
