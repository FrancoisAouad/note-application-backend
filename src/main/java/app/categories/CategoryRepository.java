package app.categories;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface CategoryRepository extends JpaRepository<CategoryModel, Long> {
    Logger logger = LoggerFactory.getLogger(CategoryRepository.class);

    CategoryModel findById(String id);

    default void deleteById(String id) {
        CategoryModel note = findById(id);
        if (note != null) {
            logger.info("Deleted note with Id: '" + id + "'");
        }
    }

    default void deleteSelected(List<String> ids) {
        for (String id : ids) {
            CategoryModel note = findById(id);
            if (note == null) {
                logger.warn("Could not find note with Id: '" + id + "'");
            }
            deleteById(id);
            logger.info("Deleted note with Id: '" + id + "'");
        }

    }
}
