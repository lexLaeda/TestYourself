package com.test.yourself.service.test;


import com.test.yourself.exception.TestAlreadyExistException;
import com.test.yourself.exception.TestNotFoundException;
import com.test.yourself.model.enums.TestMode;
import com.test.yourself.model.testsystem.subject.Question;
import com.test.yourself.model.testsystem.subject.Subject;
import com.test.yourself.model.testsystem.test.Test;

import com.test.yourself.repository.TestRepository;

import com.test.yourself.service.subject.QuestionService;
import com.test.yourself.service.subject.SubjectService;
import com.test.yourself.util.ReflectionUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.util.*;
import java.util.stream.Collectors;


@Service
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
    public Test addTest(Test test) {
        if (!isSuchTestPresent(test)){
            return testRepository.saveAndFlush(test);
        }else {
            throw new TestAlreadyExistException("Test with name \"" + test.getName() + "\" already exist");
        }
    }

    @Override
    public Test findTestById(Long id) {
        return testRepository.findById(id).orElseThrow(TestNotFoundException::new);
    }

    @Override
    public Test findTestByName(String name) {
        Test test = testRepository.findByName(name);
        if (test == null){
            throw new TestNotFoundException("Test with name \"" + name + "\" not found\"");
        }
        return test;
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
        Test testFromDb = findTestById(id);
        Test update = ReflectionUpdate.updateObject(test, testFromDb);
        return testRepository.saveAndFlush(update);
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
        Test randomTest = testGenerator.generateRandomTestBySubject(subject,size);
        return addTest(randomTest);
    }
    @Override
    public Test getTestByQuestions(List<Long> questionIdList) {
        List<Question> questionPull = questionIdList.stream()
                .map(id -> questionService.findById(id))
                .collect(Collectors.toList());
        return testGenerator.generateTestByQuestions(questionPull);
    }

    private boolean isSuchTestPresent(Test test){

        if (test.getTestMode() == TestMode.RANDOM){
            return false;
        }

        List<Test> testsBySubject = findAllBySubject(test.getSubject());

        long count = testsBySubject.stream()
                .filter(testFromDb -> test.getName().equals(testFromDb.getName()))
                .count();

        return count == 0;
    }
}
