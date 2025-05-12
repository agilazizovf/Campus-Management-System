package com.project.cms.service;

import com.project.cms.dto.request.ProfessorRequest;
import com.project.cms.dto.request.UpdateProfessorRequest;
import com.project.cms.dto.response.ProfessorResponse;
import com.project.cms.entity.FacultyEntity;
import com.project.cms.entity.ProfessorEntity;
import com.project.cms.entity.UserEntity;
import com.project.cms.exception.CustomException;
import com.project.cms.mapper.ProfessorMapper;
import com.project.cms.repository.FacultyRepository;
import com.project.cms.repository.ProfessorRepository;
import com.project.cms.repository.RoleRepository;
import com.project.cms.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfessorService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final ProfessorRepository professorRepository;
    private final FacultyRepository facultyRepository;
    private final UserService userService;
    private final RoleRepository roleRepository;

    public ProfessorResponse add(ProfessorRequest request) {
        userService.checkEmailNotExists(request.getEmail());

        FacultyEntity faculty = facultyRepository.findById(request.getFacultyId())
                .orElseThrow(() -> new CustomException("Fakültə tapılmadı", "Faculty not found", "Not found",
                        404, null));
        UserEntity user = new UserEntity(request.getEmail(), passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);

        ProfessorEntity professor = new ProfessorEntity();
        professor.setName(request.getName());
        professor.setSurname(request.getSurname());
        professor.setExperience(request.getExperience());
        professor.setPhone(request.getPhone());
        professor.setDepartment(request.getDepartment());
        professor.setTitle(request.getTitle());

        professor.setFaculty(faculty);
        professor.setUser(user);

        professorRepository.save(professor);

        roleRepository.assignProfessorRoles(user.getId());

        return ProfessorMapper.convertToDTO(professor);
    }

    public ProfessorResponse findById(Long id) {
        ProfessorEntity professor = professorRepository.findById(id)
                .orElseThrow(() -> new CustomException("Müəllim tapılmadı", "Professor not found", "Not found",
                        404, null));
        return ProfessorMapper.convertToDTO(professor);
    }

    public List<ProfessorResponse> getAll() {
        List<ProfessorEntity> professors = professorRepository.findAll();
        return ProfessorMapper.convertToDTOList(professors);
    }

    @Transactional
    public ProfessorResponse update(UpdateProfessorRequest request) {
        ProfessorEntity professor = professorRepository.findById(request.getId())
                .orElseThrow(() -> new CustomException("Müəllim tapılmadı", "Professor not found", "Not found",
                        404, null));
        FacultyEntity faculty = facultyRepository.findById(request.getFacultyId())
                .orElseThrow(() -> new CustomException("Fakültə tapılmadı", "Faculty not found", "Not found",
                        404, null));

        mapper.map(request, professor);
        professor.setFaculty(faculty);

        professorRepository.save(professor);

        return ProfessorMapper.convertToDTO(professor);
    }

    public List<ProfessorResponse> findProfessorsByFacultyId(Long facultyId) {
        FacultyEntity faculty = facultyRepository.findById(facultyId)
                .orElseThrow(() -> new CustomException("Fakültə tapılmadı", "Faculty not found", "Not found",
                        404, null));
        List<ProfessorEntity> professorEntities = professorRepository.findProfessorsByFacultyId(faculty.getId());
        return ProfessorMapper.convertToDTOList(professorEntities);
    }
}
