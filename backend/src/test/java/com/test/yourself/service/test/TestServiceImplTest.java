package com.test.yourself.service.test;

import com.test.yourself.exception.TestAlreadyExistException;
import com.test.yourself.exception.TestNotFoundException;
import com.test.yourself.model.enums.TestMode;
import com.test.yourself.model.testsystem.subject.Question;
import com.test.yourself.model.testsystem.subject.Subject;
import com.test.yourself.model.testsystem.test.SubjectTest;
import com.test.yourself.repository.TestRepository;
import org.apache.catalina.LifecycleState;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
@RunWith(MockitoJUnitRunner.class)
public class TestServiceImplTest {

    private TestService testService;
    @Mock
    private SubjectService subjectService;
    @Mock
    private QuestionService questionService;
    @Mock
    private TestRepository repository;

    private List<SubjectTest> tests;
    private SubjectTest subjectTest;
    private Long testId = 1000L;
    private Long fakeTestId = 10L;
    private Subject subject;
    private String java = "java";
    private List<Question> questions;
    @Before
    public void setUp() throws Exception {
        testService = new TestServiceImpl(repository);
        tests = new ArrayList<>();
        subjectTest = new SubjectTest();
        subject = new Subject();
        subject.setName(java);
        subject.setId(testId);
        Question question = new Question();
        question.setName("First Q");
        question.setSubject(subject);
        Question question2 = new Question();
        question2.setName("Second Q");
        question2.setSubject(subject);
        questions = new ArrayList<>();
        questions.add(question);
        questions.add(question2);
        subjectTest.setName(java);
        subjectTest.setSubject(subject);
        subjectTest.setTestMode(TestMode.RANDOM);

        Mockito.when(repository.saveAndFlush(subjectTest)).thenReturn(subjectTest);
        Mockito.when(repository.findById(testId)).thenReturn(Optional.of(subjectTest));
        Mockito.when(repository.findById(fakeTestId)).thenThrow(new TestNotFoundException());
        Mockito.when(repository.findByName(java)).thenReturn(subjectTest);
        Mockito.when(repository.findAll()).thenReturn(tests);
        Mockito.when(repository.findAllBySubjectId(testId)).thenReturn(tests);
        Mockito.when(subjectService.findById(testId)).thenReturn(subject);
        Mockito.when(questionService.getRandomQuestionsBySubject(subject,2)).thenReturn(questions);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addTest() {
        SubjectTest actualSubjectTest = testService.add(this.subjectTest);
        assertEquals(subjectTest,actualSubjectTest);
    }
    @Test(expected = TestAlreadyExistException.class)
    public void addExceptionTest() {
        SubjectTest actualSubjectTest = testService.add(this.subjectTest);
        SubjectTest fakeTest = new SubjectTest();
        Mockito.when(repository.saveAndFlush(fakeTest)).thenThrow(TestAlreadyExistException.class);
        assertEquals(subjectTest,actualSubjectTest);
    }
    @Test
    public void findTestById() {
        SubjectTest testById = testService.findById(testId);
        assertEquals(subjectTest,testById);
    }
    @Test(expected = TestNotFoundException.class)
    public void findFakeTestById() {
        SubjectTest testById = testService.findById(fakeTestId);
        assertEquals(subjectTest,testById);
    }

    @Test
    public void findTestByName() {
        SubjectTest testByName = testService.findByName(java);
        assertEquals(subjectTest,testByName);
    }

    @Test
    public void updateTest() {
        SubjectTest updatedTest = testService.update(testId, this.subjectTest);
        assertEquals(subjectTest,updatedTest);
    }

    @Test
    public void findAll() {
        List<SubjectTest> all = testService.findAll();
        assertEquals(tests, all);
    }

    @Test
    public void findAllBySubject() {
        List<SubjectTest> allBySubject = testService.findAllBySubject(subject);
        assertEquals(tests,allBySubject);
    }

    @Test
    public void findAllBySubjectId() {
        List<SubjectTest> allBySubject = testService.findAllBySubjectId(testId);
        assertEquals(tests,allBySubject);
    }

}