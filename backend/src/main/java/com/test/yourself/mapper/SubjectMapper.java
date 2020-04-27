package com.test.yourself.mapper;

import com.test.yourself.dto.SubjectDto;
import com.test.yourself.model.testsystem.subject.Question;
import com.test.yourself.model.testsystem.subject.Subject;
import com.test.yourself.service.test.QuestionService;
import com.test.yourself.service.test.SubjectService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class SubjectMapper extends AbstractMapper<Subject, SubjectDto> {

    private ModelMapper modelMapper;

    private QuestionService questionService;

    private SubjectService subjectService;

    @Autowired
    public SubjectMapper(ModelMapper modelMapper, QuestionService questionService, SubjectService subjectService) {
        super(Subject.class, SubjectDto.class);
        this.modelMapper = modelMapper;
        this.questionService = questionService;
        this.subjectService = subjectService;
    }

    @PostConstruct
    @Override
    public void initMapper() {

        modelMapper.createTypeMap(Subject.class, SubjectDto.class)
                .setPostConverter(toDtoConverter());

        modelMapper.createTypeMap(SubjectDto.class, Subject.class)
                .setPostConverter(toEntityConverter());
    }

    @Override
    protected void mapSpecificFields(Subject source, SubjectDto destination) {
        List<Question> questions = source.getQuestions();
        destination.setAmountQuestions(questions.size());
    }

    @Override
    protected void mapSpecificFields(SubjectDto source, Subject destination) {
        if (source.getId() != null) {
            Subject subject = subjectService.findById(source.getId());
            List<Question> questions = questionService.findAllBySubjectId(subject.getId());
            destination.setQuestions(questions);
        }

    }
}