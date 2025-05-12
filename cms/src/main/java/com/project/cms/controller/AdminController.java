package com.project.cms.controller;

import com.project.cms.dto.request.AdminRequest;
import com.project.cms.dto.request.UpdateAdminRequest;
import com.project.cms.dto.response.AdminResponse;
import com.project.cms.exception.CustomException;
import com.project.cms.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admins")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADD_ADMIN')")
    public ResponseEntity<AdminResponse> add(@RequestBody AdminRequest request, BindingResult br) {
        if (br.hasErrors()) {
            throw new CustomException("Məlumatların tamlığı pozulub!", "Validation failed!", "Validation", 400, br);
        }
        return ResponseEntity.ok(adminService.add(request));
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ROLE_UPDATE_ADMIN')")
    public ResponseEntity<AdminResponse> update(@RequestBody UpdateAdminRequest request, BindingResult br) {
        if (br.hasErrors()) {
            throw new CustomException("Məlumatların tamlığı pozulub!", "Validation failed!", "Validation", 400, br);
        }
        return ResponseEntity.ok(adminService.update(request));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_GET_ALL_ADMINS')")
    public ResponseEntity<List<AdminResponse>> getAll() {
        return ResponseEntity.ok(adminService.getAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_FIND_ADMIN')")
    public ResponseEntity<AdminResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(adminService.findById(id));
    }

    @GetMapping("/faculty/{facultyId}")
    @PreAuthorize("hasAuthority('ROLE_FIND_ADMINS_BY_FACULTY')")
    public ResponseEntity<List<AdminResponse>> getByFacultyId(@PathVariable Long facultyId) {
        return ResponseEntity.ok(adminService.findAdminsByFacultyId(facultyId));
    }
}
