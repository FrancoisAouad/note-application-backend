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

@RestController
@RequestMapping("/categories")
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

    @GetMapping("/{id}")
    public ResponseEntity<CategoryModel> getNoteById(@PathVariable("id") String id) {
        return categoryService.getById(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CategoryModel> update(@PathVariable("id") String id,
            @RequestBody UpdateCategoryDto category) {
        return categoryService.update(id, category);
    }

    // @DeleteMapping("/{id}")
    // public ResponseEntity<Void> deleteNoteById(@PathVariable("id") long id) {
    // try {
    // categoryService.deleteNoteById(id);
    // return ResponseEntity.noContent().build();
    // } catch (Exception e) {
    // System.out.println(e);
    // }
    // }

    // @DeleteMapping()
    // public ResponseEntity<Void> deleteAllNotes() {
    // try {
    // categoryService.deleteAllNotes();
    // return ResponseEntity.noContent().build();

    // } catch (Exception e) {
    // System.out.println(e);
    // }
    // }
}
