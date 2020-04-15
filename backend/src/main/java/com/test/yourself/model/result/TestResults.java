package com.test.yourself.model.result;

import lombok.Data;

import java.util.Map;

@Data
public class TestResults {

    private String testName;

    private int correctAmount;

    private int percent;

    private Map<Integer, QuestionResult> questionResults;
}
