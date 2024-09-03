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
@Table(name = "Classes")
public class Class {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "classID", nullable = false)
    private Long id;

    @Size(max = 50)
    @Column(name = "className", length = 50)
    private String className;

    @Size(max = 50)
    @Column(name = "classRoom", length = 50)
    private String classRoom;

    @OneToMany(mappedBy = "classID")
    private Set<ClassSubject> classSubjects = new LinkedHashSet<>();

    @OneToMany(mappedBy = "classID")
    private Set<ClassTeacher> classTeachers = new LinkedHashSet<>();

    @OneToMany(mappedBy = "classID")
    private Set<Enrollment> enrollments = new LinkedHashSet<>();

}