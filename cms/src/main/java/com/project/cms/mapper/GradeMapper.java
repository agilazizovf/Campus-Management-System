package com.project.cms.mapper;

import com.project.cms.dto.response.GradeResponse;
import com.project.cms.entity.GradeEntity;

import java.util.List;
import java.util.stream.Collectors;

public class GradeMapper {
    public static GradeResponse convertToDTO(GradeEntity grade) {
        GradeResponse response = new GradeResponse();
        response.setName(grade.getName());
        response.setGrade(grade.getGrade());
        response.setStudentId(grade.getId());

        return response;
    }
    public static List<GradeResponse> convertToDTOList(List<GradeEntity> faculties) {
        return faculties.stream()
                .map(GradeMapper::convertToDTO)
                .collect(Collectors.toList());
    }
}
