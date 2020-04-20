package com.test.yourself.service.test;

import com.test.yourself.exception.TestNotFoundException;
import com.test.yourself.model.subject.Question;
import com.test.yourself.model.result.QuestionResult;
import com.test.yourself.model.result.TestResults;
import com.test.yourself.model.result.UserAnswers;
import com.test.yourself.model.test.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TestValidationServiceImpl implements TestValidationService {


    @Override
    public TestResults getTestResults(UserAnswers answers) {
        return null;
    }


}
