package ru.soglasie.testing_hypotheses.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.soglasie.testing_hypotheses.model.enums.FaceType;

import java.time.LocalDate;

@Entity
@Table(name = "faces")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Face {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FaceType type;

    private String firstName;
    private String lastName;
    private String secondName;
    private LocalDate birthDay;
    private String name;
    private String inn;
}

