package ru.soglasie.testing_hypotheses.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.webjars.NotFoundException;
import ru.soglasie.testing_hypotheses.model.entity.Contract;
import ru.soglasie.testing_hypotheses.repository.ContractRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ContractService {
    @Autowired
    private ContractRepository contractRepository;

    public List<Contract> getAllContracts() {
        return contractRepository.findAll();
    }

    @Transactional
    public Contract createContract(Contract contract) {
        return contractRepository.save(contract);
    }

    public Optional<Contract> getContractById(@PathVariable Long id) {
        return contractRepository.findById(id);
    }

    @Transactional
    public Contract updateContract(Long id, Contract contractDetails) {
        return contractRepository.findById(id)
                .map(contract -> {
                    contract.setDateCreate(contractDetails.getDateCreate());
                    contract.setDateBegin(contractDetails.getDateBegin());
                    contract.setPremium(contractDetails.getPremium());
                    contract.setAgent(contractDetails.getAgent());
                    contract.setProduct(contractDetails.getProduct());
                    contract.setCommission(contractDetails.getCommission());
                    contract.setStatus(contractDetails.getStatus());
                    return contractRepository.save(contract);
                })
                .orElseThrow(() -> new NotFoundException("Contract с ID " + id + " не найден"));
    }

    @Transactional
    public boolean deleteContract(@PathVariable Long id) {
        return contractRepository.findById(id)
                .map(contract -> {
                    contractRepository.delete(contract);
                    return true;
                })
                .orElse(false);
    }
}
