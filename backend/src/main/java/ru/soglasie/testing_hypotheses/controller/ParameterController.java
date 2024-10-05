package ru.soglasie.testing_hypotheses.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.soglasie.testing_hypotheses.dto.ParameterDto;
import ru.soglasie.testing_hypotheses.model.entity.Parameter;
import ru.soglasie.testing_hypotheses.service.ParameterService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/parameters")
public class ParameterController {
    @Autowired
    private ParameterService parameterService;

    @GetMapping
    public ResponseEntity<List<ParameterDto>> getAllParameters() {
        List<Parameter> parameters = parameterService.getAllParameters();
        List<ParameterDto> parameterDTOs = parameters.stream()
                .map(ParameterDto::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(parameterDTOs);
    }

    @PostMapping
    public ResponseEntity<Parameter> createParameter(@RequestBody Parameter parameter) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(parameterService.createParameter(parameter));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParameterDto> getParameterById(@PathVariable Long id) {
        Optional<Parameter> parameter = parameterService.getParameterById(id);
        if (parameter.isPresent()) {
            ParameterDto parameterDto = new ParameterDto(parameter.get());
            return ResponseEntity.ok(parameterDto);
        }
        return ResponseEntity.notFound().build();
    }
}
