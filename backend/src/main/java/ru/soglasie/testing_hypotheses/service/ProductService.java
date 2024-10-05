package ru.soglasie.testing_hypotheses.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;
import ru.soglasie.testing_hypotheses.dto.ParameterDto;
import ru.soglasie.testing_hypotheses.dto.ProductDto;
import ru.soglasie.testing_hypotheses.model.entity.Product;
import ru.soglasie.testing_hypotheses.repository.ProductRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<ProductDto> getProductsByCategories(List<String> categoryParam) {
        List<Product> products = productRepository.findByCategory_NameIn(categoryParam);
        return products.stream()
                .map(ProductDto::new)
                .collect(Collectors.toList());
    }

    public List<ProductDto> searchProductsByName(String name) {
        List<Product> products = productRepository.findByNameContainingIgnoreCase(name);
        return products.stream()
                .map(ProductDto::new)
                .collect(Collectors.toList());
    }

    public List<ParameterDto> getAllParameterThisProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.map(value -> value
                .getParameters().stream()
                .map(ParameterDto::new)
                .collect(Collectors.toList())).orElse(Collections.emptyList());
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    @Transactional
    public Product createProduct(Product product) {
        product.setName(product.getName().trim());
        return productRepository.save(product);
    }

    @Transactional
    public Product updateProduct(Long id, Product productDetails) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setName(productDetails.getName());
                    product.setDescription(productDetails.getDescription());
                    product.setExplanationForManager(productDetails.getExplanationForManager());
                    product.setCategory(productDetails.getCategory());
                    product.setParameters(productDetails.getParameters());

                    return productRepository.save(product);
                })
                .orElseThrow(() -> new NotFoundException("Продукт с ID " + id + " не найден"));
    }

    @Transactional
    public boolean deleteProduct(Long id) {
        return productRepository.findById(id)
                .map(product -> {
                    productRepository.delete(product);
                    return true;
                })
                .orElse(false);
    }

    public Product createProductDuplicate(Long id) {
        return productRepository.findById(id)
                .map(product -> {
                    Product newProduct = new Product();

                    newProduct.setDescription(product.getDescription());
                    newProduct.setExplanationForManager(product.getExplanationForManager());
                    newProduct.setCategory(product.getCategory());
                    newProduct.setParameters(product.getParameters());

                    newProduct.setName(product.getName() + " (Copy)");

                    return productRepository.save(newProduct);
                })
                .orElseThrow(() -> new NotFoundException("Продукт с ID " + id + " не найден"));
    }

}
