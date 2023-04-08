package app.notes;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface NoteRepository extends JpaRepository<NoteModel, Long> {
    Logger logger = LoggerFactory.getLogger(NoteRepository.class);

    NoteModel findById(String id);

    default void deleteById(String id) {
        NoteModel note = findById(id);
        if (note != null) {
            logger.info("Deleted note with Id: '" + id + "'");
        }
    }

    default void deleteSelected(List<String> ids) {
        for (String id : ids) {
            NoteModel note = findById(id);
            if (note == null) {
                logger.warn("Could not find note with Id: '" + id + "'");
            }
            deleteById(id);
            logger.info("Deleted note with Id: '" + id + "'");
        }

    }

}
