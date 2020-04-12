package com.test.yourself.model;

import lombok.Data;

@Data
public class Answer {
    private Long id;
    private String text;
    private Boolean isCorrect;
}
