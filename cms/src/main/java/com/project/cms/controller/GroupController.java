package com.project.cms.controller;

import com.project.cms.dto.request.GroupRequest;
import com.project.cms.dto.request.ProfessorGroupRequest;
import com.project.cms.dto.request.StudentGroupRequest;
import com.project.cms.dto.request.UpdateGroupRequest;
import com.project.cms.dto.response.GroupResponse;
import com.project.cms.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADD_GROUP')")
    public GroupResponse add(@RequestBody GroupRequest request) {
        return groupService.add(request);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_FIND_GROUP')")
    public GroupResponse findById(@PathVariable Long id) {
        return groupService.findById(id);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_GET_ALL_GROUPS')")
    public List<GroupResponse> getAll() {
        return groupService.getAll();
    }

    @GetMapping("/faculty/{facultyId}")
    @PreAuthorize("hasAuthority('ROLE_GET_ALL_GROUPS_BY_FACULTY')")
    public List<GroupResponse> getAllByFaculty(@PathVariable Long facultyId) {
        return groupService.getAllByFacultyId(facultyId);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ROLE_UPDATE_GROUP')")
    public GroupResponse update(@RequestBody UpdateGroupRequest request) {
        return groupService.update(request);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_DELETE_GROUP')")
    public void delete(@PathVariable Long id) {
        groupService.delete(id);
    }

    @PostMapping("/assign-student")
    @PreAuthorize("hasAuthority('ROLE_ASSIGN_STUDENT_TO_GROUP')")
    public void assignStudentToGroup(@RequestBody StudentGroupRequest request) {
        groupService.assignToGroup(request);
    }

    @PostMapping("/assign-professor")
    @PreAuthorize("hasAuthority('ROLE_ASSIGN_PROFESSOR_TO_GROUP')")
    public void assignProfessorToGroup(@RequestBody ProfessorGroupRequest request) {
        groupService.assignProfessorToGroup(request);
    }
}
