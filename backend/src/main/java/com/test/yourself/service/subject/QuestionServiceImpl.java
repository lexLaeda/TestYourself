package com.test.yourself.service.subject;

import com.test.yourself.exception.QuestionNotFoundException;
import com.test.yourself.model.testsystem.subject.Question;
import com.test.yourself.model.testsystem.subject.Subject;
import com.test.yourself.repository.QuestionRepository;
import com.test.yourself.util.ReflectionUpdate;
import org.springframework.beans.factory.annotation.Autowired;
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
        Question updated = ReflectionUpdate.updateObject(question, questionFromDb);
        return questionRepository.saveAndFlush(updated);
    }

    @Override
    public List<Question> getRandomQuestionsBySubject(Subject subject, int size) {
        List<Question> questionPull = getQuestionsByPredicate(question -> subject.equals(question.getSubject()));
        return getRandomQuestions(questionPull,size);
     }

     private List<Question> getRandomQuestions(List<Question> questionPull, int size){
        int pullSize = questionPull.size();
        if (size >= pullSize){
            size = pullSize;
        }
        Set<Question> questions = new HashSet<>();
        Random random = new Random();
        while (questions.size() < size){
            int randomIndex = random.nextInt(size);
            Question randomQuestion = questionPull.get(randomIndex);
            questions.add(randomQuestion);
        }
        return new ArrayList<>(questions);
     }

     private List<Question> getQuestionsByPredicate(Predicate<? super Question> predicate){
        return findAll().stream()
                 .filter(predicate)
                 .collect(Collectors.toList());
     }


}
