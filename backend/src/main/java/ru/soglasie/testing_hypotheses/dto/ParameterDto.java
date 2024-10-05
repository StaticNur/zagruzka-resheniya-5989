package ru.soglasie.testing_hypotheses.dto;

import lombok.Data;
import ru.soglasie.testing_hypotheses.model.entity.Parameter;

@Data
public class ParameterDto {
    private Long id;
    private String name;
    private String description;
    private String typeParameter;
    private Float coefficientPositive;
    private Float coefficientNegative;
    private String typeView;
    private Float minValue;
    private Float maxValue;
    private Boolean checking;

    public ParameterDto(Parameter parameter) {
        this.id = parameter.getId();
        this.name = parameter.getName();
        this.description = parameter.getDescription();
        this.typeParameter = parameter.getTypeParameter();
        this.coefficientPositive = parameter.getCoefficientPositive();
        this.coefficientNegative = parameter.getCoefficientNegative();
        this.typeView = parameter.getTypeView();
        this.minValue = parameter.getMinValue();
        this.maxValue = parameter.getMaxValue();
        this.checking = parameter.getChecking();
    }
}
