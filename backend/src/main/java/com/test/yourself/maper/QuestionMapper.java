package com.test.yourself.maper;

import com.test.yourself.dto.QuestionDto;
import com.test.yourself.model.testsystem.subject.Question;
import com.test.yourself.model.testsystem.subject.Subject;
import com.test.yourself.service.test.SubjectService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QuestionMapper extends AbstractMapper<Question, QuestionDto> {

    private ModelMapper modelMapper;
    private SubjectService subjectService;

    @Autowired
    public QuestionMapper(ModelMapper modelMapper, SubjectService subjectService){
        super(Question.class, QuestionDto.class);
        this.modelMapper = modelMapper;
        this.subjectService = subjectService;
    }

    @Autowired
    public void initMapper(){
        modelMapper.createTypeMap(Question.class,QuestionDto.class)
                .setPostConverter(toDtoConverter());
        modelMapper.createTypeMap(QuestionDto.class,Question.class)
                .setPostConverter(toEntityConverter());
    }

    @Override
    public void mapSpecificFields(Question source, QuestionDto destination) {
        Subject subject = source.getSubject();
        if (subject!= null){
            Long id = subject.getId();
            destination.setSubjectId(id);
        } else {
            destination.setSubjectId(null);
        }
    }

    @Override
    public void mapSpecificFields(QuestionDto source, Question destination) {
        Long subId = source.getSubjectId();
        Subject subject = subjectService.findSubjectById(subId);
        destination.setSubject(subject);
    }
}
