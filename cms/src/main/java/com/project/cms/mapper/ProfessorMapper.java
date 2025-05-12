package com.project.cms.mapper;

import com.project.cms.dto.response.ProfessorResponse;
import com.project.cms.entity.ProfessorEntity;

import java.util.List;
import java.util.stream.Collectors;

public class ProfessorMapper {

    public static ProfessorResponse convertToDTO(ProfessorEntity professor) {
        ProfessorResponse response = new ProfessorResponse();
        response.setId(professor.getId());
        response.setName(professor.getName());
        response.setSurname(professor.getSurname());
        response.setPhone(professor.getPhone());
        response.setTitle(professor.getTitle());
        response.setDepartment(professor.getDepartment());
        response.setExperience(professor.getExperience());

        if (professor.getFaculty() != null) {
            response.setFacultyId(professor.getFaculty().getId());
        }

        return response;
    }

    public static List<ProfessorResponse> convertToDTOList(List<ProfessorEntity> professors) {
        return professors.stream()
                .map(ProfessorMapper::convertToDTO)
                .collect(Collectors.toList());
    }
}
