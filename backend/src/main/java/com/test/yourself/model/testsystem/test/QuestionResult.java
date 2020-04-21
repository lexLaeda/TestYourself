package com.test.yourself.model.testsystem.test;

import lombok.Data;

import java.util.Map;

@Data
public class QuestionResult {

    private String questionName;

    private Map<Integer,String> correctAnswers;

    private Map<Integer,String> userAnswers;

    private boolean isCorrect;
}
