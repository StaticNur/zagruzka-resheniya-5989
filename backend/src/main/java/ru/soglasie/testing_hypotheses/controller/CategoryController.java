package ru.soglasie.testing_hypotheses.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.soglasie.testing_hypotheses.model.entity.Category;
import ru.soglasie.testing_hypotheses.dto.CategoryDto;
import ru.soglasie.testing_hypotheses.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        List<Category> category = categoryRepository.findAll();
        List<CategoryDto> categoryDtos = category.stream()
                .map(CategoryDto::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(categoryDtos);
    }


    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        Category savedParameter = categoryRepository.save(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedParameter);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long id) {
        Optional<Category> parameter = categoryRepository.findById(id);
        if (parameter.isPresent()) {
            CategoryDto parameterDto = new CategoryDto(parameter.get());
            return ResponseEntity.ok(parameterDto);
        }
        return ResponseEntity.notFound().build();
    }
}
