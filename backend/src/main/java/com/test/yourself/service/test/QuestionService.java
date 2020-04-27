package com.test.yourself.service.test;

import com.test.yourself.model.testsystem.subject.Question;
import com.test.yourself.model.testsystem.subject.Subject;

import java.util.List;

public interface QuestionService extends DataService<Question>{

    List<Question> findAllBySubject(Subject subject);

    List<Question> findAllBySubjectId(Long subjectId);

    List<Question> getRandomQuestionsBySubject(Subject subject, int size);
}
