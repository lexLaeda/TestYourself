package com.test.yourself.service.test;

import com.test.yourself.model.testsystem.subject.Subject;
import com.test.yourself.model.testsystem.test.SubjectTest;

import java.util.List;

public interface TestService extends DataService<SubjectTest>{

    List<SubjectTest> findAllBySubject(Subject subject);
    List<SubjectTest> findAllBySubjectId(Long subjectId);
   
}
