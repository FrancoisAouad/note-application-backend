package app.notes;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import app.notes.NoteModel;

public interface NoteRepository extends JpaRepository<NoteModel, Long> {
    List<NoteModel> findByPublished(boolean published);

    List<NoteModel> findByTitleContaining(String title);
}
