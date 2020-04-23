package com.test.yourself.service.test;

import com.test.yourself.model.testsystem.subject.Question;
import com.test.yourself.model.testsystem.subject.Subject;
import com.test.yourself.model.testsystem.test.SubjectTest;
import com.test.yourself.model.testsystem.test.TestResults;
import com.test.yourself.model.testsystem.test.UserAnswers;

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
    SubjectTest generateRandomTestBySubject(Subject subject, int testSize);
    SubjectTest generateTestByQuestions(List<Question> questions);
    TestResults validate(UserAnswers answers);
}
