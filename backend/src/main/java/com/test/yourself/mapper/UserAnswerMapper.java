package com.test.yourself.mapper;

import com.test.yourself.dto.QuestionAnswerDto;
import com.test.yourself.dto.UserAnswerDto;
import com.test.yourself.model.testsystem.subject.Question;
import com.test.yourself.model.testsystem.test.QuestionAnswer;
import com.test.yourself.model.testsystem.test.SubjectTest;
import com.test.yourself.model.testsystem.test.UserAnswer;
import com.test.yourself.service.test.QuestionService;
import com.test.yourself.service.test.TestService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserAnswerMapper extends AbstractMapper<UserAnswer, UserAnswerDto> {

    private ModelMapper modelMapper;
    private QuestionService questionService;
    private QuestionAnswerMapper questionAnswerMapper;
    private TestService testService;

    public UserAnswerMapper(ModelMapper modelMapper, QuestionService questionService,
                            QuestionAnswerMapper questionAnswerMapper, TestService testService) {
        super(UserAnswer.class, UserAnswerDto.class);
        this.modelMapper = modelMapper;
        this.questionService = questionService;
        this.questionAnswerMapper = questionAnswerMapper;
        this.testService = testService;
    }

    @PostConstruct
    @Override
    public void initMapper() {
        modelMapper.createTypeMap(UserAnswer.class, UserAnswerDto.class)
                .setPostConverter(toDtoConverter());
        modelMapper.createTypeMap(UserAnswerDto.class, UserAnswer.class)
                .setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(UserAnswerDto source, UserAnswer destination) {
        List<QuestionAnswerDto> answers = source.getAnswers();
        List<QuestionAnswer> collect = answers.stream()
                .map(questionAnswerDto -> questionAnswerMapper.toEntity(questionAnswerDto))
                .collect(Collectors.toList());
        destination.setQuestionAnswers(collect);
        SubjectTest byId = testService.findById(source.getId());
        destination.setSubjectTest(byId);
    }

    @Override
    protected void mapSpecificFields(UserAnswer source, UserAnswerDto destination) {

    }
}
