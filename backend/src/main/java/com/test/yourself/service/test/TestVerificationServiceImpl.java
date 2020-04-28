package com.test.yourself.service.test;

import com.test.yourself.model.testsystem.subject.Answer;
import com.test.yourself.model.testsystem.subject.Question;
import com.test.yourself.model.testsystem.test.*;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class TestVerificationServiceImpl implements TestVerificationService {

    @Override
    public TestResult checkTest(AnswerSheet answerSheet) {
        SubjectTest subjectTest = answerSheet.getSubjectTest();
        List<UserAnswer> userAnswers = answerSheet.getUserAnswers();
        int numOfCorrectAnswers = getNumOfCorrectAnswers(userAnswers);
        int correctPersent = getCorrectPerсent(numOfCorrectAnswers, subjectTest.getQuestions().size());
        return checkTest(subjectTest,numOfCorrectAnswers,correctPersent,answerSheet);
    }

    private TestResult checkTest(SubjectTest subjectTest,
                                 int numOfCorrect, int correctPercent,AnswerSheet answerSheet){
        TestResult testResult = new TestResult();
        testResult.setSubjectTest(subjectTest);
        testResult.setAnswerSheet(answerSheet);
        testResult.setCorrectAmount(numOfCorrect);
        testResult.setPercent(correctPercent);
        return testResult;
    }

    private int getNumOfCorrectAnswers(List<UserAnswer> userAnswers){
        return (int) userAnswers.stream().filter(this::isCorrect).count();
    }

    private int getCorrectPerсent(int numOfCorrect, int all){
        return 100 * numOfCorrect / all;
    }

    private boolean isCorrect(UserAnswer userAnswer) {
        Question question = userAnswer.getQuestion();
        System.out.println(question);
        List<Integer> correctAnswers = question.getCorrectAnswers();
        System.out.println(correctAnswers);
        List<Answer> answerSheet = userAnswer.getAnswers();
        System.out.println(answerSheet);
        List<Answer> collect = answerSheet.stream()
                .filter(answer -> correctAnswers.contains(answer.getNumber()))
                .collect(Collectors.toList());
        return correctAnswers.size() == collect.size();
    }

}
