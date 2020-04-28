package com.test.yourself.service.test;

import com.test.yourself.model.testsystem.subject.Answer;
import com.test.yourself.model.testsystem.subject.Question;
import com.test.yourself.model.testsystem.subject.Subject;
import com.test.yourself.model.testsystem.test.AnswerSheet;
import com.test.yourself.model.testsystem.test.SubjectTest;
import com.test.yourself.model.testsystem.test.TestResult;
import com.test.yourself.model.testsystem.test.UserAnswer;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TestVerificationServiceImplTest {

    private TestVerificationService testVerificationService;

    private AnswerSheet answerSheet;
    private SubjectTest subjectTest;
    private TestResult testResult;

    private Subject subject;

    private Question one;
    private Question two;
    private Question three;

    List<Integer> corOne = Arrays.asList(1);
    List<Integer> corTwo = Arrays.asList(2);
    List<Integer> corThree = Arrays.asList(2);
    @Before
    public void setUp() throws Exception {
        testVerificationService = new TestVerificationServiceImpl();
        Answer oneA = new Answer(1,"one");
        Answer twoA = new Answer(2, "two");
        List<Answer> answers = Arrays.asList(oneA,twoA);

        one = new Question();
        one.setCorrectAnswers(corOne);
        one.setAnswers(answers);

        two = new Question();
        two.setCorrectAnswers(corOne);
        two.setAnswers(answers);

        three = new Question();
        three.setCorrectAnswers(corTwo);
        three.setAnswers(answers);

        subjectTest = new SubjectTest();
        List<Question> questions = Arrays.asList(one, two, three);
        List<Integer> correctAnswers = three.getCorrectAnswers();

        subjectTest.setQuestions(questions);

        List<Integer> oneAnswers = Arrays.asList(1);
        List<Integer> twoAnswers = Arrays.asList(2);
        List<Integer> threeAnswers = Arrays.asList(1);

        UserAnswer oneAns = new UserAnswer(one,Arrays.asList(oneA));
        UserAnswer twoAns = new UserAnswer(two,Arrays.asList(oneA));
        UserAnswer threeAns = new UserAnswer(three,Arrays.asList(oneA));
        List<UserAnswer> userAnswers = Arrays.asList(oneAns,twoAns,threeAns);

        answerSheet = new AnswerSheet();
        answerSheet.setSubjectTest(subjectTest);
        answerSheet.setUserAnswers(userAnswers);
    }

    @Test
    public void checkTest() {
        TestResult testResult = testVerificationService.checkTest(answerSheet);

        assertEquals(new Integer(2),testResult.getCorrectAmount());
        assertEquals(new Integer(66),testResult.getPercent());
        assertEquals(answerSheet,testResult.getAnswerSheet());
        assertEquals(subjectTest,testResult.getSubjectTest());
    }
}