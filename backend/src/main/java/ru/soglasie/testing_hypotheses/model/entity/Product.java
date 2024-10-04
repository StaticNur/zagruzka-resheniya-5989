package ru.soglasie.testing_hypotheses.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String explanationForManager;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    // Связь многие ко многим с промежуточной таблицей "product_parameters"
    @ManyToMany
    @JoinTable(
            name = "product_parameters", // название промежуточной таблицы
            joinColumns = @JoinColumn(name = "product_id"), // внешний ключ на сущность Product
            inverseJoinColumns = @JoinColumn(name = "parameter_id") // внешний ключ на сущность Parameter
    )
    private List<Parameter> parameters;
}

//    @OneToMany
//    @JoinColumn(name = "product_id")
//    private List<Risk> risks;
