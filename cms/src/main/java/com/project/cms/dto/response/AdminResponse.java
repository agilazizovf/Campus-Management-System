package com.project.cms.dto.response;

import lombok.Data;

@Data
public class AdminResponse {

    private Long id;
    private String name;
    private String surname;
    private String phone;
    private Long facultyId;
}
