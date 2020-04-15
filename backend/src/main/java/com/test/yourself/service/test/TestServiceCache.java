package com.test.yourself.service.test;

import com.test.yourself.exception.TestNotFoundException;
import com.test.yourself.model.Subject;
import com.test.yourself.model.test.Test;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Qualifier(value = "cache")
public class TestServiceCache implements TestService {

    private List<Test> testList;

    public TestServiceCache() {
        testList = new ArrayList<>();
    }

    @Override
    public Test findTestById(Long id) {
        return testList.stream()
                .filter(test -> id.equals(test.getId()))
                .findFirst()
                .orElseThrow(TestNotFoundException::new);
    }

    @Override
    public Test findTestByName(String name) {
        return testList.stream()
                .filter(test -> name.equals(test.getSubject().getName()))
                .findFirst()
                .orElseThrow(TestNotFoundException::new);
    }

    @Override
    public void deleteTest(Test test) {
        testList.remove(test);
    }

    @Override
    public void deleteTestById(Long id) {
        Test test = findTestById(id);
        deleteTest(test);
    }

    @Override
    public Test updateTest(Long id, Test test) {
        Test testFromCache = findTestById(id);

        return null;
    }

    @Override
    public List<Test> findAll() {
        return testList;
    }

    @Override
    public List<Test> findAllBySubject(Subject subject) {
        return testList.stream()
                .filter(test -> subject.equals(test.getSubject()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Test> findAllBySubjectId(Long subjectId) {
        return testList.stream()
                .filter(test -> subjectId.equals(test.getSubject().getId()))
                .collect(Collectors.toList());
    }

    @Override
    public void removeAll() {
        testList.clear();
    }

    @Override
    public void removeAllBySubject(Subject subject) {
        Long subjectId = subject.getId();
        removeAllBySubjectId(subjectId);
    }

    @Override
    public void removeAllBySubjectId(Long subjectId) {
        List<Test> removeList = testList.stream()
                .filter(test -> subjectId.equals(test.getSubject().getId()))
                .collect(Collectors.toList());
        testList.removeAll(removeList);
    }
}
