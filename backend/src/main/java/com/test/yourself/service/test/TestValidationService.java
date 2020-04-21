package com.test.yourself.service.test;

import com.test.yourself.model.testsystem.test.TestResults;
import com.test.yourself.model.testsystem.test.UserAnswers;

public interface TestValidationService {

    TestResults getTestResults(UserAnswers answers);

}
