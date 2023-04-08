package app.categories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import app.categories.dto.CreateCategoryDto;
import app.utils.GlobalService;
import java.util.Date;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * @function getAll - Method to return list of notes
     * @return
     */
    public ResponseEntity<List<CategoryModel>> getAll() {
        List<CategoryModel> categoryList = categoryRepository.findAll();
        if (categoryList.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(categoryList);
        }
    }

    /**
     * @function create - Method to create a single category
     * @param categoryDto - Fields needed to create a category
     * @return
     */
    public ResponseEntity<CategoryModel> create(CreateCategoryDto categoryDto) {
        CategoryModel newCategory = CategoryModel.builder()
                .id(GlobalService.generateUUID())
                .categoryName(categoryDto.getCategoryName())
                .createdDate(new Date())
                .updatedDate(new Date())
                .build();
        CategoryModel savedCategory = categoryRepository.save(newCategory);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
    }

    /**
     * @function getById - Method to get a single category by its id
     * @param id - uuid of the category
     * @return
     */
    public ResponseEntity<CategoryModel> getById(String id) {
        CategoryModel category = categoryRepository.getCategoryById(id);
        return ResponseEntity.ok(category);
    }

}
