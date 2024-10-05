package ru.soglasie.testing_hypotheses.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "parameters")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Parameter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String typeParameter;

    @Column(nullable = false)
    private Float coefficientPositive;

    @Column(nullable = false)
    private Float coefficientNegative;

    @Column(nullable = false)
    private String typeView;

    @Column
    private Float minValue;
    @Column
    private Float maxValue;
    @Column
    private Boolean checking;

    @ManyToMany(mappedBy = "parameters")
    private List<Product> products;
}

