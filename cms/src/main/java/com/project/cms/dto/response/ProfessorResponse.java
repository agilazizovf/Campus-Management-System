package com.project.cms.dto.response;

import lombok.Data;

@Data
public class ProfessorResponse {

    private Long id;
    private String name;
    private String surname;

    private String phone;
    private String title; // e.g. "Associate Professor", "Dr.", etc.
    private String department;

    private Integer experience;

    private Long facultyId;
}
