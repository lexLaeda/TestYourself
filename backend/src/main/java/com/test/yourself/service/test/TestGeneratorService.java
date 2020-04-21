package com.test.yourself.service.test;

import com.test.yourself.model.testsystem.subject.Question;
import com.test.yourself.model.testsystem.subject.Subject;
import com.test.yourself.model.testsystem.test.Test;

import java.util.List;

public interface TestGeneratorService {
    Test generateRandomTestBySubject(Subject subject, int testSize);
    Test generateTestByQuestions(List<Question> questions);
}
