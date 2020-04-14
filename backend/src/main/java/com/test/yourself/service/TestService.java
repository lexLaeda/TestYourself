package com.test.yourself.service;

import com.test.yourself.model.test.Test;
import com.test.yourself.model.test.TestResult;

public interface TestService {
    Test generateRandomTest(Long subjectId, int amount);
    TestResult getTestResults(Test test);
}
