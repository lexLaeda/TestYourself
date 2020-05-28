package com.test.yourself.service.test;

import com.test.yourself.model.testsystem.subject.Question;
import com.test.yourself.model.testsystem.test.AnswerSheet;
import com.test.yourself.model.testsystem.test.SubjectTest;
import com.test.yourself.model.testsystem.test.TestResult;
import com.test.yourself.model.testsystem.test.UserAnswer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestVerificationServiceImpl implements TestVerificationService {

    @Override
    public TestResult checkTest(AnswerSheet answerSheet) {
        SubjectTest subjectTest = answerSheet.getSubjectTest();
        List<UserAnswer> userAnswers = answerSheet.getUserAnswers();
        int numOfCorrectAnswers = getNumOfCorrectAnswers(userAnswers);
        int correctPersent = getCorrectPerсent(numOfCorrectAnswers, subjectTest.getQuestions().size());
        return checkTest(subjectTest, numOfCorrectAnswers, correctPersent, answerSheet);
    }

    private TestResult checkTest(SubjectTest subjectTest,
                                 int numOfCorrect, int correctPercent, AnswerSheet answerSheet) {
        TestResult testResult = new TestResult();
        testResult.setSubjectTest(subjectTest);
        testResult.setAnswerSheet(answerSheet);
        testResult.setCorrectAmount(numOfCorrect);
        testResult.setPercent(correctPercent);
        testResult.setStart(answerSheet.getTestStarted());
        testResult.setEnd(answerSheet.getTestEnded());
        return testResult;
    }

    private int getNumOfCorrectAnswers(List<UserAnswer> userAnswers) {
        return (int) userAnswers.stream().filter(this::isCorrect).count();
    }

    private int getCorrectPerсent(int numOfCorrect, int all) {
        return 100 * numOfCorrect / all;
    }

    private boolean isCorrect(UserAnswer userAnswer) {
        Question question = userAnswer.getQuestion();
        List<Integer> correctAnswers = question.getCorrectAnswers();
        List<Integer> userAnswers = userAnswer.getAnswerList();
        return correctAnswers.containsAll(userAnswers) && userAnswers.size() == correctAnswers.size();
    }
}
