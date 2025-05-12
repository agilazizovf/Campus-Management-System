package com.project.cms.repository;

import com.project.cms.entity.RoleEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    @Transactional
    @Query(value = "INSERT INTO user_roles (user_id, role_id) select ?1,id from roles where admin=1", nativeQuery = true)
    @Modifying
    void assignAdminRoles(Long userId);

    @Transactional
    @Query(value = "INSERT INTO user_roles (user_id, role_id) select ?1,id from roles where professor=1", nativeQuery = true)
    @Modifying
    void assignProfessorRoles(Long userId);

    @Transactional
    @Query(value = "INSERT INTO user_roles (user_id, role_id) select ?1,id from roles where student=1", nativeQuery = true)
    @Modifying
    void assignStudentRoles(Long userId);
}
