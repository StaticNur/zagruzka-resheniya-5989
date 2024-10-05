package ru.soglasie.testing_hypotheses.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.soglasie.testing_hypotheses.model.entity.Category;
import ru.soglasie.testing_hypotheses.dto.CategoryDto;
import ru.soglasie.testing_hypotheses.service.CategoryService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        List<Category> category = categoryService.getAllCategories();
        return ResponseEntity.ok(category.stream()
                .map(CategoryDto::new)
                .collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        Category savedParameter = categoryService.createCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedParameter);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id)
                .map(category -> ResponseEntity.ok(new CategoryDto(category)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
