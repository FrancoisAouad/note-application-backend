package categories;

import org.springframework.web.bind.annotation.RestController;
import categories.dto.CreateCategoryDto;
import categories.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import lib.LoggerWrapper;

@RestController
@RequestMapping("/api/v1")
// @RequiredArgsConstructor
public class CategoryController {
    private CategoryService categoryService;
    private LoggerWrapper logger;

    @PostMapping("/note")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCategory(@RequestBody CreateCategoryDto createCategoryDto) {
        try {
            categoryService.createCategory(createCategoryDto);
        } catch (Exception e) {
            logger.info("Failed to create category in 'createCategory");
        }
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String getHomeMessage() {
        return "hello";
    }
}
