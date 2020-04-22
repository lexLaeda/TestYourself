package com.test.yourself.util;

import com.test.yourself.model.enums.QuestionMode;
import com.test.yourself.model.testsystem.subject.Answer;
import com.test.yourself.model.testsystem.subject.Question;
import com.test.yourself.model.testsystem.subject.Subject;
import com.test.yourself.model.testsystem.test.SubjectTest;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ReflectionUpdateSubjectTest {

    private Subject oldJavaSub;
    private Subject updateJavaSub;
    Question oldJavaQ;
    Question updateJavaQ;

    @Before
    public void setUp() throws Exception {
        oldJavaSub = new Subject();
        oldJavaSub.setId(1L);
        oldJavaSub.setName("oldJava");
        oldJavaSub.setDescription("oldDescription");
        List<SubjectTest> oldTests = new ArrayList<>();
        oldJavaSub.setSubjectTests(oldTests);

        updateJavaSub = new Subject();
        updateJavaSub.setId(1L);
        updateJavaSub.setName("updateJava");
        updateJavaSub.setDescription("updateDescription");
        List<SubjectTest> updateTests = new ArrayList<>();
        updateJavaSub.setSubjectTests(updateTests);

        oldJavaQ = new Question();
        updateJavaSub.setId(1L);
        oldJavaQ.setSubject(oldJavaSub);
        oldJavaQ.setName("old name");
        oldJavaQ.setMode(QuestionMode.SINGLE);
        oldJavaQ.setAnswers(new ArrayList<>());
        oldJavaQ.setDescription("oldDescription");
        oldJavaQ.setCorrectAnswers(new ArrayList<>());

        updateJavaQ = new Question();
        updateJavaSub.setId(1L);
        updateJavaQ.setSubject(updateJavaSub);
        updateJavaQ.setName("update name");
        updateJavaQ.setMode(QuestionMode.MULTI);
        updateJavaQ.setAnswers(new ArrayList<>());
        updateJavaQ.setDescription("updatedDescription");
        updateJavaQ.setCorrectAnswers(new ArrayList<>());

        List<Question> oldQuestions = new ArrayList<>();
        oldQuestions.add(oldJavaQ);
        oldJavaSub.setQuestions(oldQuestions);

        List<Question> updateQuestions = new ArrayList<>();
        oldQuestions.add(updateJavaQ);
        updateJavaSub.setQuestions(oldQuestions);
    }

    @Test
    public void testUpdateQuestion() {

        assertNotEquals(oldJavaQ,updateJavaQ);

        Question result = ReflectionUpdate.updateObject(updateJavaQ, oldJavaQ);

        assertEquals(updateJavaQ,result);
    }

    @Test
    public void testUpdateSubject() {
        assertNotEquals(oldJavaSub,updateJavaSub);

        Subject result = ReflectionUpdate.updateObject(updateJavaSub, oldJavaSub);

        assertEquals(updateJavaSub,result);
    }
}