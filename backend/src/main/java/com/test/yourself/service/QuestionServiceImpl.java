package com.test.yourself.service;

import com.test.yourself.dto.QuestionDTO;
import com.test.yourself.maper.QuestionMapper;
import com.test.yourself.model.Question;
import com.test.yourself.model.Subject;
import com.test.yourself.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService {

    private QuestionRepository questionRepository;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository) {

        this.questionRepository = questionRepository;
    }

    @Override
    public Question addQuestion(Question question) {
        return questionRepository.saveAndFlush(question);
    }

    @Override
    public List<Question> findAllBySubject(Subject subject) {
        List<Question> questions = questionRepository.findAll();
        return questions.stream()
                .filter(question -> subject.equals(question.getSubject()))
                .collect(Collectors.toList());
    }


}
