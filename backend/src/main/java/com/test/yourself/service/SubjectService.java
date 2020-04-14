package com.test.yourself.service;

import com.test.yourself.model.Subject;

import java.util.List;
import java.util.Map;

public interface SubjectService {
    Subject findSubjectByName(String name);
    List<Subject> findAllSubjects();
    Map<Long,String> findMapSubjects();
    Subject addSubject(Subject subject);
    Subject findSubjectById(Long id);
}
