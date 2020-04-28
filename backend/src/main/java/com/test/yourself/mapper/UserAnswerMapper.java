package com.test.yourself.mapper;

import com.test.yourself.dto.UserAnswerDto;
import com.test.yourself.model.testsystem.subject.Answer;
import com.test.yourself.model.testsystem.subject.Question;
import com.test.yourself.model.testsystem.test.UserAnswer;
import com.test.yourself.service.test.QuestionService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserAnswerMapper extends AbstractMapper<UserAnswer, UserAnswerDto> {

    private ModelMapper modelMapper;
    private QuestionService questionService;

    public UserAnswerMapper(ModelMapper modelMapper, QuestionService questionService) {
        super(UserAnswer.class, UserAnswerDto.class);
        this.modelMapper = modelMapper;
        this.questionService = questionService;

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

        Long questionId = source.getQuestionId();
        Question byId = questionService.findById(questionId);
        destination.setQuestion(byId);


    }

    @Override
    protected void mapSpecificFields(UserAnswer source, UserAnswerDto destination) {
        Question question = source.getQuestion();
        Long id = question.getId();
        destination.setQuestionId(id);


    }
}
