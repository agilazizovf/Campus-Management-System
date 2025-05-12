package com.project.cms.controller;

import com.project.cms.dto.request.StudentRequest;
import com.project.cms.dto.request.UpdateStudentRequest;
import com.project.cms.dto.response.StudentResponse;
import com.project.cms.exception.CustomException;
import com.project.cms.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADD_STUDENT')")
    public ResponseEntity<StudentResponse> add(@RequestBody StudentRequest request, BindingResult br) {
        if (br.hasErrors()) {
            throw new CustomException("Məlumatların tamlığı pozulub!", "Validation failed!", "Validation", 400, br);
        }
        StudentResponse response = studentService.add(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_FIND_STUDENT')")
    public ResponseEntity<StudentResponse> findById(@PathVariable Long id) {
        StudentResponse response = studentService.findById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_GET_ALL_STUDENTS')")
    public ResponseEntity<List<StudentResponse>> getAll() {
        List<StudentResponse> response = studentService.getAll();
        return ResponseEntity.ok(response);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ROLE_UPDATE_STUDENT')")
    public ResponseEntity<StudentResponse> update(@RequestBody UpdateStudentRequest request, BindingResult br) {
        if (br.hasErrors()) {
            throw new CustomException("Məlumatların tamlığı pozulub!", "Validation failed!", "Validation", 400, br);
        }
        StudentResponse response = studentService.update(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/faculty/{facultyId}")
    @PreAuthorize("hasAuthority('ROLE_FIND_STUDENT_BY_FACULTY')")
    public ResponseEntity<List<StudentResponse>> findByFaculty(@PathVariable Long facultyId) {
        List<StudentResponse> response = studentService.findStudentsByFacultyId(facultyId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/profile")
    @PreAuthorize("hasAuthority('ROLE_GET_PROFILE')")
    public StudentResponse getProfile() {
        return studentService.getProfile();
    }

}
