package ru.soglasie.testing_hypotheses.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.soglasie.testing_hypotheses.model.entity.Risk;
import ru.soglasie.testing_hypotheses.service.RiskService;

import java.util.List;

@RestController
@RequestMapping("/api/risks")
public class RiskController {

    @Autowired
    private RiskService riskService;

    @GetMapping
    public ResponseEntity<List<Risk>> getAllRisks() {
        return ResponseEntity.ok(riskService.getAllRisks());
    }

    @PostMapping
    public ResponseEntity<Risk> createRisk(@RequestBody Risk risk) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(riskService.createRisk(risk));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Risk> getRiskById(@PathVariable Long id) {
        return riskService.getRiskById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Risk> updateRisk(@PathVariable Long id, @RequestBody Risk riskDetails) {
        return ResponseEntity.ok(riskService.updateRisk(id, riskDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRisk(@PathVariable Long id) {
        return riskService.deleteRisk(id)
                ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }
}
