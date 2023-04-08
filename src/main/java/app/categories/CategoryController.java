package app.categories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import app.categories.dto.CreateCategoryDto;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping()
    public ResponseEntity<List<CategoryModel>> getAll() {
        try {
            ResponseEntity<List<CategoryModel>> result = categoryService.getAll();
            return result;
        } catch (Exception e) {
            System.out.println(e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

    }

    @PostMapping()
    public ResponseEntity<CategoryModel> create(@RequestBody CreateCategoryDto category) {
        try {
            ResponseEntity<CategoryModel> result = categoryService.create(category);
            return ResponseEntity.status(HttpStatus.CREATED).body(result.getBody());
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryModel> getNoteById(@PathVariable("id") String id) {
        try {
            ResponseEntity<CategoryModel> result = categoryService.getById(id);
            System.out.println(result);
            return result;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    // @PutMapping("/{id}")
    // public ResponseEntity<CategoryModel> updateNote(@PathVariable("id") long id,
    // @RequestBody CategoryModel note) {
    // try {
    // CategoryModel updatedNote = categoryService.updateNote(id, note);
    // if (updatedNote == null) {
    // return ResponseEntity.notFound().build();
    // }
    // return ResponseEntity.ok(updatedNote);

    // } catch (Exception e) {
    // System.out.println(e);
    // }
    // }

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
