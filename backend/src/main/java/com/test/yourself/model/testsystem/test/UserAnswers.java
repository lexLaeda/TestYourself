package com.test.yourself.model.testsystem.test;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
public class UserAnswers {

    private Long testId;

    private List<QuestionAnswer> answers;

    private LocalDateTime start;

    private LocalDateTime end;
}
