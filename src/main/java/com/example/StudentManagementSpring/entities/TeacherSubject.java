package com.example.StudentManagementSpring.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "TeacherSubjects")
public class TeacherSubject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacherSubjectID", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "teacherID")
    private User teacherID;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subjectID")
    private Subject subjectID;

    @OneToMany(mappedBy = "teacherSubjectID")
    private Set<Exercise> exercises = new LinkedHashSet<>();

}