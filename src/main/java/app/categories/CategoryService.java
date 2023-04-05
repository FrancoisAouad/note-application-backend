package app.categories;

import app.lib.LoggerWrapper;
import app.categories.CategoryRepository;
import app.categories.CategoryModel;
import app.categories.dto.CreateCategoryDto;;

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
