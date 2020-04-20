package com.test.yourself.maper;

import com.test.yourself.dto.SubjectDto;
import com.test.yourself.dto.TestDto;
import com.test.yourself.model.subject.Subject;
import com.test.yourself.model.test.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class TestMapper extends AbstractMapper<Test, TestDto> {

    private ModelMapper modelMapper;

    @Autowired
    public TestMapper(ModelMapper modelMapper) {
        super(Test.class, TestDto.class);
        this.modelMapper = modelMapper;
    }
    @PostConstruct
    public void initMapper(){

        modelMapper.createTypeMap(Test.class, TestDto.class)
                .setPostConverter(toDtoConverter());

        modelMapper.createTypeMap(TestDto.class,Test.class)
                .setPostConverter(toEntityConverter());
    }


}
