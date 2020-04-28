package com.test.yourself.mapper;

import com.test.yourself.dto.UserAnswerDto;
import com.test.yourself.model.testsystem.subject.Answer;
import com.test.yourself.model.testsystem.subject.Question;
import com.test.yourself.model.testsystem.test.UserAnswer;
import com.test.yourself.service.test.QuestionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class UserAnswerMapperTest {

    private UserAnswerMapper userAnswerMapper;

    @Spy
    private ModelMapper modelMapper;

    @Spy
    private ModelMapper abstractMM;

    @Mock
    private QuestionService questionService;

    private Long testId = 1L;
    private UserAnswerDto userAnswerDto;
    private UserAnswer userAnswer;

    private int num_one = 1;
    private int num_two = 2;

    private Answer answerOne = new Answer(1, "one");
    private Answer answerTwo = new Answer(2, "two");

    private List<Integer> ansList = Arrays.asList(num_one, num_two);
    private List<Answer> answers = Arrays.asList(answerOne, answerTwo);

    private Question question;
    private String questionName = "Java";

    @Before
    public void setUp() throws Exception {
        userAnswerMapper = new UserAnswerMapper(modelMapper, questionService);
        userAnswerMapper.setMapper(abstractMM);
        question = new Question();
        question.setId(testId);
        question.setName(questionName);


        userAnswerDto = new UserAnswerDto();
        userAnswerDto.setQuestionId(testId);
        userAnswerDto.setAnswerList(ansList);

        userAnswer = new UserAnswer();
        userAnswer.setQuestion(question);


        Mockito.when(questionService.findById(testId)).thenReturn(question);
        userAnswerMapper.initMapper();
    }

    @Test
    public void testToDtoConverter() {
        UserAnswerDto userAnswerDtoFromMapper = userAnswerMapper.toDto(userAnswer);
        System.out.println();
        ;
        //TODO: разобраться и тут
    }

    @Test
    public void testToEntityConverter() {
        UserAnswer userAnswerFromMapper = userAnswerMapper.toEntity(userAnswerDto);

        System.out.println();
        //TODO: разобраться с тестированием modelMapper
    }
}