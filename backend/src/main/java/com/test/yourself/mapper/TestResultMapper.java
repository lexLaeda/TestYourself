package com.test.yourself.mapper;

import com.test.yourself.dto.AnswerSheetDto;
import com.test.yourself.dto.TestResultDto;
import com.test.yourself.model.testsystem.test.AnswerSheet;
import com.test.yourself.model.testsystem.test.SubjectTest;
import com.test.yourself.model.testsystem.test.TestResult;
import com.test.yourself.service.test.QuestionService;
import com.test.yourself.service.test.TestService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class TestResultMapper extends AbstractMapper<TestResult, TestResultDto>{
    private ModelMapper modelMapper;
    private TestService testService;
    private AnswerSheetMapper answerSheetMapper;
    private QuestionService questionService;
    @Autowired
    TestResultMapper(ModelMapper modelMapper, TestService testService, QuestionService questionService) {
        super(TestResult.class, TestResultDto.class);
        this.modelMapper = modelMapper;
        this.testService = testService;
        this.questionService = questionService;
    }

    @PostConstruct
    @Override
    public void initMapper() {
        modelMapper.createTypeMap(TestResult.class, TestResultDto.class)
                .setPostConverter(toDtoConverter());
        modelMapper.createTypeMap(TestResultDto.class, TestResult.class)
                .setPostConverter(toEntityConverter());
    }

    @Override
    public void mapSpecificFields(TestResult source, TestResultDto destination) {

        AnswerSheet answerSheet = source.getAnswerSheet();
        destination.setAnswerSheetDto(answerSheetMapper.toDto(answerSheet));

        SubjectTest subjectTest = source.getSubjectTest();
        destination.setTestId(subjectTest.getId());
    }

    @Override
    public void mapSpecificFields(TestResultDto source, TestResult destination) {

        Long testId = source.getTestId();
        SubjectTest subjectTest = testService.findById(testId);
        destination.setSubjectTest(subjectTest);

        AnswerSheetDto answerSheetDto = source.getAnswerSheetDto();
        destination.setAnswerSheet(answerSheetMapper.toEntity(answerSheetDto));
    }


}