package com.test.yourself.service;

import com.test.yourself.model.Question;
import com.test.yourself.model.Subject;
import com.test.yourself.service.subject.QuestionService;
import com.test.yourself.service.subject.SubjectService;
import org.junit.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class QuestionServiceImplTest {

    @Autowired
    private QuestionService questionService;

    private static Subject java;

    private static Subject python;

    @Autowired
    private static SubjectService subjectService;

    private Question question;

    private static final String QNAME = "Must overloaded methods change the argument list?";
    private static final String QANSWER1 = "YES";
    private static final String QANSWER2 = "NO";

    @BeforeClass
    public static void setUpQuestion()throws Exception{
        java = Subject.builder()
                .name("Java")
                .build();
        python = Subject.builder()
                .name("Python")
                .build();
        subjectService.addSubject(java);
        subjectService.addSubject(python);
    }

    @Before
    public void setUp() throws Exception {


    }

    @After
    public void tearDown() throws Exception {

    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception{
        Subject subject = subjectService.findSubjectByName("Java");
        Subject subject1 = subjectService.findSubjectByName("Python");
        subjectService.delete(subject);
        subjectService.delete(subject1);
    }

    @Test
    public void testAddQuestion() {

    }

    @Test
    public void testFindAllBySubject() {
    }

    @Test
    public void testFindAllBySubjectId() {
    }

    @Test
    public void testFindAll() {
    }

    @Test
    public void testFindById() {
    }

    @Test
    public void testUpdate() {
    }
}