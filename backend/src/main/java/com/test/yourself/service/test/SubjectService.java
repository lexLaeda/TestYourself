package com.test.yourself.service.test;

import com.test.yourself.model.testsystem.subject.Subject;

import java.util.List;
import java.util.Map;

public interface SubjectService {
    Subject findSubjectByName(String name);
    List<Subject> findAllSubjects();
    Map<Long,String> findMapSubjects();

    Subject findSubjectById(Long id);
    Subject add(Subject subject);

    Subject update(Subject subject, Long id);

    Subject updateName(String subjectName, Long id);

    void deleteById(Long id);

    void delete(Subject subject);
}
