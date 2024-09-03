package com.example.StudentManagementSpring.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ClassTeachers")
public class ClassTeacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "classTeacherID", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "teacherID")
    private User teacherID;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "classID")
    private Class classID;

}