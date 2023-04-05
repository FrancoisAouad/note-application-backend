package categories;

import lib.LoggerWrapper;
import categories.CategoryRepository;
import categories.CategoryModel;
import categories.dto.CreateCategoryDto;;

public class CategoryService {
    private CategoryRepository categoryRepository;
    private LoggerWrapper logger;

    public void createCategory(CreateCategoryDto notesData) {
        CategoryModel newCategory = CategoryModel
                .builder()
                .build();
        categoryRepository.save(newCategory);
        logger.info("Successfully created a category");
    }

}
