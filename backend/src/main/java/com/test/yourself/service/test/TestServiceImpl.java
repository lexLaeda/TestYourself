package com.test.yourself.service.test;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TestGenerator;
import com.test.yourself.exception.TestNotFoundException;
import com.test.yourself.model.subject.Question;
import com.test.yourself.model.subject.Subject;
import com.test.yourself.model.test.Test;

import com.test.yourself.repository.TestRepository;

import com.test.yourself.service.subject.QuestionService;
import com.test.yourself.service.subject.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
@Qualifier(value = "database")
public class TestServiceImpl implements TestService {

    private TestRepository testRepository;
    private TestGeneratorService testGenerator;
    private SubjectService subjectService;
    private QuestionService questionService;

    @Autowired
    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Autowired
    public void setSubjectService(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @Autowired
    public void setTestGenerator(TestGeneratorService testGenerator) {
        this.testGenerator = testGenerator;
    }

    @Autowired
    public void setTestRepository(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    @Override
    public Test findTestById(Long id) {
        return testRepository.findById(id).orElseThrow(TestNotFoundException::new);
    }

    @Override
    public Test findTestByName(String name) {
        return testRepository.findByName(name);
    }

    @Override
    public void deleteTest(Test test) {
        testRepository.delete(test);
    }

    @Override
    public void deleteTestById(Long id) {
        testRepository.deleteById(id);
    }

    @Override
    public Test updateTest(Long id, Test test) {
        return null;
    }

    @Override
    public List<Test> findAll() {
        return testRepository.findAll();
    }

    @Override
    public List<Test> findAllBySubject(Subject subject) {
        return testRepository.findAll().stream()
                .filter(test -> subject.equals(test.getSubject()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Test> findAllBySubjectId(Long subjectId) {
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
    public Test getRandomTest(Long subjectId, int size) {
        Subject subject = subjectService.findSubjectById(subjectId);
        return testGenerator.generateRandomTestBySubject(subject,size);
    }

    @Override
    public Test getTestByQuestions(List<Long> questionIdList) {
        List<Question> questionPull = questionIdList.stream()
                .map(id -> questionService.findById(id))
                .collect(Collectors.toList());
        return testGenerator.generateTestByQuestions(questionPull);
    }

}
