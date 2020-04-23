package com.test.yourself.model.testsystem.test;

import lombok.Data;

import java.util.List;
@Data
public class QuestionAnswer {

    private Long questionId;

    private List<Integer> answers;
}
