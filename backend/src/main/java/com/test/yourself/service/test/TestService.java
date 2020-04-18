package com.test.yourself.service.test;

import com.test.yourself.model.Subject;
import com.test.yourself.model.test.Test;

import java.util.List;

public interface TestService {
    Test findTestById(Long id);
    Test findTestByName(String name);
    void deleteTest(Test test);
    void deleteTestById(Long id);
    Test updateTest(Long id, Test test);
    List<Test> findAll();
    List<Test> findAllBySubject(Subject subject);
    List<Test> findAllBySubjectId(Long subjectId);
    void removeAll();
    void removeAllBySubject(Subject subject);
    void removeAllBySubjectId(Long subjectId);
}
