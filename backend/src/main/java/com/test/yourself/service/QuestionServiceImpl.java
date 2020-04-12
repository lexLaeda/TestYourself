package com.test.yourself.service;

import com.test.yourself.dto.QuestionDTO;
import com.test.yourself.maper.QuestionMapper;
import com.test.yourself.model.Question;
import com.test.yourself.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl implements QuestionService {
    private QuestionMapper questionMapper;
    private QuestionRepository questionRepository;

    @Autowired
    public QuestionServiceImpl(QuestionMapper questionMapper, QuestionRepository questionRepository){
        this.questionMapper = questionMapper;
        this.questionRepository = questionRepository;
    }
    @Override
    public QuestionDTO addQuestion(QuestionDTO questionDTO) {
        Question question = questionMapper.fromDTO(questionDTO);
        return questionMapper.toDTO(questionRepository.saveAndFlush(question));
    }
}
