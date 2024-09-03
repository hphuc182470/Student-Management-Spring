package com.example.StudentManagementSpring.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Enrollments")
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "enrollmentID", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "studentID")
    private User studentID;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "classID")
    private Class classID;

}