package ru.soglasie.testing_hypotheses.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.soglasie.testing_hypotheses.model.entity.Parameter;
import ru.soglasie.testing_hypotheses.repository.ParameterRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ParameterService {
    @Autowired
    private ParameterRepository parameterRepository;

    public List<Parameter> getAllParameters() {
        return parameterRepository.findAll();
    }

    @Transactional
    public Parameter createParameter(Parameter parameter) {
        return parameterRepository.save(parameter);
    }

    public Optional<Parameter> getParameterById(Long id) {
        return parameterRepository.findById(id);
    }
}
