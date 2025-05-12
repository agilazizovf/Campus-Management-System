package com.project.cms.service;

import com.project.cms.dto.request.FacultyRequest;
import com.project.cms.dto.request.UpdateFacultyRequest;
import com.project.cms.dto.response.FacultyResponse;
import com.project.cms.entity.FacultyEntity;
import com.project.cms.exception.CustomException;
import com.project.cms.mapper.FacultyMapper;
import com.project.cms.repository.FacultyRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FacultyService {

    private final FacultyRepository facultyRepository;
    private final ModelMapper mapper;

    public FacultyResponse add(FacultyRequest request) {
        FacultyEntity faculty = new FacultyEntity();
        mapper.map(request, faculty);
        faculty.setCreatedAt(LocalDate.now());
        faculty.setUpdatedAt(LocalDate.now());
        facultyRepository.save(faculty);

        return FacultyMapper.convertToDTO(faculty);
    }

    public List<FacultyResponse> getAll() {
        List<FacultyEntity> faculty = facultyRepository.findAll();
        return FacultyMapper.convertToDTOList(faculty);
    }

    public FacultyResponse findById(Long id) {
        FacultyEntity faculty = facultyRepository.findById(id)
                .orElseThrow(() -> new CustomException("Fakültə tapılmadı", "Faculty not found", "Not found",
                        404, null));
        return FacultyMapper.convertToDTO(faculty);
    }

    public FacultyResponse update(UpdateFacultyRequest request) {
        FacultyEntity faculty = facultyRepository.findById(request.getId())
                .orElseThrow(() -> new CustomException("Fakültə tapılmadı", "Faculty not found", "Not found",
                        404, null));
        mapper.map(request, faculty);
        faculty.setUpdatedAt(LocalDate.now());
        facultyRepository.save(faculty);

        return FacultyMapper.convertToDTO(faculty);
    }

    public void delete(Long id) {
        FacultyEntity faculty = facultyRepository.findById(id)
                .orElseThrow(() -> new CustomException("Fakültə tapılmadı", "Faculty not found", "Not found",
                        404, null));
        facultyRepository.delete(faculty);
    }
}
