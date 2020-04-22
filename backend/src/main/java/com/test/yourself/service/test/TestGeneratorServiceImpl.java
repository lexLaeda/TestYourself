package com.test.yourself.service.test;

import com.test.yourself.model.enums.TestMode;
import com.test.yourself.model.testsystem.subject.Question;
import com.test.yourself.model.testsystem.subject.Subject;
import com.test.yourself.model.testsystem.test.SubjectTest;
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
    public SubjectTest generateRandomTestBySubject(Subject subject, int testSize) {
        List<Question> testQuestions = questionService.getRandomQuestionsBySubject(subject,testSize);
        System.out.println(testQuestions.size());
        return generateTestByQuestions(testQuestions);
    }

    @Override
    public SubjectTest generateTestByQuestions(List<Question> questionPull) {
        SubjectTest subjectTest = new SubjectTest();
        if (isSameSubject(questionPull)){
            Subject subject = questionPull.get(0).getSubject();
            subjectTest.setSubject(subject);
        }
        subjectTest.setCreated(LocalDateTime.now());
        subjectTest.setName("Тест на знание " + subjectTest.getSubject().getName());
        subjectTest.setQuestions(questionPull);
        subjectTest.setTestMode(TestMode.RANDOM);
        return subjectTest;
    }

    private boolean isSameSubject(List<Question> questionPull){
        Subject fromFirst = questionPull.get(0).getSubject();
        long count = questionPull.stream()
                .filter(question -> fromFirst.equals(question.getSubject()))
                .count();
        return questionPull.size() == count;
    }

}
