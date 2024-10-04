package ru.soglasie.testing_hypotheses.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.soglasie.testing_hypotheses.model.entity.Risk;
import ru.soglasie.testing_hypotheses.repository.RiskRepository;

import java.util.List;

@RestController
@RequestMapping("/api/risks")
public class RiskController {

    @Autowired
    private RiskRepository riskRepository;

    @GetMapping
    public List<Risk> getAllRisks() {
        return riskRepository.findAll();
    }

    @PostMapping
    public Risk createRisk(@RequestBody Risk risk) {
        return riskRepository.save(risk);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Risk> getRiskById(@PathVariable Long id) {
        return riskRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Risk> updateRisk(@PathVariable Long id, @RequestBody Risk riskDetails) {
        return riskRepository.findById(id)
                .map(risk -> {
                    risk.setName(riskDetails.getName());
                    return ResponseEntity.ok(riskRepository.save(risk));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRisk(@PathVariable Long id) {
        return riskRepository.findById(id)
                .map(risk -> {
                    riskRepository.delete(risk);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
