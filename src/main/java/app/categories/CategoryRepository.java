package app.categories;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryModel, Long> {

    CategoryModel getCategoryById(String id);

    CategoryModel findById(String id);

}
