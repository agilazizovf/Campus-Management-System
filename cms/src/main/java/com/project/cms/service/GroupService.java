package com.project.cms.service;

import com.project.cms.dto.request.GroupRequest;
import com.project.cms.dto.request.ProfessorGroupRequest;
import com.project.cms.dto.request.StudentGroupRequest;
import com.project.cms.dto.request.UpdateGroupRequest;
import com.project.cms.dto.response.GroupResponse;
import com.project.cms.entity.FacultyEntity;
import com.project.cms.entity.GroupEntity;
import com.project.cms.entity.ProfessorEntity;
import com.project.cms.entity.StudentEntity;
import com.project.cms.exception.CustomException;
import com.project.cms.mapper.GroupMapper;
import com.project.cms.repository.FacultyRepository;
import com.project.cms.repository.GroupRepository;
import com.project.cms.repository.ProfessorRepository;
import com.project.cms.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final StudentRepository studentRepository;
    private final ProfessorRepository professorRepository;
    private final GroupRepository groupRepository;
    private final FacultyRepository facultyRepository;

    public GroupResponse add(GroupRequest request) {
        FacultyEntity faculty = facultyRepository.findById(request.getFacultyId())
                .orElseThrow(() -> new CustomException("Fakültə tapılmadı", "Faculty not found", "Not found",
                        404, null));
        GroupEntity group = new GroupEntity();
        group.setName(request.getName());
        group.setFaculty(faculty);
        groupRepository.save(group);

        return GroupMapper.convertToDTO(group);
    }

    public List<GroupResponse> getAll() {
        List<GroupEntity> groups = groupRepository.findAll();
        return GroupMapper.convertToDTOList(groups);
    }

    public List<GroupResponse> getAllByFacultyId(Long facultyId) {
        FacultyEntity faculty = facultyRepository.findById(facultyId)
                .orElseThrow(() -> new CustomException("Fakültə tapılmadı", "Faculty not found", "Not found", 404, null));

        List<GroupEntity> groups = groupRepository.findAllByFacultyId(faculty.getId());
        return GroupMapper.convertToDTOList(groups);
    }


    public GroupResponse findById(Long id) {
        GroupEntity group = groupRepository.findById(id)
                .orElseThrow(() -> new CustomException("Qrup tapılmadı", "Group not found", "Not found", 404, null));
        return GroupMapper.convertToDTO(group);
    }

    public GroupResponse update(UpdateGroupRequest request) {
        GroupEntity group = groupRepository.findById(request.getId())
                .orElseThrow(() -> new CustomException("Qrup tapılmadı", "Group not found", "Not found", 404, null));

        FacultyEntity faculty = facultyRepository.findById(request.getFacultyId())
                .orElseThrow(() -> new CustomException("Fakültə tapılmadı", "Faculty not found", "Not found", 404, null));

        group.setName(request.getName());
        group.setFaculty(faculty);
        groupRepository.save(group);

        return GroupMapper.convertToDTO(group);
    }

    public void delete(Long id) {
        GroupEntity group = groupRepository.findById(id)
                .orElseThrow(() -> new CustomException("Qrup tapılmadı", "Group not found", "Not found", 404, null));

        groupRepository.delete(group);
    }

    public void assignToGroup(StudentGroupRequest request) {
        StudentEntity student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new CustomException("Tələbə tapılmadı", "Student not found", "Not found", 404, null));

        GroupEntity group = groupRepository.findById(request.getGroupId())
                .orElseThrow(() -> new CustomException("Qrup tapılmadı", "Group not found", "Not found", 404, null));

        student.getGroups().add(group);
        studentRepository.save(student);
    }

    public void assignProfessorToGroup(ProfessorGroupRequest request) {
        GroupEntity group = groupRepository.findById(request.getGroupId())
                .orElseThrow(() -> new CustomException("Qrup tapılmadı", "Group not found", "Not found", 404, null));

        ProfessorEntity professor = professorRepository.findById(request.getProfessorId())
                .orElseThrow(() -> new CustomException("Müəllim tapılmadı", "Professor not found", "Not found", 404, null));

        group.setProfessor(professor);
        groupRepository.save(group);
    }
}
