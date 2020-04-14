package com.test.yourself.service;

import com.test.yourself.dto.QuestionDTO;
import com.test.yourself.model.Question;
import com.test.yourself.model.Subject;

import java.util.List;

public interface QuestionService {
    Question addQuestion(Question question);
    List<Question> findAllBySubject(Subject subject);
}
