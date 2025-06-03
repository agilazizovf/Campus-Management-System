package com.project.cms.dto.response;

import lombok.Data;

@Data
public class SubjectResponse {
    private Long id;
    private String name;
    private Long professorId;
    private Long groupId;
    private Long facultyId;
}