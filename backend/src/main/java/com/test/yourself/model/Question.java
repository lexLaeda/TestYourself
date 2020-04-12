package com.test.yourself.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
public class Question {
    private Long id;
    private String subject;
    private String question;
    private List<String> answers;
    private List<String> correctAnswers;
}
