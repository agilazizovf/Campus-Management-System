package com.project.cms.dto.response;

import lombok.Data;

@Data
public class GradeResponse {

    private Long id;
    private String name;
    private double grade;
    private Long studentId;
}
