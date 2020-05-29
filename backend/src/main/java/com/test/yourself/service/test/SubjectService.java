package com.test.yourself.service.test;

import com.test.yourself.model.testsystem.subject.Subject;

import java.util.Map;

public interface SubjectService extends DataService<Subject> {

    Map<Long, String> findMapSubjects();

    Subject updateName(String subjectName, Long id);

}
