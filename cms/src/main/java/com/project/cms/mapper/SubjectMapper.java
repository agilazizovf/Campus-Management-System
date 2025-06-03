package com.project.cms.mapper;

import com.project.cms.dto.response.SubjectResponse;
import com.project.cms.entity.SubjectEntity;

import java.util.List;
import java.util.stream.Collectors;

public class SubjectMapper {

    public static SubjectResponse convertToDTO(SubjectEntity subject) {
        SubjectResponse response = new SubjectResponse();
        response.setId(subject.getId());
        response.setName(subject.getName());
        response.setFacultyId(subject.getFaculty().getId());
        response.setGroupId(subject.getGroup().getId());
        response.setProfessorId(subject.getProfessor().getId());

        return response;
    }

    public static List<SubjectResponse> convertToDTOList(List<SubjectEntity> subjects) {
        return subjects.stream()
                .map(SubjectMapper::convertToDTO)
                .collect(Collectors.toList());
    }
}
