package com.test.yourself.model.testsystem.test;

import com.test.yourself.model.testsystem.subject.Question;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
public class TestResults {

    private String testName;

    private Long testId;

    private Integer correctAmount;

    private Integer percent;

    private LocalDateTime start;

    private LocalDateTime end;

    private List<QuestionResult> questionResults;
}
