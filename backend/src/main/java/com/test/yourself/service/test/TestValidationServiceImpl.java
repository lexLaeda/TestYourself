package com.test.yourself.service.test;

import com.test.yourself.exception.TestNotFoundException;
import com.test.yourself.model.Question;
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

    private TestService testServiceCache;


    @Autowired
    public TestValidationServiceImpl(TestService testServiceCache){
        this.testServiceCache = testServiceCache;
    }

    @Override
    public TestResults getTestResults(UserAnswers answers) throws TestNotFoundException{

        Long testId = answers.getTestId();
        Test testFromCache = getTestFromCache(testId);

        if (testFromCache != null){
            return getTestResults(testFromCache,answers);
        } else {
            throw new TestNotFoundException(answers.getTestId().toString());
        }
    }

    private TestResults getTestResults(Test test, UserAnswers answers) {
        TestResults testResults = new TestResults();
        testResults.setTestName(test.getName());

        Map<Integer, Question> testQuestions = null;
        Map<Integer, QuestionResult> questionResultMap = new HashMap<>();
        Map<Integer,Map<Integer,String>> userAnswers = answers.getResults();

        int correctAmount = 0;

        for (Integer num : testQuestions.keySet()){
            QuestionResult questionResult = getQuestionResult(testQuestions.get(num), userAnswers.get(num));
            questionResultMap.put(num,questionResult);
            if (questionResult.isCorrect()){
                correctAmount++;
            }
        }

        int percent = correctAmount / testQuestions.size();

        testResults.setCorrectAmount(correctAmount);
        testResults.setPercent(percent);
        testResults.setQuestionResults(questionResultMap);

        return testResults;
    }


    private QuestionResult getQuestionResult(Question question, Map<Integer,String> userAnswers){

        QuestionResult questionResult = new QuestionResult();
        questionResult.setQuestionName(question.getName());

        Map<Integer,String> allAnswers = question.getAnswers();
        Map<Integer, String> correctAnswers = new HashMap<>();

        for (Integer index : question.getCorrectAnswers()){
            correctAnswers.put(index,allAnswers.get(index));
        }

        questionResult.setCorrectAnswers(correctAnswers);
        questionResult.setUserAnswers(userAnswers);

        boolean isCorrect = isCorrect(question.getCorrectAnswers(),userAnswers);
        questionResult.setCorrect(isCorrect);

        return questionResult;
    }

    private boolean isCorrect(List<Integer> correctAnswers, Map<Integer, String> userAnswers){
        long correctAmount = correctAnswers.stream()
                .filter(userAnswers::containsKey)
                .count();
        return correctAmount == correctAnswers.size();
    }

    private Test getTestFromCache(Long testId){
        return testServiceCache.findTestById(testId);
    }
}
