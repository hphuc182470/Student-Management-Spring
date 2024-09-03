package com.example.StudentManagementSpring.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Exercises")
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exerciseID", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subjectID")
    private Subject subjectID;

    @Size(max = 100)
    @Column(name = "title", length = 100)
    private String title;

    @Size(max = 100)
    @Column(name = "enterCode", length = 100)
    private String enterCode;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "teacherSubjectID")
    private TeacherSubject teacherSubjectID;

    @OneToMany(mappedBy = "exerciseID")
    private Set<Score> scores = new LinkedHashSet<>();
}