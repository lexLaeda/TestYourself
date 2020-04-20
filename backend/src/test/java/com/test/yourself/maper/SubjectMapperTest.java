package com.test.yourself.maper;

import com.test.yourself.dto.SubjectDto;
import com.test.yourself.model.subject.Answer;
import com.test.yourself.model.subject.Question;
import com.test.yourself.model.subject.Subject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SubjectMapperTest {

    @Autowired
    private SubjectMapper subjectMapper;
    private Subject java;

    private Long testId = 1L;
    private String subName = "java";

    private Question question;
    private String name = "Is Java is the best language?";
    private String description = "This Question is for all";


    private SubjectDto subjectDto;

    @Before
    public void setUp() throws Exception {
        java = new Subject();
        java.setId(testId);
        java.setName(subName);
        java.setDescription(description);
        java.setTests(null);
        question = new Question();
        question.setId(testId);
        question.setName(name);
        java.setQuestions(Arrays.asList(question));

        subjectDto = new SubjectDto();
        subjectDto.setName(subName);
        subjectDto.setId(testId);
        subjectDto.setQuestions(Arrays.asList(question));
        subjectDto.setDescription(description);

    }

    @Test
    public void testToDto(){
        SubjectDto subDto = subjectMapper.toDto(java);
        assertNotNull(subDto);
        assertEquals(subjectDto,subDto);
    }

    @Test
    public void testToEntity(){
        Subject subject = subjectMapper.toEntity(subjectDto);
        System.out.println(subject);
        System.out.println(subjectDto);
        assertEquals(subject,java);
    }

}