package ru.soglasie.testing_hypotheses.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.soglasie.testing_hypotheses.model.enums.ContractStatus;

import java.time.LocalDate;

@Entity
@Table(name = "contracts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate dateCreate;

    @Column
    private LocalDate dateSign;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private LocalDate dateBegin;

    @Column
    private LocalDate dateEnd;

    @Column(nullable = false)
    private Double premium;

    @Column(nullable = false)
    private Double insuranceSum;

    @ManyToOne
    @JoinColumn(name = "agent_id", nullable = false)
    private Agent agent;

    @Column(nullable = false)
    private Double commission;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ContractStatus status;
}
