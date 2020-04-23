package com.test.yourself.service.test;


import com.test.yourself.exception.QuestionNotFoundException;
import com.test.yourself.exception.TestNotFoundException;
import com.test.yourself.model.enums.TestMode;
import com.test.yourself.model.testsystem.subject.Answer;
import com.test.yourself.model.testsystem.subject.Question;
import com.test.yourself.model.testsystem.subject.Subject;
import com.test.yourself.model.testsystem.test.*;

import com.test.yourself.repository.TestRepository;

import com.test.yourself.util.ReflectionUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class TestServiceImpl implements TestService {

    private TestRepository testRepository;
    private SubjectService subjectService;
    private QuestionService questionService;

    @Autowired
    public TestServiceImpl(TestRepository testRepository,SubjectService subjectService,
                           QuestionService questionService) {
        this.testRepository = testRepository;
        this.subjectService = subjectService;
        this.questionService = questionService;
    }

    @Override
    public SubjectTest addTest(SubjectTest subjectTest) {
        return testRepository.saveAndFlush(subjectTest);
    }

    @Override
    public SubjectTest findTestById(Long id) {
        return testRepository.findById(id).orElseThrow(TestNotFoundException::new);
    }

    @Override
    public SubjectTest findTestByName(String name) {
        SubjectTest subjectTest = testRepository.findByName(name);
        if (subjectTest == null){
            throw new TestNotFoundException("SubjectTest with name \"" + name + "\" not found\"");
        }
        return subjectTest;
    }

    @Override
    public void deleteTest(SubjectTest subjectTest) {
        testRepository.delete(subjectTest);
    }

    @Override
    public void deleteTestById(Long id) {
        testRepository.deleteById(id);
    }

    @Override
    public SubjectTest updateTest(Long id, SubjectTest subjectTest) {
        SubjectTest subjectTestFromDb = findTestById(id);
        SubjectTest update = ReflectionUpdate.updateObject(subjectTest, subjectTestFromDb);
        return testRepository.saveAndFlush(update);
    }

    @Override
    public List<SubjectTest> findAll() {
        return testRepository.findAll();
    }

    @Override
    public List<SubjectTest> findAllBySubject(Subject subject) {
        return testRepository.findAll().stream()
                .filter(test -> subject.equals(test.getSubject()))
                .collect(Collectors.toList());
    }

    @Override
    public List<SubjectTest> findAllBySubjectId(Long subjectId) {
        return testRepository.findAllBySubjectId(subjectId);
    }

    @Override
    public void removeAll() {
        testRepository.deleteAll();
    }

    @Override
    public void removeAllBySubject(Subject subject) {
        testRepository.deleteAllBySubject(subject);
    }

    @Override
    public void removeAllBySubjectId(Long subjectId) {
        testRepository.deleteAllBySubjectId(subjectId);
    }

    @Override
    public SubjectTest getRandomTest(Long subjectId, int size) {
        Subject subject = subjectService.findSubjectById(subjectId);
        SubjectTest randomSubjectTest = generateRandomTestBySubject(subject,size);
        return addTest(randomSubjectTest);
    }

    @Override
    public SubjectTest getTestByQuestions(List<Long> questionIdList) {
        List<Question> questionPull = questionIdList.stream()
                .map(id -> questionService.findById(id))
                .collect(Collectors.toList());
        return generateTestByQuestions(questionPull);
    }
    @Override
    public SubjectTest generateRandomTestBySubject(Subject subject, int testSize) {
        List<Question> testQuestions = questionService.getRandomQuestionsBySubject(subject,testSize);
        return generateTestByQuestions(testQuestions);
    }

    @Override
    public SubjectTest generateTestByQuestions(List<Question> questionPull) {
        SubjectTest subjectTest = new SubjectTest();
        if (isSameSubject(questionPull)){
            Subject subject = questionPull.get(0).getSubject();
            subjectTest.setSubject(subject);
        }
        subjectTest.setCreated(LocalDateTime.now());
        subjectTest.setName("Тест на знание " + subjectTest.getSubject().getName());
        subjectTest.setQuestions(questionPull);
        subjectTest.setTestMode(TestMode.RANDOM);
        return subjectTest;
    }


    @Override
    public TestResults validate(UserAnswers answers) {
        SubjectTest test = findTestById(answers.getTestId());
        if (test == null) {
            throw new TestNotFoundException(answers.getTestId().toString());
        }
        List<Question> questions = test.getQuestions();
        List<QuestionAnswer> answerList = answers.getAnswers();

        TestResults results = getTestResults(questions, answerList);
        results.setTestId(answers.getTestId());
        results.setStart(answers.getStart());
        results.setEnd(answers.getEnd());
        results.setTestName(test.getName());
        return results;
    }
    private boolean isSameSubject(List<Question> questionPull){
        Subject fromFirst = questionPull.get(0).getSubject();
        long count = questionPull.stream()
                .filter(question -> fromFirst.equals(question.getSubject()))
                .count();
        return questionPull.size() == count;
    }

    private TestResults getTestResults(List<Question> questions, List<QuestionAnswer> answerList) {
        TestResults results = new TestResults();
        List<QuestionResult> questionResults = new ArrayList<>();
        int correctNum = 0;

        for (QuestionAnswer answer : answerList) {
            Long questionId = answer.getQuestionId();
            Question question = getQuestionById(questionId, questions);
            QuestionResult questionResult = getResultOfAnswer(answer, question);
            if (questionResult.isCorrect()) {
                correctNum += 1;
            }
            questionResults.add(questionResult);
        }

        int correctPercent = 100 * correctNum / questions.size();
        results.setQuestionResults(questionResults);
        results.setCorrectAmount(correctNum);
        results.setPercent(correctPercent);
        return results;
    }

    private QuestionResult getResultOfAnswer(QuestionAnswer answer, Question question) {

        List<Integer> userAnswers = answer.getAnswers();
        List<Integer> correctAnswers = question.getCorrectAnswers();

        QuestionResult result = new QuestionResult();
        result.setQuestionName(question.getName());
        result.setCorrectAnswers(getAnswerList(correctAnswers, question.getAnswers()));
        result.setUserAnswers(getAnswerList(userAnswers, question.getAnswers()));

        if (userAnswers.size() == correctAnswers.size() && userAnswers.containsAll(correctAnswers)) {
            result.setCorrect(true);
        }
        return result;
    }

    private Question getQuestionById(Long id, List<Question> questions) {
        return questions.stream()
                .filter(question -> question.getId().equals(id))
                .findFirst()
                .orElseThrow(QuestionNotFoundException::new);
    }

    private List<Answer> getAnswerList(List<Integer> nums, List<Answer> answers) {
        return answers.stream()
                .filter(answer -> nums.contains(answer.getNumber()))
                .collect(Collectors.toList());
    }
}
