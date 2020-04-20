package com.test.yourself.service.test;

import com.test.yourself.model.subject.Question;
import com.test.yourself.model.subject.Subject;
import com.test.yourself.model.test.Test;

import java.util.List;

public interface TestGeneratorService {
    Test generateRandomTestBySubject(Subject subject, int testSize);
    Test generateTestByQuestions(List<Question> questions);
}
