package com.project.cms.repository;

import com.project.cms.entity.ProfessorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfessorRepository extends JpaRepository<ProfessorEntity, Long> {

    List<ProfessorEntity> findProfessorsByFacultyId(Long facultyId);
}
