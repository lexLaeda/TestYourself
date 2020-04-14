package com.test.yourself.service;

import com.test.yourself.model.Question;
import com.test.yourself.model.Subject;
import com.test.yourself.model.test.Test;

import com.test.yourself.model.test.TestResult;
import com.test.yourself.util.CacheImpl;
import com.test.yourself.util.RandomCollectionGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class TestServiceImpl implements TestService {

    private SubjectService subjectService;

    private RandomCollectionGenerator randomCollectionGenerator;

    private CacheImpl<Test> testCache;

    @Autowired
    public TestServiceImpl(SubjectService subjectService, RandomCollectionGenerator randomCollectionGenerator
            ,CacheImpl<Test> testCache){

        this.subjectService = subjectService;
        this.randomCollectionGenerator = randomCollectionGenerator;
        this.testCache = testCache;
    }

    @Override
    public Test generateRandomTest(Long subjectId, int amount) {
        Subject subject = subjectService.findSubjectById(subjectId);
        List<Question> subjectQuestions = new ArrayList<>(subject.getQuestions());
        Set<Question> testQuestions = generateRandomQuestions(subjectQuestions,amount);
        return Test.builder()
                .subject(subject)
                .questionSet(testQuestions)
                .build();
    }

    @Override
    public TestResult getTestResults(Test fromRequest) {

        return null;
    }

    private Set<Question> generateRandomQuestions(List<Question> questions, int amount){
        if (questions.size()>= amount){
            return randomCollectionGenerator.getRandomUniqueSet(questions,amount);
        } else {
            return randomCollectionGenerator.getRandomUniqueSet(questions,questions.size());
        }
    }


}
