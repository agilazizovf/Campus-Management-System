package com.project.cms.mapper;

import com.project.cms.dto.response.GroupResponse;
import com.project.cms.entity.GroupEntity;

import java.util.List;
import java.util.stream.Collectors;

public class GroupMapper {

    public static GroupResponse convertToDTO(GroupEntity group) {
        GroupResponse response = new GroupResponse();
        response.setId(group.getId());
        response.setName(group.getName());
        response.setFacultyId(group.getFaculty().getId());

        return response;
    }

    public static List<GroupResponse> convertToDTOList(List<GroupEntity> groups) {
        return groups.stream()
                .map(GroupMapper::convertToDTO)
                .collect(Collectors.toList());
    }
}
