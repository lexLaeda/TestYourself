package com.test.yourself.service.test;

import com.test.yourself.exception.TestResultsNotFoundException;
import com.test.yourself.model.testsystem.test.TestResult;
import com.test.yourself.repository.TestResultsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TestResultServiceImpl implements TestResultService {

    public TestResultServiceImpl(TestResultsRepository repository) {
        this.repository = repository;
    }

    private TestResultsRepository repository;


    @Override
    public TestResult add(TestResult entity) {
        return repository.saveAndFlush(entity);
    }

    @Override
    public TestResult update(Long id, TestResult entity) {
        return null;
    }

    @Override
    public TestResult findById(Long id) {
        return repository.findById(id)
                .orElseThrow(TestResultsNotFoundException::new);
    }

    @Override
    public TestResult findByName(String name) {
        return null;
    }

    @Override
    public List<TestResult> findAll() {
        return repository.findAll();
    }

    @Override
    public TestResult deleteById(Long id) {
        return null;
    }

    @Override
    public TestResult delete(TestResult entity) {
        return null;
    }

    @Override
    public void deleteAll() {

    }
}
