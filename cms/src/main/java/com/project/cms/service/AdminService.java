package com.project.cms.service;

import com.project.cms.dto.request.AdminRequest;
import com.project.cms.dto.request.UpdateAdminRequest;
import com.project.cms.dto.response.AdminResponse;
import com.project.cms.entity.AdminEntity;
import com.project.cms.entity.FacultyEntity;
import com.project.cms.entity.UserEntity;
import com.project.cms.exception.CustomException;
import com.project.cms.mapper.AdminMapper;
import com.project.cms.repository.AdminRepository;
import com.project.cms.repository.FacultyRepository;
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
public class AdminService {

    private final UserRepository userRepository;
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final FacultyRepository facultyRepository;
    private final RoleRepository roleRepository;
    private final UserService userService;

    public AdminResponse add(AdminRequest request) {
        userService.checkEmailNotExists(request.getEmail());
        FacultyEntity faculty = facultyRepository.findById(request.getFacultyId())
                .orElseThrow(() -> new CustomException("Fakültə tapılmadı", "Faculty not found", "Not found",
                        404, null));
        UserEntity user = new UserEntity(request.getEmail(), passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);

        AdminEntity admin = new AdminEntity();
        admin.setName(request.getName());
        admin.setSurname(request.getSurname());
        admin.setPhone(request.getPhone());
        admin.setUser(user);
        admin.setFaculty(faculty);

        adminRepository.save(admin);

        roleRepository.assignAdminRoles(user.getId());

        return AdminMapper.convertToDTO(admin);
    }

    public AdminResponse findById(Long id) {
        AdminEntity admin = adminRepository.findById(id)
                .orElseThrow(() -> new CustomException("Admin tapılmadı", "Admin not found", "Not found",
                        404, null));
        return AdminMapper.convertToDTO(admin);
    }

    public List<AdminResponse> getAll() {
        List<AdminEntity> admins = adminRepository.findAll();
        return AdminMapper.convertToDTOList(admins);
    }

    public List<AdminResponse> findAdminsByFacultyId(Long facultyId) {
        FacultyEntity faculty = facultyRepository.findById(facultyId)
                .orElseThrow(() -> new CustomException("Fakültə tapılmadı", "Faculty not found", "Not found",
                        404, null));
        List<AdminEntity> admins = adminRepository.findAdminsByFacultyId(faculty.getId());
        return AdminMapper.convertToDTOList(admins);
    }

    @Transactional
    public AdminResponse update(UpdateAdminRequest request) {
        AdminEntity admin = adminRepository.findById(request.getId())
                .orElseThrow(() -> new CustomException("Admin tapılmadı", "Admin not found", "Not found",
                        404, null));

        FacultyEntity faculty = facultyRepository.findById(request.getFacultyId())
                .orElseThrow(() -> new CustomException("Fakültə tapılmadı", "Faculty not found", "Not found",
                        404, null));

        admin.setName(request.getName());
        admin.setSurname(request.getSurname());
        admin.setPhone(request.getPhone());
        admin.setFaculty(faculty);

        adminRepository.save(admin);

        return AdminMapper.convertToDTO(admin);
    }
}
