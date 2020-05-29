package com.test.yourself.service.test;


import com.test.yourself.exception.TestNotFoundException;
import com.test.yourself.model.testsystem.subject.Question;
import com.test.yourself.model.testsystem.subject.Subject;
import com.test.yourself.model.testsystem.test.SubjectTest;
import com.test.yourself.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
        return this.testRepository.saveAndFlush(subjectTest);
    }

    @Override
    public SubjectTest findById(Long id) {
        return this.testRepository.findById(id).orElseThrow(TestNotFoundException::new);
    }

    @Override
    public SubjectTest findByName(String name) {
        SubjectTest subjectTest = this.testRepository.findByName(name);
        if (subjectTest == null) {
            throw new TestNotFoundException("SubjectTest with name \"" + name + "\" not found\"");
        }
        return subjectTest;
    }

    @Override
    public Boolean delete(SubjectTest subjectTest) {
        this.testRepository.delete(subjectTest);
        return true;
    }


    @Override
    public Boolean deleteById(Long id) {
        this.testRepository.deleteById(id);
        return true;
    }

    @Override
    public SubjectTest update(Long id, SubjectTest subjectTest) {
        SubjectTest subjectTestFromDb = findById(id);
        subjectTest.setId(subjectTestFromDb.getId());
        return this.testRepository.saveAndFlush(subjectTest);
    }

    @Override
    public List<SubjectTest> findAll() {
        return this.testRepository.findAll();
    }

    @Override
    public List<SubjectTest> findAllBySubject(Subject subject) {
        return this.testRepository.findAll().stream()
                .filter(test -> subject.equals(test.getSubject()))
                .collect(Collectors.toList());
    }

    @Override
    public List<SubjectTest> findAllBySubjectId(Long subjectId) {
        return this.testRepository.findAllBySubjectId(subjectId);
    }

    @Override
    public SubjectTest createRandomTest(List<Question> questions, int amount) {
        return null;
    }

    @Override
    public void deleteAll() {
        this.testRepository.deleteAll();
    }


}
