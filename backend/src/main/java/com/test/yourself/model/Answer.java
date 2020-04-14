package com.test.yourself.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Embeddable
public class Answer {
    private String text;
    private Boolean isCorrect;
}
