package com.test.yourself.mapper;

import com.test.yourself.dto.UserAnswerDto;
import com.test.yourself.model.testsystem.subject.Question;
import com.test.yourself.model.testsystem.test.UserAnswer;
import com.test.yourself.service.test.QuestionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class UserAnswerMapperTest {

    @Mock
    private UserAnswerMapper userAnswerMapper;

    @Spy
    private ModelMapper modelMapper;

    @Mock
    private QuestionService questionService;

    private UserAnswer userAnswer;

    private UserAnswerDto userAnswerDto;
    private Question question;
    private Long testId;
    @Before
    public void setUp() throws Exception {
        userAnswer = new UserAnswer();
        userAnswer.
    }

    @Test
    public void testToEntity() {

    }

    @Test
    public void testToDto() {
    }
}