package com.schedule.vote.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "PAUTA")
@Getter
@Setter
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private LocalDateTime deadline;
}
