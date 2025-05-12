package com.project.cms.controller;

import com.project.cms.dto.request.ProfessorRequest;
import com.project.cms.dto.request.UpdateProfessorRequest;
import com.project.cms.dto.response.ProfessorResponse;
import com.project.cms.exception.CustomException;
import com.project.cms.service.ProfessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/professors")
@RequiredArgsConstructor
public class ProfessorController {

    private final ProfessorService professorService;

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADD_PROFESSOR')")
    public ProfessorResponse add(@RequestBody ProfessorRequest request, BindingResult br) {
        if (br.hasErrors()) {
            throw new CustomException("Məlumatların tamlığı pozulub!", "Validation failed!", "Validation", 400, br);
        }
        return professorService.add(request);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_FIND_PROFESSOR')")
    public ProfessorResponse findById(@PathVariable Long id) {
        return professorService.findById(id);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_GET_ALL_PROFESSORS')")
    public List<ProfessorResponse> getAll() {
        return professorService.getAll();
    }

    @GetMapping("/faculty/{facultyId}")
    @PreAuthorize("hasAuthority('ROLE_FIND_PROFESSORS_BY_FACULTY')")
    public List<ProfessorResponse> findByFacultyId(@PathVariable Long facultyId) {
        return professorService.findProfessorsByFacultyId(facultyId);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ROLE_UPDATE_PROFESSOR')")
    public ProfessorResponse update(@RequestBody UpdateProfessorRequest request, BindingResult br) {
        if (br.hasErrors()) {
            throw new CustomException("Yeniləmə üçün məlumatlar düzgün deyil!", "Validation failed!", "Validation", 400, br);
        }
        return professorService.update(request);
    }
}
