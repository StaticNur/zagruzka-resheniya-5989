package ru.soglasie.testing_hypotheses.dto;

import lombok.Data;
import ru.soglasie.testing_hypotheses.model.entity.Category;

@Data
public class CategoryDto {
    private Long id;
    private String name;

    public CategoryDto(Category category) {
        this.id = category.getId();
        this.name = category.getName();
    }
}
