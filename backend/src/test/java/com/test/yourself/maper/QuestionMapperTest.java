package com.test.yourself.maper;

import com.test.yourself.dto.QuestionDto;
import com.test.yourself.model.subject.Answer;
import com.test.yourself.model.subject.Question;
import com.test.yourself.model.subject.Subject;
import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class QuestionMapperTest {

    @Autowired
    private QuestionMapper questionMapper;

    private Subject java;

    private Long testId = 1L;
    private String subName = "java";

    private Question testQuestion;
    private String name = "Is Java is the best language?";
    private String description = "This Question is for all";
    private String firstAnswer = "Yes";
    private String secondAnswer = "No";
    private List<Integer> correctAnswer = Arrays.asList(1);
    private Answer first;
    private Answer second;
    private List<Answer> answers;

    private QuestionDto testQuestionDto;
    @Before
    public void setUp() throws Exception {
        java = new Subject();
        java.setId(testId);
        java.setName(subName);

        testQuestion = new Question();
        testQuestion.setId(testId);
        testQuestion.setSubject(java);

        testQuestion.setName(name);
        testQuestion.setDescription(description);
        first = new Answer();
        first.setNumber(1);
        first.setTitle(firstAnswer);

        second = new Answer();
        second.setNumber(2);
        second.setTitle(secondAnswer);

        answers = new ArrayList<>();
        answers.add(first);
        answers.add(second);

        testQuestion.setAnswers(answers);
        testQuestion.setCorrectAnswers(correctAnswer);


        testQuestionDto = new QuestionDto();

        testQuestionDto.setSubjectId(testId);
        testQuestionDto.setCorrectAnswers(correctAnswer);
        testQuestionDto.setSubjectId(testId);
        testQuestionDto.setName(name);
        testQuestionDto.setDescription(description);
        testQuestionDto.setAnswers(answers);
        testQuestionDto.setId(testId);

    }

    @Test
    public void testMapToDto() {
        QuestionDto questionDto = questionMapper.toDto(testQuestion);

        assertEquals(testQuestionDto,questionDto);

    }
    @Test
    public void testMapToEntity(){
        Question question = questionMapper.toEntity(testQuestionDto);
        question.setSubject(java);
        System.out.println(question.getSubject());
        assertEquals(testQuestion,question);
    }
}