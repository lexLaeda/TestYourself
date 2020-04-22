package com.test.yourself.service.test;

import com.test.yourself.model.testsystem.subject.Subject;
import com.test.yourself.model.testsystem.test.SubjectTest;

import java.util.List;

public interface TestService {
    SubjectTest addTest(SubjectTest subjectTest);
    SubjectTest findTestById(Long id);
    SubjectTest findTestByName(String name);
    void deleteTest(SubjectTest subjectTest);
    void deleteTestById(Long id);
    SubjectTest updateTest(Long id, SubjectTest subjectTest);
    List<SubjectTest> findAll();
    List<SubjectTest> findAllBySubject(Subject subject);
    List<SubjectTest> findAllBySubjectId(Long subjectId);
    void removeAll();
    void removeAllBySubject(Subject subject);
    void removeAllBySubjectId(Long subjectId);
    SubjectTest getRandomTest(Long subjectId, int size);
    SubjectTest getTestByQuestions(List<Long> questionIdList);
}
