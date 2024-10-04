package ru.soglasie.testing_hypotheses.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.soglasie.testing_hypotheses.model.entity.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory_NameIn(List<String> categoryParam);

    List<Product> findByNameContainingIgnoreCase(String name);
}
