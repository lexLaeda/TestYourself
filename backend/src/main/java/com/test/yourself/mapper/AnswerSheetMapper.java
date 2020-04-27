package com.test.yourself.mapper;

import com.test.yourself.dto.AnswerSheetDto;
import com.test.yourself.dto.UserAnswerDto;
import com.test.yourself.model.testsystem.test.AnswerSheet;
import com.test.yourself.model.testsystem.test.SubjectTest;
import com.test.yourself.model.testsystem.test.UserAnswer;
import com.test.yourself.service.test.TestService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AnswerSheetMapper extends AbstractMapper<AnswerSheet, AnswerSheetDto> {

    private ModelMapper modelMapper;
    private UserAnswerMapper userAnswerMapper;
    private TestService testService;

    @Autowired
    public AnswerSheetMapper(ModelMapper modelMapper, UserAnswerMapper userAnswerMapper, TestService testService) {
        super(AnswerSheet.class, AnswerSheetDto.class);
        this.modelMapper = modelMapper;
        this.userAnswerMapper = userAnswerMapper;
        this.testService = testService;
    }

    @PostConstruct
    @Override
    public void initMapper() {
        modelMapper.createTypeMap(AnswerSheet.class, AnswerSheetDto.class)
                .setPostConverter(toDtoConverter());
        modelMapper.createTypeMap(AnswerSheetDto.class, AnswerSheet.class)
                .setPostConverter(toEntityConverter());
    }

    @Override
    public void mapSpecificFields(AnswerSheet source, AnswerSheetDto destination) {
        SubjectTest subjectTest = source.getSubjectTest();
        destination.setTestId(subjectTest.getId());
        List<UserAnswer> userAnswers = source.getUserAnswers();
        List<UserAnswerDto> collect = userAnswers.stream()
                .map(userAnswerMapper::toDto)
                .collect(Collectors.toList());
        destination.setQuestionAnswerDtoList(collect);
    }

    @Override
    public void mapSpecificFields(AnswerSheetDto source, AnswerSheet destination) {
        Long testId = source.getTestId();
        SubjectTest byId = testService.findById(testId);
        destination.setSubjectTest(byId);

        List<UserAnswerDto> questionAnswerDtoList = source.getQuestionAnswerDtoList();
        List<UserAnswer> collect = questionAnswerDtoList.stream()
                .map(userAnswerMapper::toEntity)
                .collect(Collectors.toList());
        destination.setUserAnswers(collect);
    }
}
