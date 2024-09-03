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
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userID", nullable = false)
    private Long id;

    @Size(max = 50)
    @Column(name = "username", length = 50)
    private String username;

    @Lob
    @Column(name = "password")
    private String password;

    @Size(max = 50)
    @Column(name = "firstName", length = 50)
    private String firstName;

    @Size(max = 50)
    @Column(name = "lastName", length = 50)
    private String lastName;

    @Size(max = 100)
    @Column(name = "email", length = 100)
    private String email;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "roleID")
    private Role roleID;

    @OneToMany(mappedBy = "teacherID")
    private Set<ClassTeacher> classTeachers = new LinkedHashSet<>();

    @OneToMany(mappedBy = "studentID")
    private Set<Enrollment> enrollments = new LinkedHashSet<>();

    @OneToMany(mappedBy = "studentID")
    private Set<Score> scores = new LinkedHashSet<>();

    @OneToMany(mappedBy = "teacherID")
    private Set<TeacherSubject> teacherSubjects = new LinkedHashSet<>();

}