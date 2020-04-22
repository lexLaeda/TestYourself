package com.test.yourself.service.test;

import com.test.yourself.model.testsystem.subject.Question;
import com.test.yourself.model.testsystem.subject.Subject;
import com.test.yourself.model.testsystem.test.SubjectTest;

import java.util.List;

public interface TestGeneratorService {
    SubjectTest generateRandomTestBySubject(Subject subject, int testSize);
    SubjectTest generateTestByQuestions(List<Question> questions);
}
