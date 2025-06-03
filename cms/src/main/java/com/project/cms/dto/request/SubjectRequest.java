package com.project.cms.dto.request;

import lombok.Data;

@Data
public class SubjectRequest {
    private String name;
    private Long professorId;
    private Long groupId;
    private Long facultyId;
}