package com.project.cms.mapper;

import com.project.cms.dto.response.StudentResponse;
import com.project.cms.entity.StudentEntity;

import java.util.List;
import java.util.stream.Collectors;

public class StudentMapper {

    public static StudentResponse convertToDTO(StudentEntity student) {
        StudentResponse response = new StudentResponse();
        response.setId(student.getId());
        response.setName(student.getName());
        response.setSurname(student.getSurname());
        response.setPhone(student.getPhone());
        response.setStudentCode(student.getStudentCode());
        response.setYear(student.getYear());
        response.setMajor(student.getMajor());

        if (student.getFaculty() != null) {
            response.setFacultyId(student.getFaculty().getId());
        }

        return response;
    }

    public static List<StudentResponse> convertToDTOList(List<StudentEntity> students) {
        return students.stream()
                .map(StudentMapper::convertToDTO)
                .collect(Collectors.toList());
    }
}
