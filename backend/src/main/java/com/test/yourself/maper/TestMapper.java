package com.test.yourself.maper;

import com.test.yourself.dto.SubjectDto;
import com.test.yourself.dto.TestDto;
import com.test.yourself.model.subject.Subject;
import com.test.yourself.model.test.Test;
import com.test.yourself.service.subject.SubjectService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class TestMapper extends AbstractMapper<Test, TestDto> {

    private ModelMapper modelMapper;

    private SubjectService subjectService;

    @Autowired
    public TestMapper(ModelMapper modelMapper, SubjectService subjectService) {
        super(Test.class, TestDto.class);
        this.modelMapper = modelMapper;
        this.subjectService = subjectService;
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
        Long subId = source.getSubject().getId();
        destination.setSubjectId(subId);
    }

    @Override
    public void mapSpecificFields(TestDto source, Test destination) {
        Long subId = source.getSubjectId();
        Subject subject = subjectService.findSubjectById(subId);
        destination.setSubject(subject);
    }
}
