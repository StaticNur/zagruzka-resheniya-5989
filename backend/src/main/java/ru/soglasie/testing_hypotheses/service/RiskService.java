package ru.soglasie.testing_hypotheses.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;
import ru.soglasie.testing_hypotheses.model.entity.Risk;
import ru.soglasie.testing_hypotheses.repository.RiskRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RiskService {
    @Autowired
    private RiskRepository riskRepository;

    public List<Risk> getAllRisks() {
        return riskRepository.findAll();
    }

    @Transactional
    public Risk createRisk(Risk risk) {
        return riskRepository.save(risk);
    }

    public Optional<Risk> getRiskById(Long id) {
        return riskRepository.findById(id);
    }

    @Transactional
    public Risk updateRisk(Long id, Risk riskDetails) {
        return riskRepository.findById(id)
                .map(risk -> {
                    risk.setName(riskDetails.getName());
                    return riskRepository.save(risk);
                })
                .orElseThrow(() -> new NotFoundException("Risk с ID " + id + " не найден"));
    }

    @Transactional
    public boolean deleteRisk(Long id) {
        return riskRepository.findById(id)
                .map(risk -> {
                    riskRepository.delete(risk);
                    return true;
                })
                .orElse(false);
    }
}
