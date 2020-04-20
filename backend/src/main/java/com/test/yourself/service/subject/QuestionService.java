package com.test.yourself.service.subject;

import com.test.yourself.model.subject.Question;
import com.test.yourself.model.subject.Subject;

import java.util.List;

public interface QuestionService {
    Question addQuestion(Question question);
    List<Question> findAllBySubject(Subject subject);

    List<Question> findAllBySubjectId(Long subjectId);

    List<Question> findAll();

    Question findById(Long id);

    Question update(Question question, Long id);
}
