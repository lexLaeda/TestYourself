package com.test.yourself.service.test;

import com.test.yourself.model.enums.TestMode;
import com.test.yourself.model.testsystem.subject.Question;
import com.test.yourself.model.testsystem.subject.Subject;
import com.test.yourself.model.testsystem.test.Test;
import com.test.yourself.service.subject.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TestGeneratorServiceImpl implements TestGeneratorService {

    private QuestionService questionService;

    @Autowired
    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Test generateRandomTestBySubject(Subject subject, int testSize) {
        List<Question> testQuestions = questionService.getRandomQuestionsBySubject(subject,testSize);
        System.out.println(testQuestions.size());
        return generateTestByQuestions(testQuestions);
    }

    @Override
    public Test generateTestByQuestions(List<Question> questionPull) {
        Test test = new Test();
        if (isSameSubject(questionPull)){
            Subject subject = questionPull.get(0).getSubject();
            test.setSubject(subject);
        }
        test.setCreated(LocalDateTime.now());
        test.setName("Тест на знание " + test.getSubject().getName());
        test.setQuestions(questionPull);
        test.setTestMode(TestMode.RANDOM);
        return test;
    }

    private boolean isSameSubject(List<Question> questionPull){
        Subject fromFirst = questionPull.get(0).getSubject();
        long count = questionPull.stream()
                .filter(question -> fromFirst.equals(question.getSubject()))
                .count();
        return questionPull.size() == count;
    }

}
