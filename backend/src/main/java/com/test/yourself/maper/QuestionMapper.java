package com.test.yourself.maper;

import com.test.yourself.dto.QuestionDto;
import com.test.yourself.model.subject.Question;
import com.test.yourself.model.subject.Subject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Objects;
@Component
public class QuestionMapper extends AbstractMapper<Question, QuestionDto> {

    private ModelMapper modelMapper;

    @Autowired
    public QuestionMapper(ModelMapper modelMapper){
        super(Question.class, QuestionDto.class);
        this.modelMapper = modelMapper;
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
}
