package com.test.yourself.mapper;

import com.test.yourself.dto.QuestionDto;
import com.test.yourself.dto.SubjectTestDto;
import com.test.yourself.model.testsystem.subject.Question;
import com.test.yourself.model.testsystem.subject.Subject;
import com.test.yourself.model.testsystem.test.SubjectTest;
import com.test.yourself.service.test.SubjectService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TestMapper extends AbstractMapper<SubjectTest, SubjectTestDto> {

    private ModelMapper modelMapper;

    private SubjectService subjectService;

    private QuestionMapper questionMapper;

    @Autowired
    public TestMapper(ModelMapper modelMapper, SubjectService subjectService, QuestionMapper questionMapper) {
        super(SubjectTest.class, SubjectTestDto.class);
        this.modelMapper = modelMapper;
        this.subjectService = subjectService;
        this.questionMapper = questionMapper;
    }

    @PostConstruct
    @Override
    public void initMapper() {

        modelMapper.createTypeMap(SubjectTest.class, SubjectTestDto.class)
                .setPostConverter(toDtoConverter());

        modelMapper.createTypeMap(SubjectTestDto.class, SubjectTest.class)
                .setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(SubjectTest source, SubjectTestDto destination) {
        Long subId = source.getSubject().getId();
        destination.setSubjectId(subId);
        List<Question> questions = source.getQuestions();
        List<QuestionDto> questionDtos = questions.stream()
                .map(question -> questionMapper.toDto(question))
                .peek(questionDto -> questionDto.getCorrectAnswers().clear())
                .collect(Collectors.toList());
        destination.setQuestions(questionDtos);
    }

    @Override
    protected void mapSpecificFields(SubjectTestDto source, SubjectTest destination) {
        Long subId = source.getSubjectId();
        Subject subject = subjectService.findById(subId);
        destination.setSubject(subject);
        List<QuestionDto> questions = source.getQuestions();
        List<Question> questionList = questions.stream()
                .map(questionDto -> questionMapper.toEntity(questionDto))
                .collect(Collectors.toList());
        destination.setQuestions(questionList);
    }
}
