package com.project.cms.mapper;

import com.project.cms.dto.response.FacultyResponse;
import com.project.cms.entity.FacultyEntity;

import java.util.List;
import java.util.stream.Collectors;

public class FacultyMapper {

    public static FacultyResponse convertToDTO(FacultyEntity faculty) {
        FacultyResponse response = new FacultyResponse();
        response.setId(faculty.getId());
        response.setName(faculty.getName());
        response.setDean(faculty.getDean());
        response.setCode(faculty.getCode());
        response.setDescription(faculty.getDescription());
        response.setPhone(faculty.getPhone());
        response.setContactEmail(faculty.getContactEmail());
        response.setEstablishedYear(faculty.getEstablishedYear());

        return response;
    }

    public static List<FacultyResponse> convertToDTOList(List<FacultyEntity> faculties) {
        return faculties.stream()
                .map(FacultyMapper::convertToDTO)
                .collect(Collectors.toList());
    }
}
