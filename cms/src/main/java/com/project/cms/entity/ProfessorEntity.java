package com.project.cms.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "professors")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;

    private String phone;
    private String title; // e.g. "Associate Professor", "Dr.", etc.
    private String department;

    private Integer experience;

    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private FacultyEntity faculty;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @JsonIgnore
    @OneToMany(mappedBy = "professor")
    private List<GroupEntity> groups;
}
