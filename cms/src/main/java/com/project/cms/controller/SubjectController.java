package com.project.cms.controller;

import com.project.cms.dto.request.SubjectRequest;
import com.project.cms.dto.request.UpdateSubjectRequest;
import com.project.cms.dto.response.SubjectResponse;
import com.project.cms.exception.CustomException;
import com.project.cms.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subjects")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADD_SUBJECT')")
    public ResponseEntity<SubjectResponse> addSubject(@RequestBody SubjectRequest request, BindingResult br) {
        if (br.hasErrors()) {
            throw new CustomException("Məlumatların tamlığı pozulub!", "Validation failed!", "Validation", 400, br);
        }
        SubjectResponse response = subjectService.add(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_GET_ALL_SUBJECTS')")
    public ResponseEntity<List<SubjectResponse>> getAllSubjects() {
        List<SubjectResponse> responseList = subjectService.findAll();
        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_FIND_SUBJECT')")
    public ResponseEntity<SubjectResponse> getSubjectById(@PathVariable Long id) {
        SubjectResponse response = subjectService.findById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ROLE_UPDATE_SUBJECT')")
    public ResponseEntity<SubjectResponse> updateSubject(@RequestBody UpdateSubjectRequest request, BindingResult br) {
        if (br.hasErrors()) {
            throw new CustomException("Məlumatların tamlığı pozulub!", "Validation failed!", "Validation", 400, br);
        }
        SubjectResponse response = subjectService.update(request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_DELETE_SUBJECT')")
    public ResponseEntity<Void> deleteSubject(@PathVariable Long id) {
        subjectService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
