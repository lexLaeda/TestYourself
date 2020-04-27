package com.test.yourself.dto;

import lombok.Data;

import java.util.List;
@Data
public class UserAnswerDto extends AbstractDto{
    private Long questionId;
    private List<Integer> answerList;
}
