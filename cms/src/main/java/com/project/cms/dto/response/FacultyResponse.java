package com.project.cms.dto.response;

import lombok.Data;

@Data
public class FacultyResponse {

    private Long id;
    private String name;

    private String description;

    private String code;

    private String dean;

    private Integer establishedYear;

    private String phone;

    private String contactEmail;
}
