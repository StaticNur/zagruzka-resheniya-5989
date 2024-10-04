package ru.soglasie.testing_hypotheses.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.soglasie.testing_hypotheses.dto.ParameterDto;
import ru.soglasie.testing_hypotheses.dto.ProductDto;
import ru.soglasie.testing_hypotheses.model.entity.Parameter;
import ru.soglasie.testing_hypotheses.model.entity.Product;
import ru.soglasie.testing_hypotheses.repository.ParameterRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/parameters")
public class ParameterController {
    @Autowired
    private ParameterRepository parameterRepository;

    @GetMapping
    public ResponseEntity<List<ParameterDto>> getAllParameters() {
        List<Parameter> parameters = parameterRepository.findAll();
        List<ParameterDto> parameterDTOs = parameters.stream()
                .map(ParameterDto::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(parameterDTOs);
    }

    @PostMapping
    public ResponseEntity<Parameter> createParameter(@RequestBody Parameter parameter) {
        Parameter savedParameter = parameterRepository.save(parameter);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedParameter);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParameterDto> getParameterById(@PathVariable Long id) {
        Optional<Parameter> parameter = parameterRepository.findById(id);
        if(parameter.isPresent()){
            ParameterDto parameterDto = new ParameterDto(parameter.get());
            return ResponseEntity.ok(parameterDto);
        }
        return ResponseEntity.notFound().build();
    }
}
