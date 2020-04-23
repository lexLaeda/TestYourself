package com.test.yourself.model.testsystem.test;

import com.test.yourself.model.testsystem.subject.Answer;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class QuestionResult {

    private String questionName;

    private List<Answer> correctAnswers;

    private List<Answer> userAnswers;

    private boolean isCorrect;
}
