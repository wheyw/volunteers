package ru.kors.springstudents.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class Otclick {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "vacancyId")
    private Long vacancyId;
    @Column(name = "volonterId")
    private UUID volonterId;
    @Column(name = "came")
    private boolean came;
}
