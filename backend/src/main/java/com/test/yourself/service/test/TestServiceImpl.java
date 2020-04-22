package com.test.yourself.service.test;


import com.test.yourself.exception.TestNotFoundException;
import com.test.yourself.model.enums.TestMode;
import com.test.yourself.model.testsystem.subject.Question;
import com.test.yourself.model.testsystem.subject.Subject;
import com.test.yourself.model.testsystem.test.SubjectTest;

import com.test.yourself.repository.TestRepository;

import com.test.yourself.service.subject.QuestionService;
import com.test.yourself.service.subject.SubjectService;
import com.test.yourself.util.ReflectionUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public SubjectTest addTest(SubjectTest subjectTest) {
        return testRepository.save(subjectTest);
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
        SubjectTest randomSubjectTest = testGenerator.generateRandomTestBySubject(subject,size);
        return addTest(randomSubjectTest);
    }

    @Override
    public SubjectTest getTestByQuestions(List<Long> questionIdList) {
        List<Question> questionPull = questionIdList.stream()
                .map(id -> questionService.findById(id))
                .collect(Collectors.toList());
        return testGenerator.generateTestByQuestions(questionPull);
    }

    private boolean isSuchTestPresent(SubjectTest subjectTest){

        if (subjectTest.getTestMode().equals(TestMode.RANDOM)){
            return false;
        }

        List<SubjectTest> testsBySubject = findAllBySubject(subjectTest.getSubject());

        long count = testsBySubject.stream()
                .filter(testFromDb -> subjectTest.getName().equals(testFromDb.getName()))
                .count();

        return count != 0;
    }
}
