package com.test.yourself.service.test;

import com.test.yourself.exception.QuestionNotFoundException;
import com.test.yourself.model.testsystem.subject.Question;
import com.test.yourself.model.testsystem.subject.Subject;
import com.test.yourself.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService {

    private QuestionRepository questionRepository;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }


    @Override
    public List<Question> findAllBySubject(Subject subject) {
        return this.questionRepository.findAllBySubject(subject);
    }

    @Override
    public List<Question> findAllBySubjectId(Long subjectId) {
        return null;
    }

    @Override
    public List<Question> getRandomQuestionsBySubject(Subject subject, int size) {
        List<Question> questionPull = getQuestionsByPredicate(question -> subject.equals(question.getSubject()));
        return getRandomQuestions(questionPull, size);
    }

    private List<Question> getRandomQuestions(List<Question> questionPull, int size) {
        int pullSize = questionPull.size();
        if (size >= pullSize) {
            size = pullSize;
        }
        Set<Question> questions = new HashSet<>();
        Random random = new Random();
        while (questions.size() < size) {
            int randomIndex = random.nextInt(size);
            Question randomQuestion = questionPull.get(randomIndex);
            questions.add(randomQuestion);
        }
        return new ArrayList<>(questions);
    }

    private List<Question> getQuestionsByPredicate(Predicate<? super Question> predicate) {
        return findAll().stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }


    @Override
    public Question add(Question entity) {
        return questionRepository.saveAndFlush(entity);
    }

    @Override
    public Question update(Long id, Question entity) {
        Question question = questionRepository.findById(id).orElseThrow(QuestionNotFoundException::new);
        entity.setId(id);
        return questionRepository.save(entity);
    }

    @Override
    public Question findById(Long id) {
        return questionRepository.findById(id).orElseThrow(QuestionNotFoundException::new);

    }

    @Override
    public Question findByName(String name) {
        return questionRepository.findByText(name);
    }

    @Override
    public List<Question> findAll() {
        return questionRepository.findAll();
    }

    @Override
    public Boolean deleteById(Long id) {
        try {
            questionRepository.deleteById(id);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    @Override
    public Boolean delete(Question entity) {
        try {
            questionRepository.delete(entity);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    @Override
    public void deleteAll() {
        questionRepository.deleteAll();
    }
}
