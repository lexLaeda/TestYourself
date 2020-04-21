package com.test.yourself.service.subject;

import com.test.yourself.model.testsystem.subject.Question;
import com.test.yourself.model.testsystem.subject.Subject;

import java.util.List;

public interface QuestionService {
    Question addQuestion(Question question);
    List<Question> findAllBySubject(Subject subject);

    List<Question> findAllBySubjectId(Long subjectId);

    List<Question> findAll();

    Question findById(Long id);

    Question update(Question question, Long id);

    List<Question> getRandomQuestionsBySubject(Subject subject, int size);
}
