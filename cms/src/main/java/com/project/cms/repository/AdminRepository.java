package com.project.cms.repository;

import com.project.cms.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository<AdminEntity, Long> {

    List<AdminEntity> findAdminsByFacultyId(Long facultyId);
}
