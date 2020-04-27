package com.test.yourself.service.test;

import com.test.yourself.model.enums.TestMode;
import com.test.yourself.model.testsystem.subject.Question;
import com.test.yourself.model.testsystem.subject.Subject;
import com.test.yourself.model.testsystem.test.SubjectTest;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class TestGeneratorServiceImpl implements TestGeneratorService {
    @Override
    public SubjectTest generateTest(Subject subject, int size) {
        SubjectTest subjectTest = new SubjectTest();
        List<Question> questions = subject.getQuestions();
        if (questions.size() < size){
            size = questions.size();
        }
        List<Question> testQuestions = generateRandom(questions,size);
        subjectTest.setSubject(subject);
        subjectTest.setTestMode(TestMode.RANDOM);
        subjectTest.setName(subject.getName());
        subjectTest.setQuestions(testQuestions);
        return subjectTest;
    }

    private List<Question> generateRandom(List<Question> questions, int size) {
        Set<Question> uniqueQuestions = new HashSet<>();

        while (uniqueQuestions.size() < size){
            Question question = getRandomQuestion(questions);
            uniqueQuestions.add(question);
        }
        return new ArrayList<>(uniqueQuestions);
    }
    private Question getRandomQuestion(List<Question> questions){
        Random random = new Random();
        return questions.get(random.nextInt(questions.size()));
    }
}
