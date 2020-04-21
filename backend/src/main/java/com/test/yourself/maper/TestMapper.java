package com.test.yourself.maper;

import com.test.yourself.dto.QuestionDto;
import com.test.yourself.dto.SubjectDto;
import com.test.yourself.dto.TestDto;
import com.test.yourself.model.subject.Question;
import com.test.yourself.model.subject.Subject;
import com.test.yourself.model.test.Test;
import com.test.yourself.service.subject.SubjectService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TestMapper extends AbstractMapper<Test, TestDto> {

    private ModelMapper modelMapper;

    private SubjectService subjectService;

    private QuestionMapper questionMapper;
    @Autowired
    public TestMapper(ModelMapper modelMapper, SubjectService subjectService, QuestionMapper questionMapper) {
        super(Test.class, TestDto.class);
        this.modelMapper = modelMapper;
        this.subjectService = subjectService;
        this.questionMapper = questionMapper;
    }
    @PostConstruct
    public void initMapper(){

        modelMapper.createTypeMap(Test.class, TestDto.class)
                .setPostConverter(toDtoConverter());

        modelMapper.createTypeMap(TestDto.class,Test.class)
                .setPostConverter(toEntityConverter());
    }

    @Override
    public void mapSpecificFields(Test source, TestDto destination) {
        System.out.println(source);
        Long subId = source.getSubject().getId();
        destination.setSubjectId(subId);
        List<Question> questions = source.getQuestions();
        List<QuestionDto> questionDtos = questions.stream()
                .map(question -> questionMapper.toDto(question))
                .collect(Collectors.toList());
        destination.setQuestions(questionDtos);
    }

    @Override
    public void mapSpecificFields(TestDto source, Test destination) {
        Long subId = source.getSubjectId();
        Subject subject = subjectService.findSubjectById(subId);
        destination.setSubject(subject);
        List<QuestionDto> questions = source.getQuestions();
        List<Question> questionList = questions.stream()
                .map(questionDto -> questionMapper.toEntity(questionDto))
                .collect(Collectors.toList());
        destination.setQuestions(questionList);
    }
}
