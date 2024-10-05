package ru.soglasie.testing_hypotheses.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.webjars.NotFoundException;
import ru.soglasie.testing_hypotheses.model.entity.Agent;
import ru.soglasie.testing_hypotheses.repository.AgentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AgentService {
    @Autowired
    private AgentRepository agentRepository;

    public List<Agent> getAllAgents() {
        return agentRepository.findAll();
    }

    @Transactional
    public Agent createAgent(Agent agent) {
        return agentRepository.save(agent);
    }

    public Optional<Agent> getAgentById(Long id) {
        return agentRepository.findById(id);
    }

    @Transactional
    public Agent updateAgent(Long id, Agent agentDetails) {
        return agentRepository.findById(id)
                .map(agent -> {
                    agent.setFace(agentDetails.getFace());
                    agent.setIkp(agentDetails.getIkp());
                    agent.setStatus(agentDetails.getStatus());
                    agent.setDateCreate(agentDetails.getDateCreate());
                    return agentRepository.save(agent);
                })
                .orElseThrow(() -> new NotFoundException("Агент с ID " + id + " не найден"));
    }

    @Transactional
    public boolean deleteAgent(@PathVariable Long id) {
        return agentRepository.findById(id)
                .map(agent -> {
                    agentRepository.delete(agent);
                    return true;
                })
                .orElse(false);
    }
}
