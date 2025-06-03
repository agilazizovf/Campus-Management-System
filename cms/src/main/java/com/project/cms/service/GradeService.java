package com.project.cms.service;

import com.project.cms.repository.GradeRepository;
import com.project.cms.repository.GroupRepository;
import com.project.cms.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GradeService {

    private final GroupRepository groupRepository;
    private final StudentRepository studentRepository;
    private final GradeRepository gradeRepository;
}
