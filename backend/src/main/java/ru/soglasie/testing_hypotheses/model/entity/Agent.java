package ru.soglasie.testing_hypotheses.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.soglasie.testing_hypotheses.model.enums.AgentStatus;

import java.time.LocalDate;

@Entity
@Table(name = "agents")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Agent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "face_id", nullable = false)
    private Face face;

    @ManyToOne
    @JoinColumn(name = "ikp_id", nullable = false)
    private IKP ikp;

    @Enumerated(EnumType.STRING)
    private AgentStatus status;

    @Column(nullable = false)
    private LocalDate dateCreate;

    @Column
    private LocalDate dateBegin;

    @Column
    private LocalDate dateEnd;
}
