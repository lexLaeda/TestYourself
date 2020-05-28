package com.test.yourself.service.test;


import com.test.yourself.exception.TestNotFoundException;
import com.test.yourself.model.testsystem.subject.Subject;
import com.test.yourself.model.testsystem.test.*;

import com.test.yourself.repository.TestRepository;

import com.test.yourself.util.ReflectionUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class TestServiceImpl implements TestService {

    private TestRepository testRepository;


    @Autowired
    public TestServiceImpl(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    @Override
    public SubjectTest add(SubjectTest subjectTest) {
        return testRepository.saveAndFlush(subjectTest);
    }

    @Override
    public SubjectTest findById(Long id) {
        return testRepository.findById(id).orElseThrow(TestNotFoundException::new);
    }

    @Override
    public SubjectTest findByName(String name) {
        SubjectTest subjectTest = testRepository.findByName(name);
        if (subjectTest == null){
            throw new TestNotFoundException("SubjectTest with name \"" + name + "\" not found\"");
        }
        return subjectTest;
    }

    @Override
    public Boolean delete(SubjectTest subjectTest) {
        testRepository.delete(subjectTest);
        return true;
    }


    @Override
    public Boolean deleteById(Long id) {
        testRepository.deleteById(id);
        return true;
    }

    @Override
    public SubjectTest update(Long id, SubjectTest subjectTest) {
        SubjectTest subjectTestFromDb = findById(id);
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
    public void deleteAll() {
        testRepository.deleteAll();
    }





}
