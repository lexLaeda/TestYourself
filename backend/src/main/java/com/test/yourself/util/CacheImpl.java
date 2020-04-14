package com.test.yourself.util;

import com.test.yourself.exception.TestNotFoundException;
import com.test.yourself.model.test.Test;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CacheImpl<T extends Test> implements Cache<T>{

    private List<T> tests;
    public CacheImpl(){
        tests = new ArrayList<>();
    }

    @Override
    public void addTestToCache(T test) {
        tests.add(test);
    }

    @Override
    public T getTestById(Long id) {
        return tests.stream()
                .filter(test -> id.equals(test.getId()))
                .findFirst()
                .orElseThrow(TestNotFoundException::new);
    }

    @Override
    public void removeTestById(Long id) {
        Test testFromCache = tests.stream()
                .filter(test-> id.equals(test.getId()))
                .findFirst()
                .orElseThrow(TestNotFoundException::new);
        tests.remove(testFromCache);
    }
}
