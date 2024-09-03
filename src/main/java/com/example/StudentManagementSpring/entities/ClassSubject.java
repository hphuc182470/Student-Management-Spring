package com.example.StudentManagementSpring.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ClassSubjects")
public class ClassSubject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "classSubjectID", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "classID")
    private Class classID;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subjectID")
    private Subject subjectID;

}