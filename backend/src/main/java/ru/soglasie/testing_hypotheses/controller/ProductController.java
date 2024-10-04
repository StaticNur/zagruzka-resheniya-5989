package ru.soglasie.testing_hypotheses.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.soglasie.testing_hypotheses.dto.ParameterDto;
import ru.soglasie.testing_hypotheses.dto.ProductDto;
import ru.soglasie.testing_hypotheses.model.entity.Product;
import ru.soglasie.testing_hypotheses.repository.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/all")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDto> productDTOs = products.stream()
                .map(ProductDto::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(productDTOs);
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getProductsByCategories(@RequestParam("category") List<String> categoryParam) {
        List<Product> products = productRepository.findByCategory_NameIn(categoryParam);

        if (!products.isEmpty()) {
            List<ProductDto> productDtos = products.stream()
                    .map(ProductDto::new)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(productDtos);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/search") // Путь для поиска
    public ResponseEntity<List<ProductDto>> searchProductsByName(@RequestParam("name") String name) {
        List<Product> products = productRepository.findByNameContainingIgnoreCase(name);

        if (!products.isEmpty()) {
            List<ProductDto> productDtos = products.stream()
                    .map(ProductDto::new)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(productDtos);
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/{id}/parameters") // Путь для поиска
    public ResponseEntity<List<ParameterDto>> getAllParameterThisProduct(@PathVariable Long id) {
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            List<ParameterDto> productDto = product.get()
                    .getParameters().stream()
                    .map(ParameterDto::new)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(productDto);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            ProductDto productDto = new ProductDto(product.get());
            return ResponseEntity.ok(productDto);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product savedProduct = productRepository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setName(productDetails.getName());
                    product.setCategory(productDetails.getCategory());
                    //product.setLineOfBusiness(productDetails.getLineOfBusiness());
                    //product.setRisks(productDetails.getRisks());
                    return ResponseEntity.ok(productRepository.save(product));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        return productRepository.findById(id)
                .map(product -> {
                    productRepository.delete(product);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}

