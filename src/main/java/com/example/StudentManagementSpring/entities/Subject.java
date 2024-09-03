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
@Table(name = "Subjects")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subjectID", nullable = false)
    private Long id;

    @Size(max = 100)
    @Column(name = "subjectName", length = 100)
    private String subjectName;

    @OneToMany(mappedBy = "subjectID")
    private Set<ClassSubject> classSubjects = new LinkedHashSet<>();

    @OneToMany(mappedBy = "subjectID")
    private Set<Exercise> exercises = new LinkedHashSet<>();

    @OneToMany(mappedBy = "subjectID")
    private Set<TeacherSubject> teacherSubjects = new LinkedHashSet<>();

}