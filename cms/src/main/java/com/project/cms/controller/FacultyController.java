package com.project.cms.controller;

import com.project.cms.dto.request.FacultyRequest;
import com.project.cms.dto.request.UpdateFacultyRequest;
import com.project.cms.dto.response.FacultyResponse;
import com.project.cms.exception.CustomException;
import com.project.cms.service.FacultyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/faculties")
@RequiredArgsConstructor
public class FacultyController {

    private final FacultyService facultyService;

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADD_FACULTY')")
    public ResponseEntity<FacultyResponse> addFaculty(@RequestBody FacultyRequest request, BindingResult br) {
        if (br.hasErrors()) {
            throw new CustomException("Məlumatların tamlığı pozulub!", "Validation failed!", "Validation", 400, br);
        }
        FacultyResponse response = facultyService.add(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_GET_ALL_FACULTIES')")
    public ResponseEntity<List<FacultyResponse>> getAllFaculties() {
        List<FacultyResponse> responseList = facultyService.getAll();
        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_FIND_FACULTY')")
    public ResponseEntity<FacultyResponse> getFacultyById(@PathVariable Long id) {
        FacultyResponse response = facultyService.findById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ROLE_UPDATE_FACULTY')")
    public ResponseEntity<FacultyResponse> updateFaculty(@RequestBody UpdateFacultyRequest request, BindingResult br) {
        if (br.hasErrors()) {
            throw new CustomException("Məlumatların tamlığı pozulub!", "Validation failed!", "Validation", 400, br);
        }
        FacultyResponse response = facultyService.update(request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_DELETE_FACULTY')")
    public ResponseEntity<Void> deleteFaculty(@PathVariable Long id) {
        facultyService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
