package app.categories;

import org.springframework.web.bind.annotation.RestController;
import app.categories.dto.CreateCategoryDto;
import app.categories.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import app.lib.LoggerWrapper;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CategoryController {
    private CategoryService categoryService;
    private LoggerWrapper logger;

    @PostMapping("/category")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCategory(@RequestBody CreateCategoryDto createCategoryDto) {
        try {
            categoryService.createCategory(createCategoryDto);
        } catch (Exception e) {
            logger.info("Failed to create category in 'createCategory");
        }
    }
}
