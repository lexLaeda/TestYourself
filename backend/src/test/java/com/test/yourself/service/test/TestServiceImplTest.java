package com.test.yourself.service.test;

import com.test.yourself.model.subject.Question;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestServiceImplTest {
    @Autowired
    private TestServiceImpl testService;

    @Test
    public void findTestById() {
    }

    @Test
    public void findTestByName() {
    }

    @Test
    public void deleteTest() {
    }

    @Test
    public void deleteTestById() {
    }

    @Test
    public void updateTest() {
    }

    @Test
    public void findAll() {
    }

    @Test
    public void findAllBySubject() {
    }

    @Test
    public void findAllBySubjectId() {
    }

    @Test
    public void removeAll() {
    }

    @Test
    public void removeAllBySubject() {
    }

    @Test
    public void removeAllBySubjectId() {
    }

    @Test
    public void getRandomTest() {
    }

    @Test
    public void getTestByQuestions() {

        com.test.yourself.model.test.Test test = new com.test.yourself.model.test.Test();
        com.test.yourself.model.test.Test randomTest = testService.getRandomTest(6L, 5);
        System.out.println(randomTest);

    }
}