package com.test.yourself.service.test;

import com.test.yourself.model.testsystem.test.AnswerSheet;
import com.test.yourself.model.testsystem.test.TestResult;

public interface TestVerificationService {
    TestResult checkTest(AnswerSheet answerList);
}
