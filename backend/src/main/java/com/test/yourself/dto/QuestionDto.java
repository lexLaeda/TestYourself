package com.test.yourself.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.test.yourself.model.enums.QuestionMode;
import com.test.yourself.model.testsystem.subject.Answer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDto extends AbstractDto{
    @JsonProperty("subject")
    private Long subjectId;

    private String name;

    private String description;

    private QuestionMode mode;

    private List<Answer> answers;

    private List<Integer> correctAnswers;


}
