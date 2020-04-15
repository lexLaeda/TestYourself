package com.test.yourself.service.subject;

import com.test.yourself.exception.QuestionNotFoundException;
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

    @Override
    public List<Question> findAllBySubjectId(Long subjectId) {

        return questionRepository.findAllBySubjectId(subjectId);
    }

    @Override
    public List<Question> findAll() {
        return questionRepository.findAll();
    }

    @Override
    public Question findById(Long id) {
        return questionRepository.findById(id).orElseThrow(QuestionNotFoundException::new);
    }

    @Override
    public Question update(Question question, Long id) {
        Question questionFromDb = questionRepository.findById(id)
                .orElseThrow(QuestionNotFoundException::new);
        questionFromDb.setAnswers(question.getAnswers());
        questionFromDb.setDescription(question.getDescription());
        questionFromDb.setName(question.getName());
        questionFromDb.setSubject(question.getSubject());
        return questionRepository.saveAndFlush(questionFromDb);
    }


}
