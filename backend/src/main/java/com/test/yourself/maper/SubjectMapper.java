package com.test.yourself.maper;

import com.test.yourself.dto.SubjectDto;
import com.test.yourself.model.subject.Question;
import com.test.yourself.model.subject.Subject;
import com.test.yourself.service.subject.QuestionService;
import com.test.yourself.service.subject.SubjectService;
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
    public void initMapper(){

        modelMapper.createTypeMap(Subject.class,SubjectDto.class)
            .setPostConverter(toDtoConverter());

        modelMapper.createTypeMap(SubjectDto.class,Subject.class)
            .setPostConverter(toEntityConverter());
    }

    @Override
    public void mapSpecificFields(Subject source, SubjectDto destination) {
        List<Question> questions = source.getQuestions();
        destination.setAmount(questions.size());
    }

    @Override
    public void mapSpecificFields(SubjectDto source, Subject destination) {
        Subject subject = subjectService.findSubjectById(source.getId());
        List<Question> questions = questionService.findAllBySubjectId(subject.getId());
        destination.setQuestions(questions);
    }
}