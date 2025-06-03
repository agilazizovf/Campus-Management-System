package com.project.cms.service;

import com.project.cms.dto.request.SubjectRequest;
import com.project.cms.dto.request.UpdateSubjectRequest;
import com.project.cms.dto.response.SubjectResponse;
import com.project.cms.entity.*;
import com.project.cms.exception.CustomException;
import com.project.cms.mapper.SubjectMapper;
import com.project.cms.repository.FacultyRepository;
import com.project.cms.repository.GroupRepository;
import com.project.cms.repository.ProfessorRepository;
import com.project.cms.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepository subjectRepository;
    private final ProfessorRepository professorRepository;
    private final FacultyRepository facultyRepository;
    private final GroupRepository groupRepository;

    public SubjectResponse add(SubjectRequest request) {
        FacultyEntity faculty = facultyRepository.findById(request.getFacultyId())
                .orElseThrow(() -> new CustomException("Fakültə tapılmadı", "Faculty not found", "Not found",
                        404, null));
        ProfessorEntity professor = professorRepository.findById(request.getProfessorId())
                .orElseThrow(() -> new CustomException("Müəllim tapılmadı", "Professor not found", "Not found",
                        404, null));
        GroupEntity group = groupRepository.findById(request.getGroupId())
                .orElseThrow(() -> new CustomException("Qrup tapılmadı", "Group not found", "Not found", 404, null));

        SubjectEntity subject = new SubjectEntity();
        subject.setName(request.getName());
        subject.setFaculty(faculty);
        subject.setProfessor(professor);
        subject.setGroup(group);
        subjectRepository.save(subject);

        return SubjectMapper.convertToDTO(subject);
    }

    public SubjectResponse findById(Long id) {
        SubjectEntity subject = subjectRepository.findById(id)
                .orElseThrow(() -> new CustomException("Fənn tapılmadı", "Subject not found", "Not found",
                        404, null));
        return SubjectMapper.convertToDTO(subject);
    }

    public List<SubjectResponse> findAll() {
        List<SubjectEntity> subjects = subjectRepository.findAll();
        return SubjectMapper.convertToDTOList(subjects);
    }

    public SubjectResponse update(UpdateSubjectRequest request) {
        SubjectEntity subject = subjectRepository.findById(request.getId())
                .orElseThrow(() -> new CustomException("Fənn tapılmadı", "Subject not found", "Not found",
                        404, null));
        FacultyEntity faculty = facultyRepository.findById(request.getFacultyId())
                .orElseThrow(() -> new CustomException("Fakültə tapılmadı", "Faculty not found", "Not found",
                        404, null));
        ProfessorEntity professor = professorRepository.findById(request.getProfessorId())
                .orElseThrow(() -> new CustomException("Müəllim tapılmadı", "Professor not found", "Not found",
                        404, null));
        GroupEntity group = groupRepository.findById(request.getGroupId())
                .orElseThrow(() -> new CustomException("Qrup tapılmadı", "Group not found", "Not found", 404, null));

        subject.setName(request.getName());
        subject.setFaculty(faculty);
        subject.setProfessor(professor);
        subject.setGroup(group);
        subjectRepository.save(subject);

        return SubjectMapper.convertToDTO(subject);
    }

    public void delete(Long id) {
        SubjectEntity subject = subjectRepository.findById(id)
                .orElseThrow(() -> new CustomException("Fənn tapılmadı", "Subject not found", "Not found",
                        404, null));
        subjectRepository.delete(subject);
    }
}
