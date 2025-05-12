package com.project.cms.mapper;

import com.project.cms.dto.response.AdminResponse;
import com.project.cms.entity.AdminEntity;

import java.util.List;
import java.util.stream.Collectors;

public class AdminMapper {

    public static AdminResponse convertToDTO(AdminEntity admin) {
        AdminResponse response = new AdminResponse();
        response.setId(admin.getId());
        response.setName(admin.getName());
        response.setSurname(admin.getSurname());
        response.setPhone(admin.getPhone());

        if (admin.getFaculty() != null) {
            response.setFacultyId(admin.getFaculty().getId());
        }

        return response;
    }

    public static List<AdminResponse> convertToDTOList(List<AdminEntity> admins) {
        return admins.stream()
                .map(AdminMapper::convertToDTO)
                .collect(Collectors.toList());
    }
}
