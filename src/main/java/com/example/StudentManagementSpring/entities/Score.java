package com.example.StudentManagementSpring.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "Scores")
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scoreID", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "exerciseID")
    private Exercise exerciseID;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "studentID")
    private User studentID;

    @Column(name = "score", precision = 5, scale = 2)
    private BigDecimal score;

}