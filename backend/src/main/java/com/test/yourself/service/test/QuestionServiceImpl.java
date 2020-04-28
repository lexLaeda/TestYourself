package com.test.yourself.service.test;

import com.test.yourself.exception.AnswerValidationException;
import com.test.yourself.exception.QuestionNotFoundException;
import com.test.yourself.model.testsystem.subject.Answer;
import com.test.yourself.model.testsystem.subject.Question;
import com.test.yourself.model.testsystem.subject.Subject;
import com.test.yourself.repository.QuestionRepository;
import com.test.yourself.util.ReflectionUpdate;
import com.test.yourself.util.StringUtils;
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
    public Question add(Question question) {
        if (checkAnswers(question.getAnswers())){
            return questionRepository.saveAndFlush(question);
        }
        throw new AnswerValidationException("Answers can`t be the same");
    }

    private boolean checkAnswers(List<Answer> answers) {
        for (Answer answer : answers){
            if (!checkAnswer(answers,answer)){
                return false;
            }
        }
        return true;
    }
    private boolean checkAnswer(List<Answer> answers, Answer currentAnswer){
        String curTitle = currentAnswer.getTitle();
        long amountOfSameAnswers = answers.stream()
                .map(Answer::getTitle)
                .filter(s -> StringUtils.isSameString(curTitle, s))
                .count();
        return amountOfSameAnswers == 1;
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
    public Question deleteById(Long id) {
        Question removedQuestion = findById(id);
        questionRepository.deleteById(id);
        return removedQuestion;
    }

    @Override
    public Question delete(Question entity) {
        questionRepository.delete(entity);
        return entity;
    }

    @Override
    public void deleteAll() {
        questionRepository.deleteAll();
    }

    @Override
    public Question findById(Long id) {
        return questionRepository.findById(id).orElseThrow(QuestionNotFoundException::new);
    }

    @Override
    public Question findByName(String name) {
        return questionRepository.findByName(name);
    }

    @Override
    public Question update(Long id, Question question) {
        Question questionFromDb = findById(id);
        questionFromDb.setName(question.getName());
        questionFromDb.setDescription(question.getDescription());
        questionFromDb.setSubject(question.getSubject());
        questionFromDb.setAnswers(question.getAnswers());
        return questionRepository.saveAndFlush(questionFromDb);
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
