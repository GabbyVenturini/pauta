package com.schedule.vote.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "VOTO")
@Getter
@Setter
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,unique = true)
    private Long idSchedule;
    @Column(nullable = false,unique = true)
    private Long idUser;
    @Column(nullable = false)
    private boolean vote;
}
