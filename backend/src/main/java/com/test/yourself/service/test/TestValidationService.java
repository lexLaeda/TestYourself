package com.test.yourself.service.test;

import com.test.yourself.model.result.TestResults;
import com.test.yourself.model.result.UserAnswers;

public interface TestValidationService {

    TestResults getTestResults(UserAnswers answers);

}
