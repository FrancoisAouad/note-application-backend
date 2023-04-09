package app.categories;

// Spring 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
// Utils
import java.util.List;
// Services
import app.categories.dto.CreateCategoryDto;
import app.categories.dto.UpdateCategoryDto;
import app.global.dto.DeleteSelectedDto;

@RestController
@RequestMapping("/categories")
@CrossOrigin(origins = "*")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping()
    public ResponseEntity<List<CategoryModel>> getAll() {
        return categoryService.getAll();
    }

    @PostMapping()
    public ResponseEntity<CategoryModel> create(@RequestBody CreateCategoryDto category) {
        return categoryService.create(category);
    }

    @DeleteMapping()
    public ResponseEntity<Void> deleteAllNotes(@RequestBody DeleteSelectedDto request) {
        List<String> ids = request.getIds();
        return categoryService.deleteAll(ids);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryModel> getNoteById(@PathVariable("id") String id) {
        return categoryService.getById(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CategoryModel> update(@PathVariable("id") String id,
            @RequestBody UpdateCategoryDto category) {
        return categoryService.update(id, category);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNoteById(@PathVariable("id") String id) {
        return categoryService.delete(id);
    }
}
