package com.project.cms.service;

import com.project.cms.dto.request.StudentRequest;
import com.project.cms.dto.request.UpdateStudentRequest;
import com.project.cms.dto.response.StudentResponse;
import com.project.cms.entity.FacultyEntity;
import com.project.cms.entity.StudentEntity;
import com.project.cms.entity.UserEntity;
import com.project.cms.exception.CustomException;
import com.project.cms.mapper.StudentMapper;
import com.project.cms.repository.FacultyRepository;
import com.project.cms.repository.RoleRepository;
import com.project.cms.repository.StudentRepository;
import com.project.cms.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final UserRepository userRepository;
    private final FacultyRepository facultyRepository;
    private final UserService userService;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper mapper;

    public StudentResponse add(StudentRequest request) {
        userService.checkEmailNotExists(request.getEmail());

        FacultyEntity faculty = facultyRepository.findById(request.getFacultyId())
                .orElseThrow(() -> new CustomException("Fakültə tapılmadı", "Faculty not found", "Not found",
                        404, null));
        UserEntity user = new UserEntity(request.getEmail(), passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);

        StudentEntity student = new StudentEntity();
        student.setName(request.getName());
        student.setSurname(request.getSurname());
        student.setPhone(request.getPhone());
        student.setStudentCode(request.getStudentCode());
        student.setMajor(request.getMajor());
        student.setYear(request.getYear());
        student.setFaculty(faculty);
        student.setUser(user);

        studentRepository.save(student);

        roleRepository.assignStudentRoles(user.getId());

        return StudentMapper.convertToDTO(student);
    }

    public StudentResponse findById(Long id) {
        StudentEntity student = studentRepository.findById(id)
                .orElseThrow(() -> new CustomException("Tələbə tapılmadı", "Student not found", "Not found",
                        404, null));
        return StudentMapper.convertToDTO(student);
    }

    public List<StudentResponse> getAll() {
        List<StudentEntity> students = studentRepository.findAll();
        return StudentMapper.convertToDTOList(students);
    }

    @Transactional
    public StudentResponse update(UpdateStudentRequest request) {
        StudentEntity student = studentRepository.findById(request.getId())
                .orElseThrow(() -> new CustomException("Tələbə tapılmadı", "Student not found", "Not found",
                        404, null));
        FacultyEntity faculty = facultyRepository.findById(request.getFacultyId())
                .orElseThrow(() -> new CustomException("Fakültə tapılmadı", "Faculty not found", "Not found",
                        404, null));

        mapper.map(request, student);
        student.setFaculty(faculty);
        studentRepository.save(student);

        return StudentMapper.convertToDTO(student);
    }

    public List<StudentResponse> findStudentsByFacultyId(Long facultyId) {
        FacultyEntity faculty = facultyRepository.findById(facultyId)
                .orElseThrow(() -> new CustomException("Fakültə tapılmadı", "Faculty not found", "Not found",
                        404, null));
        List<StudentEntity> students = studentRepository.findStudentsByFacultyId(faculty.getId());
        return StudentMapper.convertToDTOList(students);
    }

    public StudentResponse getProfile() {
        UserEntity user = userService.getCurrentUser();

        StudentEntity student = user.getStudent();
        return StudentMapper.convertToDTO(student);
    }
}
