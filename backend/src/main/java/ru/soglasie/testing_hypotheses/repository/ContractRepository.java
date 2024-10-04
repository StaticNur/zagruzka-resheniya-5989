package ru.soglasie.testing_hypotheses.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.soglasie.testing_hypotheses.model.entity.Contract;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {}
