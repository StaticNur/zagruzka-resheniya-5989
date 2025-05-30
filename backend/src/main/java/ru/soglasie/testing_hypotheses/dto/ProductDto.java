package ru.soglasie.testing_hypotheses.dto;

import lombok.Data;
import ru.soglasie.testing_hypotheses.model.entity.Product;
import ru.soglasie.testing_hypotheses.model.entity.Parameter;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private String explanationForManager;
    private String category;

    private List<Long> parameterIds;

    public ProductDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.explanationForManager = product.getExplanationForManager();
        this.category = product.getCategory().getName();
        this.parameterIds = product.getParameters().stream()
                .map(Parameter::getId)
                .collect(Collectors.toList());
    }
}
