package com.test.yourself.service.subject;

import com.test.yourself.exception.QuestionNotFoundException;
import com.test.yourself.exception.SubjectNotFoundException;
import com.test.yourself.model.testsystem.subject.Question;
import com.test.yourself.model.testsystem.subject.Subject;
import com.test.yourself.repository.QuestionRepository;
import com.test.yourself.service.test.QuestionService;
import com.test.yourself.service.test.QuestionServiceImpl;
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
public class QuestionServiceImplTest {

   @Mock
   private QuestionRepository repository;

   private QuestionService service;

   private Question inDb;

   private Question newQuestion;

   private List<Question> questionList;

   private Long testId = 1000L;

   private Long fakeTestId = 1L;

   private Subject java;

    @Before
    public void setUp() throws Exception {
        inDb = new Question();
        inDb.setSubject(java);
        inDb.setName("inDb");

        java = new Subject();
        java.setName("java");

        newQuestion = new Question();
        newQuestion.setName("newQuestion");
        newQuestion.setId(testId);

        questionList = new ArrayList<>();
        questionList.add(inDb);

        service = new QuestionServiceImpl(repository);

        Mockito.when(repository.saveAndFlush(newQuestion)).thenReturn(newQuestion);
        Mockito.when(repository.findAllBySubjectId(testId)).thenReturn(questionList);
        Mockito.when(repository.findAll()).thenReturn(questionList);
        Mockito.when(repository.findById(testId)).thenReturn(Optional.of(inDb));
        Mockito.when(repository.findById(fakeTestId)).thenThrow(new QuestionNotFoundException());
        Mockito.when(repository.findAllBySubjectId(fakeTestId)).thenThrow(new SubjectNotFoundException());
    }

    @Test
    public void testAddQuestion() {
        Question question = service.add(newQuestion);
        assertEquals(newQuestion, question);
    }

    @Test
    public void testFindAllBySubject() {
        assertEquals(questionList,service.findAll());
    }

    @Test
    public void testFindAllBySubjectId() {
        assertEquals(questionList,service.findAllBySubjectId(testId));
    }

    @Test(expected = SubjectNotFoundException.class)
    public void testExceptionFindAllBySubjectId() {
        assertEquals(questionList,service.findAllBySubjectId(fakeTestId));
    }

    @Test
    public void testFindAll() {
        assertEquals(questionList,service.findAll());
    }

    @Test
    public void testFindById() {
        assertEquals(inDb,service.findById(testId));
    }

    @Test(expected = QuestionNotFoundException.class)
    public void testExceptionFindById() {
        assertEquals(inDb,service.findById(fakeTestId));
    }

    @Test
    public void TestUpdate() {
        assertEquals(newQuestion,service.update(testId, newQuestion));
    }

    @Test
    public void testGetRandomQuestionsBySubject() {
        newQuestion.setSubject(java);
        inDb.setSubject(java);
        questionList.add(newQuestion);
        List<Question> randomQuestionsBySubject = service.getRandomQuestionsBySubject(java, 2);
        assertTrue(randomQuestionsBySubject.contains(inDb));
        assertTrue(randomQuestionsBySubject.contains(newQuestion));
    }
}