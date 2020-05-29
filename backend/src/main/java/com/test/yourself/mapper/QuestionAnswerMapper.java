package com.test.yourself.mapper;

import com.test.yourself.dto.QuestionAnswerDto;
import com.test.yourself.model.testsystem.test.QuestionAnswer;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class QuestionAnswerMapper extends AbstractMapper<QuestionAnswer, QuestionAnswerDto>{

    private ModelMapper modelMapper;

    public QuestionAnswerMapper() {
        super(QuestionAnswer.class, QuestionAnswerDto.class);
    }

    @Override
    public void initMapper() {

    }

    @Override
    protected void mapSpecificFields(QuestionAnswer source, QuestionAnswerDto destination) {
        super.mapSpecificFields(source, destination);
    }

    @Override
    protected void mapSpecificFields(QuestionAnswerDto source, QuestionAnswer destination) {
        super.mapSpecificFields(source, destination);
    }


}
