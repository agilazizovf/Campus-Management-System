package com.project.cms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "students")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;

    private String phone;

    @Column(name = "student_code")
    private String studentCode; // Unique student number

    @Column(name = "academic_year")
    private Integer academicYear; // e.g. 1st year, 2nd year
    private String major;

    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private FacultyEntity faculty;

    @ManyToMany
    @JoinTable(
            name = "student_groups",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    private List<GroupEntity> groups;


    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
