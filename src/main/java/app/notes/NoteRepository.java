package app.notes;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<NoteModel, Long> {
    List<NoteModel> findByPublished(boolean published);

    NoteModel getNoteById(String id);

    List<NoteModel> findByTitleContaining(String title);
}
