package com.project.cms.dto.request;

import lombok.Data;

@Data
public class UpdateGroupRequest {

    private Long id;
    private String name;
    private Long facultyId;
}
