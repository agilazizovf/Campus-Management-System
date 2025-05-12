package com.project.cms.dto.response;

import lombok.Data;

@Data
public class StudentResponse {
    private Long id;
    private String name;
    private String surname;

    private String phone;
    private String studentCode;
    private Integer year;
    private String major;

    private Long facultyId;
}
