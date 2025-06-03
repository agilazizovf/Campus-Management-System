package com.project.cms.dto.request;

import lombok.Data;

@Data
public class GradeRequest {

    private String name;
    private double grade;
    private Long studentId;
}
